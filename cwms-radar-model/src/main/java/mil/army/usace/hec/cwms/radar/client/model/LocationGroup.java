package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

public class LocationGroup {

    @JsonProperty("id")
    private String id;
    @JsonProperty("location-category")
    private LocationCategory locationCategory;
    @JsonProperty("office-id")
    private String officeId;
    @JsonProperty("description")
    private String description;
    @JsonProperty("shared-loc-alias-id")
    private String sharedLocAliasId;
    @JsonProperty("shared-reg-location-id")
    private String sharedRefLocationId;
    @JsonProperty("loc-group-attribute")
    private Number locGroupAttribute;
    @JsonProperty("assigned-locations")
    private List<AssignedLocation> assignedLocations;

    @ApiModelProperty(value = "Location Group's id")
    public String getId() {
        return id;
    }

    @ApiModelProperty(value = "Location Group's category")
    public LocationCategory getLocationCategory() {
        return locationCategory;
    }

    @ApiModelProperty(value = "Office that Location Group belongs to")
    public String getOfficeId() {
        return officeId;
    }

    @ApiModelProperty(value = "Location Group's description")
    public String getDescription() {
        return description;
    }

    @ApiModelProperty(value = "Location Group's shared location-alias id")
    public String getSharedLocAliasId() {
        return sharedLocAliasId;
    }

    @ApiModelProperty(value = "Location Group's shared reference-location id")
    public String getSharedRefLocationId() {
        return sharedRefLocationId;
    }

    @ApiModelProperty(value = "Location Group's attribute")
    public Number getLocGroupAttribute() {
        return locGroupAttribute;
    }

    @ApiModelProperty(value = "Location Group's assigned locations")
    public List<AssignedLocation> getAssignedLocations() {
        return assignedLocations;
    }
}
