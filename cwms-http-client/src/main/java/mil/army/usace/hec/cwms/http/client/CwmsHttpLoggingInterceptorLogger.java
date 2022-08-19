package mil.army.usace.hec.cwms.http.client;

import okhttp3.logging.HttpLoggingInterceptor;

final class CwmsHttpLoggingInterceptorLogger implements HttpLoggingInterceptor.Logger {

    @Override
    public void log(String message) {
        HttpLoggingInterceptor.Logger logger = HttpLoggingInterceptor.Logger.DEFAULT;
        if (!hideLogMessage(message)) {
            logger.log(message);
        }
    }

    private boolean hideLogMessage(String message) {
        message = message.toLowerCase();
        return message.startsWith("Authorization".toLowerCase())
            || message.startsWith("Proxy-Authorization".toLowerCase())
            || message.startsWith("Cookie".toLowerCase());
    }
}
