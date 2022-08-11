package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;

/**
 * LocationLevels
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-11T12:56:36.285-07:00[America/Los_Angeles]")
public class LocationLevels {
    @JsonProperty("page")
    private String page = null;

    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("levels")
    @Valid
    private List<LocationLevel> levels = new ArrayList<>();

    @JsonProperty("next-page")
    private String nextPage = null;

    @JsonProperty("page-size")
    private Integer pageSize = null;

    public LocationLevels page(String page) {
        this.page = page;
        return this;
    }

    /**
     * The cursor to the current page of data
     *
     * @return page
     **/

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public LocationLevels total(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * The total number of records retrieved; null or not present if not supported or unknown
     *
     * @return total
     **/

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public LocationLevels levels(List<LocationLevel> levels) {
        this.levels = levels;
        return this;
    }

    public LocationLevels addLevelsItem(LocationLevel levelsItem) {
        if (this.levels == null) {
            this.levels = new ArrayList<LocationLevel>();
        }
        this.levels.add(levelsItem);
        return this;
    }

    /**
     * List of retrieved location levels
     *
     * @return levels
     **/
    @Valid
    public List<LocationLevel> getLevels() {
        return levels;
    }

    public void setLevels(List<LocationLevel> levels) {
        this.levels = levels;
    }

    public LocationLevels nextPage(String nextPage) {
        this.nextPage = nextPage;
        return this;
    }

    /**
     * The cursor to the next page of data; null if there is no more data
     *
     * @return nextPage
     **/

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public LocationLevels pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * The number of records fetched per-page; this may be larger than the number of records actually retrieved
     *
     * @return pageSize
     **/

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LocationLevels locationLevels = (LocationLevels) o;
        return this.page == null || locationLevels.page == null ? Objects.equals(this.page, locationLevels.page) :
            this.page.equalsIgnoreCase(locationLevels.page) && Objects.equals(this.total, locationLevels.total) &&
                Objects.equals(this.levels, locationLevels.levels) && this.nextPage == null || locationLevels.nextPage == null ?
                Objects.equals(this.nextPage, locationLevels.nextPage) :
                this.nextPage.equalsIgnoreCase(locationLevels.nextPage) && Objects.equals(this.pageSize, locationLevels.pageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page == null ? 0 : page.toLowerCase(), total, levels, nextPage == null ? 0 : nextPage.toLowerCase(), pageSize);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LocationLevels {\n");

        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    levels: ").append(toIndentedString(levels)).append("\n");
        sb.append("    nextPage: ").append(toIndentedString(nextPage)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
