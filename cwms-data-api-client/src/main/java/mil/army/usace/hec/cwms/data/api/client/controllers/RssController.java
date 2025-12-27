/*
 * MIT License
 *
 * Copyright (c) 2025 Hydrologic Engineering Center
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

package mil.army.usace.hec.cwms.data.api.client.controllers;

import static mil.army.usace.hec.cwms.data.api.client.controllers.RssEndpointInput.CURSOR_QUERY_PARAMETER;

import java.io.IOException;
import mil.army.usace.hec.cwms.data.api.client.model.AtomLink;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.data.api.client.model.RssFeed;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import okhttp3.HttpUrl;

public final class RssController {

    private static final String RSS_ENDPOINT = "rss";

    public RssFeed retrieveRssFeed(ApiConnectionInfo apiConnectionInfo, RssEndpointInput.GetAll input)
            throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, RSS_ENDPOINT + "/" + input.officeId() + "/" + input.name())
                .addEndpointInput(input)
                .get();
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapXmlToObject(response.getBody(), RssFeed.class);
        }
    }

    public RssFeed retrieveRssFeedFromLink(ApiConnectionInfo apiConnectionInfo, RssEndpointInput.GetAll input, AtomLink link)
        throws IOException {
        String href = link.getHref();
        input.cursor(getCursor(href));
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, RSS_ENDPOINT + "/" + input.officeId() + "/" + input.name())
            .addEndpointInput(input)
            .get();
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapXmlToObject(response.getBody(), RssFeed.class);
        }
    }

    private String getCursor(String href) {
        HttpUrl url = HttpUrl.parse(href);
        return (url != null) ? url.queryParameter(CURSOR_QUERY_PARAMETER) : null;
    }
}
