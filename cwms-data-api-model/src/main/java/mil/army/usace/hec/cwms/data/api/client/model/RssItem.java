/*
 * MIT License
 *
 * Copyright (c) 2025 Hydrologic Engineering Center
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

package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.Objects;

/**
 * List of RSS items in the channel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-12-19T13:08:07.460137300-08:00[America/Los_Angeles]")
public class RssItem {

    @JsonProperty("description")
    private String description = null;

    @JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "EEE, dd MMM yyyy HH:mm:ss zzz",
        locale = "en_US"
    )
    @JsonProperty("pubDate")
    private Instant pubDate = null;

    @JsonProperty("guid")
    private Guid guid = null;

    public RssItem description(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RssItem pubDate(Instant pubDate) {
        this.pubDate = pubDate;
        return this;
    }

    public Instant getPubDate() {
        return pubDate;
    }

    public void setPubDate(Instant pubDate) {
        this.pubDate = pubDate;
    }

    public RssItem guid(Guid guid) {
        this.guid = guid;
        return this;
    }

    public Guid getGuid() {
        return guid;
    }

    public void setGuid(Guid guid) {
        this.guid = guid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        RssItem rssItem = (RssItem) o;
        return this.description == null || rssItem.description == null?Objects.equals(this.description, rssItem.description):this.description.equalsIgnoreCase(rssItem.description)
         && Objects.equals(this.pubDate, rssItem.pubDate)
         && Objects.equals(this.guid, rssItem.guid)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description==null?0:description.toLowerCase(), pubDate, guid);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RssItem {\n");
        
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    pubDate: ").append(toIndentedString(pubDate)).append("\n");
        sb.append("    guid: ").append(toIndentedString(guid)).append("\n");
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
