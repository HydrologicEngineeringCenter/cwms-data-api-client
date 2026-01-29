/*
 * MIT License
 *
 * Copyright (c) 2026 Hydrologic Engineering Center
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

import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.X509KeyManager;

final class CacKeyManager implements X509KeyManager {
    private static final Logger LOGGER = Logger.getLogger(CacKeyManager.class.getName());
    private final X509KeyManager delegate;
    private String certificateAlias;

    CacKeyManager(X509KeyManager delegate) {
        this.delegate = delegate;
    }

    @Override
    public String[] getClientAliases(String keyType, Principal[] issuers) {
        return delegate.getClientAliases(keyType, issuers);
    }

    @Override
    public String chooseClientAlias(String[] keyTypes, Principal[] issuers, Socket socket) {
        String retVal = null;
        if (certificateAlias != null) {
            retVal = getPivCertificate(new String[]{certificateAlias});
        }
        if (retVal == null) {
            delegate.chooseClientAlias(keyTypes, issuers, socket);
            if (keyTypes != null) {
                for (String keyType : keyTypes) {
                    String[] clientAliases = this.getClientAliases(keyType, issuers);
                    if (clientAliases != null && clientAliases.length > 0) {
                        retVal = getPivCertificate(clientAliases);
                        break;
                    }
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
                var cr = delegate.getCertificateChain(alias);
                for(var cert : cr) {
                    if (CacKeyManagerUtil.isPivCertificate(cert)) {
                        retVal = alias;
                        break;
                    }
                }
            } catch (CacCertificateException e) {
                LOGGER.log(Level.FINE, "Unable to authorize certificate for CWBI Authentication", e);
            }
        }
        return retVal;
    }

    void setCertificateAlias(String certificateAlias) {
        if(certificateAlias != null) {
            this.certificateAlias = certificateAlias;
        }
    }

    Set<String> aliases() {
        Set<String> retval = new TreeSet<>();
        for(var keyType : new String[]{"RSA", "EC", "DSA"}) {
            String[] clientAliases = delegate.getClientAliases(keyType, null);
            if(clientAliases != null) {
                Collections.addAll(retval, clientAliases);
            }
        }
        return retval;
    }
}
