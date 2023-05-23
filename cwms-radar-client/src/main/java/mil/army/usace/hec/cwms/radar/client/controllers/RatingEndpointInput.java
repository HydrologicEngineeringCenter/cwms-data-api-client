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

package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

import java.time.Instant;
import java.util.Optional;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_XML_HEADER_V2;

public final class RatingEndpointInput {

    private static final String OFFICE_QUERY_PARAMETER = "office";
    private static final String BEGIN_QUERY_PARAMETER = "begin";
    private static final String END_QUERY_PARAMETER = "end";
    private static final String METHOD_QUERY_PARAMETER = "method";

    private RatingEndpointInput() {
        throw new AssertionError("Utility class");
    }

    public static GetOne getOne(String ratingId, String officeId) {
        return new GetOne(ratingId, officeId);
    }

    public static Post post(String ratingSetXml) {
        return new Post(ratingSetXml);
    }

    public static Delete delete(String ratingId, String officeId) {
        return new Delete(ratingId, officeId);
    }

    public static final class GetOne extends EndpointInput {

        private final String ratingId;
        private final String officeId;
        private Instant begin;
        private Instant end;
        private String method = "EAGER";

        private GetOne(String ratingId, String officeId) {
            this.ratingId = ratingId;
            this.officeId = officeId;
        }

        public GetOne begin(Instant begin) {
            this.begin = begin;
            return this;
        }

        public GetOne end(Instant end) {
            this.end = end;
            return this;
        }

        public GetOne eager() {
            this.method = "EAGER";
            return this;
        }

        public GetOne reference() {
            this.method = "REFERENCE";
            return this;
        }

        public GetOne lazy() {
            this.method = "LAZY";
            return this;
        }

        String getRatingId() {
            return ratingId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            String beginStr = Optional.ofNullable(begin).map(Instant::toString).orElse(null);
            String endStr = Optional.ofNullable(end).map(Instant::toString).orElse(null);
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(METHOD_QUERY_PARAMETER, method)
                .addQueryParameter(BEGIN_QUERY_PARAMETER, beginStr)
                .addQueryParameter(END_QUERY_PARAMETER, endStr)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_XML_HEADER_V2);
        }
    }

    public static final class Post extends EndpointInput {

        private final String ratingSetXml;

        private Post(String ratingSetXml) {
            this.ratingSetXml = ratingSetXml;
        }

        String ratingSetXml() {
            return ratingSetXml;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_XML_HEADER_V2);
        }
    }

    public static final class Delete extends EndpointInput {

        private final String ratingId;
        private final String officeId;

        private Delete(String ratingId, String officeId) {
            this.ratingId = ratingId;
            this.officeId = officeId;
        }

        String getRatingId() {
            return ratingId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_XML_HEADER_V2);
        }
    }
}
