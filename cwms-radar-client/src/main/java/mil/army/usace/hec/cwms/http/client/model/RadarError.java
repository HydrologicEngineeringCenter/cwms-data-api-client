/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.validation.Valid;

/**
 * RadarError
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class RadarError {
    @JsonProperty("message")
    private String message = null;

    @JsonProperty("incidentIdentifier")
    private String incidentIdentifier = null;

    @JsonProperty("details")
    @Valid
    private Map<String, String> details = null;

    public RadarError message(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get message
     *
     * @return message
     **/
    @ApiModelProperty(value = "")

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
    @ApiModelProperty(value = "A randomly generated number to help identify your request in the logs for analysis..")

    public String getIncidentIdentifier() {
        return incidentIdentifier;
    }

    public void setIncidentIdentifier(String incidentIdentifier) {
        this.incidentIdentifier = incidentIdentifier;
    }

    public RadarError details(Map<String, String> details) {
        this.details = details;
        return this;
    }

    public RadarError putDetailsItem(String key, String detailsItem) {
        if (this.details == null) {
            this.details = new HashMap<String, String>();
        }
        this.details.put(key, detailsItem);
        return this;
    }

    /**
     * Get details
     *
     * @return details
     **/
    @ApiModelProperty(value = "")

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RadarError radarError = (RadarError) o;
        return Objects.equals(this.message, radarError.message) &&
            Objects.equals(this.incidentIdentifier, radarError.incidentIdentifier) &&
            Objects.equals(this.details, radarError.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, incidentIdentifier, details);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RadarError {\n");

        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("    incidentIdentifier: ").append(toIndentedString(incidentIdentifier))
            .append("\n");
        sb.append("    details: ").append(toIndentedString(details)).append("\n");
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
