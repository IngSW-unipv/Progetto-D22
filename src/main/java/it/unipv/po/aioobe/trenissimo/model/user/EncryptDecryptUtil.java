package it.unipv.po.aioobe.trenissimo.model.user;

import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class EncryptDecryptUtil {
    private static SecretKeySpec secretKey;
    private static byte[] key;

    /**
     * @param myKey
     * @implNote code ref "https://howtodoinjava.com/java/java-security/java-aes-encryption-example/"
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @author ArrayIndexOutOfBoundsException
     */
    public static void setKey(final String myKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest sha = null;
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
    }

    public static SecretKeySpec getSecretKey() {
        return secretKey;
    }
}
