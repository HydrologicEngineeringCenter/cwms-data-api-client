package mil.army.usace.hec.cwms.http.client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

final class CwmsHttpLoggingInterceptor implements Interceptor {

    private static final Logger LOGGER = Logger.getLogger(CwmsHttpLoggingInterceptor.class.getName());
    private static CwmsHttpLoggingInterceptor instance;
    private final CwmsHttpLogger cwmsHttpLogger = new CwmsHttpLogger();
    private final HttpLoggingInterceptor delegate = new HttpLoggingInterceptor(cwmsHttpLogger);

    private CwmsHttpLoggingInterceptor() {
        updateInterceptorLogLevel();
    }

    static CwmsHttpLoggingInterceptor getInstance() {
        if (instance == null) {
            instance = new CwmsHttpLoggingInterceptor();
        }
        return instance;
    }

    private void updateInterceptorLogLevel() {
        if (LOGGER.isLoggable(Level.ALL)) {
            delegate.level(HttpLoggingInterceptor.Level.BODY);
        } else if (LOGGER.isLoggable(Level.FINE)) {
            delegate.level(HttpLoggingInterceptor.Level.BASIC);
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return delegate.intercept(chain);
    }

    void redactHeader(String header) {
        delegate.redactHeader(header);
    }

    //for unit testing
    void setLogLevel(Level level) {
        LOGGER.setLevel(level);
        updateInterceptorLogLevel();
    }

}
