package mil.army.usace.hec.cwms.data.api.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * LocationToPublishedDataList
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-06-05T16:57:21.059473900-07:00[America/Los_Angeles]")
public class LocationToPublishedDataList {

    @JsonProperty("location-to-published-data")
    @Valid
    private List<LocationToPublishedData> locationToPublishedData = new ArrayList<>();

    @JsonProperty("next-page")
    private String nextPage = null;

    @JsonProperty("page")
    private String page = null;

    @JsonProperty("page-size")
    private Integer pageSize = null;

    @JsonProperty("total")
    private Integer total = null;

    public LocationToPublishedDataList locationToPublishedData(List<LocationToPublishedData> locationToPublishedData) {
        this.locationToPublishedData = locationToPublishedData;
        return this;
    }

    public LocationToPublishedDataList addLocationToPublishedDataItem(LocationToPublishedData locationToPublishedDataItem) {
            if (this.locationToPublishedData == null) {
            this.locationToPublishedData = new ArrayList<>();
            }
        this.locationToPublishedData.add(locationToPublishedDataItem);
        return this;
    }

    public List<LocationToPublishedData> getLocationToPublishedData() {
        return locationToPublishedData;
    }

    public void setLocationToPublishedData(List<LocationToPublishedData> locationToPublishedData) {
        this.locationToPublishedData = locationToPublishedData;
    }

    public LocationToPublishedDataList nextPage(String nextPage) {
        this.nextPage = nextPage;
        return this;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public LocationToPublishedDataList page(String page) {
        this.page = page;
        return this;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public LocationToPublishedDataList pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public LocationToPublishedDataList total(Integer total) {
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
        LocationToPublishedDataList locationToPublishedDataList = (LocationToPublishedDataList) o;
        return Objects.equals(this.locationToPublishedData, locationToPublishedDataList.locationToPublishedData)
         && this.nextPage == null || locationToPublishedDataList.nextPage == null?Objects.equals(this.nextPage, locationToPublishedDataList.nextPage):this.nextPage.equalsIgnoreCase(locationToPublishedDataList.nextPage)
         && this.page == null || locationToPublishedDataList.page == null?Objects.equals(this.page, locationToPublishedDataList.page):this.page.equalsIgnoreCase(locationToPublishedDataList.page)
         && Objects.equals(this.pageSize, locationToPublishedDataList.pageSize)
         && Objects.equals(this.total, locationToPublishedDataList.total)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationToPublishedData, nextPage==null?0:nextPage.toLowerCase(), page==null?0:page.toLowerCase(), pageSize, total);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LocationToPublishedDataList {\n");
        
        sb.append("    locationToPublishedData: ").append(toIndentedString(locationToPublishedData)).append("\n");
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
