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

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509KeyManager;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;


public final class CacKeyManagerUtil {
    static final Pattern EDIPI_PATTERN = Pattern.compile("\\d{16}@mil", Pattern.CASE_INSENSITIVE);
    private static final Logger LOGGER = Logger.getLogger(CacKeyManagerUtil.class.getName());

    private CacKeyManagerUtil() {
        throw new AssertionError("Utility class");
    }

    public static KeyManager createKeyManager() throws CacCertificateException {
        return getKeyManagerFromWindowsKeyStore(null);
    }

    public static KeyManager createKeyManager(String certificateAlias) throws CacCertificateException {
        return getKeyManagerFromWindowsKeyStore(certificateAlias);
    }

    private static CacKeyManager getKeyManagerFromWindowsKeyStore(String certificateAlias) throws CacCertificateException {
        try {
            KeyStore keystore = KeyStore.getInstance("WINDOWS-MY");
            keystore.load(null, null);
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(keystore, null);
            KeyManager[] kms = kmf.getKeyManagers();
            for (KeyManager km : kms) {
                if (km instanceof X509KeyManager) {
                    return new CacKeyManager((X509KeyManager) km, keystore, certificateAlias);
                }
            }
            throw new CacCertificateException("Failed to get X509KeyManager from Windows OS");
        } catch (KeyStoreException | UnrecoverableKeyException | NoSuchAlgorithmException | IOException |
                 CertificateException e) {
            throw new CacCertificateException("Failed to get X509KeyManager from Windows OS", e);
        }
    }


    public static List<String> getCertificateAliases() {
        Set<String> aliases = new TreeSet<>();
        try {
            KeyStore keystore = KeyStore.getInstance("WINDOWS-MY");
            keystore.load(null, null);
            Enumeration<String> keystoreAliases = keystore.aliases();
            while (keystoreAliases.hasMoreElements()) {
                String alias = keystoreAliases.nextElement();
                Certificate[] certificateChain = keystore.getCertificateChain(alias);
                if (certificateChain != null && certificateChain.length > 1 && certificateChain[0] instanceof X509Certificate) {
                    if (isPivCertificate((X509Certificate) certificateChain[0])) {
                        aliases.add(alias);
                    }
                }
            }
        } catch (IOException | NoSuchAlgorithmException | CertificateException | CacCertificateException |
                 KeyStoreException e) {
            LOGGER.log(Level.WARNING, "Error reading certificates from WINDOWS-MY keystore", e);
        }
        return new ArrayList<>(aliases);
    }

    public static boolean isPivCertificate(X509Certificate cr) throws CacCertificateException {
        try {
            Collection<List<?>> subjectAlternativeNames = cr.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return false;
            }
            for (List<?> subjectAlternativeName : subjectAlternativeNames) {
                Object bytes = subjectAlternativeName.get(1);
                if (bytes instanceof byte[]) {
                    String encoded = new String((byte[]) bytes);
                    int index = encoded.indexOf("@mil");
                    if (index >= 16) {
                        String edipiSan = encoded.substring(index - 16, index + 4);
                        if (EDIPI_PATTERN.matcher(edipiSan).matches()) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (CertificateParsingException e) {
            throw new CacCertificateException("Unable to parse X509 Certificate", e);
        }
    }

}
