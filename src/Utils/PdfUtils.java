package Utils;

import Candidato.Candidato;
import Mesario.Mesario;
import Voto.Voto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PdfUtils {
    private static DecimalFormat df = new DecimalFormat("#.##");

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    public static void createPdf(String fileLocation, String title, Mesario mesario, List<Candidato> candidatos, List<Voto> votos,Integer numeroTotal){
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileLocation));
            document.open();
            addTitlePage(document, title, mesario);
            addContent(document, candidatos,votos,numeroTotal);
            document.close();
        } catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    private static void addTitlePage(Document document, String title, Mesario mesario)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(title, catFont));

        String pattern = "dd-MM-yyyy HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        addEmptyLine(preface, 1);
        preface.add(new Paragraph(
                "Relatório gerado por: " + mesario.getNome() + ", " + simpleDateFormat.format(new Date()),
                smallBold));

        addEmptyLine(preface, 3);
        preface.add(new Paragraph(
                "Este documento apresenta os resultados desta eleição. ",
                smallBold));

        document.add(preface);
        document.newPage();
    }

    private static void addContent(Document document, List<Candidato> candidatos,List<Voto> votos,Integer numeroTotal) throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Resultados da eleição", catFont));
        addEmptyLine(preface, 1);

        createTable(preface, candidatos,votos,numeroTotal);

        int votoBranco=0;
        for(Voto voto : votos) {
            if(voto.getCandidato().getId() == 0) {
                votoBranco++;
            }
        }

        double porc = Double.valueOf(votoBranco*100)/Double.valueOf(numeroTotal);

        addEmptyLine(preface, 2);
        preface.add(new Paragraph(
                "Votos brancos ou nulos: "+votoBranco+" / Porcentagem: "+df.format(porc)+"%",
                smallBold));

        document.add(preface);

    }

    private static void createTable(Paragraph preface, List<Candidato> candidatos, List<Voto> votos,Integer numeroTotal)
            throws BadElementException {
        PdfPTable table = new PdfPTable(3);

        PdfPCell c1 = new PdfPCell(new Phrase("Candidato"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Votos"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Porcentagem"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        for (Candidato candidato : candidatos) {
            table.addCell(candidato.getNome());

            Integer votoCandidato = 0;
            for(Voto voto : votos) {
                if(voto.getCandidato().getId().equals(candidato.getId())) {
                    votoCandidato++;
                }
            }

            double porc = Double.valueOf(votoCandidato*100)/Double.valueOf(numeroTotal);


            table.addCell(votoCandidato.toString());
            table.addCell(df.format(porc)+"%");
        }

        preface.add(table);

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
