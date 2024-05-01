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

package mil.army.usace.hec.cwms.http.client.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class CacKeyManagerUtilTest {
    private static final Logger LOGGER = Logger.getLogger(CacKeyManagerUtil.class.getName());

    @Test
    void testPivEdipiPattern() {
        assertTrue(CacKeyManagerUtil.EDIPI_PATTERN.matcher("1234567890@").matches());
        assertTrue(CacKeyManagerUtil.EDIPI_PATTERN.matcher("1234567890@").matches());
        assertFalse(CacKeyManagerUtil.EDIPI_PATTERN.matcher("123456@").matches());
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testGetCertificateAliases() throws CacCertificateException {
        Instant start = Instant.now();
        assertFalse(CacKeyManagerUtil.getCertificateAliases().isEmpty(), "Windows keystore aliases should not be empty");
        Instant end1 = Instant.now();
        Duration duration = Duration.between(start, end1);
        LOGGER.info("First Attempt Time taken: " + duration.toMillis() + " milliseconds");
        assertFalse(CacKeyManagerUtil.getCertificateAliases().isEmpty(), "Cached Windows keystore aliases should not be empty");
        Instant end2 = Instant.now();
        Duration duration2 = Duration.between(end1, end2);
        LOGGER.info("Second Attempt Time taken: " + duration2.toMillis() + " milliseconds");
        assertTrue(duration.toMillis() / duration2.toMillis() >= 10, "First duration should be at least one orders of magnitude longer than the second duration");
    }
}
