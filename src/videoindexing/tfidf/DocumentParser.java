/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videoindexing.tfidf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to read documents
 *
 * @author Mubin Shrestha
 */
public class DocumentParser {

    //This variable will hold all terms of each document in an array.
    private List<String[]> termsDocsArray = new ArrayList<String[]>();
    private List<String> allTerms = new ArrayList<String>(); //to hold all terms
    private List<double[]> tfidfDocsVector = new ArrayList<double[]>();

    /**
     * Method to read files and store in array.
     * @param filePath : source file path
     * @throws FileNotFoundException
     * @throws IOException
     */
    public List<String> parseFiles(String filePath) throws FileNotFoundException, IOException {
        File[] allfiles = new File(filePath).listFiles();
        BufferedReader in = null;
        List<String> result= new ArrayList<String>();
        int count=0;
        for (File f : allfiles) {
            if (f.getName().endsWith(".txt")) {
                result.add(f.getName());
                count++;
                in = new BufferedReader(new FileReader(f));
                StringBuilder sb = new StringBuilder();
                String s = null;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                }
                String[] tokenizedTerms = sb.toString().replaceAll("[\\W&&[^\\s]]", "").split("\\W+");   //to get individual terms
                for (String term : tokenizedTerms) {
                    if (!allTerms.contains(term)) {  //avoid duplicate entry
                        allTerms.add(term);
                    }
                }
                termsDocsArray.add(tokenizedTerms);
            }
        }
return result;
    }

    /**
     * Method to create termVector according to its tfidf score.
     */
    public List<String> tfIdfCalculator(String term) {
        double tf; //term frequency
        double idf; //inverse document frequency
        double tfidf; //term requency inverse document frequency        
        List<String> result= new ArrayList<String>(); 
        int val=0;
        for (String[] docTermsArray : termsDocsArray) {
            double[] tfidfvectors = new double[allTerms.size()];
            int count = 0;
           // for (String terms : allTerms) {
                tf = new TfIdf().tfCalculator(docTermsArray, term);
                idf = new TfIdf().idfCalculator(termsDocsArray, term);
                tfidf = tf * idf;
                result.add(String.valueOf(tfidf));
                val++;
                System.out.println("tfidf: "+tfidf);
                tfidfvectors[count] = tfidf;
                count++;
          //  }
            tfidfDocsVector.add(tfidfvectors);  //storing document vectors;            
        }
        return result;
    }

    /**
     * Method to calculate cosine similarity between all the documents.
     */
    public void getCosineSimilarity() {
        for (int i = 0; i < tfidfDocsVector.size(); i++) {
            for (int j = 0; j < tfidfDocsVector.size(); j++) {
                System.out.println("between " + i + " and " + j + "  =  "
                                   + new CosineSimilarity().cosineSimilarity
                                       (
                                         tfidfDocsVector.get(i), 
                                         tfidfDocsVector.get(j)
                                       )
                                  );
            }
        }
    }
}
