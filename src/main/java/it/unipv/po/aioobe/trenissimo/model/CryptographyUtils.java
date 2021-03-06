package it.unipv.po.aioobe.trenissimo.model;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;

/**
 * Classe mediante la quale criptiamo una password passata come stringa
 *
 * @author ArrayIndexOutOfBoundsException
 */
public class CryptographyUtils {

    /**
     * See <a href="https://howtodoinjava.com/java/java-security/java-aes-encryption-example/"></a>
     * Computa la stringa passata come parametro per renderla una chiave privata utilizzabile per la criptazione di tipo AES
     *
     * @param myKey
     * @return
     * @throws NoSuchAlgorithmException
     * @author ArrayIndexOutOfBoundsException
     */
    public static SecretKeySpec computeKey(final String myKey) throws NoSuchAlgorithmException {
        byte[] key;
        MessageDigest sha = null;
        key = myKey.getBytes(StandardCharsets.UTF_8);
        sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        return new SecretKeySpec(key, "AES");
    }

    /**
     * See <a href="https://howtodoinjava.com/java/java-security/java-aes-encryption-example/"></a>
     * Cripta la password passata come parametro utilizzando una chiave segreta (calcolata mediante il metodo SecretKeySpec computeKey(final String myKey))
     *
     * @param password
     * @return password criptata
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @author ArrayIndexOutOfBoundsException
     */
    public static String encryptPassword(String password) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        final String CFGNAME = "secretKey";
        Properties p = new Properties(System.getProperties());
        p.load(new FileInputStream("src/main/resources/it/unipv/po/aioobe/trenissimo/properties/properties"));
        var secretKey = CryptographyUtils.computeKey(p.getProperty(CFGNAME));
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(password.getBytes(StandardCharsets.UTF_8)));
    }


}

