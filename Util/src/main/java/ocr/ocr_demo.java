package ocr;

import java.io.File;
import net.sourceforge.tess4j.*;

public class ocr_demo {
//https://github.com/tesseract-ocr/tessdoc
    public static void main(String[] args) {
        File imageFile = new File("E:\\new123.jpg");
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        instance.setDatapath("E:\\data");
        instance.setLanguage("chi_sim");

        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }

}
