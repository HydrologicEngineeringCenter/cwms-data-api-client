package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;


/**
 * PumpLocation
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-11-22T13:03:28.808153100-08:00[America/Los_Angeles]")
public class PumpLocation {

    @JsonProperty("pump-in")
    private CwmsId pumpIn = null;

    @JsonProperty("pump-out")
    private CwmsId pumpOut = null;

    @JsonProperty("pump-below")
    private CwmsId pumpBelow = null;

    public PumpLocation pumpIn(CwmsId pumpIn) {
        this.pumpIn = pumpIn;
        return this;
    }

    public CwmsId getPumpIn() {
        return pumpIn;
    }

    public void setPumpIn(CwmsId pumpIn) {
        this.pumpIn = pumpIn;
    }

    public PumpLocation pumpOut(CwmsId pumpOut) {
        this.pumpOut = pumpOut;
        return this;
    }

    public CwmsId getPumpOut() {
        return pumpOut;
    }

    public void setPumpOut(CwmsId pumpOut) {
        this.pumpOut = pumpOut;
    }

    public PumpLocation pumpBelow(CwmsId pumpBelow) {
        this.pumpBelow = pumpBelow;
        return this;
    }

    public CwmsId getPumpBelow() {
        return pumpBelow;
    }

    public void setPumpBelow(CwmsId pumpBelow) {
        this.pumpBelow = pumpBelow;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PumpLocation pumpLocation = (PumpLocation) o;
        return Objects.equals(this.pumpIn, pumpLocation.pumpIn)
                && Objects.equals(this.pumpOut, pumpLocation.pumpOut)
                && Objects.equals(this.pumpBelow, pumpLocation.pumpBelow)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pumpIn, pumpOut, pumpBelow);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PumpLocation {\n");

        sb.append("    pumpIn: ").append(toIndentedString(pumpIn)).append("\n");
        sb.append("    pumpOut: ").append(toIndentedString(pumpOut)).append("\n");
        sb.append("    pumpBelow: ").append(toIndentedString(pumpBelow)).append("\n");
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
