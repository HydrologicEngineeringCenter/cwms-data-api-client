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

package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Parameter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-08-05T09:28:48.954185300-07:00[America/Los_Angeles]")
public class Parameter {

    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("base-parameter")
    private String baseParameter = null;

    @JsonProperty("sub-parameter")
    private String subParameter = null;

    @JsonProperty("sub-parameter-description")
    private String subParameterDescription = null;

    @JsonProperty("db-unit-id")
    private String dbUnitId = null;

    @JsonProperty("unit-long-name")
    private String unitLongName = null;

    @JsonProperty("unit-description")
    private String unitDescription = null;

    public Parameter officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public Parameter name(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parameter baseParameter(String baseParameter) {
        this.baseParameter = baseParameter;
        return this;
    }

    public String getBaseParameter() {
        return baseParameter;
    }

    public void setBaseParameter(String baseParameter) {
        this.baseParameter = baseParameter;
    }

    public Parameter subParameter(String subParameter) {
        this.subParameter = subParameter;
        return this;
    }

    public String getSubParameter() {
        return subParameter;
    }

    public void setSubParameter(String subParameter) {
        this.subParameter = subParameter;
    }

    public Parameter subParameterDescription(String subParameterDescription) {
        this.subParameterDescription = subParameterDescription;
        return this;
    }

    public String getSubParameterDescription() {
        return subParameterDescription;
    }

    public void setSubParameterDescription(String subParameterDescription) {
        this.subParameterDescription = subParameterDescription;
    }

    public Parameter dbUnitId(String dbUnitId) {
        this.dbUnitId = dbUnitId;
        return this;
    }

    public String getDbUnitId() {
        return dbUnitId;
    }

    public void setDbUnitId(String dbUnitId) {
        this.dbUnitId = dbUnitId;
    }

    public Parameter unitLongName(String unitLongName) {
        this.unitLongName = unitLongName;
        return this;
    }

    public String getUnitLongName() {
        return unitLongName;
    }

    public void setUnitLongName(String unitLongName) {
        this.unitLongName = unitLongName;
    }

    public Parameter unitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
        return this;
    }

    public String getUnitDescription() {
        return unitDescription;
    }

    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Parameter parameter = (Parameter) o;
        return this.officeId == null || parameter.officeId == null ? Objects.equals(this.officeId, parameter.officeId) :
            this.officeId.equalsIgnoreCase(parameter.officeId)
                && this.name == null || parameter.name == null ? Objects.equals(this.name, parameter.name) :
                this.name.equalsIgnoreCase(parameter.name)
                    && this.baseParameter == null || parameter.baseParameter == null ?
                    Objects.equals(this.baseParameter, parameter.baseParameter) :
                    this.baseParameter.equalsIgnoreCase(parameter.baseParameter)
                        && this.subParameter == null || parameter.subParameter == null ?
                        Objects.equals(this.subParameter, parameter.subParameter) :
                        this.subParameter.equalsIgnoreCase(parameter.subParameter)
                            && this.subParameterDescription == null || parameter.subParameterDescription == null ?
                            Objects.equals(this.subParameterDescription, parameter.subParameterDescription) :
                            this.subParameterDescription.equalsIgnoreCase(parameter.subParameterDescription)
                                && this.dbUnitId == null || parameter.dbUnitId == null ?
                                Objects.equals(this.dbUnitId, parameter.dbUnitId) :
                                this.dbUnitId.equalsIgnoreCase(parameter.dbUnitId)
                                    && this.unitLongName == null || parameter.unitLongName == null ?
                                    Objects.equals(this.unitLongName, parameter.unitLongName) :
                                    this.unitLongName.equalsIgnoreCase(parameter.unitLongName)
                                        && this.unitDescription == null || parameter.unitDescription == null ?
                                        Objects.equals(this.unitDescription, parameter.unitDescription) :
                                        this.unitDescription.equalsIgnoreCase(parameter.unitDescription)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId == null ? 0 : officeId.toLowerCase(), name == null ? 0 : name.toLowerCase(),
            baseParameter == null ? 0 : baseParameter.toLowerCase(),
            subParameter == null ? 0 : subParameter.toLowerCase(),
            subParameterDescription == null ? 0 : subParameterDescription.toLowerCase(),
            dbUnitId == null ? 0 : dbUnitId.toLowerCase(), unitLongName == null ? 0 : unitLongName.toLowerCase(),
            unitDescription == null ? 0 : unitDescription.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Parameter {\n");

        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    baseParameter: ").append(toIndentedString(baseParameter)).append("\n");
        sb.append("    subParameter: ").append(toIndentedString(subParameter)).append("\n");
        sb.append("    subParameterDescription: ").append(toIndentedString(subParameterDescription)).append("\n");
        sb.append("    dbUnitId: ").append(toIndentedString(dbUnitId)).append("\n");
        sb.append("    unitLongName: ").append(toIndentedString(unitLongName)).append("\n");
        sb.append("    unitDescription: ").append(toIndentedString(unitDescription)).append("\n");
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
