package hec.army.usace.hec.cwbi.auth.http.client;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

final class OAuth2ObjectMapper {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
        .configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true)
        .configure(JsonReadFeature.ALLOW_MISSING_VALUES.mappedFeature(), true);

    private OAuth2ObjectMapper() {
        throw new AssertionError("Utility class");
    }

    static <T> T mapJsonToObject(String json, Class<T> classObject) throws IOException {
        return OBJECT_MAPPER.readValue(json, classObject);
    }

}
