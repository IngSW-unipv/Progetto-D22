package it.unipv.po.aioobe.trenissimo.model;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class CryptographyUtils {


    /**
     * @param myKey
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @implNote code ref "https://howtodoinjava.com/java/java-security/java-aes-encryption-example/"
     * @author ArrayIndexOutOfBoundsException
     */
    public static SecretKeySpec computeKey(final String myKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] key;
        MessageDigest sha = null;
        key = myKey.getBytes(StandardCharsets.UTF_8);
        sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        return new SecretKeySpec(key, "AES");
    }

    /**
     * @param password
     * @return password criptata
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @implNote code ref "https://howtodoinjava.com/java/java-security/java-aes-encryption-example/"
     * @author ArrayIndexOutOfBoundsException
     */
    public static String encryptPassword(String password) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        final String secret = "AIOOBE"; //TODO: da mettere in file configurazione
        var secretKey = CryptographyUtils.computeKey(secret);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(password.getBytes(StandardCharsets.UTF_8)));
    }


}

