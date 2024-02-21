package mil.army.usace.hec.cwms.http.client;

import okhttp3.Cache;

public final class CacheFactory {

	private CacheFactory() {
		throw new AssertionError("Utility class");
	}

	public static CacheFactory.CacheSupplier okHttpCacheSupplier()
	{
		return new OkHttpCacheSupplier();
	}

	public static CacheFactory.CacheSupplier okHttpCacheSupplier(CwmsHttpCache cache)
	{
		return new OkHttpCacheSupplier(cache);
	}

	public abstract static class CacheSupplier
	{
		abstract Cache getCache();
	}

	private static final class OkHttpCacheSupplier extends CacheFactory.CacheSupplier
	{

		private final Cache _cache;

		private OkHttpCacheSupplier() {
			this(CwmsHttpCache.getInstance());
		}

		private OkHttpCacheSupplier(CwmsHttpCache cache) {
			this(cache.getOkCache());
		}

		private OkHttpCacheSupplier(Cache cache) {
			this._cache = cache;
		}

		@Override
		Cache getCache() {
			return _cache;
		}
	}
}
