/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.radar.client.controllers;

import java.io.IOException;
import java.net.ConnectException;
import mil.army.usace.hec.cwms.radar.client.ClientNotFoundException;
import mil.army.usace.hec.cwms.radar.client.NoDataFoundException;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

abstract class AbstractController {

    final <T> T extractValueFromBody(String context, OkHttpClient client, Request build, Class<T> type) throws IOException {
        try (Response response = client.newCall(build).execute()) {
            response.isSuccessful();
            int code = response.code();
            if (code == 404) {
                try (ResponseBody body = response.body()) {
                    if (body == null) {
                        throw new NoDataFoundException("No data found for requested ID: " + context);
                    }
                    throw new NoDataFoundException("No data found for requested ID: " + context + "\n" + body.string());
                }
            } else if (code != 200) {
                throw new IOException("Error from requested ID: " + context + " error was: \n" + code + " " + response.message());
            }
            try (ResponseBody body = response.body()) {
                if (body == null) {
                    throw new IOException("Error with request, body not returned: " + build);
                }
                String string = body.string();
                return RadarObjectMapper.mapJsonToObject(string, type);
            }
        } catch (ConnectException connectException) {
            throw new ClientNotFoundException(connectException);
        }
    }
}
