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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.stream.Collectors;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;

class TestHttpRequestResponse {

    @Test
    void testHttpRequestResponse() throws IOException {
        String bodyStr =
            "{\"message\":\"Unable to find category based on parameters given\",\"incidentIdentifier\":\"-49092149940938\",\"details\":{}}";
        ResponseBody body = ResponseBody.create(bodyStr, MediaType.parse("application/json"));
        HttpRequestResponse httpRequestResponse = new HttpRequestResponse(body, Collections.emptyList());
        assertEquals(bodyStr, httpRequestResponse.getBody());
    }

    @Test
    void testHttpRequestResponseStream() throws IOException {
        String bodyStr = "Hello World";
        byte[] bytes = bodyStr.getBytes();
        ResponseBody body = ResponseBody.create(bytes, MediaType.parse("text/plain"));
        HttpRequestResponse httpRequestResponse = new HttpRequestResponse(body, Collections.emptyList());
        try (InputStream inputStream = httpRequestResponse.getStream()) {
            String result = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
            assertEquals(bodyStr, result);
        }
    }

}
