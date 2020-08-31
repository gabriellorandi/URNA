package Utils;

import Mesario.Mesario;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class PdfUtils {
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    public static void createPdf(String fileLocation, String title, Mesario mesario){
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileLocation));
            document.open();
            addTitlePage(document, title, mesario);
            addContent(document);
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

        addEmptyLine(preface, 1);
        preface.add(new Paragraph(
                "Relatório gerado por: " + mesario.getNome() + ", " + new Date(),
                smallBold));

        addEmptyLine(preface, 3);
        preface.add(new Paragraph(
                "Este documento apresenta os resultados desta eleição. ",
                smallBold));

        document.add(preface);
        document.newPage();
    }

    private static void addContent(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Resultados da eleição", catFont));

        createTable(preface);

    }

    private static void createTable(Paragraph preface)
            throws BadElementException {
        PdfPTable table = new PdfPTable(3);

        PdfPCell c1 = new PdfPCell(new Phrase("Candidato"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Votos"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Porcnetagem"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        table.addCell("Lula");
        table.addCell("10");
        table.addCell("50%");
        table.addCell("Bolsonaro");
        table.addCell("10");
        table.addCell("50%");

        preface.add(table);

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
