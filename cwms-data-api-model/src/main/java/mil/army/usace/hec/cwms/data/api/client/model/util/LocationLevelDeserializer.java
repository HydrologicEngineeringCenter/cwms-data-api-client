/*
 * MIT License
 *
 * Copyright (c) 2025 Hydrologic Engineering Center
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

package mil.army.usace.hec.cwms.data.api.client.model.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import mil.army.usace.hec.cwms.data.api.client.model.ConstantLocationLevel;
import mil.army.usace.hec.cwms.data.api.client.model.LocationLevel;
import mil.army.usace.hec.cwms.data.api.client.model.SeasonalLocationLevel;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesLocationLevel;
import mil.army.usace.hec.cwms.data.api.client.model.VirtualLocationLevel;

public final class LocationLevelDeserializer extends JsonDeserializer<LocationLevel> {

    @Override
    public LocationLevel deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);
        if (node.has("constituents")) {
            return mapper.treeToValue(node, VirtualLocationLevel.class);
        } else if (node.has("constant-value")) {
            return mapper.treeToValue(node, ConstantLocationLevel.class);
        } else if (node.has("seasonal-time-series-id")) {
            return mapper.treeToValue(node, TimeSeriesLocationLevel.class);
        } else if (node.has("seasonal-values")) {
            return mapper.treeToValue(node, SeasonalLocationLevel.class);
        }

        throw new IllegalArgumentException("Unable to determine LocationLevel implementation type from JSON");
    }
}

