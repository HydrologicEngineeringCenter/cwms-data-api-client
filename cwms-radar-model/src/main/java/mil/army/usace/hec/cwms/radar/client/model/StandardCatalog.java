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
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * StandardCatalog
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-02-13T10:48:01.962877200-08:00[America/Los_Angeles]")
public class StandardCatalog {
    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("entries")
    @Valid
    private List<StandardCatalogEntry> entries = new ArrayList<>();

    public StandardCatalog officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    /**
     * Owning office of object.
     *
     * @return officeId
     **/
    @NotNull

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public StandardCatalog entries(List<StandardCatalogEntry> entries) {
        this.entries = entries;
        return this;
    }

    public StandardCatalog addEntriesItem(StandardCatalogEntry entriesItem) {
        if (this.entries == null) {
            this.entries = new ArrayList<StandardCatalogEntry>();
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
    public List<StandardCatalogEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<StandardCatalogEntry> entries) {
        this.entries = entries;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StandardCatalog standardCatalog = (StandardCatalog) o;
        return this.officeId == null || standardCatalog.officeId == null ? Objects.equals(this.officeId, standardCatalog.officeId) : this.officeId.equalsIgnoreCase(standardCatalog.officeId)
                && Objects.equals(this.entries, standardCatalog.entries)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId == null ? 0 : officeId.toLowerCase(), entries);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StandardCatalog {\n");

        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
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
