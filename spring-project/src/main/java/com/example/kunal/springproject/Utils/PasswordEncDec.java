package com.example.kunal.springproject.Utils;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;

import java.security.spec.KeySpec;


public class PasswordEncDec {

    private static Cipher cipher;
    private static final String UNICODE_FORMAT = "UTF8";
    static SecretKey key;
    static byte[] arrayBytes;
    private static KeySpec ks;
    private static SecretKeyFactory skf;
    private static String myEncryptionKey;

    public static String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            myEncryptionKey = "MysecretkeyMysecretKeyMySecretKey";
            arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
            ks = new DESedeKeySpec(arrayBytes);
            skf = SecretKeyFactory.getInstance("DESede");
            cipher = Cipher.getInstance("DESede");
            key = skf.generateSecret(ks);

            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }
}
