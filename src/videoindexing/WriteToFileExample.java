/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videoindexing;

/**
 *
 * @author Sony
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
 
public class WriteToFileExample {
	public void copyText(String args,String videoFileName) {
		try {
 
			String content = "This is the content to write into file new line";
                        String textPath="";
			textPath="D:\\dataSet\\"+videoFileName+".txt";
                        //File file = new File("D:/toefl/filename.txt");
                        File file = new File(textPath);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
                            
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.newLine();
                        bw.append(args);
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
        
        public void copyTagText(String args,String videoFileName) {
		try {
 
			String content = "This is the content to write into file new line";
                        String textPath="";
			textPath="D:\\TagData\\"+videoFileName+".txt";
                        //File file = new File("D:/toefl/filename.txt");
                        File file = new File(textPath);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
                            
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.newLine();
                        bw.append(args);
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}