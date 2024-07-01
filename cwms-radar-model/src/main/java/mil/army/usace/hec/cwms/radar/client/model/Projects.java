package mil.army.usace.hec.cwms.radar.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;

/**
 * Projects
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-07-01T15:41:30.828168500-07:00[America/Los_Angeles]")
public class Projects
{

	@JsonProperty("next-page")
	private String nextPage = null;

	@JsonProperty("page")
	private String page = null;

	@JsonProperty("page-size")
	private Integer pageSize = null;

	@JsonProperty("projects")
	@Valid
	private List<Project> projects = new ArrayList<>();

	@JsonProperty("total")
	private Integer total = null;

	public Projects nextPage(String nextPage)
	{
		this.nextPage = nextPage;
		return this;
	}

	public String getNextPage()
	{
		return nextPage;
	}

	public void setNextPage(String nextPage)
	{
		this.nextPage = nextPage;
	}

	public Projects page(String page)
	{
		this.page = page;
		return this;
	}

	public String getPage()
	{
		return page;
	}

	public void setPage(String page)
	{
		this.page = page;
	}

	public Projects pageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
		return this;
	}

	public Integer getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	public Projects projects(List<Project> projects)
	{
		this.projects = projects;
		return this;
	}

	public Projects addProjectsItem(Project projectsItem)
	{
		if(this.projects == null)
		{
			this.projects = new ArrayList<>();
		}
		this.projects.add(projectsItem);
		return this;
	}

	public List<Project> getProjects()
	{
		return projects;
	}

	public void setProjects(List<Project> projects)
	{
		this.projects = projects;
	}

	public Projects total(Integer total)
	{
		this.total = total;
		return this;
	}

	public Integer getTotal()
	{
		return total;
	}

	public void setTotal(Integer total)
	{
		this.total = total;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o)
		{
			return true;
		}
		if(o == null || getClass() != o.getClass())
		{
			return false;
		}
		Projects other = (Projects) o;
		return this.nextPage == null || other.nextPage == null ? Objects.equals(this.nextPage,
				other.nextPage) : this.nextPage.equalsIgnoreCase(
				other.nextPage) && this.page == null || other.page == null ? Objects.equals(this.page,
				other.page) : this.page.equalsIgnoreCase(other.page) && Objects.equals(this.pageSize,
				other.pageSize) && Objects.equals(this.projects, other.projects) && Objects.equals(this.total,
				other.total);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(nextPage == null ? 0 : nextPage.toLowerCase(), page == null ? 0 : page.toLowerCase(),
				pageSize, projects, total);
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("class Projects {\n");

		sb.append("    nextPage: ").append(toIndentedString(nextPage)).append("\n");
		sb.append("    page: ").append(toIndentedString(page)).append("\n");
		sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
		sb.append("    projects: ").append(toIndentedString(projects)).append("\n");
		sb.append("    total: ").append(toIndentedString(total)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(Object o)
	{
		if(o == null)
		{
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
