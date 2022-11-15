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

import java.util.Optional;

import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;


public final class RatingTemplateEndpointInput extends EndpointInput {

    static final String OFFICE_QUERY_PARAMETER = "office";
    static final String TEMPLATE_ID_MASK_QUERY_PARAMETER = "template-id-mask";
    static final String PAGE_QUERY_PARAMETER = "page";
    static final String PAGE_SIZE_QUERY_PARAMETER = "page-size";

    private String templateId;
    private String templateIdMask;
    private String officeId;

    private String page;
    private Integer pageSize;

    public RatingTemplateEndpointInput() {
        this.officeId = null;
        this.templateId = null;
        this.templateIdMask = null;
    }

    String getTemplateId() {
        return templateId;
    }

    public RatingTemplateEndpointInput officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    public RatingTemplateEndpointInput templateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public RatingTemplateEndpointInput templateIdMask(String templateIdMask) {
        this.templateIdMask = templateIdMask;
        return this;
    }

    public RatingTemplateEndpointInput page(String page) {
        this.page = page;
        return this;
    }

    public RatingTemplateEndpointInput pageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    @Override
    protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
        String pageSizeString = Optional.ofNullable(pageSize).map(Object::toString).orElse(null);

        return httpRequestBuilder.addQueryParameter(TEMPLATE_ID_MASK_QUERY_PARAMETER, templateIdMask)
                                 .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                                 .addQueryParameter(PAGE_QUERY_PARAMETER, page)
                                 .addQueryParameter(PAGE_SIZE_QUERY_PARAMETER, pageSizeString)
                                 .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
    }
}