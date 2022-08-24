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