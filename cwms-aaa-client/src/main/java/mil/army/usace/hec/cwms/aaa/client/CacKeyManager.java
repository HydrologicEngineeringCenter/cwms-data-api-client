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

package mil.army.usace.hec.cwms.aaa.client;

import java.net.Socket;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.net.ssl.X509KeyManager;
import org.bouncycastle.asn1.BERTags;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.DLTaggedObject;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;

final class CacKeyManager implements X509KeyManager {
    static final Pattern EDIPI_PATTERN = Pattern.compile("\\d{16}@mil", Pattern.CASE_INSENSITIVE);
    private static final Logger LOGGER = Logger.getLogger(CacKeyManager.class.getName());
    private final X509KeyManager delegate;
    private final KeyStore keystore;

    CacKeyManager(X509KeyManager delegate, KeyStore keystore) {
        this.delegate = delegate;
        this.keystore = keystore;
    }

    @Override
    public String[] getClientAliases(String keyType, Principal[] issuers) {
        return delegate.getClientAliases(keyType, issuers);
    }

    @Override
    public String chooseClientAlias(String[] keyTypes, Principal[] issuers, Socket socket) {
        String retVal = delegate.chooseClientAlias(keyTypes, issuers, socket);
        if (keyTypes != null) {
            for (String keyType : keyTypes) {
                String[] clientAliases = this.getClientAliases(keyType, issuers);
                if (clientAliases != null && clientAliases.length > 0) {
                    retVal = getPivCertificate(clientAliases);
                    break;
                }
            }

        }
        return retVal;
    }

    @Override
    public String[] getServerAliases(String keyType, Principal[] issuers) {
        return delegate.getServerAliases(keyType, issuers);
    }

    @Override
    public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
        return delegate.chooseServerAlias(keyType, issuers, socket);
    }

    @Override
    public X509Certificate[] getCertificateChain(String alias) {
        return delegate.getCertificateChain(alias);
    }

    @Override
    public PrivateKey getPrivateKey(String alias) {
        return delegate.getPrivateKey(alias);
    }

    private String getPivCertificate(String[] aliases) {
        String retVal = aliases[0];
        for (String alias : aliases) {
            try {
                Certificate cr = keystore.getCertificate(alias);
                if (cr instanceof X509Certificate && isPivCertificate((X509Certificate) cr)) {
                    retVal = alias;
                    break;
                }
            } catch (CacCertificateException | KeyStoreException e) {
                LOGGER.log(Level.FINE, "Unable to authorize certificate for CWBI Authentication", e);
            }
        }
        return retVal;
    }

    private boolean isPivCertificate(X509Certificate cr) throws CacCertificateException {
        try {
            Collection<List<?>> anc = JcaX509ExtensionUtils.getSubjectAlternativeNames(cr);
            for (List<?> li : anc) {
                for (Object le : li) {
                    if (le instanceof DLSequence) {
                        DLSequence ds = (DLSequence) le;
                        if (dlSequenceContainsPiv(ds)) {
                            return true;
                        }
                    }
                }
            }
        } catch (CertificateParsingException e) {
            throw new CacCertificateException("Unable to parse X509 Certificate", e);
        }
        return false;
    }

    private static boolean dlSequenceContainsPiv(DLSequence ds) {
        for (Enumeration<?> en = ds.getObjects(); en.hasMoreElements(); ) {
            Object obj = en.nextElement();
            if (obj instanceof DLTaggedObject) {
                DLTaggedObject dt = (DLTaggedObject) obj;
                if (dt.getTagClass() == BERTags.CONTEXT_SPECIFIC && dt.getBaseObject() instanceof DERUTF8String) {
                    DERUTF8String dstr = (DERUTF8String) dt.getBaseObject();
                    if (EDIPI_PATTERN.matcher(dstr.getString()).matches()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
