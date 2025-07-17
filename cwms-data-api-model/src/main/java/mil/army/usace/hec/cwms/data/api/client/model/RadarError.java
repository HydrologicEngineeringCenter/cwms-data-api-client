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
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * RadarError
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-10-04T10:30:29.690-07:00[America/Los_Angeles]")
public class RadarError {
    @JsonProperty("message")
    private String message = null;

    @JsonProperty("incidentIdentifier")
    private String incidentIdentifier = null;

    @JsonProperty("details")
    @Valid
    private Map<String, Object> details = null;

    public RadarError message(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get message
     *
     * @return message
     **/

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RadarError incidentIdentifier(String incidentIdentifier) {
        this.incidentIdentifier = incidentIdentifier;
        return this;
    }

    /**
     * A randomly generated number to help identify your request in the logs for analysis..
     *
     * @return incidentIdentifier
     **/

    public String getIncidentIdentifier() {
        return incidentIdentifier;
    }

    public void setIncidentIdentifier(String incidentIdentifier) {
        this.incidentIdentifier = incidentIdentifier;
    }

    public RadarError details(Map<String, Object> details) {
        this.details = details;
        return this;
    }

    public RadarError putDetailsItem(String key, Object detailsItem) {
        if (this.details == null) {
            this.details = new HashMap<String, Object>();
        }
        this.details.put(key, detailsItem);
        return this;
    }

    /**
     * Get details
     *
     * @return details
     **/

    public Map<String, Object> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RadarError radarError = (RadarError) o;
        return this.message == null || radarError.message == null ? Objects.equals(this.message, radarError.message) :
            this.message.equalsIgnoreCase(radarError.message)
                && this.incidentIdentifier == null || radarError.incidentIdentifier == null ?
                Objects.equals(this.incidentIdentifier, radarError.incidentIdentifier) :
                this.incidentIdentifier.equalsIgnoreCase(radarError.incidentIdentifier)
                    && Objects.equals(this.details, radarError.details)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(message == null ? 0 : message.toLowerCase(), incidentIdentifier == null ? 0 : incidentIdentifier.toLowerCase(), details);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RadarError {\n");

        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("    incidentIdentifier: ").append(toIndentedString(incidentIdentifier)).append("\n");
        sb.append("    details: ").append(toIndentedString(details)).append("\n");
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
