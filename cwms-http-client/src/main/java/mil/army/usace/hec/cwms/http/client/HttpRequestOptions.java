/*
 * MIT License
 *
 * Copyright (c) 2026 Hydrologic Engineering Center
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

import java.io.InterruptedIOException;
import java.time.Duration;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

/**
 * Immutable per-request transport options for {@link HttpRequestBuilder#withOptions(HttpRequestOptions)}.
 *
 * <p>Every field is optional. A field that is not set inherits the value of the shared client, so
 * {@link #defaults()} (and not supplying options at all) leaves current behavior unchanged: timeouts come from the
 * {@code cwms.http.client.*timeout.seconds} system properties (read once when the shared client is created), and
 * automatic connection retry and redirects follow the OkHttp defaults (all enabled).</p>
 *
 * <p>Options are applied to a client derived for each request from the dependency-owned shared client, so they never
 * change global or system properties, other requests, the shared connection pool or the shared dispatcher. Callers do
 * not create, evict or shut down any transport resource. The caller does own closing the
 * {@link HttpRequestResponse} returned by a successful request.</p>
 *
 * <p>Timeout scope:</p>
 * <ul>
 *   <li>The call timeout bounds the whole call, from the start of the request until the response body has been fully
 *   read or the {@link HttpRequestResponse} is closed. It therefore also covers body consumption through
 *   {@link HttpRequestResponse#getBody()} and {@link HttpRequestResponse#getStream()} after {@code execute()}
 *   returns. When body-level HTTP logging is enabled the logging interceptor reads the body inside
 *   {@code execute()}, so a body timeout or read failure then surfaces from {@code execute()} instead.</li>
 *   <li>The connect, read and write timeouts apply to each individual connect, socket read and socket write. A read
 *   timeout is not a total body deadline: a body that keeps trickling data can exceed it overall unless a call timeout
 *   is also set.</li>
 *   <li>Blocking that these timeouts cannot reliably bound: host name resolution, interactive SSL/client-certificate
 *   prompts, and requests an authenticator makes on its own client. A call timeout expiring surfaces as an
 *   {@link InterruptedIOException}; it is not converted to {@link ServerNotFoundException}.</li>
 * </ul>
 */
public final class HttpRequestOptions {

    private static final HttpRequestOptions DEFAULTS = new Builder().build();
    private static final Duration MAX_TIMEOUT = Duration.ofMillis(Integer.MAX_VALUE);
    private static final Duration MIN_TIMEOUT = Duration.ofMillis(1);

    private final Duration callTimeout;
    private final Duration connectTimeout;
    private final Duration readTimeout;
    private final Duration writeTimeout;
    private final Boolean retryOnConnectionFailure;
    private final Boolean followRedirects;
    private final Boolean followSslRedirects;

    private HttpRequestOptions(Builder builder) {
        this.callTimeout = builder.callTimeout;
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.writeTimeout = builder.writeTimeout;
        this.retryOnConnectionFailure = builder.retryOnConnectionFailure;
        this.followRedirects = builder.followRedirects;
        this.followSslRedirects = builder.followSslRedirects;
    }

    /**
     * Options that set nothing; every setting inherits the shared client's value.
     *
     * @return the default options
     */
    public static HttpRequestOptions defaults() {
        return DEFAULTS;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Optional<Duration> callTimeout() {
        return Optional.ofNullable(callTimeout);
    }

    public Optional<Duration> connectTimeout() {
        return Optional.ofNullable(connectTimeout);
    }

    public Optional<Duration> readTimeout() {
        return Optional.ofNullable(readTimeout);
    }

    public Optional<Duration> writeTimeout() {
        return Optional.ofNullable(writeTimeout);
    }

    public Optional<Boolean> retryOnConnectionFailure() {
        return Optional.ofNullable(retryOnConnectionFailure);
    }

    public Optional<Boolean> followRedirects() {
        return Optional.ofNullable(followRedirects);
    }

    public Optional<Boolean> followSslRedirects() {
        return Optional.ofNullable(followSslRedirects);
    }

    /**
     * Derives a client from the given one. The given client is not modified, and the derived client shares its
     * connection pool and dispatcher.
     */
    OkHttpClient applyTo(OkHttpClient client) {
        if (this.equals(DEFAULTS)) {
            return client;
        }
        OkHttpClient.Builder builder = client.newBuilder();
        if (callTimeout != null) {
            builder.callTimeout(callTimeout.toMillis(), TimeUnit.MILLISECONDS);
        }
        if (connectTimeout != null) {
            builder.connectTimeout(connectTimeout.toMillis(), TimeUnit.MILLISECONDS);
        }
        if (readTimeout != null) {
            builder.readTimeout(readTimeout.toMillis(), TimeUnit.MILLISECONDS);
        }
        if (writeTimeout != null) {
            builder.writeTimeout(writeTimeout.toMillis(), TimeUnit.MILLISECONDS);
        }
        if (retryOnConnectionFailure != null) {
            builder.retryOnConnectionFailure(retryOnConnectionFailure);
        }
        if (followRedirects != null) {
            builder.followRedirects(followRedirects);
        }
        if (followSslRedirects != null) {
            builder.followSslRedirects(followSslRedirects);
        }
        return builder.build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HttpRequestOptions)) {
            return false;
        }
        HttpRequestOptions that = (HttpRequestOptions) o;
        return Objects.equals(callTimeout, that.callTimeout)
            && Objects.equals(connectTimeout, that.connectTimeout)
            && Objects.equals(readTimeout, that.readTimeout)
            && Objects.equals(writeTimeout, that.writeTimeout)
            && Objects.equals(retryOnConnectionFailure, that.retryOnConnectionFailure)
            && Objects.equals(followRedirects, that.followRedirects)
            && Objects.equals(followSslRedirects, that.followSslRedirects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(callTimeout, connectTimeout, readTimeout, writeTimeout, retryOnConnectionFailure,
            followRedirects, followSslRedirects);
    }

    @Override
    public String toString() {
        return "HttpRequestOptions{callTimeout=" + callTimeout + ", connectTimeout=" + connectTimeout
            + ", readTimeout=" + readTimeout + ", writeTimeout=" + writeTimeout
            + ", retryOnConnectionFailure=" + retryOnConnectionFailure + ", followRedirects=" + followRedirects
            + ", followSslRedirects=" + followSslRedirects + '}';
    }

    /**
     * Mutable builder; not thread safe. Timeouts must be at least one millisecond and at most
     * {@link Integer#MAX_VALUE} milliseconds.
     */
    public static final class Builder {
        private Duration callTimeout;
        private Duration connectTimeout;
        private Duration readTimeout;
        private Duration writeTimeout;
        private Boolean retryOnConnectionFailure;
        private Boolean followRedirects;
        private Boolean followSslRedirects;

        private Builder() {
        }

        /**
         * Sets the call, connect, read and write timeouts to the same value.
         *
         * @param timeout the timeout, at least 1 millisecond and at most {@link Integer#MAX_VALUE} milliseconds
         * @return this builder
         */
        public Builder timeout(Duration timeout) {
            Duration valid = validate(timeout, "timeout");
            this.callTimeout = valid;
            this.connectTimeout = valid;
            this.readTimeout = valid;
            this.writeTimeout = valid;
            return this;
        }

        public Builder callTimeout(Duration timeout) {
            this.callTimeout = validate(timeout, "callTimeout");
            return this;
        }

        public Builder connectTimeout(Duration timeout) {
            this.connectTimeout = validate(timeout, "connectTimeout");
            return this;
        }

        public Builder readTimeout(Duration timeout) {
            this.readTimeout = validate(timeout, "readTimeout");
            return this;
        }

        public Builder writeTimeout(Duration timeout) {
            this.writeTimeout = validate(timeout, "writeTimeout");
            return this;
        }

        public Builder retryOnConnectionFailure(boolean retry) {
            this.retryOnConnectionFailure = retry;
            return this;
        }

        public Builder followRedirects(boolean follow) {
            this.followRedirects = follow;
            return this;
        }

        public Builder followSslRedirects(boolean follow) {
            this.followSslRedirects = follow;
            return this;
        }

        public HttpRequestOptions build() {
            return new HttpRequestOptions(this);
        }

        private static Duration validate(Duration timeout, String name) {
            Objects.requireNonNull(timeout, name + " must be defined");
            if (timeout.compareTo(MIN_TIMEOUT) < 0) {
                throw new IllegalArgumentException(name + " must be at least 1 millisecond: " + timeout);
            }
            if (timeout.compareTo(MAX_TIMEOUT) > 0) {
                throw new IllegalArgumentException(name + " must not exceed " + Integer.MAX_VALUE
                    + " milliseconds: " + timeout);
            }
            return timeout;
        }
    }
}
