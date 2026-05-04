package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * LocationLevelRefs
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-05-03T12:34:59.760765100-07:00[America/Los_Angeles]")
public class LocationLevelRefs {

    @JsonProperty("levels")
    @Valid
    private List<LocationLevelRef> levels = new ArrayList<>();

    @JsonProperty("next-page")
    private String nextPage = null;

    @JsonProperty("page")
    private String page = null;

    @JsonProperty("page-size")
    private Integer pageSize = null;

    @JsonProperty("total")
    private Integer total = null;

    public LocationLevelRefs levels(List<LocationLevelRef> levels) {
        this.levels = levels;
        return this;
    }

    public LocationLevelRefs addLevelsItem(LocationLevelRef levelsItem) {
            if (this.levels == null) {
            this.levels = new ArrayList<>();
            }
        this.levels.add(levelsItem);
        return this;
    }

    public List<LocationLevelRef> getLevels() {
        return levels;
    }

    public void setLevels(List<LocationLevelRef> levels) {
        this.levels = levels;
    }

    public LocationLevelRefs nextPage(String nextPage) {
        this.nextPage = nextPage;
        return this;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public LocationLevelRefs page(String page) {
        this.page = page;
        return this;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public LocationLevelRefs pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public LocationLevelRefs total(Integer total) {
        this.total = total;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        LocationLevelRefs locationLevelRefs = (LocationLevelRefs) o;
        return Objects.equals(this.levels, locationLevelRefs.levels)
         && this.nextPage == null || locationLevelRefs.nextPage == null?Objects.equals(this.nextPage, locationLevelRefs.nextPage):this.nextPage.equalsIgnoreCase(locationLevelRefs.nextPage)
         && this.page == null || locationLevelRefs.page == null?Objects.equals(this.page, locationLevelRefs.page):this.page.equalsIgnoreCase(locationLevelRefs.page)
         && Objects.equals(this.pageSize, locationLevelRefs.pageSize)
         && Objects.equals(this.total, locationLevelRefs.total)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(levels, nextPage==null?0:nextPage.toLowerCase(), page==null?0:page.toLowerCase(), pageSize, total);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LocationLevelRefs {\n");
        
        sb.append("    levels: ").append(toIndentedString(levels)).append("\n");
        sb.append("    nextPage: ").append(toIndentedString(nextPage)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
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
