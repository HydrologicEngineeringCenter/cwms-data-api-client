package mil.army.usace.hec.cwms.http.client;

import java.util.Objects;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

public final class SslSocketData {

    private final SSLSocketFactory sslSocketFactory;
    private final X509TrustManager x509TrustManager;

    public SslSocketData(SSLSocketFactory sslSocketFactory, X509TrustManager x509TrustManager) {
        this.sslSocketFactory = Objects.requireNonNull(sslSocketFactory, "Missing required SSLSocketFactory");
        this.x509TrustManager = Objects.requireNonNull(x509TrustManager, "Missing required X509TrustManager");
    }

    SSLSocketFactory getSslSocketFactory() {
        return sslSocketFactory;
    }

    X509TrustManager getX509TrustManager() {
        return x509TrustManager;
    }
}
