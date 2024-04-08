package mil.army.usace.hec.cwms.http.client;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestApiConnectionFactory {

    @Test
    void testApiConnectionFactory() {
        String root = "http://localhost:11524/cwms-data/";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfoBuilder(root).build();

        assertEquals(root, apiConnectionInfo.getApiRoot());
        assertEquals(Optional.empty(), apiConnectionInfo.authenticator());

        String newRoot = "http://localhost:11111/cwms-data/";
        apiConnectionInfo = ApiConnectionInfoFactory.cloneWithNewUrl(apiConnectionInfo, newRoot);
        assertEquals(newRoot, apiConnectionInfo.getApiRoot());
        assertEquals(Optional.empty(), apiConnectionInfo.authenticator());
    }
}
