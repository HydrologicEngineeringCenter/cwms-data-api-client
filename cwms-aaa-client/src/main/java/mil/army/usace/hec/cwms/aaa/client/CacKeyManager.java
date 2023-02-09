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
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
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

    @SuppressWarnings("unchecked")
    private boolean isPivCertificate(X509Certificate cr) throws CacCertificateException {
        try {
            return JcaX509ExtensionUtils.getSubjectAlternativeNames(cr)
                .stream()
                .flatMap(s -> ((List<?>) s).stream())
                .filter(DLSequence.class::isInstance)
                .map(l -> ((DLSequence) l).getObjects())
                .flatMap(e -> Collections.list((Enumeration<?>) e).stream())
                .filter(DLTaggedObject.class::isInstance)
                .filter(dt -> ((DLTaggedObject) dt).getTagClass() == BERTags.CONTEXT_SPECIFIC
                    && ((DLTaggedObject) dt).getBaseObject() instanceof DERUTF8String)
                .map(dt -> ((DLTaggedObject) dt).getBaseObject())
                .map(o -> ((DERUTF8String) o).getString())
                .map(s -> EDIPI_PATTERN.matcher(s.toString()))
                .anyMatch(m -> ((Matcher) m).matches());
        } catch (CertificateParsingException e) {
            throw new CacCertificateException("Unable to parse X509 Certificate", e);
        }
    }

}
