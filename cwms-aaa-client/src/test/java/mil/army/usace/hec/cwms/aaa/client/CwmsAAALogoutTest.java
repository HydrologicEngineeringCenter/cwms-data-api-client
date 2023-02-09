/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package mil.army.usace.hec.cwms.aaa.client;

import static mil.army.usace.hec.cwms.aaa.client.CwmsAAALoginTest.TOMCAT_SERVER;
import static mil.army.usace.hec.cwms.aaa.client.CwmsAAALoginTest.getKeyManagerFromJreKeyStore;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.List;
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
        ApiConnectionInfo apiConnectionInfo;
        boolean testMock = true;
        if(testMock) {
            MockHttpServer mockHttpServer = MockHttpServer.create();
            String collect = readFile("cwms_aaa/cwms_aaa_banner_agreement.html");
            List<String> cookie = Arrays.asList("JSESSIONID=53693739C7450D5D5261ED35E2093458", "JSESSIONIDSSO=8AAF8621FD4748C050814BE6D6AFDAFC");
            mockHttpServer.enqueue(collect, cookie);
            collect = readFile("cwms_aaa/cwms_aaa_login.json");
            mockHttpServer.enqueue(collect);
            collect = readFile("cwms_aaa/cwms_aaa_logout.html");
            mockHttpServer.enqueue(collect);
            mockHttpServer.start();
            String baseUrl = String.format("http://localhost:%s", mockHttpServer.getPort());
            KeyManager keyManager = getKeyManagerFromJreKeyStore();
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            sc.init(new KeyManager[] {keyManager}, trustManagers, null);
            SSLSocketFactory socketFactory = sc.getSocketFactory();
            apiConnectionInfo = new ApiConnectionInfoBuilder(baseUrl + "/CWMSLogin/")
                .withCookieJarSupplier(CookieJarFactory.inMemoryCookieJar())
                .withSslSocketData(new SslSocketData(socketFactory, (X509TrustManager) trustManagers[0]))
                .build();
        } else {
            KeyManager keyManager = CacKeyManagerUtil.getKeyManager();
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            sc.init(new KeyManager[] {keyManager}, trustManagers, null);
            SSLSocketFactory socketFactory = sc.getSocketFactory();
            apiConnectionInfo = new ApiConnectionInfoBuilder(TOMCAT_SERVER + "/CWMSLogin/")
                .withCookieJarSupplier(CookieJarFactory.inMemoryCookieJar())
                .withSslSocketData(new SslSocketData(socketFactory, (X509TrustManager) trustManagers[0]))
                .build();
        }
        CwmsLoginController cwmsLoginController = new CwmsLoginController();
        cwmsLoginController.login(apiConnectionInfo);
        assertDoesNotThrow(() -> cwmsLoginController.logout(apiConnectionInfo));
    }
}
