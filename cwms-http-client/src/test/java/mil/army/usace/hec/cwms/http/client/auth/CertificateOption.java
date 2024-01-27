/*
 * MIT License
 *
 * Copyright (c) 2023 Hydrologic Engineering Center
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

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CertificateOption {
	private final Certificate _cert;
	private final String _alias;

	CertificateOption(String alias, Certificate cert) {
		_cert = cert;
		_alias = alias;
	}

	private static String getCN(String dn) {
		String pat = "CN=(.*?),";

		Pattern r = Pattern.compile(pat);
		Matcher m = r.matcher(dn);

		if (m.find()) {
			if (m.groupCount() == 1) {
				return m.group(1);
			}
		}
		return null;
	}

	public String getAlias() {
		return _alias;
	}

	@Override
	public String toString() {
		if (_cert instanceof X509Certificate) {
			X509Certificate xc = (X509Certificate) _cert;
			String subject = getCN(xc.getSubjectX500Principal().getName());
			String issuer = getCN(xc.getIssuerX500Principal().getName());

			if (subject != null && issuer != null) {
				return "Subject: " + subject + " Issuer: " + issuer;
			}
		}
		return _alias;
	}

}

