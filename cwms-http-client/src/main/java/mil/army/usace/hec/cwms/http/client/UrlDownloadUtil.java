package mil.army.usace.hec.cwms.http.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class UrlDownloadUtil {

    private UrlDownloadUtil() {
        throw new AssertionError("Utility class for generating InputStream for given URL");
    }

    /**
     * Get Input Stream.
     *
     * @param url - url of file to generate input stream of
     * @return InputStream
     * @throws IOException - thrown if execution of request fails
     */
    public static InputStream getStream(URL url) throws IOException {
        Response response = getResponse(url);
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            throw new IllegalStateException("Response doesn't contain a file");
        }
        return responseBody.byteStream();
    }

    private static Response getResponse(URL url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        OkHttpClient client = OkHttpClientInstance.getInstance();
        return client.newCall(request).execute();
    }
}
