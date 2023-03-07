/*
 * Copyright (c) 2023
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.Preferences;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import org.junit.jupiter.api.Test;

/**
 *
 */
final class TestCookieJarFactory {

    @Test
    void testFilteredCookieJarSupplier() throws Exception {

        MockHttpServer mockHttpServer = MockHttpServer.create();
        String body = readFile("success.json");
        List<String> setCookies = Arrays.asList("JSESSIONID=53693739C7450D5D5261ED35E2093458", "JSESSIONIDSSO=8AAF8621FD4748C050814BE6D6AFDAFC");
        mockHttpServer.enqueue(body, setCookies);
        mockHttpServer.start();
        String endpoint = "success";
        String baseUrl = String.format("http://localhost:%s", mockHttpServer.getPort());
        String sendToUrl = String.format("http://localhost:%s", mockHttpServer.getPort()) + "/" + "send-to";
        Preferences node = Preferences.userRoot().node("test").node("cwms").node("http_client");
        PreferencesBackedCookieStore preferencesBackedCookieStore = new PreferencesBackedCookieStore(node);
        CookieJarFactory.CookieJarSupplier cookieJarSupplier = CookieJarFactory.preferenceBackedCookieJar(preferencesBackedCookieStore);
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfoBuilder(baseUrl)
            .withCookieJarSupplier(cookieJarSupplier)
            .build();
        HttpRequestExecutor executer = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .get()
            .withMediaType("application/json");
        try (HttpRequestResponse response = executer.execute()) {
            List<Cookie> cookies = cookieJarSupplier.getCookieJar().loadForRequest(HttpUrl.get(sendToUrl));
            assertEquals("53693739C7450D5D5261ED35E2093458", cookies.stream().filter(c -> c.name().equals("JSESSIONID")).map(Cookie::value).findFirst().get());
            assertEquals("8AAF8621FD4748C050814BE6D6AFDAFC", cookies.stream().filter(c -> c.name().equals("JSESSIONIDSSO")).map(Cookie::value).findFirst().get());
        }
    }

    private String readFile(String filePath) throws IOException {
        URL resource = getClass().getClassLoader().getResource(filePath);
        if (resource == null) {
            throw new IOException("Resource not found: " + filePath);
        }
        Path path = new File(resource.getFile()).toPath();
        return String.join("\n", Files.readAllLines(path));
    }
}
