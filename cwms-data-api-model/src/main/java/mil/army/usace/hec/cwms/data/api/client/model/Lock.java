package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Lock
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-11-08T14:48:35.102974200-08:00[America/Los_Angeles]")
public final class Lock {

    @JsonProperty("project-id")
    private CwmsId projectId = null;

    @JsonProperty("location")
    private Location location = null;

    @JsonProperty("chamber-type")
    private LookupType chamberType = null;

    @JsonProperty("lock-width")
    private Double lockWidth = null;

    @JsonProperty("lock-length")
    private Double lockLength = null;

    @JsonProperty("normal-lock-lift")
    private Double normalLockLift = null;

    @JsonProperty("maximum-lock-lift")
    private Double maximumLockLift = null;

    @JsonProperty("length-units")
    private String lengthUnits = null;

    @JsonProperty("volume-units")
    private String volumeUnits = null;

    @JsonProperty("volume-per-lockage")
    private Double volumePerLockage = null;

    @JsonProperty("minimum-draft")
    private Double minimumDraft = null;

    @JsonProperty("high-water-upper-pool-location-level")
    private LockLocationLevelRef highWaterUpperPoolLocationLevel = null;

    @JsonProperty("low-water-lower-pool-location-level")
    private LockLocationLevelRef lowWaterLowerPoolLocationLevel = null;

    @JsonProperty("high-water-lower-pool-location-level")
    private LockLocationLevelRef highWaterLowerPoolLocationLevel = null;

    @JsonProperty("low-water-upper-pool-location-level")
    private LockLocationLevelRef lowWaterUpperPoolLocationLevel = null;

    @JsonProperty("high-water-upper-pool-warning-level")
    private Double highWaterUpperPoolWarningLevel = null;

    @JsonProperty("high-water-lower-pool-warning-level")
    private Double highWaterLowerPoolWarningLevel = null;

    @JsonProperty("elevation-units")
    private String elevationUnits = null;

    public Lock projectId(CwmsId projectId) {
        this.projectId = projectId;
        return this;
    }

    public CwmsId getProjectId() {
        return projectId;
    }

    public void setProjectId(CwmsId projectId) {
        this.projectId = projectId;
    }

    public Lock location(Location location) {
        this.location = location;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Lock chamberType(LookupType chamberType) {
        this.chamberType = chamberType;
        return this;
    }

    public LookupType getChamberType() {
        return chamberType;
    }

    public void setChamberType(LookupType chamberType) {
        this.chamberType = chamberType;
    }

    public Lock lockWidth(Double lockWidth) {
        this.lockWidth = lockWidth;
        return this;
    }

    public Double getLockWidth() {
        return lockWidth;
    }

    public void setLockWidth(Double lockWidth) {
        this.lockWidth = lockWidth;
    }

    public Lock lockLength(Double lockLength) {
        this.lockLength = lockLength;
        return this;
    }

    public Double getLockLength() {
        return lockLength;
    }

    public void setLockLength(Double lockLength) {
        this.lockLength = lockLength;
    }

    public Lock normalLockLift(Double normalLockLift) {
        this.normalLockLift = normalLockLift;
        return this;
    }

    public Double getNormalLockLift() {
        return normalLockLift;
    }

    public void setNormalLockLift(Double normalLockLift) {
        this.normalLockLift = normalLockLift;
    }

    public Lock maximumLockLift(Double maximumLockLift) {
        this.maximumLockLift = maximumLockLift;
        return this;
    }

    public Double getMaximumLockLift() {
        return maximumLockLift;
    }

    public void setMaximumLockLift(Double maximumLockLift) {
        this.maximumLockLift = maximumLockLift;
    }

    public Lock lengthUnits(String lengthUnits) {
        this.lengthUnits = lengthUnits;
        return this;
    }

    public String getLengthUnits() {
        return lengthUnits;
    }

    public void setLengthUnits(String lengthUnits) {
        this.lengthUnits = lengthUnits;
    }

    public Lock volumeUnits(String volumeUnits) {
        this.volumeUnits = volumeUnits;
        return this;
    }

    public String getVolumeUnits() {
        return volumeUnits;
    }

    public void setVolumeUnits(String volumeUnits) {
        this.volumeUnits = volumeUnits;
    }

    public Lock volumePerLockage(Double volumePerLockage) {
        this.volumePerLockage = volumePerLockage;
        return this;
    }

    public Double getVolumePerLockage() {
        return volumePerLockage;
    }

    public void setVolumePerLockage(Double volumePerLockage) {
        this.volumePerLockage = volumePerLockage;
    }

    public Lock minimumDraft(Double minimumDraft) {
        this.minimumDraft = minimumDraft;
        return this;
    }

    public Double getMinimumDraft() {
        return minimumDraft;
    }

    public void setMinimumDraft(Double minimumDraft) {
        this.minimumDraft = minimumDraft;
    }

    public Lock highWaterUpperPoolLocationLevel(LockLocationLevelRef highWaterUpperPoolLocationLevel) {
        this.highWaterUpperPoolLocationLevel = highWaterUpperPoolLocationLevel;
        return this;
    }

    public LockLocationLevelRef getHighWaterUpperPoolLocationLevel() {
        return highWaterUpperPoolLocationLevel;
    }

    public void setHighWaterUpperPoolLocationLevel(LockLocationLevelRef highWaterUpperPoolLocationLevel) {
        this.highWaterUpperPoolLocationLevel = highWaterUpperPoolLocationLevel;
    }

    public Lock lowWaterLowerPoolLocationLevel(LockLocationLevelRef lowWaterLowerPoolLocationLevel) {
        this.lowWaterLowerPoolLocationLevel = lowWaterLowerPoolLocationLevel;
        return this;
    }

    public LockLocationLevelRef getLowWaterLowerPoolLocationLevel() {
        return lowWaterLowerPoolLocationLevel;
    }

    public void setLowWaterLowerPoolLocationLevel(LockLocationLevelRef lowWaterLowerPoolLocationLevel) {
        this.lowWaterLowerPoolLocationLevel = lowWaterLowerPoolLocationLevel;
    }

    public Lock highWaterLowerPoolLocationLevel(LockLocationLevelRef highWaterLowerPoolLocationLevel) {
        this.highWaterLowerPoolLocationLevel = highWaterLowerPoolLocationLevel;
        return this;
    }

    public LockLocationLevelRef getHighWaterLowerPoolLocationLevel() {
        return highWaterLowerPoolLocationLevel;
    }

    public void setHighWaterLowerPoolLocationLevel(LockLocationLevelRef highWaterLowerPoolLocationLevel) {
        this.highWaterLowerPoolLocationLevel = highWaterLowerPoolLocationLevel;
    }

    public Lock lowWaterUpperPoolLocationLevel(LockLocationLevelRef lowWaterUpperPoolLocationLevel) {
        this.lowWaterUpperPoolLocationLevel = lowWaterUpperPoolLocationLevel;
        return this;
    }

    public LockLocationLevelRef getLowWaterUpperPoolLocationLevel() {
        return lowWaterUpperPoolLocationLevel;
    }

    public void setLowWaterUpperPoolLocationLevel(LockLocationLevelRef lowWaterUpperPoolLocationLevel) {
        this.lowWaterUpperPoolLocationLevel = lowWaterUpperPoolLocationLevel;
    }

    public Lock highWaterUpperPoolWarningLevel(Double highWaterUpperPoolWarningLevel) {
        this.highWaterUpperPoolWarningLevel = highWaterUpperPoolWarningLevel;
        return this;
    }

    public Double getHighWaterUpperPoolWarningLevel() {
        return highWaterUpperPoolWarningLevel;
    }

    public void setHighWaterUpperPoolWarningLevel(Double highWaterUpperPoolWarningLevel) {
        this.highWaterUpperPoolWarningLevel = highWaterUpperPoolWarningLevel;
    }

    public Lock highWaterLowerPoolWarningLevel(Double highWaterLowerPoolWarningLevel) {
        this.highWaterLowerPoolWarningLevel = highWaterLowerPoolWarningLevel;
        return this;
    }

    public Double getHighWaterLowerPoolWarningLevel() {
        return highWaterLowerPoolWarningLevel;
    }

    public void setHighWaterLowerPoolWarningLevel(Double highWaterLowerPoolWarningLevel) {
        this.highWaterLowerPoolWarningLevel = highWaterLowerPoolWarningLevel;
    }

    public Lock elevationUnits(String elevationUnits) {
        this.elevationUnits = elevationUnits;
        return this;
    }

    public String getElevationUnits() {
        return elevationUnits;
    }

    public void setElevationUnits(String elevationUnits) {
        this.elevationUnits = elevationUnits;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lock lock = (Lock) o;
        return Objects.equals(this.projectId, lock.projectId)
                && Objects.equals(this.location, lock.location)
                && Objects.equals(this.chamberType, lock.chamberType)
                && Objects.equals(this.lockWidth, lock.lockWidth)
                && Objects.equals(this.lockLength, lock.lockLength)
                && Objects.equals(this.normalLockLift, lock.normalLockLift)
                && Objects.equals(this.maximumLockLift, lock.maximumLockLift)
                && this.lengthUnits == null || lock.lengthUnits == null
                ? Objects.equals(this.lengthUnits, lock.lengthUnits)
                : this.lengthUnits.equalsIgnoreCase(lock.lengthUnits)
                && this.volumeUnits == null || lock.volumeUnits == null
                ? Objects.equals(this.volumeUnits, lock.volumeUnits)
                : this.volumeUnits.equalsIgnoreCase(lock.volumeUnits)
                && Objects.equals(this.volumePerLockage, lock.volumePerLockage)
                && Objects.equals(this.minimumDraft, lock.minimumDraft)
                && Objects.equals(this.highWaterUpperPoolLocationLevel, lock.highWaterUpperPoolLocationLevel)
                && Objects.equals(this.lowWaterLowerPoolLocationLevel, lock.lowWaterLowerPoolLocationLevel)
                && Objects.equals(this.highWaterLowerPoolLocationLevel, lock.highWaterLowerPoolLocationLevel)
                && Objects.equals(this.lowWaterUpperPoolLocationLevel, lock.lowWaterUpperPoolLocationLevel)
                && Objects.equals(this.highWaterUpperPoolWarningLevel, lock.highWaterUpperPoolWarningLevel)
                && Objects.equals(this.highWaterLowerPoolWarningLevel, lock.highWaterLowerPoolWarningLevel)
                && this.elevationUnits == null || lock.elevationUnits == null
                ? Objects.equals(this.elevationUnits, lock.elevationUnits)
                : this.elevationUnits.equalsIgnoreCase(lock.elevationUnits)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, location, chamberType, lockWidth, lockLength, normalLockLift, maximumLockLift,
                lengthUnits == null ? 0 : lengthUnits.toLowerCase(),
                volumeUnits == null ? 0 : volumeUnits.toLowerCase(),
                volumePerLockage, minimumDraft, highWaterUpperPoolLocationLevel, lowWaterLowerPoolLocationLevel,
                highWaterLowerPoolLocationLevel, lowWaterUpperPoolLocationLevel, highWaterUpperPoolWarningLevel,
                highWaterLowerPoolWarningLevel, elevationUnits == null ? 0 : elevationUnits.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Lock {\n");

        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    location: ").append(toIndentedString(location)).append("\n");
        sb.append("    chamberType: ").append(toIndentedString(chamberType)).append("\n");
        sb.append("    lockWidth: ").append(toIndentedString(lockWidth)).append("\n");
        sb.append("    lockLength: ").append(toIndentedString(lockLength)).append("\n");
        sb.append("    normalLockLift: ").append(toIndentedString(normalLockLift)).append("\n");
        sb.append("    maximumLockLift: ").append(toIndentedString(maximumLockLift)).append("\n");
        sb.append("    lengthUnits: ").append(toIndentedString(lengthUnits)).append("\n");
        sb.append("    volumeUnits: ").append(toIndentedString(volumeUnits)).append("\n");
        sb.append("    volumePerLockage: ").append(toIndentedString(volumePerLockage)).append("\n");
        sb.append("    minimumDraft: ").append(toIndentedString(minimumDraft)).append("\n");
        sb.append("    highWaterUpperPoolLocationLevel: ").append(toIndentedString(highWaterUpperPoolLocationLevel)).append("\n");
        sb.append("    lowWaterLowerPoolLocationLevel: ").append(toIndentedString(lowWaterLowerPoolLocationLevel)).append("\n");
        sb.append("    highWaterLowerPoolLocationLevel: ").append(toIndentedString(highWaterLowerPoolLocationLevel)).append("\n");
        sb.append("    lowWaterUpperPoolLocationLevel: ").append(toIndentedString(lowWaterUpperPoolLocationLevel)).append("\n");
        sb.append("    highWaterUpperPoolWarningLevel: ").append(toIndentedString(highWaterUpperPoolWarningLevel)).append("\n");
        sb.append("    highWaterLowerPoolWarningLevel: ").append(toIndentedString(highWaterLowerPoolWarningLevel)).append("\n");
        sb.append("    elevationUnits: ").append(toIndentedString(elevationUnits)).append("\n");
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
