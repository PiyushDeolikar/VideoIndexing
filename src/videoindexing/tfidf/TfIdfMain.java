/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videoindexing.tfidf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import videoindexing.SearchResultPojo;

/**
 *
 * @author Mubin Shrestha
 */
public class TfIdfMain {
    
    /**
     * Main method
     * @param args
     * @throws FileNotFoundException
     * @throws IOException 
     */
    
    public List<String> searchQuery(String query,int tagFlag){
            DocumentParser dp = new DocumentParser();    
    SearchResultPojo[] objSearchResult=new SearchResultPojo[10];
    List<String> urlList= new ArrayList<String>();
    List<String> urlListFinal= new ArrayList<String>();
    String[] fileNames= new String[10];    
    StringBuilder tempStr= new StringBuilder();
    StringBuilder tempInt=new StringBuilder();
    List<String> tfidfRank= new ArrayList<String>();
    
    try {
            if(tagFlag==1){
                urlList=dp.parseFiles("D:\\tagData");
            }else{
           urlList=dp.parseFiles("D:\\dataSet");
            }
            
           tfidfRank=dp.tfIdfCalculator(query); //calculates tfidf
           
           
// Bubble sort           
//           for(int i=0;i<tfidfRank.size()-1;i++){
//               
//               for(int j=1;j<tfidfRank.size()-i;j++){
//                  if(Float.parseFloat(tfidfRank.get(j-1)) < Float.parseFloat(tfidfRank.get(j))){
//                      tempInt = new StringBuilder(tfidfRank.get(j-1));
//                      tfidfRank.set(j-1, tfidfRank.get(j));
//                      tfidfRank.set(j, tempInt.toString());
//                      tempStr=new StringBuilder(urlList.get(j-1));
//                      urlList.set(j-1, urlList.get(j));
//                      urlList.set(j, tempStr.toString());
//                  } 
//               }
//           }
          
quickSortInDescendingOrder(urlList, tfidfRank,0,tfidfRank.size()-1);           
           
System.out.println("tfidfRank.size()"+tfidfRank.size());
           for(int cnt=0; cnt<tfidfRank.size();cnt++){
               System.out.println("final "+tfidfRank.get(cnt));
               if(!tfidfRank.get(cnt).equals("0.0") && !tfidfRank.get(cnt).equals("NaN") ){
                 System.out.println("inside if");
                 urlListFinal.add(urlList.get(cnt));  
               }
           }
           System.out.println("tfidfRank.size()"+tfidfRank.size());
          int flag =0; 
           for(int nxt=0; nxt<tfidfRank.size();nxt++){
              if(!tfidfRank.get(nxt).equalsIgnoreCase("NaN")){
                  flag=1;
              } 
           }
           
           if(flag==0){
               return null;
           }
           
           for(int nxtnum=0; nxtnum<tfidfRank.size();nxtnum++){
              if(!tfidfRank.get(nxtnum).equalsIgnoreCase("0.0")){
                  flag=1;
              } 
           }
           
           if(flag==0){
               return null;
           }
//           if(null==tfidfRank || tfidfRank.isEmpty()){
//               return null;
//           }
//           for(int i=0;i<fileNames.length;i++){
//               urlList.add(fileNames[i]);
//           }
//           for(int i=0;i<5;i++){
//               objSearchResult[i]= new SearchResultPojo();
//               objSearchResult[i].setVideoName(fileNames[i]);
//               objSearchResult[i].setWordCount(tfidfRank[i]);
//               objSearchResult[i].setUrl(fileNames[i]);
//           }
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TfIdfMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TfIdfMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Results count is: "+urlListFinal.size());
        if(urlListFinal.size()==0){
            return null;
        }
return urlListFinal;
    }
    
    //This method sorts the input array in descending order using quick sort
public static void quickSortInDescendingOrder (List<String> urlsL, List<String> numbers, int low, int high)
{
int i=low;
int j=high;
StringBuilder temp = new StringBuilder();
StringBuilder strTemp = new StringBuilder();
StringBuilder middle= new StringBuilder(numbers.get((low+high)/2));

while (i<j)
{
    while (Float.parseFloat(numbers.get(i))> Float.parseFloat(middle.toString()))
    {
        i++;
    }
    while (Float.parseFloat(numbers.get(j))< Float.parseFloat(middle.toString()))
    {
        j--;
    }
    if (j>=i)
    {
        temp = new StringBuilder(numbers.get(i));
        numbers.set(i,numbers.get(j));
        numbers.set(j,temp.toString());
        strTemp = new StringBuilder(urlsL.get(i));
        urlsL.set(i, urlsL.get(j));
        urlsL.set(j, strTemp.toString());

        i++;
        j--;
    }
 }

    if (low<j)
    {
        quickSortInDescendingOrder(urlsL,numbers, low, j);
    }
    if (i<high)
    {
        quickSortInDescendingOrder(urlsL,numbers, i, high);
    }
}
    
    public static void main(String args[]) throws FileNotFoundException, IOException
    {
        DocumentParser dp = new DocumentParser();
        dp.parseFiles("D:\\dataSet");
        dp.tfIdfCalculator("java"); //calculates tfidf
      //  dp.getCosineSimilarity(); //calculated cosine similarity   
    }
}
