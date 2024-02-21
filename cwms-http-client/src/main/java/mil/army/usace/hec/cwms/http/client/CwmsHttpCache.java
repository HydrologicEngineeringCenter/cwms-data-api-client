package mil.army.usace.hec.cwms.http.client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import okhttp3.Cache;

public class CwmsHttpCache {
    static final String CACHE_SIZE_PROPERTY_KEY = "cwms.http.client.cache.maxsizebytes";
    static final String CACHE_DIRECTORY_PROPERTY_KEY = "cwms.http.client.cache.directory";
    static final Path CACHE_DEFAULT_DIRECTORY = Paths.get(System.getProperty("java.io.tmpdir"))
            .resolve("CWMS").resolve("cwms-http-client").resolve("cache");
    static final long CACHE_DEFAULT_SIZE_MB = 100;
    private final Cache cache;

    private static final CwmsHttpCache INSTANCE = new Builder().build();

    private CwmsHttpCache(Cache cache)
    {
        this.cache = cache;
    }

    Cache getOkCache()
    {
        return cache;
    }

    public static CwmsHttpCache getInstance() {
        return INSTANCE;
    }

    static class Builder
    {
        private Path directory = Paths.get(
                System.getProperty(CACHE_DIRECTORY_PROPERTY_KEY, CACHE_DEFAULT_DIRECTORY.toString()));
        private long maxSizeBytes = Long.getLong(CACHE_SIZE_PROPERTY_KEY, CACHE_DEFAULT_SIZE_MB * 1024 * 1024);

        public Builder()
        {
        }

        public Builder withDirectory(Path cacheDirectory)
        {
            this.directory = cacheDirectory;
            return this;
        }

        public Builder withMaxSizeBytes(long cacheSize)
        {
            this.maxSizeBytes = cacheSize;
            return this;
        }

        public CwmsHttpCache build()
        {
            try
            {
                Files.createDirectories(directory);
                return new CwmsHttpCache(new Cache(directory.toFile(), maxSizeBytes));
            }
            catch(IOException e)
            {
                throw new IllegalStateException("Could not create cache directory:" + directory, e);
            }
        }

    }
}
