package mil.army.usace.hec.cwms.radar.client;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import mil.army.usace.hec.cwms.http.client.OkHttpUtil;
import mil.army.usace.hec.cwms.radar.client.model.Location;
import okhttp3.*;

import java.io.IOException;
import java.net.ConnectException;

public class LocationController {
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);

    public Location retrieveLocation(HttpUrlProvider radarUrlProvider, String locationName, String officeId, String units) throws IOException {
        OkHttpClient client = OkHttpUtil.getClient();
        HttpUrl httpUrl = radarUrlProvider.buildHttpUrl("/locations")
                .newBuilder()
                .addQueryParameter("name", locationName)
                .addQueryParameter("office", officeId)
                .addQueryParameter("unit", units)
                .build();
        Request build = new Request.Builder()
                .url(httpUrl)
                .addHeader("accept", "application/json;version=2")
                .build();
        return extractValueFromBody(locationName, client, build);
    }

    private Location extractValueFromBody(String locationName, OkHttpClient client, Request build) throws IOException{
        try {
            Response execute = client.newCall(build).execute();
            execute.isSuccessful();
            int code = execute.code();
            if (code == 404) {
                ResponseBody body = execute.body();
                if (body == null) {
                    throw new NoDataFoundException("No data found for requested Location: " + locationName);
                }
                throw new NoDataFoundException("No data found for requested Location: " + locationName + "\n" + body.string());
            } else if (code != 200) {
                throw new IOException(
                        "Error retrieving Location: " + locationName + " error was: \n" + code + " " + execute.message());
            }
            ResponseBody body = execute.body();
            if (body == null) {
                throw new IOException("Error with request, body not returned: " + build);
            }
            String string = body.string();
            return objectMapper.readValue(string, Location.class);
        } catch (ConnectException connectException) {
            throw new ClientNotFoundException(connectException);
        }
    }
}
