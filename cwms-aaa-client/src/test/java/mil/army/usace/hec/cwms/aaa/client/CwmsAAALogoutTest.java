/*
 * Copyright (c) 2022
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.aaa.client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import mil.army.usace.hec.cwms.htp.client.MockHttpServer;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import mil.army.usace.hec.cwms.http.client.CookieJarFactory;
import mil.army.usace.hec.cwms.http.client.SslSocketData;
import org.junit.jupiter.api.Test;

final class CwmsAAALogoutTest {
    static {
        System.setProperty("cwms.aaa.trust.hostname", "leary");
        System.setProperty("cwms.aaa.trust.port", "8443");
    }

    String readFile(String jsonPath) throws IOException {
        URL resource = getClass().getClassLoader().getResource(jsonPath);
        if (resource == null) {
            throw new IOException("Resource not found: " + jsonPath);
        }
        Path path = new File(resource.getFile()).toPath();
        return String.join("\n", Files.readAllLines(path));
    }

    @Test
    public void testCwmsAAASessionLogout() throws Exception {
        SSLContext sc = SSLContext.getInstance("TLS");
        KeyStore ts = KeyStore.getInstance("JKS");
        ts.load(null, null);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);
        KeyManager keyManager = CacKeyManagerUtil.getKeyManager();
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        sc.init(new KeyManager[] {keyManager}, trustManagers, null);
        SSLSocketFactory socketFactory = sc.getSocketFactory();
        ApiConnectionInfo apiConnectionInfo;
        boolean testMock = true;
        if(testMock) {
            MockHttpServer mockHttpServer = MockHttpServer.create();
            String collect = readFile("cwms_aaa/cwms_aaa_banner_agreement.html");
            String cookie = "JSESSIONID=53693739C7450D5D5261ED35E2093458";
            mockHttpServer.enqueue(collect, cookie);
            collect = readFile("cwms_aaa/cwms_aaa_login.json");
            mockHttpServer.enqueue(collect);
            collect = readFile("cwms_aaa/cwms_aaa_logout.html");
            mockHttpServer.enqueue(collect);
            mockHttpServer.start();
            String baseUrl = String.format("http://localhost:%s", mockHttpServer.getPort());
            apiConnectionInfo = new ApiConnectionInfoBuilder(baseUrl + "/CWMSLogin/")
                .withCookieJarBuilder(CookieJarFactory.inMemoryCookieJar())
                .withSslSocketData(new SslSocketData(socketFactory, (X509TrustManager) trustManagers[0]))
                .build();
        } else {
            apiConnectionInfo = new ApiConnectionInfoBuilder("https://leary:8443/CWMSLogin/")
                .withSslSocketData(new SslSocketData(socketFactory, (X509TrustManager) trustManagers[0]))
                .build();
        }
        CwmsLoginController cwmsLoginController = new CwmsLoginController();
        cwmsLoginController.login(apiConnectionInfo);
        assertDoesNotThrow(() -> cwmsLoginController.logout(apiConnectionInfo));
    }
}
