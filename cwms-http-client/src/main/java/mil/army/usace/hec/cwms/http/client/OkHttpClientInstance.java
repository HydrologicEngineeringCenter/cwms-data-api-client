/*
 * MIT License
 *
 * Copyright (c) 2021 Hydrologic Engineering Center
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

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.OkHttpClient;

/**
 *
 */
final class OkHttpClientInstance {

    private static final Logger LOGGER = Logger.getLogger(OkHttpClientInstance.class.getName());
    private static final String CALL_TIMEOUT_PROPERTY_KEY = "cwms.http.client.calltimeout.seconds";
    private static final Duration CALL_TIMEOUT_PROPERTY_DEFAULT = Duration.ofSeconds(TimeUnit.MINUTES.toSeconds(5));
    private static final String CONNECT_TIMEOUT_PROPERTY_KEY = "cwms.http.client.connecttimeout.seconds";
    private static final Duration CONNECT_TIMEOUT_PROPERTY_DEFAULT = Duration.ofSeconds(TimeUnit.MINUTES.toSeconds(5));
    private static final String READ_TIMEOUT_PROPERTY_KEY = "cwms.http.client.connecttimeout.seconds";
    private static final Duration READ_TIMEOUT_PROPERTY_DEFAULT = Duration.ofSeconds(5);

    private static final OkHttpClient INSTANCE = new OkHttpClient.Builder()
        .callTimeout(getCallTimeout())
        .connectTimeout(getConnectTimeout())
        .readTimeout(getReadTimeout())
        .build();


    private OkHttpClientInstance() {
        throw new AssertionError("Singleton utility class, cannot instantiate");
    }

    private static Duration getReadTimeout() {
        String readTimeoutPropertyValue = System.getProperty(CONNECT_TIMEOUT_PROPERTY_KEY);
        Duration readTimeout = CALL_TIMEOUT_PROPERTY_DEFAULT;
        if (readTimeoutPropertyValue == null) {
            LOGGER.log(Level.FINE,
                () -> "Setting " + READ_TIMEOUT_PROPERTY_KEY + " is not set in system properties. Defaulting to " + READ_TIMEOUT_PROPERTY_DEFAULT);
        }
        else {
            LOGGER.log(Level.FINE,
                () -> "Setting " + READ_TIMEOUT_PROPERTY_KEY + " read from system properties as " + readTimeoutPropertyValue);
            readTimeout = Duration.parse(readTimeoutPropertyValue);
        }
        return readTimeout;
    }

    private static Duration getConnectTimeout() {
        String connectTimeoutPropertyValue = System.getProperty(CONNECT_TIMEOUT_PROPERTY_KEY);
        Duration connectTimeout = CONNECT_TIMEOUT_PROPERTY_DEFAULT;
        if (connectTimeoutPropertyValue == null) {
            LOGGER.log(Level.FINE,
                () -> "Setting " + CONNECT_TIMEOUT_PROPERTY_KEY + " is not set in system properties. Defaulting to " +
                    CONNECT_TIMEOUT_PROPERTY_DEFAULT);
        }
        else {
            LOGGER.log(Level.FINE,
                () -> "Setting " + CONNECT_TIMEOUT_PROPERTY_KEY + " read from system properties as " + connectTimeoutPropertyValue);
            connectTimeout = Duration.parse(connectTimeoutPropertyValue);
        }
        return connectTimeout;
    }

    private static Duration getCallTimeout() {
        String callTimeoutPropertyValue = System.getProperty(CALL_TIMEOUT_PROPERTY_KEY);
        Duration callTimeout = CALL_TIMEOUT_PROPERTY_DEFAULT;
        if (callTimeoutPropertyValue == null) {
            LOGGER.log(Level.FINE,
                () -> "Setting " + CALL_TIMEOUT_PROPERTY_KEY + " is not set in system properties. Defaulting to " + CALL_TIMEOUT_PROPERTY_DEFAULT);
        }
        else {
            LOGGER.log(Level.FINER,
                () -> "Setting " + CALL_TIMEOUT_PROPERTY_KEY + " read from system properties as " + callTimeoutPropertyValue);
            callTimeout = Duration.parse(callTimeoutPropertyValue);
        }
        return callTimeout;
    }

    static OkHttpClient getInstance() {
        return INSTANCE;
    }
}
