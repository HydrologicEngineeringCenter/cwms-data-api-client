/*
 * MIT License
 *
 * Copyright (c) 2021 Hydrologic Engineering Center
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
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;

/**
 * TimeSeries
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-12-01T13:20:30.413-08:00[America/Los_Angeles]")
public class TimeSeries {
    @JsonProperty("begin")
    private ZonedDateTime begin = null;

    @JsonProperty("end")
    private ZonedDateTime end = null;

    @JsonProperty("interval")
    private Duration interval = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("next-page")
    private String nextPage = null;

    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("page")
    private String page = null;

    @JsonProperty("page-size")
    private Integer pageSize = null;

    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("units")
    private String units = null;

    @JsonProperty("value-columns")
    @Valid
    private List<TimeSeriesColumn> valueColumns = null;

    @JsonProperty("values")
    private List<TimeSeriesValues> values = null;

    @JsonProperty("vertical-datum-info")
    private VerticalDatumInfo verticalDatumInfo = null;

    public TimeSeries begin(ZonedDateTime begin) {
        this.begin = begin;
        return this;
    }

    /**
     * The requested start time of the data, in ISO-8601 format with offset and timezone ('yyyy-MM-dd'T'HH:mm:ssZ'['VV']'')
     *
     * @return begin
     **/

    @Valid
    public ZonedDateTime getBegin() {
        return begin;
    }

    public void setBegin(ZonedDateTime begin) {
        this.begin = begin;
    }

    public TimeSeries end(ZonedDateTime end) {
        this.end = end;
        return this;
    }

    /**
     * The requested end time of the data, in ISO-8601 format with offset and timezone ('yyyy-MM-dd'T'HH:mm:ssZ'['VV']'')
     *
     * @return end
     **/

    @Valid
    public ZonedDateTime getEnd() {
        return end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    public TimeSeries interval(Duration interval) {
        this.interval = interval;
        return this;
    }

    /**
     * Get interval
     *
     * @return interval
     **/

    @Valid
    public Duration getInterval() {
        return interval;
    }

    public void setInterval(Duration interval) {
        this.interval = interval;
    }

    public TimeSeries name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Time-series name
     *
     * @return name
     **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimeSeries nextPage(String nextPage) {
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

    public TimeSeries officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    /**
     * Office ID that owns the time-series
     *
     * @return officeId
     **/

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public TimeSeries page(String page) {
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

    public TimeSeries pageSize(Integer pageSize) {
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

    public TimeSeries total(Integer total) {
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

    public TimeSeries units(String units) {
        this.units = units;
        return this;
    }

    /**
     * The units of the time series data
     *
     * @return units
     **/

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public TimeSeries valueColumns(List<TimeSeriesColumn> valueColumns) {
        this.valueColumns = valueColumns;
        return this;
    }

    public TimeSeries addValueColumnsItem(TimeSeriesColumn valueColumnsItem) {
        if (this.valueColumns == null) {
            this.valueColumns = new ArrayList<TimeSeriesColumn>();
        }
        this.valueColumns.add(valueColumnsItem);
        return this;
    }

    /**
     * Get valueColumns
     *
     * @return valueColumns
     **/
    @Valid
    public List<TimeSeriesColumn> getValueColumns() {
        return valueColumns;
    }

    public void setValueColumns(List<TimeSeriesColumn> valueColumns) {
        this.valueColumns = valueColumns;
    }

    public TimeSeries values(List<TimeSeriesValues> values) {
        this.values = values;
        return this;
    }

    /**
     * Get values
     *
     * @return values
     **/

    @Valid
    public List<TimeSeriesValues> getValues() {
        return values;
    }

    public void setValues(List<TimeSeriesValues> values) {
        this.values = values;
    }

    public TimeSeries verticalDatumInfo(VerticalDatumInfo verticalDatumInfo) {
        this.verticalDatumInfo = verticalDatumInfo;
        return this;
    }

    /**
     * Get verticalDatumInfo
     *
     * @return verticalDatumInfo
     **/

    @Valid
    public VerticalDatumInfo getVerticalDatumInfo() {
        return verticalDatumInfo;
    }

    public void setVerticalDatumInfo(VerticalDatumInfo verticalDatumInfo) {
        this.verticalDatumInfo = verticalDatumInfo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeries timeSeries = (TimeSeries) o;
        return Objects.equals(this.begin, timeSeries.begin)
            && Objects.equals(this.end, timeSeries.end)
            && Objects.equals(this.interval, timeSeries.interval)
            && this.name == null || timeSeries.name == null ? Objects.equals(this.name, timeSeries.name) : this.name.equalsIgnoreCase(timeSeries.name)
            && this.nextPage == null || timeSeries.nextPage == null ? Objects.equals(this.nextPage, timeSeries.nextPage) :
            this.nextPage.equalsIgnoreCase(timeSeries.nextPage)
                && this.officeId == null || timeSeries.officeId == null ? Objects.equals(this.officeId, timeSeries.officeId) :
                this.officeId.equalsIgnoreCase(timeSeries.officeId)
                    && this.page == null || timeSeries.page == null ? Objects.equals(this.page, timeSeries.page) :
                    this.page.equalsIgnoreCase(timeSeries.page)
                        && Objects.equals(this.pageSize, timeSeries.pageSize)
                        && Objects.equals(this.total, timeSeries.total)
                        && this.units == null || timeSeries.units == null ? Objects.equals(this.units, timeSeries.units) :
                        this.units.equalsIgnoreCase(timeSeries.units)
                            && Objects.equals(this.valueColumns, timeSeries.valueColumns)
                            && Objects.equals(this.values, timeSeries.values)
                            && Objects.equals(this.verticalDatumInfo, timeSeries.verticalDatumInfo)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(begin, end, interval, name == null ? 0 : name.toLowerCase(), nextPage == null ? 0 : nextPage.toLowerCase(),
            officeId == null ? 0 : officeId.toLowerCase(), page == null ? 0 : page.toLowerCase(), pageSize, total,
            units == null ? 0 : units.toLowerCase(), valueColumns, values, verticalDatumInfo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeries {\n");

        sb.append("    begin: ").append(toIndentedString(begin)).append("\n");
        sb.append("    end: ").append(toIndentedString(end)).append("\n");
        sb.append("    interval: ").append(toIndentedString(interval)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    nextPage: ").append(toIndentedString(nextPage)).append("\n");
        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    units: ").append(toIndentedString(units)).append("\n");
        sb.append("    valueColumns: ").append(toIndentedString(valueColumns)).append("\n");
        sb.append("    values: ").append(toIndentedString(values)).append("\n");
        sb.append("    verticalDatumInfo: ").append(toIndentedString(verticalDatumInfo)).append("\n");
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
