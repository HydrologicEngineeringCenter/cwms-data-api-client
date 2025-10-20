package mil.army.usace.hec.cwms.http.client.request;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class to support creation and query of URL query parameters
 */
public class QueryParameters {
    
    private Map<String, List<String>> parameters = new HashMap<>();



    private QueryParameters() {

    }

    /**
     * Retrieve Parameter list
     * @param parameter
     * @return List of parameters. If no values are set for the parameter an empty list is returned.
     */
    public List<String> get(String parameter) {
        List<String> values = parameters.computeIfAbsent(parameter, p -> new ArrayList<>());
        return values;
    }

    /**
     * Add an additional value to the parameter
     * @param parameter
     * @param value
     * @return this instance to allow for fluent syntax operations
     */
    public QueryParameters add(String parameter, String value) {
        List<String> values = parameters.computeIfAbsent(parameter, p -> new ArrayList<>());
        values.add(value);
        return this;
    }

    /**
     * Set given parameter to only the given value. Any previously stored values are lost.
     * @param parameter
     * @param value
     * @return this instance to allow for fluent syntax operations
     */
    public QueryParameters set(String parameter, String value) {
        List<String> values = new ArrayList<>();
        values.add(value);
        parameters.put(parameter, values);
        return this;
    }

    /**
     * URL encoded query parameters suitable for appending to query. Does not include the initial ?
     * @return
     */
    public String encode() {
        final ArrayList<String> sets = new ArrayList<>();
        parameters.forEach((k,v) -> {
            ArrayList<String> pairs = new ArrayList<>();
            for (String value: v) {
                pairs.add(String.format("%s=%s",
                                        URLEncoder.encode(k, StandardCharsets.UTF_8),
                                        URLEncoder.encode(value, StandardCharsets.UTF_8)));
            }
            sets.add(String.join("&", pairs));
        });

        return String.join("&", sets);
    }

    /**
     * Given a URL query string, create a QueryParameters object for additional processing.
     * @param query
     * @return
     */
    public static QueryParameters parse(String query) {
        QueryParameters parameters = new QueryParameters();
        for (String pair: query.split("&")) {
            String[] kv = pair.split("=");
            if (kv[0].trim().isEmpty()) {
                continue;
            }
            String parameter = URLDecoder.decode(kv[0], StandardCharsets.UTF_8);
            String value = kv.length > 1 ? URLDecoder.decode(kv[1], StandardCharsets.UTF_8) : null;
            parameters.parameters.computeIfAbsent(parameter, p -> new ArrayList<>()).add(value);
        }
        return parameters;
    }

    /**
     * Create a new QueryParameters object.
     * @return
     */
    public static QueryParameters empty() {
        return parse("");
    }
}
