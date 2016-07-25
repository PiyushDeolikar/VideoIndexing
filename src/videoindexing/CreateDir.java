/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videoindexing;

import java.io.File;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Sony
 */

 public class CreateDir {
    public static void createFolder(String path) throws Exception {
        File dir = new File(path);
        dir.mkdir();
    }

    public static void main(String[] args) throws Exception {
       // String path = args[0]; //takes the first argument from the command line
        String str="test4";
        FileUtils.cleanDirectory(new File("C:\\snapshots"));  
     //   createFolder("D:\\test\\"+str);
    }
}   

