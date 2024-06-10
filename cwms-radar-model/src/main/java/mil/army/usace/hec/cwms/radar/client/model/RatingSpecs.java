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
 * RatingSpecs
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-09T09:29:33.859-07:00[America/Los_Angeles]")
public class RatingSpecs {
    @JsonProperty("page")
    private String page = null;

    @JsonProperty("nextPage")
    private String nextPage = null;

    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("specs")
    @Valid
    private List<RatingSpec> specs = new ArrayList<>();

    public RatingSpecs page(String page) {
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

    public RatingSpecs nextPage(String nextPage) {
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

    public RatingSpecs total(Integer total) {
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

    public RatingSpecs pageSize(Integer pageSize) {
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

    public RatingSpecs specs(List<RatingSpec> specs) {
        this.specs = specs;
        return this;
    }

    public RatingSpecs addSpecsItem(RatingSpec specsItem) {
        if (this.specs == null) {
            this.specs = new ArrayList<RatingSpec>();
        }
        this.specs.add(specsItem);
        return this;
    }

    /**
     * Get specs
     *
     * @return specs
     **/
    @Valid
    public List<RatingSpec> getSpecs() {
        return specs;
    }

    public void setSpecs(List<RatingSpec> specs) {
        this.specs = specs;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RatingSpecs ratingSpecs = (RatingSpecs) o;
        return this.page == null || ratingSpecs.page == null ? Objects.equals(this.page, ratingSpecs.page) :
            this.page.equalsIgnoreCase(ratingSpecs.page) && this.nextPage == null || ratingSpecs.nextPage == null ?
                Objects.equals(this.nextPage, ratingSpecs.nextPage) :
                this.nextPage.equalsIgnoreCase(ratingSpecs.nextPage) && Objects.equals(this.total, ratingSpecs.total) &&
                    Objects.equals(this.pageSize, ratingSpecs.pageSize) && Objects.equals(this.specs, ratingSpecs.specs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page == null ? 0 : page.toLowerCase(), nextPage == null ? 0 : nextPage.toLowerCase(), total, pageSize, specs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RatingSpecs {\n");

        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    nextPage: ").append(toIndentedString(nextPage)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    specs: ").append(toIndentedString(specs)).append("\n");
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
