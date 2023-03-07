package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets DeleteMethod
 */
public enum DeleteMethod {
    ALL("DELETE_ALL"), KEY("DELETE_KEY"), DATA("DELETE_DATA");

    private String value;

    DeleteMethod(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static DeleteMethod fromValue(String text) {
        for (DeleteMethod b : DeleteMethod.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}
