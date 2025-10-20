/*
 * MIT License
 *
 * Copyright (c) 2024 Hydrologic Engineering Center
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
package hec.army.usace.hec.cwbi.auth.http.client;

import java.net.URI;
import java.util.function.Consumer;

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;

public interface TokenRequestFluentBuilder<T> {

    /**
     * If given auth method uses a single URL.
     * @param url
     * @return
     * @deprecated even for implementations, like direct grant/resource owner password credentials
     *             should use the individual endpoints in the appropriate sections to avoid configuration
     *             details that are too specific but filter up among the usage.
     */
    @Deprecated(forRemoval = true)
    RequestClientId withUrl(ApiConnectionInfo url);

    /**
     * Create object for next step in auth.
     * @return
     */
    RequestClientId buildRequest();

    /**
     * set specific Auth URL endpoint.
     * @param url
     * @return
     */
    TokenRequestFluentBuilder<T> withAuthUrl(ApiConnectionInfo url);

    /**
     * set specific Token URL endpoint.
     * @param url
     * @return
     */
    TokenRequestFluentBuilder<T> withTokenUrl(ApiConnectionInfo url);

    /**
     * For methods where an external step is required to finish authentication
     * pass in desired operation
     * @param authCallback
     * @return
     */
    TokenRequestFluentBuilder<T> withAuthCallback(Consumer<URI> authCallback);
}
