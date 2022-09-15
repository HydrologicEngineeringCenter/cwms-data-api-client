package mil.army.usace.hec.cwms.http.client;

import okhttp3.logging.HttpLoggingInterceptor;

final class CwmsHttpLogger implements HttpLoggingInterceptor.Logger {

    private static final String ACCESS_TOKEN = "access_token";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final HttpLoggingInterceptor.Logger DELEGATE = HttpLoggingInterceptor.Logger.DEFAULT;
    private StringBuilder collector;

    @Override
    public void log(String s) {
        String lowerCaseS = s.toLowerCase();
        if (!lowerCaseS.contains(ACCESS_TOKEN.toLowerCase()) && !lowerCaseS.contains(REFRESH_TOKEN.toLowerCase())) {
            DELEGATE.log(s);
            collector.append(s);
        }
    }

    //for unit testing
    void setCollector(StringBuilder collector) {
        this.collector = collector;
    }
}
