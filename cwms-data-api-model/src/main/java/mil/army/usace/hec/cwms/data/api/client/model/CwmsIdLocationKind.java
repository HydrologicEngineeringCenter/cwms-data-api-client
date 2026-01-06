package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * CwmsIdLocationKind
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-12-31T09:45:55.836339800-08:00[America/Los_Angeles]")
public class CwmsIdLocationKind {

    @JsonProperty("location-kind-id")
    private String locationKindId = null;

    @JsonProperty("location-id")
    private CwmsId locationId = null;

    public CwmsIdLocationKind locationKindId(String locationKindId) {
        this.locationKindId = locationKindId;
        return this;
    }

    public String getLocationKindId() {
        return locationKindId;
    }

    public void setLocationKindId(String locationKindId) {
        this.locationKindId = locationKindId;
    }

    public CwmsIdLocationKind locationId(CwmsId locationId) {
        this.locationId = locationId;
        return this;
    }

    public CwmsId getLocationId() {
        return locationId;
    }

    public void setLocationId(CwmsId locationId) {
        this.locationId = locationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        CwmsIdLocationKind cwmsIdLocationKind = (CwmsIdLocationKind) o;
        return this.locationKindId == null || cwmsIdLocationKind.locationKindId == null?Objects.equals(this.locationKindId, cwmsIdLocationKind.locationKindId):this.locationKindId.equalsIgnoreCase(cwmsIdLocationKind.locationKindId)
         && Objects.equals(this.locationId, cwmsIdLocationKind.locationId)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationKindId==null?0:locationKindId.toLowerCase(), locationId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CwmsIdLocationKind {\n");
        
        sb.append("    locationKindId: ").append(toIndentedString(locationKindId)).append("\n");
        sb.append("    locationId: ").append(toIndentedString(locationId)).append("\n");
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
