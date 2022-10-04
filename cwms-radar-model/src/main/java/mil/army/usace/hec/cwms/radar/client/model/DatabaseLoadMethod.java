package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets DatabaseLoadMethod
 */
public enum DatabaseLoadMethod {
    EAGER("EAGER"),
    LAZY("LAZY"),
    REFERENCE("REFERENCE");

    private final String value;

    DatabaseLoadMethod(String value) {
        this.value = value;
    }

    @JsonCreator
    public static DatabaseLoadMethod fromValue(String text) {
        for (DatabaseLoadMethod b : DatabaseLoadMethod.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }
}
