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

package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.time.Instant;
import java.util.Optional;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.LocationLevel;

public final class LocationLevelEndpointInput {

    static final String OFFICE_QUERY_PARAMETER = "office";
    static final String LEVEL_ID_MASK_QUERY_PARAMETER = "level-id-mask";
    static final String LEVEL_ID_QUERY_PARAMETER = "level-id";
    static final String PAGE_SIZE_QUERY_PARAMETER = "page-size";
    static final String PAGE_QUERY_PARAMETER = "page";
    static final String EFFECTIVE_DATE_QUERY_PARAMETER = "effective-date";
    static final String BEGIN_QUERY_PARAMETER = "begin";
    static final String END_QUERY_PARAMETER = "end";
    static final String UNIT_QUERY_PARAMETER = "unit";
    static final String CASCADE_DELETE_QUERY_PARAMETER = "cascade-delete*";

    private LocationLevelEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetOne getOne(String levelId, String officeId, Instant effectiveDate) {
        return new GetOne(levelId, officeId, effectiveDate);
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static Post post(LocationLevel level) {
        return new Post(level);
    }

    public static Patch patch(String originalLocationLevelId, LocationLevel level) {
        return new Patch(originalLocationLevelId, level);
    }

    public static Delete delete(String levelId) {
        return new Delete(levelId);
    }

    public static final class GetOne extends EndpointInput {
        private String officeId;
        private Instant effectiveDate;
        private String levelId;

        private GetOne(String levelId, String officeId, Instant effectiveDate) {
            this.officeId = officeId;
            this.levelId = levelId;
            this.effectiveDate = effectiveDate;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(LEVEL_ID_QUERY_PARAMETER, levelId)
                .addQueryParameter(EFFECTIVE_DATE_QUERY_PARAMETER, effectiveDate.toString())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }

        String levelId() {
            return levelId;
        }
    }

    public static final class GetAll extends EndpointInput {
        private String officeId;
        private String levelIdMask = "*";
        private Instant begin;
        private Instant end;
        private String unit = "SI";
        private String page;
        private Integer pageSize;

        private GetAll() {
            //Empty private ctor
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            String pageSizeString = Optional.ofNullable(pageSize).map(Object::toString).orElse(null);
            String beginString = Optional.ofNullable(begin).map(Object::toString).orElse(null);
            String endString = Optional.ofNullable(end).map(Object::toString).orElse(null);
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(LEVEL_ID_MASK_QUERY_PARAMETER, levelIdMask)
                .addQueryParameter(UNIT_QUERY_PARAMETER, unit)
                .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(BEGIN_QUERY_PARAMETER, beginString)
                .addQueryParameter(END_QUERY_PARAMETER, endString)
                .addQueryParameter(PAGE_QUERY_PARAMETER, page)
                .addQueryParameter(PAGE_SIZE_QUERY_PARAMETER, pageSizeString)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }

        public GetAll officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        public GetAll levelIdMask(String levelIdMask) {
            this.levelIdMask = levelIdMask;
            return this;
        }

        public GetAll begin(Instant begin) {
            this.begin = begin;
            return this;
        }

        public GetAll end(Instant end) {
            this.end = end;
            return this;
        }

        public GetAll page(String page) {
            this.page = page;
            return this;
        }

        public GetAll pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public GetAll unitSystem(String unit) {
            this.unit = unit;
            return this;
        }

        public GetAll unit(String unit) {
            this.unit = unit;
            return this;
        }
    }

    public static final class Post extends EndpointInput {

        private final LocationLevel level;

        private Post(LocationLevel level) {
            this.level = level;
        }

        LocationLevel level() {
            return level;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, level.getOfficeId())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Patch extends EndpointInput {

        private final LocationLevel level;
        private final String originalLocationLevelId;

        private Patch(String originalLocationLevelId, LocationLevel level) {
            this.originalLocationLevelId = originalLocationLevelId;
            this.level = level;
        }

        LocationLevel level() {
            return level;
        }

        String originalLocationLevelId() {
            return originalLocationLevelId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, level.getOfficeId())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {

        private final String levelId;
        private String officeId;
        private boolean cascadeDelete = false;
        private Instant effectiveDate;

        private Delete(String levelId) {
            this.levelId = levelId;
        }

        String levelId() {
            return levelId;
        }

        public Delete officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        public Delete cascadeDelete(boolean cascadeDelete) {
            this.cascadeDelete = cascadeDelete;
            return this;
        }

        public Delete effectiveDate(Instant effectiveDate) {
            this.effectiveDate = effectiveDate;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            String effectiveDate = Optional.ofNullable(this.effectiveDate).map(Object::toString).orElse(null);
            return httpRequestBuilder.addQueryParameter(LEVEL_ID_QUERY_PARAMETER, levelId)
                .addQueryParameter(CASCADE_DELETE_QUERY_PARAMETER, Boolean.toString(cascadeDelete))
                .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(EFFECTIVE_DATE_QUERY_PARAMETER, effectiveDate)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
