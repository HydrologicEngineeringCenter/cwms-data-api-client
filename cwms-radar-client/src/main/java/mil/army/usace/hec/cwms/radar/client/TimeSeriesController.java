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
import java.time.Instant;
import mil.army.usace.hec.cwms.http.client.OkHttpUtil;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeries;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public final class TimeSeriesController {

    private final ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);

    public TimeSeries retrieveTimeSeries(HttpUrlProvider radarUrlProvider, String timeSeriesId,
                                         String officeId, String units, String datum,
                                         Instant start, Instant end, String page)
        throws IOException {
        OkHttpClient client = OkHttpUtil.getClient();
        HttpUrl httpUrl = radarUrlProvider.buildHttpUrl("/timeseries")
            .newBuilder()
            .addQueryParameter("name", timeSeriesId)
            .addQueryParameter("office", officeId)
            .addQueryParameter("unit", units)
            .addQueryParameter("datum", datum)
            .addQueryParameter("begin", start.toString())
            .addQueryParameter("end", end.toString())
            .addQueryParameter("page", page)
            .addQueryParameter("timezone", "UTC")
            .build();
        Request build = new Request.Builder()
            .url(httpUrl)
            .addHeader("accept", "application/json;version=2")
            .build();
        return extractValueFromBody(timeSeriesId, client, build, TimeSeries.class);
    }

    private <T> T extractValueFromBody(String timeSeriesId, OkHttpClient client, Request build, Class<T> type) throws IOException {
        try {
            Response execute = client.newCall(build).execute();
            execute.isSuccessful();
            int code = execute.code();
            if (code == 404) {
                ResponseBody body = execute.body();
                if (body == null) {
                    throw new NoDataFoundException("No data found for requested ID: " + timeSeriesId);
                }
                throw new NoDataFoundException("No data found for requested ID: " + timeSeriesId + "\n" + body.string());
            } else if (code != 200) {
                throw new IOException(
                    "Error retrieving time series: " + timeSeriesId + " error was: \n" + code + " " + execute.message());
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
