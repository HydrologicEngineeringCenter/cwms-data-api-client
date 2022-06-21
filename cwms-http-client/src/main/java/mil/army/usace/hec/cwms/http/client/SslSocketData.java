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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SslSocketData that = (SslSocketData) o;
        return Objects.equals(getSslSocketFactory(), that.getSslSocketFactory())
            && Objects.equals(getX509TrustManager(), that.getX509TrustManager());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSslSocketFactory(), getX509TrustManager());
    }
}
