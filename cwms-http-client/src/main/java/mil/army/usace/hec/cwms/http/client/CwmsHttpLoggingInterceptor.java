package mil.army.usace.hec.cwms.http.client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

final class CwmsHttpLoggingInterceptor implements Interceptor {

    private static final CwmsHttpLogger CWMS_HTTP_LOGGER = new CwmsHttpLogger();
    private static final HttpLoggingInterceptor DELEGATE = new HttpLoggingInterceptor(CWMS_HTTP_LOGGER);
    private static final Logger LOGGER = Logger.getLogger(CwmsHttpLoggingInterceptor.class.getName());

    private CwmsHttpLoggingInterceptor() {
        applyLoggerLevelToDelegate();
    }

    private void applyLoggerLevelToDelegate() {
        if (LOGGER.isLoggable(Level.ALL)) {
            DELEGATE.level(HttpLoggingInterceptor.Level.BODY);
        } else if (LOGGER.isLoggable(Level.FINE)) {
            DELEGATE.level(HttpLoggingInterceptor.Level.BASIC);
        }
    }

    static CwmsHttpLoggingInterceptor getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return DELEGATE.intercept(chain);
    }

    void redactHeader(String header) {
        DELEGATE.redactHeader(header);
    }

    //for unit testing
    void setLogLevel(Level level) {
        LOGGER.setLevel(level);
        applyLoggerLevelToDelegate();
    }

    //for unit testing
    void setLogCollector(StringBuilder collector) {
        CWMS_HTTP_LOGGER.setCollector(collector);
    }

    private static class InstanceHolder {
        public static final CwmsHttpLoggingInterceptor INSTANCE = new CwmsHttpLoggingInterceptor();
    }
}
