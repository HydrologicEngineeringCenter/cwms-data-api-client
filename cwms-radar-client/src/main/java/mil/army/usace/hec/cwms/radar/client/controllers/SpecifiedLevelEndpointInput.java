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
import mil.army.usace.hec.cwms.radar.client.model.SpecifiedLevel;

import java.util.Objects;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

public final class SpecifiedLevelEndpointInput {

    static final String OFFICE_QUERY_PARAMETER = "office";
    static final String SPECIFIED_LEVEL_MASK_QUERY_PARAMETER = "template-id-mask";
    static final String SPECIFIED_LEVEL_QUERY_PARAMETER = "specified-level-id";
    static final String FAIL_IF_EXISTS_QUERY_PARAMETER = "fail-if-exists";

    public static GetAll getAll() {
        return new GetAll();
    }

    public static Post post(SpecifiedLevel specifiedLevel) {
        return new Post(specifiedLevel);
    }

    public static Patch patch(String originalId, String newId, String officeId) {
        return new Patch(originalId, newId, officeId);
    }

    public static Delete delete(String specifiedLevelId, String officeId) {
        return new Delete(specifiedLevelId, officeId);
    }

    public static final class GetAll extends EndpointInput {
        private String officeId;
        private String specifiedLevelMask;

        private GetAll() {

        }

        public GetAll officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        public GetAll specifiedLevelMask(String specifiedLevelMask) {
            this.specifiedLevelMask = specifiedLevelMask;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(SPECIFIED_LEVEL_MASK_QUERY_PARAMETER, specifiedLevelMask)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Post extends EndpointInput {
        private final SpecifiedLevel specifiedLevel;
        private boolean failIfExists = true;

        private Post(SpecifiedLevel specifiedLevel) {
            this.specifiedLevel = Objects.requireNonNull(specifiedLevel, "Cannot store a specified level without a data object");
        }

        SpecifiedLevel specifiedLevel() {
            return specifiedLevel;
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER, Boolean.toString(failIfExists))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Patch extends EndpointInput {
        private final String originalId;
        private final String newId;
        private final String officeId;

        private Patch(String originalId, String newId, String officeId) {

            this.originalId = Objects.requireNonNull(originalId, "Cannot update specified level id without the original id");
            this.newId = Objects.requireNonNull(newId, "Cannot update a specified level id without a new id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot update a specified level id without an office");
        }

        public String originalId() {
            return originalId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(SPECIFIED_LEVEL_QUERY_PARAMETER, newId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Delete extends EndpointInput {
        private final String officeId;
        private final String specifiedLevel;

        private Delete(String specifiedLevel, String officeId) {
            this.specifiedLevel = Objects.requireNonNull(specifiedLevel, "Cannot delete a specified level without specifying an id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot delete a specified level without specifying an office");
        }

        String specifiedLevel() {
            return specifiedLevel;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }
}
