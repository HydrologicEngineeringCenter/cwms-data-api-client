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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The RSS channel containing feed metadata and items
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-12-19T13:08:07.460137300-08:00[America/Los_Angeles]")
public class RssChannel {

    @JsonProperty("title")
    private String title = null;

    @JsonProperty("nextLink")
    private AtomLink nextLink = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("items")
    @Valid
    private List<RssItem> items = new ArrayList<>();

    public RssChannel title(String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RssChannel nextLink(AtomLink nextLink) {
        this.nextLink = nextLink;
        return this;
    }

    public AtomLink getNextLink() {
        return nextLink;
    }

    public void setNextLink(AtomLink nextLink) {
        this.nextLink = nextLink;
    }

    public RssChannel description(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RssChannel items(List<RssItem> items) {
        this.items = items;
        return this;
    }

    public RssChannel addItemsItem(RssItem itemsItem) {
            if (this.items == null) {
            this.items = new ArrayList<>();
            }
        this.items.add(itemsItem);
        return this;
    }

    public List<RssItem> getItems() {
        return items;
    }

    public void setItems(List<RssItem> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        RssChannel rssChannel = (RssChannel) o;
        return this.title == null || rssChannel.title == null?Objects.equals(this.title, rssChannel.title):this.title.equalsIgnoreCase(rssChannel.title)
         && Objects.equals(this.nextLink, rssChannel.nextLink)
         && this.description == null || rssChannel.description == null?Objects.equals(this.description, rssChannel.description):this.description.equalsIgnoreCase(rssChannel.description)
         && Objects.equals(this.items, rssChannel.items)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title==null?0:title.toLowerCase(), nextLink, description==null?0:description.toLowerCase(), items);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RssChannel {\n");
        
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    nextLink: ").append(toIndentedString(nextLink)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    items: ").append(toIndentedString(items)).append("\n");
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
