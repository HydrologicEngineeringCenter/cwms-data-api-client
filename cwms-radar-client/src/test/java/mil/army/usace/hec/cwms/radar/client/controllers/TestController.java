/*
 * Copyright (c) 2023
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.radar.client.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import java.util.prefs.Preferences;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import mil.army.usace.hec.cwms.aaa.client.CacKeyManagerUtil;
import mil.army.usace.hec.cwms.aaa.client.CwmsAuthCookieCallback;
import mil.army.usace.hec.cwms.aaa.client.CwmsLoginController;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import mil.army.usace.hec.cwms.http.client.AuthCookieCallback;
import mil.army.usace.hec.cwms.http.client.CookieJarFactory;
import mil.army.usace.hec.cwms.http.client.MockHttpServer;
import mil.army.usace.hec.cwms.http.client.PreferencesBackedCookieStore;
import mil.army.usace.hec.cwms.http.client.SslSocketData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

abstract class TestController {

    private static final boolean USE_MOCK = true;
    private static final String TOMCAT_SERVER = System.getProperty("tomcat.test.url", "https://");
    private static PreferencesBackedCookieStore preferencesBackedCookieStore;
    MockHttpServer mockHttpServer;
    static CookieJarFactory.CookieJarSupplier cookieJarSupplier;

    @BeforeAll
    public static void setupAuth() throws IOException {
        cookieJarSupplier = login();
    }

    @BeforeEach
    void setUp() throws IOException {
        mockHttpServer = MockHttpServer.create();
    }

    @AfterEach
    void tearDown() throws IOException {
        mockHttpServer.shutdown();
        if (preferencesBackedCookieStore != null) {
            preferencesBackedCookieStore.writeCookiesToPreferences();
        }
    }

    ApiConnectionInfo buildConnectionInfo() {
        String baseUrl = getRadarBaseUrl();
        return new ApiConnectionInfoBuilder(baseUrl).build();
    }

    static CookieJarFactory.CookieJarSupplier login() throws IOException {
        Preferences node = Preferences.userRoot().node("test").node("cwms").node("http_client");
        preferencesBackedCookieStore = new PreferencesBackedCookieStore(node);
        CookieJarFactory.CookieJarSupplier cookieJarSupplier = CookieJarFactory.preferenceBackedCookieJar(preferencesBackedCookieStore);
        MockHttpServer mockHttpServer = MockHttpServer.create();
        if (cookieJarSupplier.isCookieExpired(getCwmsAAABaseUrl(mockHttpServer), "JSESSIONIDSSO")) {
            mockHttpServer.enqueue(readJsonFile("cwms_aaa/cwms_aaa_banner_agreement.html"));
            mockHttpServer.enqueue(readJsonFile("cwms_aaa/cwms_aaa_login.json"));
            ApiConnectionInfo apiConnectionInfo = buildCwmsAAALoginConnectionInfo(cookieJarSupplier, mockHttpServer);
            new CwmsLoginController().login(apiConnectionInfo);
        }
        mockHttpServer.shutdown();
        return cookieJarSupplier;
    }

    ApiConnectionInfo buildConnectionInfo(CookieJarFactory.CookieJarSupplier cookieJar) throws IOException {
        String baseUrl = getRadarBaseUrl();
        AuthCookieCallback callback = new CwmsAuthCookieCallback(buildCwmsAAALoginConnectionInfo(cookieJar, mockHttpServer), cookieJar);
        return new ApiConnectionInfoBuilder(baseUrl)
            .withCookieJarSupplier(cookieJar)
            .withCookieAuthenticator(callback)
            .build();
    }

    String getRadarBaseUrl() {
        if (USE_MOCK) {
            return String.format("http://localhost:%s", mockHttpServer.getPort());
        } else {
            return TOMCAT_SERVER + "/cwms-data/";
        }
    }

    static String getCwmsAAABaseUrl(MockHttpServer mockHttpServer) {
        if (USE_MOCK) {
            return String.format("http://localhost:%s", mockHttpServer.getPort());
        } else {
            return TOMCAT_SERVER + "/CWMSLogin/";
        }
    }

    static ApiConnectionInfo buildCwmsAAALoginConnectionInfo(CookieJarFactory.CookieJarSupplier cookieJarSupplier, MockHttpServer mockHttpServer)
        throws IOException {
        String baseUrl = getCwmsAAABaseUrl(mockHttpServer);
        SslSocketData sslSocketData = buildSslSocketDataCwmsAAA();
        return new ApiConnectionInfoBuilder(baseUrl)
            .withCookieAuthenticator(() -> {
                mockHttpServer.enqueue(readJsonFile("cwms_aaa/cwms_aaa_banner_agreement.html"));
                mockHttpServer.enqueue(readJsonFile("cwms_aaa/cwms_aaa_login.json"));
                return new CwmsAuthCookieCallback(new ApiConnectionInfoBuilder(baseUrl)
                    .withCookieJarSupplier(cookieJarSupplier)
                    .withSslSocketData(sslSocketData)
                    .build(), cookieJarSupplier)
                    .authenticate();
            })
            .withCookieJarSupplier(cookieJarSupplier)
            .withSslSocketData(sslSocketData)
            .build();
    }

    private static SslSocketData buildSslSocketDataCwmsAAA() throws IOException {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            SSLContext sc = SSLContext.getInstance("TLS");
            KeyManager keyManager;
            if(USE_MOCK) {
                keyManager = getKeyManagerFromJreKeyStore();
            } else {
                keyManager = CacKeyManagerUtil.getKeyManager();
            }
            sc.init(new KeyManager[] {keyManager}, trustManagerFactory.getTrustManagers(), null);
            SSLSocketFactory socketFactory = sc.getSocketFactory();
            return new SslSocketData(socketFactory, (X509TrustManager) trustManagerFactory.getTrustManagers()[0]);
        } catch (Exception ex) {
            throw new IOException(ex);
        }
    }

    static String readJsonFile(String jsonPath) throws IOException {
        URL resource = TestController.class.getClassLoader().getResource(jsonPath);
        if (resource == null) {
            throw new IOException("Resource not found: " + jsonPath);
        }
        Path path = new File(resource.getFile()).toPath();
        return String.join("\n", Files.readAllLines(path));
    }

    private static KeyManager getKeyManagerFromJreKeyStore() throws Exception {
        String defaultType = KeyStore.getDefaultType();
        KeyStore keystore = KeyStore.getInstance(defaultType);
        keystore.load(null, null);
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(keystore, null);
        KeyManager[] kms = kmf.getKeyManagers();
        return kms[0];
    }
}
