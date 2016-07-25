/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videoindexing;

/**
 *
 * @author Sony
 */
import java.awt.BorderLayout;
import java.awt.Font;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
 
public class Find {
 
    public SearchResultPojo[] searchVideo(String s) throws IOException {
        int count =0;
        String srcDir="D:\\dataSet\\";
         SearchResultPojo[] objSearchResult=new SearchResultPojo[10];
        
         File folder = new File(srcDir);
        File[] listOfFiles = folder.listFiles();
                if (listOfFiles.length > 0) {
            for (int i = 0; i < listOfFiles.length; i++) {
                
                if (listOfFiles[i].isFile()) {
                    Scanner a = null;
                    a = new Scanner(new BufferedReader(new FileReader(srcDir + listOfFiles[i].getName())));
                while (a.hasNext()){
                String words = a.next();
                if (words.equalsIgnoreCase(s)){
                   count++;
                }
 
            }
                
    System.out.println("the total is:" + count );
 
    objSearchResult[i]=new SearchResultPojo(listOfFiles[i].getName(),String.valueOf(count),listOfFiles[i].getAbsolutePath());
        }
    }
    }
        
                return objSearchResult;
    }
    }
 