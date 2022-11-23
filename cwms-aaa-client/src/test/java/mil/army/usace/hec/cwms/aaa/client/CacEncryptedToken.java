/*
 * Copyright (c) 2022
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.aaa.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.X509KeyManager;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

final class CacEncryptedToken {

    @Disabled
    @Test
    public void testCacEncryptedToken() throws Exception {
        String unencryptedToken = "8AAF8621FD4748C050814BE6D6AFDAFC";
        X509KeyManager keyManager = (X509KeyManager) CacKeyManagerUtil.getKeyManager();
        String[] aliases = keyManager.getClientAliases("RSA", null);
        String encryptionAlias = Arrays.stream(aliases)
            .filter(a -> a.toLowerCase().contains("encryption"))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("Could not find encryption certificate on CAC. Cannot encrypt server credential token"));
        X509Certificate certificate = keyManager.getCertificateChain(encryptionAlias)[0];
        PublicKey publicKey = certificate.getPublicKey();
        PrivateKey privateKey = keyManager.getPrivateKey(encryptionAlias);
        byte[] needsToBeEncryptedByteArray = unencryptedToken.getBytes();
        List<byte[]> encryptedBytes = encrypt(publicKey, needsToBeEncryptedByteArray);
        byte[] decryptedTBytes = decrypt(privateKey, encryptedBytes);
        assertEquals(unencryptedToken, new String(decryptedTBytes).trim());
    }

    private List<byte[]> encrypt(PublicKey publicKey, byte[] bytes)
        throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        List<byte[]> retval = new ArrayList<>();
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int i = 0;

        int keySize = 2048;
        if(publicKey instanceof RSAKey)
        {
            keySize = ((RSAKey) publicKey).getModulus().bitLength();
        }
        while(i <= bytes.length)
        {
            //This is done because (bit size/8) = number of bytes, minus 11 bits of padding
            //https://stackoverflow.com/questions/10007147/getting-a-illegalblocksizeexception-data-must-not-be-longer-than-256-bytes-when
            int end = i + ((keySize / 8) - 11);
            byte[] block = Arrays.copyOfRange(bytes, i, Math.min(bytes.length, end));
            // Encrypt the message
            retval.add(cipher.doFinal(block));
            i = end;
        }
        return retval;
    }

    private byte[] decrypt(PrivateKey privateKey, List<byte[]> encryptedBytes) throws NoSuchAlgorithmException,
        NoSuchPaddingException, InvalidKeyException,
        IllegalBlockSizeException, BadPaddingException
    {
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        int count = (int) encryptedBytes.stream()
            .mapToLong(s -> s.length)
            .sum();
        byte[] retval = new byte[count];
        int i = 0;
        for(byte[] encrypted : encryptedBytes)
        {
            byte[] decrypted = cipher.doFinal(encrypted);
            for(byte b : decrypted)
            {
                retval[i] = b;
                i++;
            }
        }
        return retval;
    }
}
