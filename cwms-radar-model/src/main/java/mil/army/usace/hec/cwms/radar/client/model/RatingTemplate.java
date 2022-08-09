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
 * RatingTemplate
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-09T09:29:33.859-07:00[America/Los_Angeles]")
public class RatingTemplate {
    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("id")
    private String id = null;

    @JsonProperty("version")
    private String version = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("dependent-parameter")
    private String dependentParameter = null;

    @JsonProperty("independent-parameter-specs")
    @Valid
    private List<ParameterSpec> independentParameterSpecs = new ArrayList<>();

    @JsonProperty("rating-ids")
    @Valid
    private List<String> ratingIds = new ArrayList<>();

    public RatingTemplate officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    /**
     * Get officeId
     *
     * @return officeId
     **/

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public RatingTemplate id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RatingTemplate version(String version) {
        this.version = version;
        return this;
    }

    /**
     * Get version
     *
     * @return version
     **/

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public RatingTemplate description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get description
     *
     * @return description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RatingTemplate dependentParameter(String dependentParameter) {
        this.dependentParameter = dependentParameter;
        return this;
    }

    /**
     * Get dependentParameter
     *
     * @return dependentParameter
     **/

    public String getDependentParameter() {
        return dependentParameter;
    }

    public void setDependentParameter(String dependentParameter) {
        this.dependentParameter = dependentParameter;
    }

    public RatingTemplate independentParameterSpecs(List<ParameterSpec> independentParameterSpecs) {
        this.independentParameterSpecs = independentParameterSpecs;
        return this;
    }

    public RatingTemplate addIndependentParameterSpecsItem(ParameterSpec independentParameterSpecsItem) {
        if (this.independentParameterSpecs == null) {
            this.independentParameterSpecs = new ArrayList<ParameterSpec>();
        }
        this.independentParameterSpecs.add(independentParameterSpecsItem);
        return this;
    }

    /**
     * Get independentParameterSpecs
     *
     * @return independentParameterSpecs
     **/
    @Valid
    public List<ParameterSpec> getIndependentParameterSpecs() {
        return independentParameterSpecs;
    }

    public void setIndependentParameterSpecs(List<ParameterSpec> independentParameterSpecs) {
        this.independentParameterSpecs = independentParameterSpecs;
    }

    public RatingTemplate ratingIds(List<String> ratingIds) {
        this.ratingIds = ratingIds;
        return this;
    }

    public RatingTemplate addRatingIdsItem(String ratingIdsItem) {
        if (this.ratingIds == null) {
            this.ratingIds = new ArrayList<String>();
        }
        this.ratingIds.add(ratingIdsItem);
        return this;
    }

    /**
     * Get ratingIds
     *
     * @return ratingIds
     **/

    public List<String> getRatingIds() {
        return ratingIds;
    }

    public void setRatingIds(List<String> ratingIds) {
        this.ratingIds = ratingIds;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RatingTemplate ratingTemplate = (RatingTemplate) o;
        return this.officeId == null || ratingTemplate.officeId == null ? Objects.equals(this.officeId, ratingTemplate.officeId) :
            this.officeId.equalsIgnoreCase(ratingTemplate.officeId) && this.id == null || ratingTemplate.id == null ?
                Objects.equals(this.id, ratingTemplate.id) :
                this.id.equalsIgnoreCase(ratingTemplate.id) && this.version == null || ratingTemplate.version == null ?
                    Objects.equals(this.version, ratingTemplate.version) :
                    this.version.equalsIgnoreCase(ratingTemplate.version) && this.description == null || ratingTemplate.description == null ?
                        Objects.equals(this.description, ratingTemplate.description) :
                        this.description.equalsIgnoreCase(ratingTemplate.description) && this.dependentParameter == null ||
                            ratingTemplate.dependentParameter == null ? Objects.equals(this.dependentParameter, ratingTemplate.dependentParameter) :
                            this.dependentParameter.equalsIgnoreCase(ratingTemplate.dependentParameter) &&
                                Objects.equals(this.independentParameterSpecs, ratingTemplate.independentParameterSpecs) &&
                                Objects.equals(this.ratingIds, ratingTemplate.ratingIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId == null ? 0 : officeId.toLowerCase(), id == null ? 0 : id.toLowerCase(),
            version == null ? 0 : version.toLowerCase(), description == null ? 0 : description.toLowerCase(),
            dependentParameter == null ? 0 : dependentParameter.toLowerCase(), independentParameterSpecs, ratingIds);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RatingTemplate {\n");

        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    version: ").append(toIndentedString(version)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    dependentParameter: ").append(toIndentedString(dependentParameter)).append("\n");
        sb.append("    independentParameterSpecs: ").append(toIndentedString(independentParameterSpecs)).append("\n");
        sb.append("    ratingIds: ").append(toIndentedString(ratingIds)).append("\n");
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
