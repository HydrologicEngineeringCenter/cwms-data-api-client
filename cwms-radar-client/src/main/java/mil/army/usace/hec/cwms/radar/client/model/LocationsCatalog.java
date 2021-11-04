/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;

/**
 * Catalog
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class LocationsCatalog {
    @JsonProperty("page")
    private String page = null;

    @JsonProperty("next-page")
    private String nextPage = null;

    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("page-size")
    private Integer pageSize = null;

    @JsonProperty("entries")
    @Valid
    private List<LocationsCatalogEntry> entries = null;

    /**
     * The cursor to the current page of data
     *
     * @return page
     **/
    @ApiModelProperty(value = "The cursor to the current page of data")

    public String getPage() {
        return page;
    }

    /**
     * The cursor to the next page of data; null if there is no more data
     *
     * @return nextPage
     **/
    @ApiModelProperty(value = "The cursor to the next page of data; null if there is no more data")

    public String getNextPage() {
        return nextPage;
    }

    /**
     * The total number of records retrieved; null or not present if not supported or unknown
     *
     * @return total
     **/
    @ApiModelProperty(value = "The total number of records retrieved; null or not present if not supported or unknown")

    public Integer getTotal() {
        return total;
    }

    /**
     * The number of records fetched per-page; this may be larger than the number of records actually retrieved
     *
     * @return pageSize
     **/
    @ApiModelProperty(value = "The number of records fetched per-page; this may be larger than the number of records actually retrieved")

    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * Get entries
     *
     * @return entries
     **/
    @ApiModelProperty(value = "")
    @Valid
    public List<LocationsCatalogEntry> getEntries() {
        return entries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LocationsCatalog catalog = (LocationsCatalog) o;
        return Objects.equals(this.page, catalog.page) &&
            Objects.equals(this.nextPage, catalog.nextPage) &&
            Objects.equals(this.total, catalog.total) &&
            Objects.equals(this.pageSize, catalog.pageSize) &&
            Objects.equals(this.entries, catalog.entries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, nextPage, total, pageSize, entries);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Catalog {\n");

        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    nextPage: ").append(toIndentedString(nextPage)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    entries: ").append(toIndentedString(entries)).append("\n");
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