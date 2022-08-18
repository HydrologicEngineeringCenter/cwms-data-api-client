package mil.army.usace.hec.cwms.http.client;

import okhttp3.logging.HttpLoggingInterceptor;

final class CwmsHttpLoggingInterceptorLogger implements HttpLoggingInterceptor.Logger {

    @Override
    public void log(String message) {
        HttpLoggingInterceptor.Logger logger = HttpLoggingInterceptor.Logger.DEFAULT;
        if (!message.startsWith("Authorization:")) {
            logger.log(message);
        }
    }
}
