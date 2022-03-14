package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils;

import com.itextpdf.text.pdf.*;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * Classe che si occupa di creare e riempire dei file pdf
 * @author ArrayIndexOutOfBoundsException
 * @See <a href="https://api.itextpdf.com/iText5/java/5.5.13.3/">IText 5.5.13.3</a>
 */
public class TicketBuilder {

    private String sPart;
    private String sDest;
    private String dataPart;
    private String dataArr;
    private String oraPart;
    private String oraArr;

    private String nome;
    private String cognome;
    private String dataNascita;
    private String id;
    private String importo;

    private String messaggio;

    private String adulti;
    private String ragazzi;
    private String bambini;
    private String animali;

    /**
     * DEST serve per ottenere il percorso delle cartelle di sistema che contengono file temporanei.
     */
    public static final String DEST = System.getProperty("java.io.tmpdir").concat("NuovoBiglietto.pdf");
    /**
     * SRC contiene il percorso del template per il biglietto corsa singola.
     */
    public static final String SRC = "src/main/resources/it/unipv/po/aioobe/trenissimo/assets/TemplateTicketIcon.pdf";
    /**
     * SRCVO contiene il percorso del template per il voucher (generato dopo un rimborso).
     */
    public static final String SRCVO = "src/main/resources/it/unipv/po/aioobe/trenissimo/assets/TemplateVoucher.pdf";
    /**
     * SRCVOR contiene il percorso del template per il voucher regalo (o gift card) acquistabile dalla sezione apposita.
     */
    public static final String SRCVOR = "src/main/resources/it/unipv/po/aioobe/trenissimo/assets/TemplateGiftCard.pdf";


    /**
     * Metodo generale che, ricevuto un id di un biglietto, ne analizza i primi 2 caratteri per restituire la funzione
     * di creazione del pdf.
     * @param id
     * @throws Exception
     */
    public void createPdf(String id) throws Exception {
        if (id.startsWith("CS"))
            createCS();
        else
            createVO();
    }

    /**
     * Metodo che crea (e compila) il pdf del biglietto corsa singola.
     * @throws Exception
     */
    public void createCS() throws Exception {
        PdfReader originale = new PdfReader(SRC);
        PdfStamper copiaModificata = new PdfStamper(originale, new FileOutputStream(DEST));
        AcroFields placeholder = copiaModificata.getAcroFields();

        placeholder.setField("STAZIONEPARTENZA", this.sPart);
        placeholder.setField("STAZIONEARRIVO", this.sDest);
        placeholder.setField("DATAPARTENZA", this.dataPart);
        placeholder.setField("DATAARRIVO", this.dataArr);
        placeholder.setField("ORARIOPARTENZA", this.oraPart);
        placeholder.setField("ORARIOARRIVO", this.oraArr);
        placeholder.setField("NOME", this.nome);
        placeholder.setField("COGNOME", this.cognome);
        placeholder.setField("DATANASCITA", this.dataNascita);
        placeholder.setField("BIGLIETTO", this.id);
        placeholder.setField("EURO", this.importo);

        placeholder.setField("AD", this.adulti);
        placeholder.setField("R", this.ragazzi);
        placeholder.setField("B", this.bambini);
        placeholder.setField("AN", this.animali);

        copiaModificata.setFormFlattening(true);
        copiaModificata.close();
        originale.close();
    }

    /**
     * Metodo che crea (e compila) il pdf del voucher.
     * @throws Exception
     */
    public void createVO() throws Exception {
        PdfReader originale = new PdfReader(SRCVO);
        PdfStamper copiaModificata = new PdfStamper(originale, new FileOutputStream(DEST));
        AcroFields placeholder = copiaModificata.getAcroFields();

        placeholder.setField("ID", this.id);
        placeholder.setField("EURO", this.importo);

        copiaModificata.setFormFlattening(true);
        copiaModificata.close();
        originale.close();
    }

    /**
     * Metodo che crea (e compila) il pdf della gift card.
     * @throws Exception
     */
    public void createVORegalo() throws Exception {
        PdfReader originale = new PdfReader(SRCVOR);
        PdfStamper copiaModificata = new PdfStamper(originale, new FileOutputStream(DEST));
        AcroFields placeholder = copiaModificata.getAcroFields();

        placeholder.setField("ID", this.id);
        placeholder.setField("EURO", this.importo);
        placeholder.setField("MESSAGGIO", this.messaggio);

        copiaModificata.setFormFlattening(true);
        copiaModificata.close();
        originale.close();
    }

    /**
     * Costruttore richiamato nel caso in cui si voglia generare un biglietto corsa singola.
     * @param sPart
     * @param sDest
     * @param dataPart
     * @param dataArr
     * @param oraPart
     * @param oraArr
     * @param nome
     * @param cognome
     * @param dataNascita
     * @param id
     * @param importo
     * @param adulti
     * @param ragazzi
     * @param bambini
     * @param animali
     */
    public TicketBuilder(String sPart, String sDest, String dataPart, String dataArr, String oraPart, String oraArr,
                         String nome, String cognome, String dataNascita, String id, String importo, String adulti, String ragazzi, String bambini, String animali) {

        this.sPart = sPart;
        this.sDest = sDest;
        this.dataPart = dataPart;
        this.dataArr = dataArr;
        this.oraPart = oraPart;
        this.oraArr = oraArr;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.id = id;
        this.importo = importo;
        this.adulti = adulti;
        this.ragazzi = ragazzi;
        this.bambini = bambini;
        this.animali = animali;
    }

    /**
     * Costruttore richiamato nel caso in cui si voglia generare un voucher.
     * @param id
     * @param importo
     */
    public TicketBuilder(String id, String importo) {
        this.importo = importo;
        this.id = id;
    }

    /**
     * Costruttore richiamato nel caso in cui si voglia generare un voucher regalo (o Gift Card).
     * @param id
     * @param importo
     * @param messaggio
     */
    public TicketBuilder(String id, String importo, String messaggio) {
            this.importo = importo;
            this.id = id;
            this.messaggio = messaggio;
        }

    /**
     * Metodo che, quando richiamato, si occupa di copiare un file esistente (source) in un nuovo file (target).
     * (Non è consentito sovrascrivere un file con lo stesso nome).
     * @param source
     * @param target
     * @throws IOException
     */
    public static void copy(File source, File target) throws IOException {
        FileChannel sourceChannel = new FileInputStream(source).getChannel();
        FileChannel targetChannel = new FileOutputStream(target).getChannel();

            targetChannel.transferFrom(sourceChannel, 0,
                    sourceChannel.size());

            targetChannel.close();
            sourceChannel.close();
        }
    }

