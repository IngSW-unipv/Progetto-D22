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

    private String nome;
    private String cognome;
    private String dataNascita;
    private String id;
    private String importo;

    private File biglietto;

    public static final String DEST = System.getProperty("java.io.tmpdir").concat("NuovoBiglietto.pdf");
    public static final String SRC = "src/main/resources/it/unipv/po/aioobe/trenissimo/assets/TemplateBigliettoWBorder.pdf";
    public static final String SRCVO = "src/main/resources/it/unipv/po/aioobe/trenissimo/assets/nomeTemplateVoucher.pdf";


    public void createPdf(String id) throws Exception {
        if (id.substring(0,2).equals("CS"))
            createCS();
        else
            createVO();
    }

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

        copiaModificata.setFormFlattening(true);
        copiaModificata.close();
        originale.close();
    }
    public void createVO() throws Exception {
        PdfReader originale = new PdfReader(SRCVO);
        PdfStamper copiaModificata = new PdfStamper(originale, new FileOutputStream(DEST));
        AcroFields placeholder = copiaModificata.getAcroFields();

        placeholder.setField("ID", this.id);
        placeholder.setField("VALORE", this.importo);

        copiaModificata.setFormFlattening(true);
        copiaModificata.close();
        originale.close();
    }

    public TicketBuilder(String sPart, String sDest, String dataPart, String dataArr, String oraPart, String oraArr,
                         String nome, String cognome, String dataNascita, String id, String importo) {

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
    }

    public TicketBuilder(String id, String importo) {
        this.importo = importo;
        this.id = id;
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

