package mil.army.usace.hec.cwms.http.client;

public final class ApiConnectionInfoFactory {

    private ApiConnectionInfoFactory() {
        throw new AssertionError("Utility class");
    }

    public static ApiConnectionInfo cloneWithNewUrl(ApiConnectionInfo apiConnectionInfo, String url) {
        return new ApiConnectionInfo(url,
                apiConnectionInfo.sslSocketData().orElse(null),
                apiConnectionInfo.cookieJar().orElse(null),
                apiConnectionInfo.interceptors(),
                apiConnectionInfo.authenticator().orElse(null),
                apiConnectionInfo.hostnameVerifier().orElse(null),
                apiConnectionInfo.cacheSupplier().orElse(null));
    }
}
