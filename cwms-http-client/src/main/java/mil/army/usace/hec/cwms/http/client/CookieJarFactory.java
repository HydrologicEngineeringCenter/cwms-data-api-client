/*
 * Copyright (c) 2022
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client;

import java.net.CookieManager;
import okhttp3.CookieJar;
import okhttp3.JavaNetCookieJar;

public final class CookieJarFactory {

    private CookieJarFactory() {
        throw new AssertionError("Utility class");
    }

    public static CookieJarBuilder inMemoryCookieJar() {
        return new InMemoryCookieJarBuilder();
    }

    public abstract static class CookieJarBuilder {

        abstract CookieJar buildCookieJar();
    }

    private static class InMemoryCookieJarBuilder extends CookieJarBuilder {

        @Override
        CookieJar buildCookieJar() {
            CookieManager cookieManager = new CookieManager();
            return new JavaNetCookieJar(cookieManager);
        }
    }
}
