/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.radar.client;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.OkHttpUtil;
import mil.army.usace.hec.cwms.radar.client.model.LocationsCatalog;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesCatalog;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public final class CatalogController extends AbstractController {

    public TimeSeriesCatalog retrieveTimeSeriesCatalog(HttpUrlProvider radarUrlProvider, String officeId, String unitSystem, String cursor)
        throws IOException {
        OkHttpClient client = OkHttpUtil.getClient();
        HttpUrl httpUrl = radarUrlProvider.buildHttpUrl("/catalog/timeseries")
            .newBuilder()
            .addQueryParameter("office", officeId)
            .addQueryParameter("unitSystem", unitSystem)
            .addQueryParameter("cursor", cursor)
            .build();
        Request build = new Request.Builder()
            .url(httpUrl)
            .addHeader("accept", "application/json;version=2")
            .build();
        return extractValueFromBody(officeId, client, build, TimeSeriesCatalog.class);
    }

    public LocationsCatalog retrieveLocationsCatalog(HttpUrlProvider radarUrlProvider, String officeId, String unitSystem, String cursor)
        throws IOException {
        OkHttpClient client = OkHttpUtil.getClient();
        HttpUrl httpUrl = radarUrlProvider.buildHttpUrl("/catalog/locations")
            .newBuilder()
            .addQueryParameter("office", officeId)
            .addQueryParameter("unitSystem", unitSystem)
            .addQueryParameter("cursor", cursor)
            .build();
        Request build = new Request.Builder()
            .url(httpUrl)
            .addHeader("accept", "application/json;version=2")
            .build();
        return extractValueFromBody(officeId, client, build, LocationsCatalog.class);
    }
}
