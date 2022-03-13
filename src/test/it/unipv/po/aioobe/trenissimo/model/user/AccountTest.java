package it.unipv.po.aioobe.trenissimo.model.user;

import it.unipv.po.aioobe.trenissimo.model.persistence.service.ViaggiPreferitiService;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.Ricerca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.text.DateFormatter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    public void signUp() throws NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String username = "ale";
        String password = "ale";
        String nome = "Alessandro";
        String cognome = "Putortì";
        LocalDate dataDiNascita = LocalDate.parse("1997-05-28");
        String email = "alessandro.putortì@gmail.com";
        String via = "Via Leopardi";
        String civico = "4";
        String citta = "Monza";
        String cap = "20900";
        boolean result = false;
        result = result || Account.signUp(username, password, nome, cognome, dataDiNascita, email, via, civico, citta, cap);
        assertFalse(result);
    }

    @Test
    public void login() {
        String[] username = {"vale", "nyquist", "zambo", "ale"};
        boolean result = true;
        for(String s:username) {
            Account.login(s);
            result = result && Account.getLoggedIn();
        }
        assertTrue(result);
    }

    @Test
    public void addViaggioPreferito() {
        boolean result = true;
        Ricerca search = new Ricerca(1707, 1386, LocalDateTime.now());
        search.setNumAdulti(1);
        search.setNumBambini(0);
        search.setNumAnimali(0);
        search.setNumRagazzi(0);
        search.eseguiRicerca();
        List<Viaggio> viaggi = search.getRisultatiAndata();
        Account.login("ale");
        result = result && Account.getInstance().addViaggioPreferito(viaggi.get(0));
        assertTrue(result);
    }

}