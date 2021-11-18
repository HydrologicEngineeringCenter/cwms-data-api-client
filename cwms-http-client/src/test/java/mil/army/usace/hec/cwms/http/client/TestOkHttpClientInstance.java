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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.Duration;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestOkHttpClientInstance {

    @BeforeEach
    private void resetSingleton() throws Exception {
        Arrays.stream(Logger.getLogger(OkHttpClientInstance.class.getName()).getHandlers())
              .forEach(h -> h.setLevel(Level.FINEST));
        Logger.getLogger(OkHttpClientInstance.class.getName()).setLevel(Level.FINEST);
        Field field = OkHttpClientInstance.class.getDeclaredField("INSTANCE");
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, OkHttpClientInstance.createClient());
    }

    @Test
    void testOkHttpClientInstanceDefaults() {
        OkHttpClient instance = OkHttpClientInstance.getInstance();
        assertEquals(OkHttpClientInstance.READ_TIMEOUT_PROPERTY_DEFAULT.toMillis(), instance.readTimeoutMillis());
        assertEquals(OkHttpClientInstance.CALL_TIMEOUT_PROPERTY_DEFAULT.toMillis(), instance.callTimeoutMillis());
        assertEquals(OkHttpClientInstance.CONNECT_TIMEOUT_PROPERTY_DEFAULT.toMillis(), instance.connectTimeoutMillis());
    }

    @Test
    void testOkHttpClientInstanceSystemProperties() throws Exception {
        Duration fiveSeconds = Duration.ofSeconds(5);
        Duration sixSeconds = Duration.ofSeconds(6);
        Duration sevenSeconds = Duration.ofSeconds(7);
        System.setProperty(OkHttpClientInstance.READ_TIMEOUT_PROPERTY_KEY, fiveSeconds.toString());
        System.setProperty(OkHttpClientInstance.CALL_TIMEOUT_PROPERTY_KEY, sixSeconds.toString());
        System.setProperty(OkHttpClientInstance.CONNECT_TIMEOUT_PROPERTY_KEY, sevenSeconds.toString());
        resetSingleton();
        try {
            OkHttpClient instance = OkHttpClientInstance.getInstance();
            assertEquals(fiveSeconds.toMillis(), instance.readTimeoutMillis());
            assertEquals(sixSeconds.toMillis(), instance.callTimeoutMillis());
            assertEquals(sevenSeconds.toMillis(), instance.connectTimeoutMillis());
        }
        finally {
            System.clearProperty(OkHttpClientInstance.READ_TIMEOUT_PROPERTY_KEY);
            System.clearProperty(OkHttpClientInstance.CALL_TIMEOUT_PROPERTY_KEY);
            System.clearProperty(OkHttpClientInstance.CONNECT_TIMEOUT_PROPERTY_KEY);
            resetSingleton();
        }

    }
}
