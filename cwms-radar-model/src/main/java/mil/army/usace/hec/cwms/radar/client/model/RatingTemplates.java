package mil.army.usace.hec.cwms.radar.client.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

/**
 * RatingTemplates
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-06-30T22:13:10.613Z[GMT]")
public class RatingTemplates
{
	@JsonProperty("page")
	private String page = null;

	@JsonProperty("nextPage")
	private String nextPage = null;

	@JsonProperty("total")
	private Integer total = null;

	@JsonProperty("pageSize")
	private Integer pageSize = null;

    @JsonProperty("templates")
    @Valid
    private List<RatingTemplate> templates = new ArrayList<>();

	public RatingTemplates page(String page)
	{
		this.page = page;
		return this;
	}

	/**
	 * The cursor to the current page of data
	 *
	 * @return page
	 **/
	public String getPage()
	{
		return page;
	}

	public void setPage(String page)
	{
		this.page = page;
	}

	public RatingTemplates nextPage(String nextPage)
	{
		this.nextPage = nextPage;
		return this;
	}

	/**
	 * The cursor to the next page of data; null if there is no more data
	 *
	 * @return nextPage
	 **/
	public String getNextPage()
	{
		return nextPage;
	}

	public void setNextPage(String nextPage)
	{
		this.nextPage = nextPage;
	}

	public RatingTemplates total(Integer total)
	{
		this.total = total;
		return this;
	}

	/**
	 * The total number of records retrieved; null or not present if not supported or unknown
	 *
	 * @return total
	 **/

	public Integer getTotal()
	{
		return total;
	}

	public void setTotal(Integer total)
	{
		this.total = total;
	}

	public RatingTemplates pageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
		return this;
	}

	/**
	 * The number of records fetched per-page; this may be larger than the number of records actually retrieved
	 *
	 * @return pageSize
	 **/
	public Integer getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	public RatingTemplates templates(List<RatingTemplate> templates)
	{
		this.templates = templates;
		return this;
	}

	public RatingTemplates addTemplatesItem(RatingTemplate templatesItem)
	{
		if(this.templates == null)
		{
			this.templates = new ArrayList<RatingTemplate>();
		}
		this.templates.add(templatesItem);
		return this;
	}

	/**
	 * Get templates
	 *
	 * @return templates
	 **/
	@Valid
	public List<RatingTemplate> getTemplates()
	{
		return templates;
	}

	public void setTemplates(List<RatingTemplate> templates)
	{
		this.templates = templates;
	}


	@Override
	public boolean equals(java.lang.Object o)
	{
		if(this == o)
		{
			return true;
		}
		if(o == null || getClass() != o.getClass())
		{
			return false;
		}
		RatingTemplates ratingTemplates = (RatingTemplates) o;
		return Objects.equals(this.page, ratingTemplates.page) && Objects.equals(this.nextPage,
				ratingTemplates.nextPage) && Objects.equals(this.total, ratingTemplates.total) && Objects.equals(
				this.pageSize, ratingTemplates.pageSize) && Objects.equals(this.templates, ratingTemplates.templates);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(page, nextPage, total, pageSize, templates);
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("class RatingTemplates {\n");

		sb.append("    page: ").append(toIndentedString(page)).append("\n");
		sb.append("    nextPage: ").append(toIndentedString(nextPage)).append("\n");
		sb.append("    total: ").append(toIndentedString(total)).append("\n");
		sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
		sb.append("    templates: ").append(toIndentedString(templates)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o)
	{
		if(o == null)
		{
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
