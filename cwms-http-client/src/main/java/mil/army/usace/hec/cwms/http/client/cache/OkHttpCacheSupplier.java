package mil.army.usace.hec.cwms.http.client.cache;

import okhttp3.Cache;

public interface OkHttpCacheSupplier {
	Cache getOkCache();
}