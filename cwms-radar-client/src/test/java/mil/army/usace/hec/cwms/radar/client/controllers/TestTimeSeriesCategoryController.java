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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.http.client.NoDataFoundException;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesCategory;
import org.junit.jupiter.api.Test;

class TestTimeSeriesCategoryController extends TestController {

    @Test
    void testRetrieveSpecificTimeSeriesCategory() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_category.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesCategoryEndpointInput input = new TimeSeriesCategoryEndpointInput("RDL_Aliases")
            .officeId("SWT");
        TimeSeriesCategory timeSeriesCategory = new TimeSeriesCategoryController().retrieveTimeSeriesCategory(buildConnectionInfo(), input);
        assertEquals("RDL_Aliases", timeSeriesCategory.getId());
        assertEquals("RDL_Aliases", timeSeriesCategory.getDescription());
        assertEquals("SWT", timeSeriesCategory.getOfficeId());
    }

    @Test
    void testRetrieveSpecificTimeSeriesCategoryNull() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_category_nodatafound.json");
        mockHttpServer.enqueue(404, collect);
        mockHttpServer.start();
        TimeSeriesCategoryEndpointInput input = new TimeSeriesCategoryEndpointInput()
            .officeId("SWT");
        TimeSeriesCategoryController controller = new TimeSeriesCategoryController();
        assertThrows(NoDataFoundException.class, () -> controller.retrieveTimeSeriesCategory(buildConnectionInfo(), input));
    }

    @Test
    void testRetrieveAllTimeSeriesCategories() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_categories.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesCategoryEndpointInput input = new TimeSeriesCategoryEndpointInput()
            .officeId("SWT");
        List<TimeSeriesCategory> timeSeriesCategories = new TimeSeriesCategoryController().retrieveTimeSeriesCategories(buildConnectionInfo(), input);
        assertEquals(4, timeSeriesCategories.size());
        TimeSeriesCategory timeSeriesCategory = timeSeriesCategories.get(0);
        assertEquals("Lakes", timeSeriesCategory.getId());
        assertNull(timeSeriesCategory.getDescription());
        assertEquals("SWT", timeSeriesCategory.getOfficeId());
    }
}
