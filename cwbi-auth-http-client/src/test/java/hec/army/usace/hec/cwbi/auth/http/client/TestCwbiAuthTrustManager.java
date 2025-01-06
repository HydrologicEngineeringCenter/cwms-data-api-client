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
package hec.army.usace.hec.cwbi.auth.http.client;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hec.army.usace.hec.cwbi.auth.http.client.trustmanagers.CwbiAuthTrustManager;
import java.security.cert.CertificateExpiredException;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.net.ssl.X509TrustManager;
import org.junit.jupiter.api.Test;

final class TestCwbiAuthTrustManager {

    @Test
    void testGetTrustManager() {
        X509TrustManager trustManager = CwbiAuthTrustManager.getTrustManager();
        assertNotNull(trustManager);
        X509Certificate[] acceptedIssuers = trustManager.getAcceptedIssuers();
        assertFalse(Arrays.asList(acceptedIssuers).isEmpty());
        List<String> details = Arrays.asList(acceptedIssuers[0].getIssuerDN().toString().split(","));
        details = details.stream().map(String::trim)
                    .collect(toList());
        assertFalse(details.isEmpty());
    }

    @Test
    void testCheckValidity() {
        X509TrustManager trustManager = CwbiAuthTrustManager.getTrustManager();
        assertNotNull(trustManager);
        X509Certificate[] acceptedIssuers = trustManager.getAcceptedIssuers();
        X509Certificate issuer = acceptedIssuers[0];
        assertThrows(CertificateExpiredException.class, () -> issuer.checkValidity(getExpiredDate(issuer.getNotAfter())));
    }

    public static Date getExpiredDate(Date dateOfExpiration) {
        Instant expiredInstant = dateOfExpiration.toInstant().plus(Duration.ofDays(1));
        return Date.from(expiredInstant);
    }

}
