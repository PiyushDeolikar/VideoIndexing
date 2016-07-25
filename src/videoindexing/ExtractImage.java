/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videoindexing;

/**
 *
 * @author Sony
 */

import com.aspose.ocr.ILanguage;
import com.aspose.ocr.ImageStream;
import com.aspose.ocr.Language;
import com.aspose.ocr.OcrEngine;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
//import com.asprise.ocr.Ocr;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExtractImage {
 
    private static String tempStat= "NA";
    
 public void fetchText(String videoFileName,String imageFileName){
 String resourcesFolderPath = "C:\\Users\\Sony\\Downloads\\Aspose_OCR_Java-master\\Aspose_OCR_Java-master\\src\\programmersguide\\workingwithocr\\performocronimage\\data\\resource.zip";
 OcrEngine ocr = new OcrEngine();  
 try {
            try {
            // Set Resources for OcrEngine
            ocr.setResource(new FileInputStream(resourcesFolderPath));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AsposeOCR.class.getName()).log(Level.SEVERE, null, ex);
        }
        ocr.setImage(ImageStream.fromFile(imageFileName));
        ILanguage language = Language.load("english");
ocr.getLanguages().addLanguage(language);
if (ocr.process()) {
		System.out.println("\ranswer -> " + ocr.getText());
	}
 //Image image = ImageIO.read(new File("C:/temp/Emp1.png"));
// Ocr.setUp();
// Ocr ocr=new Ocr(); 
// ocr.startEngine("eng", Ocr.SPEED_FASTEST);
 
 //String s = ocr.recognize(new File[] {new File("C:/temp/msg.png")},
 // Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);       
 
// String s = ocr.recognize(new File[] {new File(imageFileName)},
// 
// Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);

 //String s = new OCR().recognizeCharacters((RenderedImage) image);
 String s= ocr.getText().toString();
 System.out.println("Text From Image : \n"+ s);
 System.out.println("Text From previous Image : \n"+ tempStat);
 System.out.println("Length of total text : \n"+ s.length());
 
 //ocr.stopEngine();
 
 WriteToFileExample obj= new WriteToFileExample();
 if(!s.equalsIgnoreCase(tempStat)){
 System.out.println("");
     obj.copyText(s,videoFileName);
 }   
 tempStat=s;
 /* Use below code If you want to read image location from your hard disk   
  *   
   BufferedImage image = ImageIO.read(new File("Image location"));   
   String imageText = new OCR().recognizeCharacters((RenderedImage) image);  
   System.out.println("Text From Image : \n"+ imageText);  
   System.out.println("Length of total text : \n"+ imageText.length());   
      
   */ 
 } catch (Exception ex) {
            Logger.getLogger(ExtractImage.class.getName()).log(Level.SEVERE, null, ex);
        }
 
}

 public static void main(String args[]){
     ExtractImage obj= new ExtractImage();
     obj.fetchText("sample", "C:\\snapshots\\intro2.png");
  }
}
