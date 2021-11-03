/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.time.Instant;
import mil.army.usace.hec.cwms.http.client.model.TimeSeries;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public final class TimeSeriesController {

    private final ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);

    public TimeSeries retrieveTimeSeries(HttpUrlProvider radarUrlProvider, String timeSeriesId,
                                         String officeId, String units, String datum,
                                         Instant start, Instant end, String page)
        throws IOException {
        OkHttpClient client = OkHttpUtil.getClient();
        HttpUrl httpUrl = radarUrlProvider.buildHttpUrl("/timeseries");
        HttpUrl.Builder builder = httpUrl.newBuilder()
            .addQueryParameter("name", timeSeriesId)
            .addQueryParameter("office", officeId)
            .addQueryParameter("unit", units)
            .addQueryParameter("datum", datum)
            .addQueryParameter("begin", start.toString())
            .addQueryParameter("end", end.toString())
            .addQueryParameter("page", page)
            .addQueryParameter("timezone", "UTC");
        HttpUrl url = builder.build();
        Request build = new Request.Builder()
            .url(url)
            .addHeader("accept", "application/json;version=2")
            .build();
        Response execute = client.newCall(build).execute();
        int code = execute.code();
        if (code != 200) {
            throw new IOException(
                "Error retrieving time series: " + timeSeriesId + " error was: \n" +
                    execute.body().string());
        }
        String string = execute.body().string();
        return objectMapper.readValue(string, TimeSeries.class);
    }
}
