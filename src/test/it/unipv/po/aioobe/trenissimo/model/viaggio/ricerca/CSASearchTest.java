package it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CSASearchTest {

    @Test
    void eseguiRicerca() {
        boolean result = true;
        CSASearch search = new CSASearch();
        result = result && search.eseguiRicerca(367, 400).isEmpty();
        assertTrue(result);
    }
}