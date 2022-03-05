package it.unipv.po.aioobe.trenissimo.model.user;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.AccountService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.DatiPersonaliService;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;

import static it.unipv.po.aioobe.trenissimo.model.user.EncryptDecryptUtil.getSecretKey;

public class Registrazione {

    private static Registrazione instance;

    public static Registrazione getInstance() {
        if (instance == null)
            instance = new Registrazione();
        return instance;
    }

    public void signUp(String username, String password, String nome, String cognome, String dataDiNascita, String mail, String via, String civico, String citta, String cap) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {
        AccountService accountService = new AccountService();
        DatiPersonaliService datiPersonaliService = new DatiPersonaliService();
        AccountEntity account = new AccountEntity();
        DatiPersonaliEntity dati = new DatiPersonaliEntity();
        account.setUsername(username);
        account.setPassword(criptPassword(password));
        dati.setUsername(username);
        dati.setNome(nome);
        dati.setCognome(cognome);
        dati.setDataNascita(java.sql.Date.valueOf(dataDiNascita));
        dati.setMail(mail);
        dati.setVia(via);
        dati.setCivico(Integer.valueOf(civico));
        dati.setCitta(citta);
        dati.setCap(Integer.valueOf(cap));
        accountService.persist(account);
        datiPersonaliService.persist(dati);
    }

    /**
     * @param password
     * @implNote code ref "https://howtodoinjava.com/java/java-security/java-aes-encryption-example/"
     * @return password criptata
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @author ArrayIndexOutOfBoundsException
     */

    public String criptPassword (String password) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        final String secret = "AIOOBE";
        EncryptDecryptUtil.setKey(secret);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
        return Base64.getEncoder()
                .encodeToString(cipher.doFinal(password.getBytes("UTF-8")));

    }

}
