package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class AssignedLocation {
    @JsonProperty("location-id")
    private String locationId;
    @JsonProperty("base-location-id")
    private String baseLocationId;
    @JsonProperty("sub-location-id")
    private String subLocationId;
    @JsonProperty("alias-id")
    private String aliasId;
    @JsonProperty("attribute")
    private Number attribute;
    @JsonProperty("location-code")
    private Number locationCode;
    @JsonProperty("ref-location-id")
    private String refLocationId;

    @ApiModelProperty(value = "Assigned location's id")
    public String getLocationId() {
        return locationId;
    }

    @ApiModelProperty(value = "Assigned Location's base location id")
    public String getBaseLocationId() {
        return baseLocationId;
    }

    @ApiModelProperty(value = "Assigned Location's sub location id")
    public String getSubLocationId() {
        return subLocationId;
    }

    @ApiModelProperty(value = "Assigned Location's alias id")
    public String getAliasId() {
        return aliasId;
    }

    @ApiModelProperty(value = "Location's latitude")
    public Number getAttribute() {
        return attribute;
    }

    @ApiModelProperty(value = "Location's latitude")
    public Number getLocationCode() {
        return locationCode;
    }

    @ApiModelProperty(value = "Assigned Location's reference location id")
    public String getRefLocationId() {
        return refLocationId;
    }
}
