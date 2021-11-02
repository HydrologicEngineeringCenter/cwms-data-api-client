/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.Valid;

/**
 * Pools
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class Pools {
    @JsonProperty("page")
    private String page = null;

    @JsonProperty("nextPage")
    private String nextPage = null;

    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("pools")
    private PoolsPools pools = null;

    public Pools page(String page) {
        this.page = page;
        return this;
    }

    /**
     * The cursor to the current page of data
     *
     * @return page
     **/
    @ApiModelProperty(value = "The cursor to the current page of data")

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Pools nextPage(String nextPage) {
        this.nextPage = nextPage;
        return this;
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

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public Pools total(Integer total) {
        this.total = total;
        return this;
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

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Pools pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
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

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Pools pools(PoolsPools pools) {
        this.pools = pools;
        return this;
    }

    /**
     * Get pools
     *
     * @return pools
     **/
    @ApiModelProperty(value = "")

    @Valid
    public PoolsPools getPools() {
        return pools;
    }

    public void setPools(PoolsPools pools) {
        this.pools = pools;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pools pools = (Pools) o;
        return Objects.equals(this.page, pools.page) &&
            Objects.equals(this.nextPage, pools.nextPage) &&
            Objects.equals(this.total, pools.total) &&
            Objects.equals(this.pageSize, pools.pageSize) &&
            Objects.equals(this.pools, pools.pools);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, nextPage, total, pageSize, pools);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Pools {\n");

        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    nextPage: ").append(toIndentedString(nextPage)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    pools: ").append(toIndentedString(pools)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
