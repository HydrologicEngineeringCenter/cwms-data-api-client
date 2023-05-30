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

package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.RatingSpec;
import mil.army.usace.hec.cwms.radar.client.model.RatingSpecs;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestRatingSpecController extends TestController {

    @Test
    void testRetrieveRatingSpecs() throws IOException {
        String collect = readJsonFile("radar/v2/json/rating_specs.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        RatingSpecController controller = new RatingSpecController();

        RatingSpecEndpointInput.GetAll input = RatingSpecEndpointInput.getAll().officeId("SWT").ratingIdMask("BUFF.Stage;Flow.WCDS.Production");

        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        RatingSpecs specs = controller.retrieveRatingSpecs(apiConnectionInfo, input);
        assertNotNull(specs);
    }

    @Test
    void testRetrieveRatingSpec() throws IOException {
        String collect = readJsonFile("radar/v2/json/rating_spec.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        RatingSpecController controller = new RatingSpecController();

        RatingSpecEndpointInput.GetOne input = RatingSpecEndpointInput.getOne("BUFF.Stage;Flow.WCDS.Production", "SWT");

        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        RatingSpec spec = controller.retrieveRatingSpec(apiConnectionInfo, input);
        assertNotNull(spec);
    }

    @Test
    void testPost() throws IOException {
        String collect = readJsonFile("radar/v2/json/rating_spec.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        RatingSpecController controller = new RatingSpecController();

        RatingSpecEndpointInput.Post input = RatingSpecEndpointInput.post("xml");

        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        assertDoesNotThrow(() -> controller.storeRatingSpec(apiConnectionInfo, input));
    }

    @Test
    void testDelete() throws IOException {
        String collect = readJsonFile("radar/v2/json/rating_spec.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        RatingSpecController controller = new RatingSpecController();

        RatingSpecEndpointInput.Delete input = RatingSpecEndpointInput.delete("BUFF.Stage;Flow.WCDS.Production", "SWT", DeleteMethod.ALL);

        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        assertDoesNotThrow(() -> controller.deleteRatingSpec(apiConnectionInfo, input));
    }

}
