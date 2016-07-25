/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videoindexing;

import com.aspose.ocr.ILanguage;
import com.aspose.ocr.ImageStream;
import com.aspose.ocr.Language;
import com.aspose.ocr.OcrEngine;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sony
 */
public class AsposeOCR {

public static void main(String[] args){    
String resourcesFolderPath = "C:\\Users\\Sony\\Downloads\\Aspose_OCR_Java-master\\Aspose_OCR_Java-master\\src\\programmersguide\\workingwithocr\\performocronimage\\data\\resource.zip";
String imagePath = "C:\\Users\\Sony\\Downloads\\Aspose_OCR_Java-master\\Aspose_OCR_Java-master\\src\\programmersguide\\workingwithocr\\performocronimage\\data\\Sampleocr.bmp";

// Create an instance of OcrEngine
OcrEngine ocr = new OcrEngine();
        try {
            // Set Resources for OcrEngine
            ocr.setResource(new FileInputStream(resourcesFolderPath));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AsposeOCR.class.getName()).log(Level.SEVERE, null, ex);
        }

// Set image file
ocr.setImage(ImageStream.fromFile(imagePath));

// Add language
ILanguage language = Language.load("english");
ocr.getLanguages().addLanguage(language);

// Perform OCR and get extracted text
try {
	if (ocr.process()) {
		System.out.println("\ranswer -> " + ocr.getText());
	}
} catch (Exception e) {
	e.printStackTrace();
}
}
}
