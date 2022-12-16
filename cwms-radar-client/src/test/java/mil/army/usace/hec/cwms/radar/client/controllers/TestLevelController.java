/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.radar.client.model.LocationLevel;
import mil.army.usace.hec.cwms.radar.client.model.LocationLevels;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
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

    @Test
    void testRetrieveLocationLevels() throws IOException {
        String resource = "radar/v2/json/location_levels.json";
        String collect = readJsonFile(resource);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LocationLevelEndpointInput.GetAll input = LocationLevelEndpointInput.getAll()
            .officeId("LRL");
        LocationLevels locationLevels = new LevelController().retrieveLocationLevels(buildConnectionInfo(), input);
        assertEquals(100, locationLevels.getPageSize());
        assertNotNull(locationLevels.getPage());
        assertNotNull(locationLevels.getNextPage());
        List<LocationLevel> levels = locationLevels.getLevels();
        assertFalse(levels.isEmpty());
        Optional<LocationLevel> level = levels.stream()
                                              .filter(s -> s.getLocationLevelId().equals("CarrCreek.Elev.Inst.0.Top of Flood"))
                                              .findAny();
        assertTrue(level.isPresent());
        assertEquals("LRL", level.get().getOfficeId());
        assertEquals("Top of Flood", level.get().getSpecifiedLevelId());
        assertEquals(LocationLevel.ParameterTypeIdEnum.INST, level.get().getParameterTypeId());
        assertEquals("Elev", level.get().getParameterId());
        assertEquals(321.564, level.get().getConstantValue());
        assertEquals("m", level.get().getLevelUnitsId());
        ZonedDateTime effectiveDate = ZonedDateTime.of(1900, 1, 1, 5, 0, 0, 0, ZoneId.of("UTC"));
        assertEquals(effectiveDate, level.get().getLevelDate());
        assertEquals("0", level.get().getDurationId());
    }

    @Test
    void testRetrieveLocationLevel() throws IOException {
        String resource = "radar/v2/json/location_level.json";
        String collect = readJsonFile(resource);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Instant instant = Instant.parse("1900-01-01T06:00:00Z");
        LocationLevelEndpointInput.GetOne input = LocationLevelEndpointInput.getOne("AARK.Elev.Inst.0.Bottom of Inlet", "SWT",
            instant);
        LocationLevel level = new LevelController().retrieveLocationLevel(buildConnectionInfo(), input);
        assertEquals("SWT", level.getOfficeId());
        assertEquals("Bottom of Inlet", level.getSpecifiedLevelId());
        assertEquals(LocationLevel.ParameterTypeIdEnum.INST, level.getParameterTypeId());
        assertEquals("Elev", level.getParameterId());
        assertEquals(145.6944, level.getConstantValue());
        assertEquals("m", level.getLevelUnitsId());
        assertEquals(instant, level.getLevelDate().toInstant());
        assertEquals("0", level.getDurationId());
    }

    @Test
    void testStoreLocationLevel() throws IOException {
        String resource = "radar/v2/json/location_level.json";
        String collect = readJsonFile(resource);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LocationLevel locationLevel = RadarObjectMapper.mapJsonToObject(collect, LocationLevel.class);
        LocationLevelEndpointInput.Post input = LocationLevelEndpointInput.post(locationLevel);
        assertDoesNotThrow(() -> new LevelController().storeLevel(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testPatchLocationLevel() throws IOException {
        String collect = readJsonFile("radar/v2/json/location_level.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(readJsonFile("radar/v1/json/level_rename_response.json"));
        mockHttpServer.start();
        LocationLevel locationLevel = RadarObjectMapper.mapJsonToObject(collect, LocationLevel.class);
        LocationLevelEndpointInput.Post input = LocationLevelEndpointInput.post(locationLevel);
        LevelController levelController = new LevelController();
        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo(cookieJarSupplier);
        levelController.storeLevel(apiConnectionInfo, input);
        String locationLevelId = locationLevel.getLocationLevelId();
        locationLevel.locationLevelId(locationLevelId + "Adam");
        LocationLevelEndpointInput.Patch patch = LocationLevelEndpointInput.patch(locationLevelId, locationLevel);
        levelController.updateLocationLevel(apiConnectionInfo, patch);
    }

    @Test
    void testDeleteLocationLevel() throws IOException {
        String resource = "radar/v2/json/location_level.json";
        String collect = readJsonFile(resource);
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LocationLevel locationLevel = RadarObjectMapper.mapJsonToObject(collect, LocationLevel.class);
        LocationLevelEndpointInput.Post input = LocationLevelEndpointInput.post(locationLevel);
        new LevelController().storeLevel(buildConnectionInfo(cookieJarSupplier), input);
        LocationLevelEndpointInput.Delete delete = LocationLevelEndpointInput.delete(locationLevel.getLocationLevelId())
            .officeId(locationLevel.getOfficeId())
            .cascadeDelete(false)
            .effectiveDate(locationLevel.getLevelDate().toInstant());
        assertDoesNotThrow(() -> new LevelController().deleteLevel(buildConnectionInfo(cookieJarSupplier), delete));
    }
}
