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
package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.radar.client.model.Measurement;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class TestMeasurementController extends TestController {

    @Test
    void testRetrieveMeasurements() throws IOException {
        String collect = readJsonFile("radar/v1/json/measurements.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();

        MeasurementEndpointInput.GetAll input = MeasurementEndpointInput.getAll()
                .withOfficeIdMask("SPK")
                .withLocationIdMask("StreamLoc321")
                .withBegin(Instant.parse("2024-09-16T00:00:00Z"))
                .withEnd(Instant.parse("2024-09-17T12:00:00Z"));

        MeasurementController controller = new MeasurementController();
        List<Measurement> measurements = controller.retrieveMeasurements(buildConnectionInfo(), input);

        assertNotNull(measurements, "Measurements list should not be null");
        assertFalse(measurements.isEmpty(), "Measurements list should not be empty");
        assertEquals(2, measurements.size(), "There should be exactly 2 measurements");

        Measurement first = measurements.get(0);
        assertEquals("ft", first.getHeightUnit(), "Height unit should be 'ft'");
        assertEquals("cfs", first.getFlowUnit(), "Flow unit should be 'cfs'");
        assertEquals("F", first.getTempUnit(), "Temperature unit should be 'F'");
        assertEquals("fps", first.getVelocityUnit(), "Velocity unit should be 'fps'");
        assertEquals("ft2", first.getAreaUnit(), "Area unit should be 'ft2'");
        assertTrue(first.isUsed(), "First measurement should be marked as used");
        assertEquals("USGS", first.getAgency(), "Agency should be 'USGS'");
        assertEquals("SPK", first.getParty(), "Party should be 'SPK'");
        assertEquals("Measurement made during normal flow conditions.", first.getWmComments(), "WM comments mismatch");
        assertEquals(Instant.parse("2024-09-16T00:00:00Z"), first.getInstant(), "Instant mismatch");
        assertEquals("123456", first.getNumber(), "Measurement number mismatch");
        assertEquals("StreamLoc321", first.getId().getName(), "Location ID mismatch");
        assertEquals("SPK", first.getId().getOfficeId(), "Office ID mismatch");

        Measurement second = measurements.get(1);
        assertEquals("ft", second.getHeightUnit(), "Height unit should be 'ft'");
        assertEquals("cfs", second.getFlowUnit(), "Flow unit should be 'cfs'");
        assertEquals("F", second.getTempUnit(), "Temperature unit should be 'F'");
        assertEquals("fps", second.getVelocityUnit(), "Velocity unit should be 'fps'");
        assertEquals("ft2", second.getAreaUnit(), "Area unit should be 'ft2'");
        assertTrue(second.isUsed(), "Second measurement should be marked as used");
        assertEquals("USGS", second.getAgency(), "Agency should be 'USGS'");
        assertEquals("SPK", second.getParty(), "Party should be 'SPK'");
        assertEquals("Measurement made after recent rainfall.", second.getWmComments(), "WM comments mismatch");
        assertEquals(Instant.parse("2024-09-17T12:00:00Z"), second.getInstant(), "Instant mismatch");
        assertEquals("654321", second.getNumber(), "Measurement number mismatch");
        assertEquals("StreamLoc321", second.getId().getName(), "Location ID mismatch");
        assertEquals("SPK", second.getId().getOfficeId(), "Office ID mismatch");
    }

    @Test
    void testStoreMeasurement() throws IOException {
        Logger.getLogger("").setLevel(Level.ALL);
        String collect = readJsonFile("radar/v1/json/measurements.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();

        List<Measurement> measurements = RadarObjectMapper.mapJsonToListOfObjects(collect, Measurement.class);

        MeasurementController controller = new MeasurementController();

        MeasurementEndpointInput.Post input = MeasurementEndpointInput.post(measurements);

        assertDoesNotThrow(() -> controller.storeMeasurements(buildConnectionInfo(cookieJarSupplier), input),
                "Storing measurement should not throw an exception");
    }

    @Test
    void testDeleteMeasurement() throws IOException {
        String collect = readJsonFile("radar/v1/json/measurement.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect); // Adjust if a different response is expected for DELETE
        mockHttpServer.start();

        List<Measurement> measurements = RadarObjectMapper.mapJsonToListOfObjects(collect, Measurement.class);
        Measurement measurement = measurements.get(0);

        MeasurementController controller = new MeasurementController();

        MeasurementEndpointInput.Post postInput = MeasurementEndpointInput.post(measurements);
        controller.storeMeasurements(buildConnectionInfo(cookieJarSupplier), postInput);

        String officeId = measurement.getId().getOfficeId();
        String locationId = measurement.getId().getName();
        Instant begin = measurement.getInstant(); // Assuming begin is the instant of the measurement
        Instant end = measurement.getInstant();   // Assuming end is the same for single measurement

        MeasurementEndpointInput.Delete deleteInput = MeasurementEndpointInput.delete(officeId, locationId, begin, end);

        assertDoesNotThrow(() -> controller.deleteMeasurement(buildConnectionInfo(cookieJarSupplier), deleteInput),
                "Deleting measurement should not throw an exception");
    }
}
