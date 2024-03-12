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

import okhttp3.OkHttpClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestOkHttpClientInstance {

    @AfterEach
    public void tearDown() {
        System.clearProperty(OkHttpClientInstance.READ_TIMEOUT_PROPERTY_KEY);
        System.clearProperty(OkHttpClientInstance.CALL_TIMEOUT_PROPERTY_KEY);
        System.clearProperty(OkHttpClientInstance.CONNECT_TIMEOUT_PROPERTY_KEY);
        System.clearProperty(OkHttpClientInstance.WRITE_TIMEOUT_PROPERTY_KEY);
        System.clearProperty(CwmsHttpCache.CACHE_DIRECTORY_PROPERTY_KEY);
        System.clearProperty(CwmsHttpCache.CACHE_SIZE_PROPERTY_KEY);
    }

    @Test
    void testOkHttpClientInstanceDefaults() {
        OkHttpClient instance = OkHttpClientInstance.getInstance();
        assertEquals(OkHttpClientInstance.READ_TIMEOUT_PROPERTY_DEFAULT.toMillis(), instance.readTimeoutMillis());
        assertEquals(OkHttpClientInstance.CALL_TIMEOUT_PROPERTY_DEFAULT.toMillis(), instance.callTimeoutMillis());
        assertEquals(OkHttpClientInstance.CONNECT_TIMEOUT_PROPERTY_DEFAULT.toMillis(), instance.connectTimeoutMillis());
    }

    @Test
    void testOkHttpClientInstanceSystemProperties() {
        Duration fiveSeconds = Duration.ofSeconds(5);
        Duration sixSeconds = Duration.ofSeconds(6);
        Duration sevenSeconds = Duration.ofSeconds(7);
        Duration eightSeconds = Duration.ofSeconds(8);
        System.setProperty(OkHttpClientInstance.READ_TIMEOUT_PROPERTY_KEY, fiveSeconds.toString());
        System.setProperty(OkHttpClientInstance.CALL_TIMEOUT_PROPERTY_KEY, sixSeconds.toString());
        System.setProperty(OkHttpClientInstance.CONNECT_TIMEOUT_PROPERTY_KEY, sevenSeconds.toString());
        System.setProperty(OkHttpClientInstance.WRITE_TIMEOUT_PROPERTY_KEY, eightSeconds.toString());
        System.setProperty(CwmsHttpCache.CACHE_DIRECTORY_PROPERTY_KEY, CwmsHttpCache.CACHE_DEFAULT_DIRECTORY.getParent().resolve("testCache").toString());
        int mbMultiplicativeFactor = (1024 * 1024);
        int sizeInBytes = 124 * mbMultiplicativeFactor;
        System.setProperty(CwmsHttpCache.CACHE_SIZE_PROPERTY_KEY, String.valueOf(sizeInBytes));

        OkHttpClient instance = OkHttpClientInstance.createClient(new CwmsHttpCache.Builder().build().getOkCache());
        assertEquals(fiveSeconds.toMillis(), instance.readTimeoutMillis());
        assertEquals(sixSeconds.toMillis(), instance.callTimeoutMillis());
        assertEquals(sevenSeconds.toMillis(), instance.connectTimeoutMillis());
        assertEquals(eightSeconds.toMillis(), instance.writeTimeoutMillis());
        assertEquals(sizeInBytes, instance.cache().maxSize());
        assertEquals(CwmsHttpCache.CACHE_DEFAULT_DIRECTORY.getParent().resolve("testCache").toString(),
                instance.cache().directory().toString());
    }
}
