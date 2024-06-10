/*
 * MIT License
 *
 * Copyright (c) 2024 Hydrologic Engineering Center
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
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clobs
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-04-10T10:29:07.376-07:00[America/Los_Angeles]")
public class Clobs {
    @JsonProperty("page")
    private String page = null;

    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("clobs")
    @Valid
    private List<Clob> clobs = new ArrayList<>();

    @JsonProperty("next-page")
    private String nextPage = null;

    @JsonProperty("page-size")
    private Integer pageSize = null;

    public Clobs page(String page) {
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

    public Clobs total(Integer total) {
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

    public Clobs clobs(List<Clob> clobs) {
        this.clobs = clobs;
        return this;
    }

    public Clobs addClobsItem(Clob clobsItem) {
        if (this.clobs == null) {
            this.clobs = new ArrayList<Clob>();
        }
        this.clobs.add(clobsItem);
        return this;
    }

    /**
     * List of retrieved clobs
     *
     * @return clobs
     **/
    @Valid
    public List<Clob> getClobs() {
        return clobs;
    }

    public void setClobs(List<Clob> clobs) {
        this.clobs = clobs;
    }

    public Clobs nextPage(String nextPage) {
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

    public Clobs pageSize(Integer pageSize) {
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
        Clobs clobs = (Clobs) o;
        return this.page == null || clobs.page == null ? Objects.equals(this.page, clobs.page) : this.page.equalsIgnoreCase(clobs.page)
            && Objects.equals(this.total, clobs.total)
            && Objects.equals(this.clobs, clobs.clobs)
            && this.nextPage == null || clobs.nextPage == null ? Objects.equals(this.nextPage, clobs.nextPage) :
            this.nextPage.equalsIgnoreCase(clobs.nextPage)
                && Objects.equals(this.pageSize, clobs.pageSize)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(page == null ? 0 : page.toLowerCase(), total, clobs, nextPage == null ? 0 : nextPage.toLowerCase(), pageSize);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Clobs {\n");

        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    clobs: ").append(toIndentedString(clobs)).append("\n");
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
