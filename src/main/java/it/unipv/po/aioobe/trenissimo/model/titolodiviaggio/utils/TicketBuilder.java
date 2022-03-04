package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils;


import com.itextpdf.text.pdf.*;

import java.io.*;
import java.nio.channels.FileChannel;

public class TicketBuilder {

    private String sPart;
    private String sDest;
    private String dataPart;
    private String dataArr;
    private String oraPart;
    private String oraArr;
    private String classe;
    private String carrozza;
    private String posto;

    private String nome;
    private String cognome;
    private String cf;
    private String dataNascita;
    private String numBiglietto;
    private String costo;

    private File biglietto;

    public static final String DEST = System.getProperty("java.io.tmpdir").concat("NuovoBiglietto.pdf");
    public static final String SRC = "src/main/resources/it/unipv/po/aioobe/trenissimo/assets/TemplateBigliettoWBorder.pdf";
    public static final String DW = System.getProperty("user.home").concat("/Downloads/");

    public void createPdf() throws Exception {
        PdfReader originale = new PdfReader(SRC);
        PdfStamper copiaModificata = new PdfStamper(originale, new FileOutputStream(DEST));
        AcroFields placeholder = copiaModificata.getAcroFields();

        placeholder.setField("STAZIONEPARTENZA", this.sPart);
        placeholder.setField("STAZIONEARRIVO", this.sDest);
        placeholder.setField("DATAPARTENZA", this.dataPart);
        placeholder.setField("DATAARRIVO", this.dataArr);
        placeholder.setField("ORARIOPARTENZA", this.oraPart);
        placeholder.setField("ORARIOARRIVO", this.oraArr);
        placeholder.setField("CLASSE", this.classe);
        placeholder.setField("CARROZZA", this.carrozza);
        placeholder.setField("POSTO", this.posto);
        placeholder.setField("NOME", this.nome);
        placeholder.setField("COGNOME", this.cognome);
        placeholder.setField("CF", this.cf);
        placeholder.setField("DATANASCITA", this.dataNascita);
        placeholder.setField("BIGLIETTO", this.numBiglietto);
        placeholder.setField("EURO", this.costo);

        copiaModificata.setFormFlattening(true);
        copiaModificata.close();
        originale.close();
    }

    public TicketBuilder(String sPart, String sDest, String dataPart, String dataArr, String oraPart, String oraArr,
                         String classe, String carrozza, String posto, String nome, String cognome, String cf, String dataNascita,
                         String numBiglietto, String costo) {

        this.sPart = sPart;
        this.sDest = sDest;
        this.dataPart = dataPart;
        this.dataArr = dataArr;
        this.oraPart = oraPart;
        this.oraArr = oraArr;
        this.classe = classe;
        this.carrozza = carrozza;
        this.posto = posto;
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.dataNascita = dataNascita;
        this.numBiglietto = numBiglietto;
        this.costo = costo;
    }

    public static void main(String[] args) throws Exception {

        //System.out.println(System.getProperty("java.io.tmpdir"));
        /*
        TicketBuilder ticket = new TicketBuilder("Milano", "roma", "10.05.2022", "10.05.2022", "10", "13", "2", "3", "22A", "Mario", "Rossi", "mariorossicf", "10.05.2000", "M12345", "3000");
        ticket.createPdf(SRC, DEST);

        File biglietto = new File(DEST);
        Desktop.getDesktop().open(biglietto);

        File nuovo = new File(DW);
        copy(biglietto,nuovo);

        //biglietto.delete();
        */
    }

    public static void copy(File source, File target) throws IOException {
        FileChannel sourceChannel = new FileInputStream(source).getChannel();
        FileChannel targetChannel = new FileOutputStream(target).getChannel();

            targetChannel.transferFrom(sourceChannel, 0,
                    sourceChannel.size());

            targetChannel.close();
            sourceChannel.close();
        }
    }

