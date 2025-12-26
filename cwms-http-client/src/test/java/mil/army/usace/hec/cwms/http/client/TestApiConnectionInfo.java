/*
 * MIT License
 *
 * Copyright (c) 2025 Hydrologic Engineering Center
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

package mil.army.usace.hec.cwms.http.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.InetAddress;
import java.net.Socket;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2TokenProvider;
import okhttp3.HttpUrl;
import org.junit.jupiter.api.Test;

class TestApiConnectionInfo {

    @Test
    void testApiConnectionInfo() {
        String root = "http://localhost:11524/cwms-data/";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfoBuilder(root).build();
        assertEquals(root, apiConnectionInfo.getApiRoot());
    }

    @Test
    void testApiConnectionInfoWithTrailingSlash() throws Exception {
        String root = "http://localhost:11524/cwms-data/";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfoBuilder(root).build();
        HttpRequestBuilderImpl httpRequestBuilder = ((HttpRequestBuilderImpl.HttpRequestExecutorImpl) new HttpRequestBuilderImpl(apiConnectionInfo, "catalog")
            .get())
            .getInstance();
        HttpUrl url = httpRequestBuilder.createRequest().url();
        assertEquals("http://localhost:11524/cwms-data/catalog", url.url().toString());
    }

    @Test
    void testApiConnectionInfoFilename() throws Exception {
        String root = "http://localhost:11524/cwms-data.txt";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfoBuilder(root).build();
        HttpRequestBuilderImpl httpRequestBuilder = ((HttpRequestBuilderImpl.HttpRequestExecutorImpl) new HttpRequestBuilderImpl(apiConnectionInfo)
            .get())
            .getInstance();
        HttpUrl url = httpRequestBuilder.createRequest().url();
        assertEquals("http://localhost:11524/cwms-data.txt", url.url().toString());
    }

    @Test
    void testApiConnectionInfoFilenameEndpoint() throws Exception {
        String root = "http://localhost:11524/cwms-data";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfoBuilder(root).build();
        HttpRequestBuilderImpl httpRequestBuilder = ((HttpRequestBuilderImpl.HttpRequestExecutorImpl) new HttpRequestBuilderImpl(apiConnectionInfo, "data.txt")
            .get())
            .getInstance();
        HttpUrl url = httpRequestBuilder.createRequest().url();
        assertEquals("http://localhost:11524/cwms-data/data.txt", url.url().toString());
    }

    @Test
    void testApiConnectionInfoNoTrailingSlash() throws Exception {
        String root = "http://localhost:11524/cwms-data";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfoBuilder(root).build();
        HttpRequestBuilderImpl httpRequestBuilder = ((HttpRequestBuilderImpl.HttpRequestExecutorImpl) new HttpRequestBuilderImpl(apiConnectionInfo, "catalog")
            .get())
            .getInstance();
        HttpUrl url = httpRequestBuilder.createRequest().url();
        assertEquals("http://localhost:11524/cwms-data/catalog", url.url().toString());
    }

    static SSLSocketFactory getTestSslSocketFactory() {
        return new SSLSocketFactory() {
            @Override
            public String[] getDefaultCipherSuites() {
                return new String[0];
            }

            @Override
            public String[] getSupportedCipherSuites() {
                return new String[0];
            }

            @Override
            public Socket createSocket(Socket socket, String s, int i, boolean b) {
                return null;
            }

            @Override
            public Socket createSocket(String s, int i) {
                return null;
            }

            @Override
            public Socket createSocket(String s, int i, InetAddress inetAddress, int i1) {
                return null;
            }

            @Override
            public Socket createSocket(InetAddress inetAddress, int i) {
                return null;
            }

            @Override
            public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress1, int i1) {
                return null;
            }
        };
    }

    static X509TrustManager getTestX509TrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    @Test
    void testApiConnectionInfoWithOAuth2Token() {
        OAuth2TokenProvider tokenProvider = getTestTokenProvider();
        String root = "http://localhost:11524/cwms-data/";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfoBuilder(root)
            .withTokenProvider(tokenProvider)
            .build();
        assertEquals(root, apiConnectionInfo.getApiRoot());
        assertTrue(apiConnectionInfo.authenticator().isPresent());
        assertFalse(apiConnectionInfo.interceptors().isEmpty());
    }

    @Test
    void testApiConnectionInfoWithSslSocketData() {
        SslSocketData sslSocketData = getTestSslSocketData();
        String root = "http://localhost:11524/cwms-data/";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfoBuilder(root)
            .withSslSocketData(sslSocketData)
            .build();
        assertEquals(root, apiConnectionInfo.getApiRoot());
        assertTrue(apiConnectionInfo.sslSocketData().isPresent());
        assertEquals(sslSocketData, apiConnectionInfo.sslSocketData().get());
    }

    @Test
    void testApiConnectionInfoWithOAuth2TokenAndSslSocketData() {
        OAuth2TokenProvider tokenProvider = getTestTokenProvider();
        SslSocketData sslSocketData = getTestSslSocketData();
        String root = "http://localhost:11524/cwms-data/";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfoBuilder(root)
            .withSslSocketData(sslSocketData)
            .withTokenProvider(tokenProvider)
            .build();
        assertEquals(root, apiConnectionInfo.getApiRoot());
        assertTrue(apiConnectionInfo.authenticator().isPresent());
        assertFalse(apiConnectionInfo.interceptors().isEmpty());
        assertTrue(apiConnectionInfo.sslSocketData().isPresent());
        assertEquals(sslSocketData, apiConnectionInfo.sslSocketData().get());
    }

    @Test
    void testApiConnectionInfoNulls() {
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfoBuilder(null).build();
        assertNull(apiConnectionInfo.getApiRoot());
        assertFalse(apiConnectionInfo.sslSocketData().isPresent());
        assertFalse(apiConnectionInfo.authenticator().isPresent());
    }

    private OAuth2TokenProvider getTestTokenProvider() {
        return new OAuth2TokenProvider() {
            OAuth2Token token = new OAuth2Token();
            @Override
            public void clear() {
                token = null;
            }

            @Override
            public OAuth2Token getToken() {
                if(token == null) {
                    token = newToken();
                }
                return token;
            }

            @Override
            public OAuth2Token refreshToken() {
                return getToken();
            }

            @Override
            public OAuth2Token newToken() {
                token = new OAuth2Token();
                token.setAccessToken("MTQ0NjJkZmQ5OTM2NDE1ZTZjNGZmZjI3");
                token.setTokenType("Bearer");
                token.setExpiresIn(3600);
                token.setScope("create");
                return token;
            }

            @Override
            public ApiConnectionInfo getAuthUrl() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getAuthUrl'");
            }

            @Override
            public ApiConnectionInfo getTokenUrl() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getTokenUrl'");
            }
        };
    }

    private SslSocketData getTestSslSocketData() {

        return new SslSocketData(getTestSslSocketFactory(), getTestX509TrustManager());
    }

    @Test
    void testMultipleAuthTypes() {
        ApiConnectionInfoBuilder builder = new ApiConnectionInfoBuilder("")
            .withCookieAuthenticator(() -> null)
            .withAuthorizationKeyProvider(() -> null);
        assertThrows(IllegalArgumentException.class, builder::build);
        ApiConnectionInfoBuilder builder2 = new ApiConnectionInfoBuilder("")
            .withCookieAuthenticator(() -> null)
            .withTokenProvider(buildNulledTokenProvider());
        assertThrows(IllegalArgumentException.class, builder2::build);
        ApiConnectionInfoBuilder builder3 = new ApiConnectionInfoBuilder("")
            .withAuthorizationKeyProvider(() -> null)
            .withTokenProvider(buildNulledTokenProvider());
        assertThrows(IllegalArgumentException.class, builder3::build);
    }

    private static OAuth2TokenProvider buildNulledTokenProvider() {
        return new OAuth2TokenProvider() {
            @Override
            public void clear() {

            }

            @Override
            public OAuth2Token getToken() {
                return null;
            }

            @Override
            public OAuth2Token refreshToken() {
                return null;
            }

            @Override
            public OAuth2Token newToken() {
                return null;
            }

            @Override
            public ApiConnectionInfo getAuthUrl() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getAuthUrl'");
            }

            @Override
            public ApiConnectionInfo getTokenUrl() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getTokenUrl'");
            }
        };
    }

}
