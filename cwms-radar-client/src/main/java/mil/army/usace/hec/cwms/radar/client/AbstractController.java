/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.radar.client;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.net.ConnectException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

abstract class AbstractController {

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
        .configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);

    final <T> T extractValueFromBody(String context, OkHttpClient client, Request build, Class<T> type) throws IOException {
        try {
            Response execute = client.newCall(build).execute();
            execute.isSuccessful();
            int code = execute.code();
            if (code == 404) {
                ResponseBody body = execute.body();
                if (body == null) {
                    throw new NoDataFoundException("No data found for requested ID: " + context);
                }
                throw new NoDataFoundException("No data found for requested ID: " + context + "\n" + body.string());
            } else if (code != 200) {
                throw new IOException("Error from requested ID: " + context + " error was: \n" + code + " " + execute.message());
            }
            ResponseBody body = execute.body();
            if (body == null) {
                throw new IOException("Error with request, body not returned: " + build);
            }
            String string = body.string();
            return objectMapper.readValue(string, type);
        } catch (ConnectException connectException) {
            throw new ClientNotFoundException(connectException);
        }
    }
}
