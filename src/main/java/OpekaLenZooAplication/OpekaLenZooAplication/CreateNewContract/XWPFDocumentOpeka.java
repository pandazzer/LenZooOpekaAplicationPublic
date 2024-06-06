package OpekaLenZooAplication.OpekaLenZooAplication.CreateNewContract;

import OpekaLenZooAplication.OpekaLenZooAplication.Constants;
import OpekaLenZooAplication.OpekaLenZooAplication.CreateNewContract.Exeption.ExistFileExeption;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class XWPFDocumentOpeka extends XWPFDocument {
    public XWPFDocumentOpeka(InputStream in) throws IOException {
        super(in);
    }

    public void replace(String old, String newText) {
        String[] arrayNewText;
        if (newText.contains("\n")) {
            arrayNewText = newText.split("\n");
            XWPFParagraph addP = null;
            for (XWPFParagraph pr : this.getParagraphs()) {
                for (XWPFRun r : pr.getRuns()) {
                    if (r.getText(0) != null && r.getText(0).trim().equals(old)) {
                        r.setText(newText, 0);
                        addP = pr;
                    }
                }
            }
            createParagraphs(addP, arrayNewText);
            return;
        }
        for (XWPFParagraph pr : this.getParagraphs()) {
            for (XWPFRun r : pr.getRuns()) {
                if (r.getText(0) != null && r.getText(0).trim().equals(old)) {
                    r.setText(newText, 0);
                }
            }
        }
        for (XWPFTable table : this.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            if (r.getText(0) != null && r.getText(0).trim().equals(old)) {
                                r.setText(newText, 0);
                            }
                        }
                    }
                }
            }
        }
    }

    private void createParagraphs(XWPFParagraph p, String[] paragraphs) {
        if (p != null) {
            XWPFDocument doc = p.getDocument();
            XmlCursor cursor = p.getCTP().newCursor();
            for (int i = 0; i < paragraphs.length; i++) {
                XWPFParagraph newP = doc.createParagraph();
                newP.getCTP().setPPr(p.getCTP().getPPr());
                XWPFRun newR = newP.createRun();
                newR.getCTR().setRPr(p.getRuns().get(0).getCTR().getRPr());
                newR.setText(paragraphs[i]);
                XmlCursor c2 = newP.getCTP().newCursor();
                c2.moveXml(cursor);
                c2.dispose();
            }
            cursor.removeXml(); // Removes replacement text paragraph
            cursor.dispose();
        }
    }

    public void replace(String old, String newText, String font) {
        for (XWPFParagraph pr : this.getParagraphs()) {
            for (XWPFRun r : pr.getRuns()) {
                if (r.getText(0) != null && r.getText(0).trim().equals(old)) {
                    r.setText(newText, 0);
                    r.setFontFamily(font);
                }
            }
        }
    }

    public void save(String path, String nameDocx) throws ExistFileExeption {
        File newContractsPath = new File(path);
        File newContracts = new File(path + "/" + nameDocx.replace("\"",""));
        if (!newContractsPath.exists()) newContractsPath.mkdir();
        if (newContracts.exists()){
            throw new ExistFileExeption();
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(newContracts);
            this.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Не удалось сохранить договор");
        }
    }
}
