/*
 * MIT License
 *
 * Copyright (c) 2024 Hydrologic Engineering Center
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 *
 */
public final class RadarObjectMapper {

    private static final ObjectMapper OBJECT_MAPPER =
            new ObjectMapper().registerModule(new JavaTimeModule())
                    .configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true)
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                    .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                    .configure(JsonReadFeature.ALLOW_MISSING_VALUES.mappedFeature(), true);

    private static final XmlMapper XML_MAPPER = new XmlMapper();

    private RadarObjectMapper() {
        throw new AssertionError("Utility class");
    }

    public static <T> T mapJsonToObject(String json, Class<T> classObject) throws IOException {
        return OBJECT_MAPPER.readValue(json, classObject);
    }

    public static <T> String mapObjectToJson(T object) throws IOException {
        return OBJECT_MAPPER.writeValueAsString(object);
    }

    public static <T> List<T> mapJsonToListOfObjects(String json, Class<T> classObject) throws IOException {
        return OBJECT_MAPPER.readValue(json, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, classObject));
    }

    public static <T> List<T> mapJsonToListOfObjects(String json, Class<T> classObject, String... path) throws IOException {
        JsonNode node = OBJECT_MAPPER.readTree(json);
        for (String pathNode : path) {
            node = node.path(pathNode);
        }
        return OBJECT_MAPPER.readValue(node.toString(), OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, classObject));
    }

    public static <T> List<T> mapXmlToListOfObjects(String json, Class<T> classObject, String path) throws IOException {
        JsonNode value = XML_MAPPER.readTree(json).findValue(path);
        return mapJsonToListOfObjects(value.toString(), classObject);
    }

    public static <T> Set<T> mapJsonToSetOfObjects(String json, Class<T> classObject) throws IOException {
        return OBJECT_MAPPER.readValue(json, OBJECT_MAPPER.getTypeFactory().constructCollectionType(Set.class, classObject));
    }
}
