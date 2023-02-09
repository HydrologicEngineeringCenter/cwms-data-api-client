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

package mil.army.usace.hec.cwms.http.client;

import static mil.army.usace.hec.cwms.http.client.TestApiConnectionInfo.getTestSslSocketFactory;
import static mil.army.usace.hec.cwms.http.client.TestApiConnectionInfo.getTestX509TrustManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import org.junit.jupiter.api.Test;

class TestSslSocketData {

    @Test
    void testSslSocketData() {
        SSLSocketFactory testSslSocketFactory = getTestSslSocketFactory();
        X509TrustManager trustManager = getTestX509TrustManager();
        SslSocketData sslSocketData = new SslSocketData(testSslSocketFactory, trustManager);
        assertEquals(testSslSocketFactory, sslSocketData.getSslSocketFactory());
        assertEquals(trustManager, sslSocketData.getX509TrustManager());
    }

    @Test
    void testNulls() {
        assertThrows(NullPointerException.class, () -> new SslSocketData(null, getTestX509TrustManager()));
        assertThrows(NullPointerException.class, () -> new SslSocketData(getTestSslSocketFactory(), null));
    }

}
