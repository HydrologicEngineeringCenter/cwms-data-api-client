package mil.army.usace.hec.cwms.data.api.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Measurement
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-11-29T09:10:21.459880-08:00[America/Los_Angeles]")
public class Measurement {

    @JsonProperty("height-unit")
    private String heightUnit = null;

    @JsonProperty("flow-unit")
    private String flowUnit = null;

    @JsonProperty("temp-unit")
    private String tempUnit = null;

    @JsonProperty("velocity-unit")
    private String velocityUnit = null;

    @JsonProperty("area-unit")
    private String areaUnit = null;

    @JsonProperty("used")
    private Boolean used = null;

    @JsonProperty("agency")
    private String agency = null;

    @JsonProperty("party")
    private String party = null;

    @JsonProperty("wm-comments")
    private String wmComments = null;

    @JsonProperty("instant")
    private Instant instant = null;

    @JsonProperty("id")
    private CwmsId id = null;

    @JsonProperty("number")
    private String number = null;

    @JsonProperty("streamflow-measurement")
    private StreamflowMeasurement streamflowMeasurement = null;

    @JsonProperty("supplemental-streamflow-measurement")
    private SupplementalStreamflowMeasurement supplementalStreamflowMeasurement = null;

    @JsonProperty("usgs-measurement")
    private UsgsMeasurement usgsMeasurement = null;

    public Measurement heightUnit(String heightUnit) {
        this.heightUnit = heightUnit;
        return this;
    }

    public String getHeightUnit() {
        return heightUnit;
    }

    public void setHeightUnit(String heightUnit) {
        this.heightUnit = heightUnit;
    }

    public Measurement flowUnit(String flowUnit) {
        this.flowUnit = flowUnit;
        return this;
    }

    public String getFlowUnit() {
        return flowUnit;
    }

    public void setFlowUnit(String flowUnit) {
        this.flowUnit = flowUnit;
    }

    public Measurement tempUnit(String tempUnit) {
        this.tempUnit = tempUnit;
        return this;
    }

    public String getTempUnit() {
        return tempUnit;
    }

    public void setTempUnit(String tempUnit) {
        this.tempUnit = tempUnit;
    }

    public Measurement velocityUnit(String velocityUnit) {
        this.velocityUnit = velocityUnit;
        return this;
    }

    public String getVelocityUnit() {
        return velocityUnit;
    }

    public void setVelocityUnit(String velocityUnit) {
        this.velocityUnit = velocityUnit;
    }

    public Measurement areaUnit(String areaUnit) {
        this.areaUnit = areaUnit;
        return this;
    }

    public String getAreaUnit() {
        return areaUnit;
    }

    public void setAreaUnit(String areaUnit) {
        this.areaUnit = areaUnit;
    }

    public Measurement used(Boolean used) {
        this.used = used;
        return this;
    }

    public Boolean isUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Measurement agency(String agency) {
        this.agency = agency;
        return this;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public Measurement party(String party) {
        this.party = party;
        return this;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public Measurement wmComments(String wmComments) {
        this.wmComments = wmComments;
        return this;
    }

    public String getWmComments() {
        return wmComments;
    }

    public void setWmComments(String wmComments) {
        this.wmComments = wmComments;
    }

    public Measurement instant(Instant instant) {
        this.instant = instant;
        return this;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public Measurement id(CwmsId id) {
        this.id = id;
        return this;
    }

    public CwmsId getId() {
        return id;
    }

    public void setId(CwmsId id) {
        this.id = id;
    }

    public Measurement number(String number) {
        this.number = number;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Measurement streamflowMeasurement(StreamflowMeasurement streamflowMeasurement) {
        this.streamflowMeasurement = streamflowMeasurement;
        return this;
    }

    public StreamflowMeasurement getStreamflowMeasurement() {
        return streamflowMeasurement;
    }

    public void setStreamflowMeasurement(StreamflowMeasurement streamflowMeasurement) {
        this.streamflowMeasurement = streamflowMeasurement;
    }

    public Measurement supplementalStreamflowMeasurement(SupplementalStreamflowMeasurement supplementalStreamflowMeasurement) {
        this.supplementalStreamflowMeasurement = supplementalStreamflowMeasurement;
        return this;
    }

    public SupplementalStreamflowMeasurement getSupplementalStreamflowMeasurement() {
        return supplementalStreamflowMeasurement;
    }

    public void setSupplementalStreamflowMeasurement(SupplementalStreamflowMeasurement supplementalStreamflowMeasurement) {
        this.supplementalStreamflowMeasurement = supplementalStreamflowMeasurement;
    }

    public Measurement usgsMeasurement(UsgsMeasurement usgsMeasurement) {
        this.usgsMeasurement = usgsMeasurement;
        return this;
    }

    public UsgsMeasurement getUsgsMeasurement() {
        return usgsMeasurement;
    }

    public void setUsgsMeasurement(UsgsMeasurement usgsMeasurement) {
        this.usgsMeasurement = usgsMeasurement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        Measurement measurement = (Measurement) o;
        return this.heightUnit == null || measurement.heightUnit == null?Objects.equals(this.heightUnit, measurement.heightUnit):this.heightUnit.equalsIgnoreCase(measurement.heightUnit)
         && this.flowUnit == null || measurement.flowUnit == null?Objects.equals(this.flowUnit, measurement.flowUnit):this.flowUnit.equalsIgnoreCase(measurement.flowUnit)
         && this.tempUnit == null || measurement.tempUnit == null?Objects.equals(this.tempUnit, measurement.tempUnit):this.tempUnit.equalsIgnoreCase(measurement.tempUnit)
         && this.velocityUnit == null || measurement.velocityUnit == null?Objects.equals(this.velocityUnit, measurement.velocityUnit):this.velocityUnit.equalsIgnoreCase(measurement.velocityUnit)
         && this.areaUnit == null || measurement.areaUnit == null?Objects.equals(this.areaUnit, measurement.areaUnit):this.areaUnit.equalsIgnoreCase(measurement.areaUnit)
         && Objects.equals(this.used, measurement.used)
         && this.agency == null || measurement.agency == null?Objects.equals(this.agency, measurement.agency):this.agency.equalsIgnoreCase(measurement.agency)
         && this.party == null || measurement.party == null?Objects.equals(this.party, measurement.party):this.party.equalsIgnoreCase(measurement.party)
         && this.wmComments == null || measurement.wmComments == null?Objects.equals(this.wmComments, measurement.wmComments):this.wmComments.equalsIgnoreCase(measurement.wmComments)
         && Objects.equals(this.instant, measurement.instant)
         && Objects.equals(this.id, measurement.id)
         && this.number == null || measurement.number == null?Objects.equals(this.number, measurement.number):this.number.equalsIgnoreCase(measurement.number)
         && Objects.equals(this.streamflowMeasurement, measurement.streamflowMeasurement)
         && Objects.equals(this.supplementalStreamflowMeasurement, measurement.supplementalStreamflowMeasurement)
         && Objects.equals(this.usgsMeasurement, measurement.usgsMeasurement)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(heightUnit==null?0:heightUnit.toLowerCase(), flowUnit==null?0:flowUnit.toLowerCase(), tempUnit==null?0:tempUnit.toLowerCase(), velocityUnit==null?0:velocityUnit.toLowerCase(), areaUnit==null?0:areaUnit.toLowerCase(), used, agency==null?0:agency.toLowerCase(), party==null?0:party.toLowerCase(), wmComments==null?0:wmComments.toLowerCase(), instant, id, number==null?0:number.toLowerCase(), streamflowMeasurement, supplementalStreamflowMeasurement, usgsMeasurement);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Measurement {\n");
        
        sb.append("    heightUnit: ").append(toIndentedString(heightUnit)).append("\n");
        sb.append("    flowUnit: ").append(toIndentedString(flowUnit)).append("\n");
        sb.append("    tempUnit: ").append(toIndentedString(tempUnit)).append("\n");
        sb.append("    velocityUnit: ").append(toIndentedString(velocityUnit)).append("\n");
        sb.append("    areaUnit: ").append(toIndentedString(areaUnit)).append("\n");
        sb.append("    used: ").append(toIndentedString(used)).append("\n");
        sb.append("    agency: ").append(toIndentedString(agency)).append("\n");
        sb.append("    party: ").append(toIndentedString(party)).append("\n");
        sb.append("    wmComments: ").append(toIndentedString(wmComments)).append("\n");
        sb.append("    instant: ").append(toIndentedString(instant)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    number: ").append(toIndentedString(number)).append("\n");
        sb.append("    streamflowMeasurement: ").append(toIndentedString(streamflowMeasurement)).append("\n");
        sb.append("    supplementalStreamflowMeasurement: ").append(toIndentedString(supplementalStreamflowMeasurement)).append("\n");
        sb.append("    usgsMeasurement: ").append(toIndentedString(usgsMeasurement)).append("\n");
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
