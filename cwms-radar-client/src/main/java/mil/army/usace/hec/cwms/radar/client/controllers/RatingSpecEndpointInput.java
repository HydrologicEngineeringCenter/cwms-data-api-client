package mil.army.usace.hec.cwms.radar.client.controllers;

import java.util.Optional;

import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

public class RatingSpecEndpointInput extends EndpointInput
{

	static final String OFFICE_QUERY_PARAMETER = "office";
	static final String RATING_ID_MASK_QUERY_PARAMETER = "rating-id-mask";
	static final String PAGE_QUERY_PARAMETER = "page";
	static final String PAGE_SIZE_QUERY_PARAMETER = "page-size";

	private String ratingId;
	private String ratingIdMask;
	private String officeId;

	private String page;
	private Integer pageSize;

	public RatingSpecEndpointInput()
	{
		this.officeId = null;
		this.ratingId = null;
		this.ratingIdMask = null;
	}

	String getRatingIdMask()
	{
		return ratingIdMask;
	}
	String getRatingId()
	{
		return ratingId;
	}

	public RatingSpecEndpointInput officeId(String officeId)
	{
		this.officeId = officeId;
		return this;
	}

	public RatingSpecEndpointInput ratingId(String rId)
	{
		this.ratingId = rId;
		return this;
	}

	public RatingSpecEndpointInput ratingIdMask(String rIdMask)
	{
		this.ratingIdMask = rIdMask;
		return this;
	}

	public RatingSpecEndpointInput page(String page)
	{
		this.page = page;
		return this;
	}

	public RatingSpecEndpointInput pageSize(int pageSize)
	{
		this.pageSize = pageSize;
		return this;
	}

	@Override
	protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder)
	{
		String pageSizeString = Optional.ofNullable(pageSize).map(Object::toString).orElse(null);

		return httpRequestBuilder
				.addQueryParameter(RATING_ID_MASK_QUERY_PARAMETER, ratingIdMask)
				.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
				.addQueryParameter(PAGE_QUERY_PARAMETER, page)
				.addQueryParameter(	PAGE_SIZE_QUERY_PARAMETER, pageSizeString)
				.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
	}
}