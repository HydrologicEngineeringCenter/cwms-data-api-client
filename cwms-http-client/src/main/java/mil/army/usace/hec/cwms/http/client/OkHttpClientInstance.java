/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
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

import okhttp3.OkHttpClient;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

final class OkHttpClientInstance {

    private static final Logger LOGGER = Logger.getLogger(OkHttpClientInstance.class.getName());
    static final String CALL_TIMEOUT_PROPERTY_KEY = "cwms.http.client.calltimeout.seconds";
    static final Duration CALL_TIMEOUT_PROPERTY_DEFAULT = Duration.ofSeconds(0);
    static final String CONNECT_TIMEOUT_PROPERTY_KEY = "cwms.http.client.connecttimeout.seconds";
    static final Duration CONNECT_TIMEOUT_PROPERTY_DEFAULT = Duration.ofSeconds(5);
    static final String READ_TIMEOUT_PROPERTY_KEY = "cwms.http.client.readtimeout.seconds";
    static final Duration READ_TIMEOUT_PROPERTY_DEFAULT = Duration.ofMinutes(5);
    static final String WRITE_TIMEOUT_PROPERTY_KEY = "cwms.http.client.writetimeout.seconds";
    static final Duration WRITE_TIMEOUT_PROPERTY_DEFAULT = Duration.ofMinutes(1);
    private static final CwmsHttpLoggingInterceptor LOGGING_INTERCEPTOR = CwmsHttpLoggingInterceptor.getInstance();

    private static final OkHttpClient INSTANCE = createClient();


    private OkHttpClientInstance() {
        throw new AssertionError("Singleton utility class, cannot instantiate");
    }

    // package scoped for testing only
    static OkHttpClient createClient() {
        return new OkHttpClient.Builder()
                .callTimeout(getCallTimeout())
                .connectTimeout(getConnectTimeout())
                .readTimeout(getReadTimeout())
                .writeTimeout(getWriteTimeout())
                .addInterceptor(LOGGING_INTERCEPTOR)
                .cache(getCache())
            .build();
    }

    private static Duration getReadTimeout() {
        return getDurationProperty(READ_TIMEOUT_PROPERTY_KEY, READ_TIMEOUT_PROPERTY_DEFAULT);
    }

    private static Duration getWriteTimeout() {
        return getDurationProperty(WRITE_TIMEOUT_PROPERTY_KEY, WRITE_TIMEOUT_PROPERTY_DEFAULT);
    }

    private static Duration getConnectTimeout() {
        return getDurationProperty(CONNECT_TIMEOUT_PROPERTY_KEY, CONNECT_TIMEOUT_PROPERTY_DEFAULT);
    }

    private static Duration getCallTimeout() {
        return getDurationProperty(CALL_TIMEOUT_PROPERTY_KEY, CALL_TIMEOUT_PROPERTY_DEFAULT);
    }

    private static okhttp3.Cache getCache() {
        return CwmsHttpCache.getInstance();
    }

    private static Duration getDurationProperty(String timeoutPropertyKey, Duration timeoutPropertyDefault) {
        String writeTimeoutPropertyValue = System.getProperty(timeoutPropertyKey);
        Duration writeTimeout = timeoutPropertyDefault;
        if (writeTimeoutPropertyValue == null) {
            LOGGER.log(Level.FINE,
                () -> "Setting " + timeoutPropertyKey + " is not set in system properties. Defaulting to " + timeoutPropertyDefault);
        } else {
            LOGGER.log(Level.FINE,
                () -> "Setting " + timeoutPropertyKey + " read from system properties as " + writeTimeoutPropertyValue);
            writeTimeout = Duration.parse(writeTimeoutPropertyValue);
        }
        return writeTimeout;
    }

    static OkHttpClient getInstance() {
        return INSTANCE;
    }
}
