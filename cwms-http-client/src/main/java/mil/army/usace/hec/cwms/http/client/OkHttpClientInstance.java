/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
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

    private static final Logger LOGGER = Logger.getLogger(HttpRequestBuilder.class.getName());
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
