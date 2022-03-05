package it.unipv.po.aioobe.trenissimo.model.user;

import it.unipv.po.aioobe.trenissimo.model.persistence.service.AccountService;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;

public class Login {

    private static Login instance;

    public static Login getInstance() {
        if (instance == null)
            instance = new Login();
        return instance;
    }

    public boolean checkUserPassword(String user, String password) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {
        var accountUser = new AccountService().findByUsername(user);
        return accountUser != null && decryptPassword(accountUser.getPassword()).equals(password);
    }

    public void login (String user) {
        Account account = Account.getInstance();
        account.setAccount(user);
        account.setDatiPersonali(user);
    }

    /**
     * @param password
     * @implNote code ref "https://howtodoinjava.com/java/java-security/java-aes-encryption-example/"
     * @return password decriptata
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @author ArrayIndexOutOfBoundsException
     */
    public String decryptPassword(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeyException {
        final String secret = "AIOOBE";
        EncryptDecryptUtil.setKey(secret);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, EncryptDecryptUtil.getSecretKey());
        return new String(cipher.doFinal(Base64.getDecoder()
                .decode(password)));
    }

}
