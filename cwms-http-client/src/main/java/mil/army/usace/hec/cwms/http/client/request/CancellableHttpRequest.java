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

package mil.army.usace.hec.cwms.http.client.request;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;

/**
 * A single-use request that can be cancelled from another thread. Cancellation is cooperative and best effort: it
 * asks the transport to fail the call and returns immediately without waiting for it to stop.
 *
 * <ul>
 *   <li>A cancelled call fails with an {@link IOException} (typically with the message "Canceled"). Cancelling before
 *   {@link #execute()} makes {@code execute()} fail the same way without sending the request.</li>
 *   <li>Cancelling after {@code execute()} returned fails further reads of the response body, but the caller must still
 *   close the {@link HttpRequestResponse}.</li>
 *   <li>Cancellation does not interrupt blocking work the transport cannot abort, such as host name resolution or an
 *   interactive SSL/client-certificate prompt; the call fails once that work returns. An immediate hard abort is not
 *   promised.</li>
 *   <li>Cancelling a call that has completed or was already cancelled has no effect.</li>
 * </ul>
 */
public interface CancellableHttpRequest {

    /**
     * Executes the request. Behaves like {@link HttpRequestExecutor#execute()}, including who closes the response.
     *
     * @return the successful response, which the caller must close
     * @throws IOException if the request fails or is cancelled
     * @throws IllegalStateException if this request was already executed
     */
    HttpRequestResponse execute() throws IOException;

    /**
     * Requests cancellation. Safe to call from any thread, any number of times.
     */
    void cancel();

    /**
     * @return true once {@link #cancel()} has been called
     */
    boolean isCancelled();
}
