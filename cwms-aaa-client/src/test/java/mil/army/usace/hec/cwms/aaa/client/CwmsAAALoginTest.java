/*
 * Copyright (c) 2022
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.aaa.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import java.util.Arrays;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import mil.army.usace.hec.cwms.htp.client.MockHttpServer;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import mil.army.usace.hec.cwms.http.client.SslSocketData;
import org.junit.jupiter.api.Test;

final class CwmsAAALoginTest {
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
    public void testCwmsAAASessionId() throws Exception {
        ApiConnectionInfo apiConnectionInfo;
        boolean testMock = true;
        if(testMock) {
            MockHttpServer mockHttpServer = MockHttpServer.create();
            String resource = "cwms_aaa/cwms_aaa_banner_agreement.html";
            String collect = readFile(resource);
            String cookie = "JSESSIONID=53693739C7450D5D5261ED35E2093458; domain=localhost; path=/";
            mockHttpServer.enqueue(collect, cookie);
            resource = "cwms_aaa/cwms_aaa_login.json";
            collect = readFile(resource);
            mockHttpServer.enqueue(collect);
            mockHttpServer.start();
            String baseUrl = String.format("http://localhost:%s", mockHttpServer.getPort());
            apiConnectionInfo = new ApiConnectionInfoBuilder(baseUrl + "/CWMSLogin/")
                .build();
        } else {
            String baseUrl = "https://leary:8443";
            HostnameVerifier hostnameVerifier = (s, sslSession) -> true;
            SSLContext sc = SSLContext.getInstance("TLS");
            KeyStore ts = KeyStore.getInstance("JKS");
            ts.load(null, null);
            TrustManager[] trustAllCerts = new TrustManager[] {new TrustAllManager()};
            KeyManager keyManager = CacKeyManagerUtil.getKeyManager();
            sc.init(new KeyManager[] {keyManager}, trustAllCerts, null);
            SSLSocketFactory socketFactory = sc.getSocketFactory();
            apiConnectionInfo = new ApiConnectionInfoBuilder(baseUrl + "/CWMSLogin/")
                .withSslSocketData(new SslSocketData(socketFactory, new TrustAllManager()))
                .withHostnameVerifier(hostnameVerifier)
                .build();
        }
        CwmsAAAAuthToken cwmsAAAAuthToken = new CwmsLoginController().login(apiConnectionInfo);
        assertEquals("Q0HECANK", cwmsAAAAuthToken.username());
        assertEquals(Arrays.asList("All Users", "CWMS Users", "TS ID Creator", "cac_auth"), cwmsAAAAuthToken.roles());
        assertNotNull(cwmsAAAAuthToken.lastLogin());
        assertNotNull(cwmsAAAAuthToken.jSessionId());
        assertFalse(cwmsAAAAuthToken.jSessionId().isEmpty());
    }

    static final class TrustAllManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[] {};
        }
    }
}
