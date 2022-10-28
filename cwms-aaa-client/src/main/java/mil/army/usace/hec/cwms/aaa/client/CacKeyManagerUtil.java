/*
 * Copyright (c) 2022
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.aaa.client;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509KeyManager;


public final class CacKeyManagerUtil {
    private static KeyManager instance;

    private CacKeyManagerUtil() {
        throw new AssertionError("Utility class");
    }

    private static KeyManager createKeyManager() throws CacCertificateException {
        try {
            KeyStore keystore = KeyStore.getInstance("WINDOWS-MY");
            keystore.load(null, null);
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(keystore, null);
            KeyManager[] kms = kmf.getKeyManagers();
            for (KeyManager km : kms) {
                if (km instanceof X509KeyManager) {
                    return new CacKeyManager((X509KeyManager) km, keystore);
                }
            }
            throw new CacCertificateException("Failed to get X509KeyManager from Windows OS");
        } catch (KeyStoreException | UnrecoverableKeyException | NoSuchAlgorithmException | IOException | CertificateException e) {
            throw new CacCertificateException("Failed to get X509KeyManager from Windows OS", e);
        }
    }

    public static synchronized KeyManager getKeyManager() throws CacCertificateException {
        if (instance == null) {
            instance = createKeyManager();
        }
        return instance;
    }

}
