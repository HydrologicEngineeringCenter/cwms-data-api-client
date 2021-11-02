/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

class TestOkHttpUtil {

    private static final String BASE_URL = "http://localhost:11524";

    @Test
    void testGoogle() throws IOException {
        OkHttpClient client = OkHttpUtil.getClient();
        String url = HttpUrl.parse("https://www.google.com").newBuilder().build().toString();
        Request build = new Request.Builder()
            .url(url)
            .build();
        Response execute = client.newCall(build).execute();
        assertEquals(200, execute.code());
    }
}
