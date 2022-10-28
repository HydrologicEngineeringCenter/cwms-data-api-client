/*
 * Copyright (c) 2022
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
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
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.DLTaggedObject;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;

final class CacKeyManager implements X509KeyManager {
    private static final Logger LOGGER = Logger.getLogger(CacKeyManager.class.getName());
    private static final Pattern EDIPI_PATTERN = Pattern.compile("\\d{16}@mil");
    private final X509KeyManager _delegate;
    private final KeyStore _keystore;

    CacKeyManager(X509KeyManager delegate, KeyStore keystore) {
        _delegate = delegate;
        _keystore = keystore;
    }

    @Override
    public String[] getClientAliases(String keyType, Principal[] issuers) {
        return _delegate.getClientAliases(keyType, issuers);
    }

    @Override
    public String chooseClientAlias(String[] keyTypes, Principal[] issuers, Socket socket) {
        String retVal = _delegate.chooseClientAlias(keyTypes, issuers, socket);
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
        return _delegate.getServerAliases(keyType, issuers);
    }

    @Override
    public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
        return _delegate.chooseServerAlias(keyType, issuers, socket);
    }

    @Override
    public X509Certificate[] getCertificateChain(String alias) {
        return _delegate.getCertificateChain(alias);
    }

    @Override
    public PrivateKey getPrivateKey(String alias) {
        return _delegate.getPrivateKey(alias);
    }

    private String getPivCertificate(String[] aliases) {
        String retVal = aliases[0];
        for (String alias : aliases) {
            try {
                Certificate cr = _keystore.getCertificate(alias);
                if (cr instanceof X509Certificate && isPIVCertificate(cr)) {
                    retVal = alias;
                    break;
                }
            } catch (CacCertificateException | KeyStoreException e) {
                LOGGER.log(Level.FINE, "Unable to authorize certificate for CWBI Authentication", e);
            }
        }
        return retVal;
    }

    private static boolean isPIVCertificate(Certificate cr) throws CacCertificateException {
        try {
            if (!(cr instanceof X509Certificate)) {
                throw new CacCertificateException("Certificate is not an X509Certificate");
            }
            X509Certificate xcr = (X509Certificate) cr;
            Collection<List<?>> anc = JcaX509ExtensionUtils.getSubjectAlternativeNames(xcr);

            for (List<?> li : anc) {
                for (Object le : li) {
                    if (le instanceof DLSequence) {
                        DLSequence ds = (DLSequence) le;
                        for (Enumeration<?> en = ds.getObjects(); en.hasMoreElements(); ) {
                            Object obj = en.nextElement();
                            if (obj instanceof DLTaggedObject) {
                                DLTaggedObject dt = (DLTaggedObject) obj;
                                if (dt.getObject() instanceof DERUTF8String) {
                                    DERUTF8String dstr = (DERUTF8String) dt.getObject();
                                    if (EDIPI_PATTERN.matcher(dstr.getString()).matches()) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (CertificateParsingException e) {
            throw new CacCertificateException("Unable to parse X509 Certificate", e);
        }
        return false;
    }
}
