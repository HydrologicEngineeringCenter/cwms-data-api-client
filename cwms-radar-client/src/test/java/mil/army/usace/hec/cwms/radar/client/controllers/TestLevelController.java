/*
 * MIT License
 *
 * Copyright (c) 2021 Hydrologic Engineering Center
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

package mil.army.usace.hec.cwms.radar.client.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import mil.army.usace.hec.cwms.radar.client.model.SpecifiedLevel;
import org.junit.jupiter.api.Test;

class TestLevelController extends TestController {

    @Test
    void testRetrieveSpecifiedLevels() throws IOException {
        String resource = "radar/v2/json/specified_levels.json";
        String collect = readJsonFile(resource);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        SpecifiedLevelEndpointInput input = new SpecifiedLevelEndpointInput()
            .officeId("CWMS");
        Set<SpecifiedLevel> specifiedLevels = new LevelController().retrieveSpecifiedLevels(buildConnectionInfo(), input);
        assertNotNull(specifiedLevels);
        Optional<SpecifiedLevel> specifiedLevel = specifiedLevels.stream()
                                                                 .filter(s -> s.getId().equals("Bottom of Exclusive Flood Control"))
                                                                 .findAny();
        assertTrue(specifiedLevel.isPresent());
        assertEquals("CWMS", specifiedLevel.get().getOfficeId());
        assertEquals("Bottom of Exclusive Flood Control Level", specifiedLevel.get().getDescription());
    }
}
