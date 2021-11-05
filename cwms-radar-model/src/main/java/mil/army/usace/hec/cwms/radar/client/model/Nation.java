package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Nation {
    US("US"),

    CANADA("CANADA"),

    MEXICO("MEXICO");

    private final String value;

    Nation(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Nation fromValue(String text) {
        for (Nation b : Nation.values()) {
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
