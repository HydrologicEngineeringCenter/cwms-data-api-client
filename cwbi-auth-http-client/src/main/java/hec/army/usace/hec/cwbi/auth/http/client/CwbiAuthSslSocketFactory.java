/*
 * MIT License
 *
 * Copyright (c) 2024 Hydrologic Engineering Center
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
package hec.army.usace.hec.cwbi.auth.http.client;

import hec.army.usace.hec.cwbi.auth.http.client.trustmanagers.CwbiAuthTrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public final class CwbiAuthSslSocketFactory {

    private CwbiAuthSslSocketFactory() {
        throw new AssertionError("Utility class");
    }

    /**
     * Builds SSLSocketFactory configured for CWBI Auth and specified KeyManagers.
     * @param keyManagers - KeyManager list
     * @return SSLSocketFactory
     * @throws IOException - thrown if building SSLSocketFactory failed
     */
    public static SSLSocketFactory buildSSLSocketFactory(List<KeyManager> keyManagers) throws IOException {
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(keyManagers.toArray(new KeyManager[]{}),
                new TrustManager[] {CwbiAuthTrustManager.getTrustManager()}, null);
            return sc.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new IOException(e);
        }
    }
}
