package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class LocationCategory {

    @JsonProperty("office-id")
    private String officeId;
    @JsonProperty("id")
    private String id;
    @JsonProperty("description")
    private String description;

    @ApiModelProperty(value = "Office that Location Category belongs to")
    public String getOfficeId() {
        return officeId;
    }

    @ApiModelProperty(value = "Location Category's id")
    public String getId() {
        return id;
    }

    @ApiModelProperty(value = "Location Category's description")
    public String getDescription() {
        return description;
    }
}
