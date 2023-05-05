/*
 * MIT License
 *
 * Copyright (c) 2023 Hydrologic Engineering Center
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package mil.army.usace.hec.cwms.http.client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * JUL Interceptor to update the logging functionality of the OkHttp HttpLogginInterceptor based on JUL log levels
 * <p>
 * java.util.logging.Level.FINE - the OkHttp interceptor will log at HttpLoggingInterceptor.Level.BASIC
 * java.util.logging.Level.FINEST - the OkHttp interceptor will log at HttpLoggingInterceptor.Level.BASIC and this class will log stack traces for each request
 * java.util.logging.Level.ALL - the OkHttp interceptor will log at HttpLoggingInterceptor.Level.BODY and this class will log stack traces for each request
 */
final class CwmsHttpLoggingInterceptor implements Interceptor {

    private static final Logger LOGGER = Logger.getLogger(CwmsHttpLoggingInterceptor.class.getName());
    private static final String SET_COOKIE_HEADER = "Set-Cookie";
    private static final String COOKIE_HEADER = "Cookie";
    private static CwmsHttpLoggingInterceptor instance;
    private final CwmsHttpLogger cwmsHttpLogger = new CwmsHttpLogger();
    private final HttpLoggingInterceptor delegate = new HttpLoggingInterceptor(cwmsHttpLogger);

    private CwmsHttpLoggingInterceptor() {
        updateInterceptorLogLevel();
        if (!Boolean.getBoolean("cwms.http.client.log.all.cookies")) {
            redactHeader(SET_COOKIE_HEADER);
            redactHeader(COOKIE_HEADER);
        }
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

    void logStackTraceForRequest(Request request) {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, new Exception(), () -> "CWMS HTTP API executing request: " + request.url());
        }
    }
}
