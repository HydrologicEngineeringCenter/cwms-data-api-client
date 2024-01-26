package mil.army.usace.hec.cwms.http.client;

import okhttp3.Cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

final class CwmsHttpCache {
    static final String CACHE_SIZE_PROPERTY_KEY = "cwms.http.client.cache.size";
    static final String CACHE_DIRECTORY_PROPERTY_KEY = "cwms.http.client.cache.directory";
    static final Path CACHE_DEFAULT_DIRECTORY = Paths.get(System.getProperty("java.io.tmpdir"))
            .resolve("CWMS").resolve("cwms-http-client").resolve("cache");
    static final long CACHE_DEFAULT_SIZE_MB = 100;
    static final Cache INSTANCE = new Builder().build();

    private CwmsHttpCache() {
        throw new AssertionError("Singleton utility class, do not instantiate");
    }


    static Cache getInstance() {
        return INSTANCE;
    }

    public static class Builder{
        private Path cacheDirectory = Paths.get(System.getProperty(CACHE_DIRECTORY_PROPERTY_KEY, CACHE_DEFAULT_DIRECTORY.toString()));
        private long cacheSize = Long.parseLong(System.getProperty(CACHE_SIZE_PROPERTY_KEY, CACHE_DEFAULT_SIZE_MB + ""));

        public Builder(){
        }

        public Builder withCacheDirectory(Path cacheDirectory){
            this.cacheDirectory = cacheDirectory;
            return this;
        }

        public Builder withCacheSize(long cacheSize){
            this.cacheSize = cacheSize;
            return this;
        }

        public Cache build(){
            try {
                Files.createDirectories(cacheDirectory);
                return new Cache(cacheDirectory.toFile(), cacheSize * 1024 * 1024);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
