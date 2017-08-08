import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.marcisz.patryk.integration.model.Question;

/***
 * TODO: obsluga obrazkow
 */
public class Main {
    public static void main(String args[]) throws IOException {
        File file = new File("java.pdf");
        System.out.println(file.canRead());
        PDDocument document = PDDocument.load(file);
        PDFTextStripper pdfStripper = new CustomPDFTextStripper();
        String text = pdfStripper.getText(document);
//        System.out.println(text);
        document.close();
        Question qestion = new Question();


    }
}

public void getHyperlinks(){

}

/** nadpisanie klasy pdfTextStripper, modyfikujac jej zachowanie podczas uzycia getText() */

class CustomPDFTextStripper extends PDFTextStripper{

    public CustomPDFTextStripper() throws IOException {

    }

    public void getHyperlinks(){

    }

    public void

    /**
     * Override the default functionality of PDFTextStripper.
     */

    @Override
    protected void writeString(String text, List<TextPosition> textPositions) throws IOException{
        TextPosition firstPosition = textPositions.get(0);
        PDFont font = firstPosition.getFont();
        String type = font.toString().contains("Times New Roman,Bold") ? "QUESTION" :
                font.toString().contains("Times New Roman") || font.toString().contains("Courier New") ? "RESPONSE" : "OTHER";

        writeString(String.format("%s , %s [%s] %s", firstPosition.getFontSize(),
                firstPosition.getFont(), type, text));
    }
}
