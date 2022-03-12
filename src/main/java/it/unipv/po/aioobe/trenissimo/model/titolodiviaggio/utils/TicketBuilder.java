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

    private String messaggio;
    private File biglietto;

    private String adulti;
    private String ragazzi;
    private String bambini;
    private String animali;

    public static final String DEST = System.getProperty("java.io.tmpdir").concat("NuovoBiglietto.pdf");
    public static final String SRC = "src/main/resources/it/unipv/po/aioobe/trenissimo/assets/TemplateTicketIcon.pdf";
    public static final String SRCVO = "src/main/resources/it/unipv/po/aioobe/trenissimo/assets/TemplateVoucher.pdf";
    public static final String SRCVOR = "src/main/resources/it/unipv/po/aioobe/trenissimo/assets/templateRegalo.pdf"; // todo da cambiare percorso


    public void createPdf(String id) throws Exception {
        if (id.startsWith("CS"))
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

        placeholder.setField("AD", this.adulti);
        placeholder.setField("R", this.ragazzi);
        placeholder.setField("B", this.bambini);
        placeholder.setField("AN", this.animali);

        copiaModificata.setFormFlattening(true);
        copiaModificata.close();
        originale.close();
    }

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

    public TicketBuilder(String id, String importo) {
        this.importo = importo;
        this.id = id;
    }

    public TicketBuilder(String id, String importo, String messaggio) {
            this.importo = importo;
            this.id = id;
            this.messaggio = messaggio;
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

