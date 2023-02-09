package mil.army.usace.hec.cwms.http.client;

import mil.army.usace.hec.cwms.http.client.auth.OAuth2TokenProvider;
import okhttp3.OkHttpClient;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jsse.provider.BouncyCastleJsseProvider;

import java.security.Security;
import java.util.Optional;

final class OkHttpClientFactory {

    private OkHttpClientFactory() {

    }

    static OkHttpClient buildOkHttpClient(ApiConnectionInfo apiConnectionInfo) {
        Optional<SslSocketData> optionalSslSocketData = apiConnectionInfo.getSslSocketData();
        Optional<OAuth2TokenProvider> optionalTokenProvider = apiConnectionInfo.getOAuth2TokenProvider();
        OkHttpClient retVal = OkHttpClientInstance.getInstance();
        if (optionalSslSocketData.isPresent()) {
            SslSocketData sslSocketData = optionalSslSocketData.get();
            retVal = retVal.newBuilder()
                    .sslSocketFactory(sslSocketData.getSslSocketFactory(), sslSocketData.getX509TrustManager())
                    .build();
        }
        if (optionalTokenProvider.isPresent()) {
            retVal = retVal.newBuilder()
                    .authenticator(new OAuth2TokenAuthenticator(optionalTokenProvider.get()))
                    .addInterceptor(new OAuth2TokenInterceptor(optionalTokenProvider.get()))
                    .build();
        }
        if (!isHttp2NativelySupported()) {
            enableHttp2();
        }
        return retVal;
    }

    private static void enableHttp2() {
        //if Java 8 less than minor version 251, then use BouncyCastle to allow for HTTP/2 requests
        if (!isHttp2NativelySupported()) {
            Security.insertProviderAt(new BouncyCastleProvider(), 1);
            Security.insertProviderAt(new BouncyCastleJsseProvider(), 2);
        }
    }

    private static boolean isHttp2NativelySupported() {
        boolean retVal = false;
        String version = System.getProperty("java.version");
        if (version.startsWith("1.")) {
            version = version.substring(2, 3);
        } else { //if Java 9 or higher
            int dot = version.indexOf(".");
            if (dot != -1) {
                version = version.substring(0, dot);
            }
        }
        int majorVersion = Integer.parseInt(version);
        if (majorVersion == 8) {
            String minorVersionStr = version.substring(version.lastIndexOf("_") + 1);
            retVal = Integer.parseInt(minorVersionStr) >= 251;
        } else if (majorVersion > 8) {
            retVal = true;
        }
        return retVal;
    }
}
