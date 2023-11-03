package mil.army.usace.hec.cwms.http.client;

import okhttp3.Cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

final class CwmsHttpCache {
    static final String CACHE_SIZE_PROPERTY_KEY = "cwms.http.client.cache.size";
    static final String CACHE_DIRECTORY_PROPERTY_KEY = "cwms.http.client.cache.directory";
    static final String CACHE_DEFAULT_DIRECTORY = Paths.get(System.getProperty("java.io.tmpdir")).resolve("CWMS").resolve("cwms-http-client").resolve("cache").toString();
    static final long CACHE_DEFAULT_SIZE_MB = 100;
    static final Cache INSTANCE = createCache();

    private CwmsHttpCache() {
        throw new AssertionError("Singleton utility class, do not instantiate");
    }

    static Cache createCache() {
        Path cacheDirectory = Paths.get(System.getProperty(CACHE_DIRECTORY_PROPERTY_KEY, CACHE_DEFAULT_DIRECTORY));
        long cacheSize = Long.parseLong(System.getProperty(CACHE_SIZE_PROPERTY_KEY, CACHE_DEFAULT_SIZE_MB + ""));
        try {
            Files.createDirectories(cacheDirectory);
            return new Cache(cacheDirectory.toFile(), cacheSize * 1024 * 1024);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static Cache getInstance() {
        return INSTANCE;
    }
}
