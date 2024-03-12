package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Version type specifies the type of timeseries response to be received. Can be max aggregate or single version. Max aggregate cannot be run if version date field is specified.
 */
public enum VersionType {
    MAX_AGGREGATE("MAX_AGGREGATE"),
    SINGLE_VERSION("SINGLE_VERSION"),
    UNVERSIONED("UNVERSIONED"),
    UNDEF("UNDEF");

    private String value;

    VersionType(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static VersionType fromValue(String text) {
        for (VersionType b : VersionType.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}
