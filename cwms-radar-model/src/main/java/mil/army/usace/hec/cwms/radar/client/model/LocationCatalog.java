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

package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;

/**
 * LocationCatalog
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-07-22T11:14:35.029-07:00[America/Los_Angeles]")
public class LocationCatalog {
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
    private List<LocationCatalogEntry> entries = new ArrayList<>();

    public LocationCatalog page(String page) {
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

    public LocationCatalog nextPage(String nextPage) {
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

    public LocationCatalog total(Integer total) {
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

    public LocationCatalog pageSize(Integer pageSize) {
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

    public LocationCatalog entries(List<LocationCatalogEntry> entries) {
        this.entries = entries;
        return this;
    }

    public LocationCatalog addEntriesItem(LocationCatalogEntry entriesItem) {
        if (this.entries == null) {
            this.entries = new ArrayList<LocationCatalogEntry>();
        }
        this.entries.add(entriesItem);
        return this;
    }

    /**
     * Get entries
     *
     * @return entries
     **/
    @Valid
    public List<LocationCatalogEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<LocationCatalogEntry> entries) {
        this.entries = entries;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LocationCatalog catalog = (LocationCatalog) o;
        return this.page == null || catalog.page == null ? Objects.equals(this.page, catalog.page) :
            this.page.equalsIgnoreCase(catalog.page) && this.nextPage == null || catalog.nextPage == null ?
                Objects.equals(this.nextPage, catalog.nextPage) :
                this.nextPage.equalsIgnoreCase(catalog.nextPage) && Objects.equals(this.total, catalog.total) &&
                    Objects.equals(this.pageSize, catalog.pageSize) && Objects.equals(this.entries, catalog.entries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page == null ? 0 : page.toLowerCase(), nextPage == null ? 0 : nextPage.toLowerCase(), total, pageSize, entries);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LocationCatalog {\n");

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
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
