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

import java.io.IOException;
import java.net.ConnectException;
import java.time.Instant;
import mil.army.usace.hec.cwms.http.client.OkHttpUtil;
import mil.army.usace.hec.cwms.radar.client.HttpUrlProvider;
import mil.army.usace.hec.cwms.radar.client.NoDataFoundException;
import mil.army.usace.hec.cwms.radar.client.ServerNotFoundException;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeries;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public final class TimeSeriesController {

    public TimeSeries retrieveTimeSeries(HttpUrlProvider radarUrlProvider, String officeId, String timeSeriesId, String units, String datum,
                                         Instant start, Instant end, String page) throws IOException {
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
            if (execute.isSuccessful()) {
                try (ResponseBody body = execute.body()) {
                    if (body == null) {
                        throw new IOException("Error with request, body not returned: " + build);
                    }
                    String string = body.string();
                    return RadarObjectMapper.mapJsonToObject(string, type);
                }
            } else {
                int code = execute.code();
                if (code == 404) {
                    ResponseBody body = execute.body();
                    if (body == null) {
                        throw new NoDataFoundException("No data found for requested ID: " + timeSeriesId);
                    }
                    throw new NoDataFoundException("No data found for requested ID: " + timeSeriesId + "\n" + body.string());
                } else {
                    throw new IOException(
                        "Error retrieving time series: " + timeSeriesId + " error was: \n" + code + " " + execute.message());
                }
            }
        } catch (ConnectException connectException) {
            throw new ServerNotFoundException(connectException);
        }
    }
}
