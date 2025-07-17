/*
 * MIT License
 *
 * Copyright (c) 2023 Hydrologic Engineering Center
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

package mil.army.usace.hec.cwms.data.api.client.controllers;

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.data.api.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.data.api.client.model.ParameterSpec;
import mil.army.usace.hec.cwms.data.api.client.model.RatingTemplate;
import mil.army.usace.hec.cwms.data.api.client.model.RatingTemplates;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestRatingTemplateController extends TestController {

    @Test
    void testRetrieveRatingTemplates() throws IOException {
        String collect = readJsonFile("radar/v2/json/rating_templates.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        RatingTemplateController controller = new RatingTemplateController();

        RatingTemplateEndpointInput.GetAll input = RatingTemplateEndpointInput.getAll()
                .pageSize(2)
                .officeId("LRL");

        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        RatingTemplates templates = controller.retrieveRatingTemplates(apiConnectionInfo, input);
        assertNotNull(templates);
        assertEquals(2, templates.getPageSize());
        assertFalse(templates.getTemplates().isEmpty());
        assertNotNull(templates.getPage());
        assertNotNull(templates.getNextPage());
        RatingTemplate ratingTemplate = templates.getTemplates().get(0);
        assertEquals("Stage;Stage.USGS-CORR", ratingTemplate.getId());
        assertEquals("USGS-CORR", ratingTemplate.getVersion());
        assertEquals("Stream Stage Correction Rating", ratingTemplate.getDescription());
        assertEquals("LRL", ratingTemplate.getOfficeId());
        assertFalse(ratingTemplate.getRatingIds().isEmpty());
        assertEquals("Stage", ratingTemplate.getDependentParameter());
        List<ParameterSpec> independentParameterSpecs = ratingTemplate.getIndependentParameterSpecs();
        assertEquals(1, independentParameterSpecs.size());
        ParameterSpec parameterSpec = independentParameterSpecs.get(0);
        assertEquals("Stage", parameterSpec.getParameter());
        assertEquals("ERROR", parameterSpec.getInRangeMethod());
        assertEquals("ERROR", parameterSpec.getOutRangeHighMethod());
        assertEquals("LINEAR", parameterSpec.getOutRangeLowMethod());

    }

    @Test
    void testRetrieveRatingTemplate() throws IOException {
        String collect = readJsonFile("radar/v2/json/rating_template.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        RatingTemplateController controller = new RatingTemplateController();

        RatingTemplateEndpointInput.GetOne input = RatingTemplateEndpointInput.getOne("Stage;Stage.USGS-CORR", "LRL");

        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        RatingTemplate ratingTemplate = controller.retrieveRatingTemplate(apiConnectionInfo, input);
        assertNotNull(ratingTemplate);
        assertEquals("Stage;Stage.USGS-CORR", ratingTemplate.getId());
        assertEquals("USGS-CORR", ratingTemplate.getVersion());
        assertEquals("Stream Stage Correction Rating", ratingTemplate.getDescription());
        assertEquals("LRL", ratingTemplate.getOfficeId());
        assertFalse(ratingTemplate.getRatingIds().isEmpty());
        assertEquals("Stage", ratingTemplate.getDependentParameter());
        List<ParameterSpec> independentParameterSpecs = ratingTemplate.getIndependentParameterSpecs();
        assertEquals(1, independentParameterSpecs.size());
        ParameterSpec parameterSpec = independentParameterSpecs.get(0);
        assertEquals("Stage", parameterSpec.getParameter());
        assertEquals("ERROR", parameterSpec.getInRangeMethod());
        assertEquals("ERROR", parameterSpec.getOutRangeHighMethod());
        assertEquals("LINEAR", parameterSpec.getOutRangeLowMethod());
    }

    @Test
    void testStore() throws IOException {
        String collect = readJsonFile("radar/v2/json/rating_template.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        RatingTemplateController controller = new RatingTemplateController();

        RatingTemplateEndpointInput.Post input = RatingTemplateEndpointInput.post("xml");

        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        assertDoesNotThrow(() -> controller.storeRatingTemplate(apiConnectionInfo, input));
    }

    @Test
    void testDelete() throws IOException {
        String collect = readJsonFile("radar/v2/json/rating_template.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        RatingTemplateController controller = new RatingTemplateController();

        RatingTemplateEndpointInput.Delete input = RatingTemplateEndpointInput.delete("Stage;Stage.USGS-CORR", "LRL", DeleteMethod.ALL);
        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        assertDoesNotThrow(() -> controller.deleteRatingTemplate(apiConnectionInfo, input));
    }

}
