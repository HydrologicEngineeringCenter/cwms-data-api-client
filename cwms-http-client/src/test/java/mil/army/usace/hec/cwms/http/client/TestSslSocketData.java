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
