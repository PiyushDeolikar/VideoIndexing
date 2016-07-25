/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videoindexing.tfidf;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import videoindexing.CreateDir;

/**
 *
 * @author Sony
 */
public class UploadVideo {
   private File src = new File ("C:\\Users\\Sony\\Desktop\\New Folder\\abcd.mp4");
   
   public UploadVideo(File src, String destFileName){
        try {
           try {
               //File dir = new File("C:\\Users\\Public\\Videos\\test\\"+destFileName);
           //dir.mkdir();
             CreateDir.createFolder("D:\\videos\\"+destFileName+".txt");
           } catch (Exception ex) {
               Logger.getLogger(UploadVideo.class.getName()).log(Level.SEVERE, null, ex);
           }
            File dest = new File("D:\\videos\\"+destFileName+".txt\\"+destFileName);
         if(!dest.exists()){
           dest.createNewFile();
         }
         FileUtils.copyFile(src,dest);
        } catch (IOException ex) {
            Logger.getLogger(UploadVideo.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public static void main(String args[]){
     //  UploadVideo objUpload= new UploadVideo();
       
   }
}
