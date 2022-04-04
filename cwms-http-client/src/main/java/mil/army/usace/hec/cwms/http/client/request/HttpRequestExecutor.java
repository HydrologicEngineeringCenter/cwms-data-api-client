package mil.army.usace.hec.cwms.http.client.request;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;

public interface HttpRequestExecutor {

    HttpRequestResponse execute() throws IOException;

}
