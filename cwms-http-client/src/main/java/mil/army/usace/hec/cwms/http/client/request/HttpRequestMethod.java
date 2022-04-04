package mil.army.usace.hec.cwms.http.client.request;

public enum HttpRequestMethod {
    POST("POST"),
    GET("GET");

    private final String name;

    HttpRequestMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
