/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
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

import rma.util.lookup.Lookup;
import rma.util.lookup.Lookups;
import usace.metrics.services.Metrics;
import usace.metrics.services.MetricsService;
import usace.metrics.services.MetricsServiceProvider;

final class CwmsHttpClientMetrics {

    private static final Lookup _lookup = Lookups.forPath(CwmsHttpMetricsServiceProvider.SERVICE_PATH);

    private CwmsHttpClientMetrics() {
        throw new AssertionError("Instantiated a utility class.");
    }

    private static MetricsService getMetricsService() {
        return getMetricsServiceProvider().getMetricsService();
    }

    private static MetricsServiceProvider getMetricsServiceProvider() {
        return _lookup.lookup(MetricsServiceProvider.class);
    }

    public static boolean isMetricsEnabled() {
        return getMetricsService().getConfig().isMetricsEnabled();
    }

    public static Metrics createMetrics(String... paths) {
        return getMetricsService().createMetrics(paths);
    }
}
