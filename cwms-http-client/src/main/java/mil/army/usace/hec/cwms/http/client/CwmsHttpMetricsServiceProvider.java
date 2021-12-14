/*
 * MIT License
 *
 * Copyright (c) 2021 Hydrologic Engineering Center
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import rma.services.annotations.ServiceProvider;
import rma.util.lookup.Lookup;
import usace.metrics.services.MetricsApp;
import usace.metrics.services.MetricsService;
import usace.metrics.services.MetricsServiceProvider;
import usace.metrics.services.MetricsUtil;
import usace.metrics.services.config.MetricsConfig;
import usace.metrics.services.config.MetricsConfigBuilder;

@ServiceProvider(service = MetricsServiceProvider.class,
    path = CwmsHttpMetricsServiceProvider.SERVICE_PATH,
    position = CwmsHttpMetricsServiceProvider.SERVICE_POSITION)
public final class CwmsHttpMetricsServiceProvider implements MetricsServiceProvider {

    static final String SERVICE_PATH = "cwmshttp_metrics";
    static final int SERVICE_POSITION = 0;
    private static final Logger LOGGER = Logger.getLogger(CwmsHttpMetricsServiceProvider.class.getName());

    private MetricsService service;

    private MetricsApp getMetricsApp() {
        return Lookup.getDefault().lookup(MetricsApp.class);
    }

    private void init() {
        MetricsApp app = getMetricsApp();
        String appName = getAppName(app);
        Properties cwmsConfig = getConfigurationFile(appName);

        MetricsConfig config = buildMetricsConfig(cwmsConfig, app, appName);

        service = new MetricsService(config);
    }

    private MetricsConfig buildMetricsConfig(Properties cwmsConfig, MetricsApp app, String appName) {
        MetricsConfigBuilder builder = new MetricsConfigBuilder();
        if (cwmsConfig != null) {
            //This can be non-null when app is null, otherwise it could sit around here.
            builder.withPropertiesFile(cwmsConfig);
        }
        MetricsConfig appConfig = null;
        if (app != null) {
            appConfig = app.getAppConfig();
        }
        Properties appOverrides = buildCwmsOverrideProperties(appName);
        builder.withPropertiesFile(appOverrides);
        if (appConfig != null) {
            builder.withBackingConfiguration(appConfig);
        }
        return builder.withSystemPropertyOptions().build();
    }

    private Properties buildCwmsOverrideProperties(String appName) {
        Properties appOverrides = new Properties();
        Date now = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd kkmm-ss");
        String filePrefix = format.format(now);

        appOverrides.setProperty(MetricsConfig.METRICS_FILE_LOCATION_PREF, "CwmsHttp" + appName);
        appOverrides.setProperty(MetricsConfig.METRICS_FILE_PREFIX_PREF, filePrefix);

        return appOverrides;
    }

    private String getAppName(MetricsApp app) {
        String appName = "";
        if (app != null) {
            appName = app.getAppName();

            if (appName == null) {
                appName = "";
            }
        }
        return appName;
    }

    private Properties getConfigurationFile(String appName) {
        String configFileProperty = getConfigPropertyName(appName);
        Properties output = null;
        try {
            output = MetricsUtil.readPropertiesFromSystemProperty(configFileProperty);
        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.WARNING, "Unable to find file for CwmsHttp Metrics configuration.", ex);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Exception occurred while reading CwmsHttp metrics configuration file.", ex);
        }

        return output;
    }

    private String getConfigPropertyName(String appName) {
        String configFileProperty = "cwms.http";
        if (!appName.isEmpty()) {
            configFileProperty += "." + appName.toLowerCase();
        }

        configFileProperty += ".config";
        return configFileProperty;
    }

    @Override
    public synchronized MetricsService getMetricsService() {
        if (service == null) {
            init();
        }
        return service;
    }
}
