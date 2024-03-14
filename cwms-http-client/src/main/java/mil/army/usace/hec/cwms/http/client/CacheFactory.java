/*
 * MIT License
 *
 * Copyright (c) 2024 Hydrologic Engineering Center
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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

	public static CacheFactory.CacheSupplier noCache() {
		return new CacheSupplier() {
			@Override
			Cache getCache() {
				return null;
			}
		};
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
