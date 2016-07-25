/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videoindexing;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IVideoPictureEvent;
import com.xuggle.xuggler.Global;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableModel;
import org.apache.commons.io.FileUtils;
import videoindexing.tfidf.TfIdfMain;
import videoindexing.tfidf.UploadVideo;

public class VideoIndexingUI extends JPanel
{
   String[] columns = {"Video Name", "TFIDF Rank"};
   String[][] resultObj= new String[5][5];
   SearchResultPojo[] dataObj=new SearchResultPojo[10]; 
   JPanel userPanel= new JPanel();
   JPanel loginPanel= new JPanel();
   JPanel ctrlPanel= new JPanel();
   JPanel passPanel= new JPanel();
   private ImageIcon ic= new ImageIcon("D:\\piyush code\\videoIndexing\\images\\Video1.jpg");        
   JLabel backLabel= new JLabel(ic);
   JLabel uploadLabel= new JLabel("                          Video Uploaded Successfully !!!!!!             ");
   JTable table;
    JScrollPane scrollPane = new JScrollPane(table);
    JLabel lblHeading = new JLabel("Search Results");
    JLabel usernameLabel= new JLabel("Username");
    JLabel passwordLabel= new JLabel("Password");
    JTextField userText= new JTextField();
    JPasswordField passText= new JPasswordField();
    JLabel browseLabel= new JLabel("Please select a video file....");
    JLabel authorLabel= new JLabel("Author");
    JTextField authorText= new JTextField();
    JLabel descLabel= new JLabel("Desc");
    JTextField descText= new JTextField();
    JButton button = new JButton("Select  File");
    JButton search = new JButton("Content Search");
    JButton searchTag = new JButton("Tag based Search");
    JButton tagSubmit = new JButton("Submit");
    JButton prev = new JButton("prev");
    JButton next = new JButton("next");
    JButton login = new JButton("Login");
    JButton open = new JButton("Open Video");
    JButton back= new JButton("Sign Out");
    JButton upload= new JButton("Upload Video");
    JTextField queryText= new JTextField();
    static List<String> urlList = new ArrayList<String>();
    JLabel errorMsg= new JLabel("************Invalid login.. Please try again..************");    
    public static final double SECONDS_BETWEEN_FRAMES = 10;
    File selectedFile;
    int resultSize=0;
    int currentResultSize=0;
    private static StringBuilder inputFilename = new StringBuilder("c:/Users/Sony/Desktop/New Folder/abcd.mp4");
    private static StringBuilder onlyFileName= new StringBuilder();
    private static final String outputFilePrefix = "c:/snapshots/mysnapshot";
     
    // The video stream index, used to ensure we display frames from one and
    // only one video stream from the media container.
    private static int mVideoStreamIndex = -1;
    
    // Time of last frame write
    private static long mLastPtsWrite = Global.NO_PTS;
static final JEditorPane ResultsArea = new JEditorPane();    
    public static final long MICRO_SECONDS_BETWEEN_FRAMES = 
        (long)(Global.DEFAULT_PTS_PER_SECOND * SECONDS_BETWEEN_FRAMES);
JButton buttonLink = new JButton();
JButton buttonLink1 = new JButton();
JButton buttonLink2 = new JButton();
JButton buttonLink3 = new JButton();
JButton buttonLink4 = new JButton();

JLabel resultText= new JLabel("");
JLabel resultText1= new JLabel("");
JLabel resultText2= new JLabel("");
JLabel resultText3= new JLabel("");
JLabel resultText4= new JLabel("");

List<String> listButton= new ArrayList<String>(); 
    //-----------------------------------------------------------------
   //  Constructor: Sets up the GUI.
   //-----------------------------------------------------------------
   public VideoIndexingUI () throws URISyntaxException
   {
       final URI uri = new URI("file:///C:/Users/Sony/Videos/");
 String txt= "Here"; 
    buttonLink.setText("<HTML>Click <FONT color=\"#000099\"><U>"+txt+"</U></FONT>"
        + " to open the video files.</HTML>");
//    buttonLink.setHorizontalAlignment(SwingConstants.LEFT);
    buttonLink.setForeground(Color.blue);
    buttonLink.setBorderPainted(false);
    buttonLink.setOpaque(false);
    buttonLink.setBackground(Color.WHITE);
    buttonLink.setToolTipText(uri.toString());
    buttonLink.addActionListener(new OpenUrlAction());

    buttonLink1.setText("<HTML>Click <FONT color=\"#000099\"><U>"+txt+"</U></FONT>"
        + " to open the video files.</HTML>");
   // buttonLink1.setHorizontalAlignment(SwingConstants.LEFT);
    buttonLink1.setForeground(Color.blue);
    buttonLink1.setBorderPainted(false);
    buttonLink1.setOpaque(false);
    buttonLink1.setBackground(Color.WHITE);
    buttonLink1.setToolTipText(uri.toString());
    buttonLink1.addActionListener(new OpenUrlAction());

    buttonLink2.setText("<HTML>Click <FONT color=\"#000099\"><U>"+txt+"</U></FONT>"
        + " to open the video files.</HTML>");
   // buttonLink2.setHorizontalAlignment(SwingConstants.LEFT);
    buttonLink2.setForeground(Color.blue);
    buttonLink2.setBorderPainted(false);
    buttonLink2.setOpaque(false);
    buttonLink2.setBackground(Color.WHITE);
    buttonLink2.setToolTipText(uri.toString());
    buttonLink2.addActionListener(new OpenUrlAction());

    buttonLink3.setText("<HTML>Click <FONT color=\"#000099\"><U>"+txt+"</U></FONT>"
        + " to open the video files.</HTML>");
   // buttonLink3.setHorizontalAlignment(SwingConstants.LEFT);
    buttonLink3.setForeground(Color.blue);
    buttonLink3.setBorderPainted(false);
    buttonLink3.setOpaque(false);
    buttonLink3.setBackground(Color.WHITE);
    buttonLink3.setToolTipText(uri.toString());
    buttonLink3.addActionListener(new OpenUrlAction());

    buttonLink4.setText("<HTML>Click <FONT color=\"#000099\"><U>"+txt+"</U></FONT>"
        + " to open the video files.</HTML>");
   // buttonLink4.setHorizontalAlignment(SwingConstants.LEFT);
    buttonLink4.setForeground(Color.blue);
    buttonLink4.setBorderPainted(false);
    buttonLink4.setOpaque(false);
    buttonLink4.setBackground(Color.WHITE);
    buttonLink4.setToolTipText(uri.toString());
    buttonLink4.addActionListener(new OpenUrlAction());

userPanel.setOpaque(false);    
userPanel.setBounds(0, 40, 400, 30);
passPanel.setOpaque(false);    
passPanel.setBounds(0, 80, 400, 30);
ctrlPanel.setOpaque(false);
ctrlPanel.setBounds(150, 120, 200, 500);
loginPanel.setOpaque(false);
loginPanel.setBounds(150, 150, 200, 500);
errorMsg.setBounds(0, 0, 400, 40);
errorMsg.setOpaque(false);

add(backLabel);



uploadLabel.setVisible(false);
ResultsArea.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
ResultsArea.setEditable(false);  
ctrlPanel.setVisible(false);
buttonLink.setVisible(false);     
buttonLink1.setVisible(false);
buttonLink2.setVisible(false);
buttonLink3.setVisible(false);
buttonLink4.setVisible(false);

resultText.setVisible(false);
resultText1.setVisible(false);
resultText2.setVisible(false);
resultText3.setVisible(false);
resultText4.setVisible(false);

queryText.setColumns(12);
descText.setColumns(13);
authorText.setColumns(12);
     userText.setColumns(19);
     passText.setColumns(19);
     authorLabel.setForeground(Color.BLUE);
     descLabel.setForeground(Color.BLUE);
     browseLabel.setForeground(Color.BLUE);
    usernameLabel.setForeground(Color.BLUE);
    passwordLabel.setForeground(Color.BLUE);
    errorMsg.setForeground(Color.red);
    browseLabel.setVisible(false);
    authorText.setVisible(false);
    descText.setVisible(false);
    authorLabel.setVisible(false);
    descLabel.setVisible(false);
    button.setVisible(false);
    tagSubmit.setVisible(false); 
    upload.setVisible(false);
    queryText.setVisible(false);
    search.setVisible(false);
    searchTag.setVisible(false);
    errorMsg.setVisible(false);
    back.setVisible(false);
    login.addActionListener(new LoginListner());
    open.addActionListener(new OpenVideoListner());
    back.addActionListener(new BackListner());
    search.addActionListener(new SearchListner());
    searchTag.addActionListener(new SearchListner());
    upload.addActionListener(new UploadListner());
    next.addActionListener(new NextListner());
    prev.addActionListener(new PrevListner());
    tagSubmit.addActionListener(new TagSubmitListner());
//    table.setFillsViewportHeight(true);
    lblHeading.setFont(new Font("Arial",Font.TRUETYPE_FONT,24));
    lblHeading.setVisible(false);
//    scrollPane.setVisible(false);
  //  table.setVisible(false);
    open.setVisible(false);
    ResultsArea.setVisible(false);
    
    //add(lblHeading,BorderLayout.PAGE_START);
    //add(ResultsArea);    
   // add(scrollPane,BorderLayout.CENTER);
    backLabel.add(errorMsg);
    userPanel.add(usernameLabel);
    userPanel.add(userText);
    //userPanel.setLocation(0, 0);
//    loginPanel.add(userPanel);
    passPanel.add(passwordLabel);
    passPanel.add(passText);
   // passPanel.setBounds(50, 50, 50, 50);
 //   loginPanel.add(passPanel);
    loginPanel.setAlignmentX(50);
    
backLabel.add(userPanel);    
   // add(loginPanel);
    //add(userPanel);
backLabel.add(passPanel);
//add(passPanel);
loginPanel.add(login);
    
    loginPanel.add(buttonLink);
    loginPanel.add(resultText);
    loginPanel.add(buttonLink1);
    loginPanel.add(resultText1);
    loginPanel.add(buttonLink2);
    loginPanel.add(resultText2);
    loginPanel.add(buttonLink3);
    loginPanel.add(resultText3);
    loginPanel.add(buttonLink4);
    loginPanel.add(resultText4);
    
    ctrlPanel.add(prev);
    ctrlPanel.add(next);
    
    backLabel.add(ctrlPanel);
    backLabel.add(loginPanel);
    userPanel.add(browseLabel);
    userPanel.add(button);
    
  //  add(upload);
    loginPanel.add(authorLabel);
    loginPanel.add(authorText);
    loginPanel.add(descLabel);
    loginPanel.add(descText);
    loginPanel.add(tagSubmit);
    userPanel.add(queryText);
    userPanel.add(search); 
    passPanel.add(searchTag);
    passPanel.add(back);
    add(uploadLabel);
    //userPanel.add(lblHeading);
    
    //add(buttonLink);
    add(open);
    
    //add(backLabel);
    setPreferredSize(new Dimension(400, 600));
    setBackground(Color.white);
    
//    System.out.println("select row is: "+table.getSelectedRow());
    
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
          
          tagSubmit.setVisible(false);
          uploadLabel.setVisible(false);
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          selectedFile = fileChooser.getSelectedFile();
          
          System.out.println(selectedFile.getAbsolutePath());
          inputFilename = new StringBuilder(selectedFile.getAbsolutePath());
          System.out.println("input file"+inputFilename.toString());
          
          onlyFileName= new StringBuilder(selectedFile.getName());
          File file = new File("C:\\Users\\Sony\\Videos\\"+onlyFileName.toString()+"");
System.out.println("File name is"+file.getName());	
          // if file doesnt exists, then create it
			if (!file.exists()) {
          IMediaReader mediaReader = ToolFactory.makeReader(inputFilename.toString());
          mVideoStreamIndex=-1;
          mLastPtsWrite = Global.NO_PTS;
        // stipulate that we want BufferedImages created in BGR 24bit color space
        mediaReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);
        
        mediaReader.addListener(new VideoIndexingUI.ImageSnapListener());

        // read out the contents of the media file and
        // dispatch events to the attached listener
        while (mediaReader.readPacket() == null);
          }else{
                          uploadLabel.setText("        File already exists");  
                            System.out.println("File already exists");
                        }
          }
        
         UploadVideo obj= new UploadVideo(selectedFile, onlyFileName.toString());
         File fileTag = new File("D:\\TagData\\"+onlyFileName.toString()+".txt");
         if (!fileTag.exists()) {
              try {
                  fileTag.createNewFile();
              } catch (IOException ex) {
                  Logger.getLogger(VideoIndexingUI.class.getName()).log(Level.SEVERE, null, ex);
              }
		}
         uploadLabel.setText("                          Video Uploaded Successfully !!!!!!             ");
         uploadLabel.setVisible(true);
         tagSubmit.setVisible(true);
         try {
                FileUtils.cleanDirectory(new File("C:\\snapshots"));
            } catch (IOException ex) {
                Logger.getLogger(VideoIndexingUI.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
    }); 
   }

   //*****************************************************************
   //  Represents a listener for button push (action) events.
   //*****************************************************************
   
      //--------------------------------------------------------------
      //  Updates the counter and label when the button is pushed.
      //--------------------------------------------------------------
   class OpenUrlAction implements ActionListener {
   
    @Override public void actionPerformed(ActionEvent e) {
   URI uri;
        try {
            System.out.println("button clicked from"+e.getActionCommand());
            uri = new URI("file:///D:/videos/"+e.getActionCommand());
        open(uri);
        } catch (URISyntaxException ex) {
            Logger.getLogger(OpenUrlAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      }
    
   }
   
   private static void open(URI uri) {
    if (Desktop.isDesktopSupported()) {
      try {
        Desktop.getDesktop().browse(uri);
      } catch (IOException e) { /* TODO: error handling */ }
    } else { /* TODO: error handling */ }
  }
       private static class ImageSnapListener extends MediaListenerAdapter {

        public void onVideoPicture(IVideoPictureEvent event) {

            if (event.getStreamIndex() != mVideoStreamIndex) {
                // if the selected video stream id is not yet set, go ahead an
                // select this lucky video stream
                if (mVideoStreamIndex == -1)
                    mVideoStreamIndex = event.getStreamIndex();
                // no need to show frames from this video stream
                else
                    return;
            }

            // if uninitialized, back date mLastPtsWrite to get the very first frame
            if (mLastPtsWrite == Global.NO_PTS)
                mLastPtsWrite = event.getTimeStamp() - MICRO_SECONDS_BETWEEN_FRAMES;

            // if it's time to write the next frame
            if (event.getTimeStamp() - mLastPtsWrite >= 
                    MICRO_SECONDS_BETWEEN_FRAMES) {
                                
                String outputFilename = dumpImageToFile(event.getImage());

                // indicate file written
                double seconds = ((double) event.getTimeStamp()) / 
                    Global.DEFAULT_PTS_PER_SECOND;
                System.out.printf(
                        "at elapsed time of %6.3f seconds wrote: %s\n",
                        seconds, outputFilename);
            ExtractImage obj= new ExtractImage();
            System.out.println("inputFilename: "+inputFilename);
            System.out.println("onlyFileName: "+onlyFileName);
            obj.fetchText(onlyFileName.toString(), outputFilename);
                // update last write time
                mLastPtsWrite += MICRO_SECONDS_BETWEEN_FRAMES;
            }

        }
        
        private String dumpImageToFile(BufferedImage image) {
            try {
                String outputFilename = outputFilePrefix + 
                     System.currentTimeMillis() + ".png";
                ImageIO.write(image, "png", new File(outputFilename));
                return outputFilename;
            } 
            catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

    }

   private class LoginListner implements ActionListener{
       
       public void actionPerformed(ActionEvent event) {
           
           
           
           if(userText.getText().equalsIgnoreCase("faculty") && passText.getText().equalsIgnoreCase("faculty")){
    usernameLabel.setVisible(false);
    userText.setVisible(false);
    passwordLabel.setVisible(false);
    passText.setVisible(false);
    login.setVisible(false);
    browseLabel.setVisible(true);
    descLabel.setVisible(true);
    descText.setVisible(true);
    authorLabel.setVisible(true);
    authorText.setVisible(true);
    button.setVisible(true);
    tagSubmit.setVisible(true);
    upload.setVisible(true);
    queryText.setVisible(false);
    search.setVisible(false);
    searchTag.setVisible(false);
    errorMsg.setVisible(false);
    back.setVisible(true);
    ResultsArea.setVisible(false);
    buttonLink.setVisible(false);
    buttonLink1.setVisible(false);
    buttonLink2.setVisible(false);
    buttonLink3.setVisible(false);
    buttonLink4.setVisible(false);
    resultText.setVisible(false);
resultText1.setVisible(false);
resultText2.setVisible(false);
resultText3.setVisible(false);
resultText4.setVisible(false);
ctrlPanel.setVisible(false);
           }
           else if(userText.getText().equalsIgnoreCase("student") && passText.getText().equalsIgnoreCase("student")){
    usernameLabel.setVisible(false);
    userText.setVisible(false);
    passwordLabel.setVisible(false);
    passText.setVisible(false);
    login.setVisible(false);
    ctrlPanel.setVisible(false);
    browseLabel.setVisible(false);
    descLabel.setVisible(false);
    descText.setVisible(false);
    authorLabel.setVisible(false);
    authorText.setVisible(false);
    button.setVisible(false);
    tagSubmit.setVisible(false);
    upload.setVisible(false);
    queryText.setText("");
    queryText.setVisible(true);
    search.setVisible(true);
    searchTag.setVisible(true);
    errorMsg.setVisible(false);
    //ResultsArea.setVisible(true);
    back.setVisible(true);
    buttonLink.setVisible(false);
    buttonLink1.setVisible(false);
    buttonLink2.setVisible(false);
    buttonLink3.setVisible(false);
    buttonLink4.setVisible(false);
    resultText.setVisible(false);
resultText1.setVisible(false);
resultText2.setVisible(false);
resultText3.setVisible(false);
resultText4.setVisible(false);
    
           }       
           else{
               userText.setText("");
               passText.setText("");
               errorMsg.setVisible(true);
               back.setVisible(false);
               ctrlPanel.setVisible(false);
           }
       }
   }
   
    private class OpenVideoListner implements ActionListener{
       
       public void actionPerformed(ActionEvent event) {
           
           
         System.out.println("table.getSelectedRow(): "+table.getSelectedRow()); 
          if(-1!=table.getSelectedRow()){
               try {  
                   Desktop.getDesktop().open(new File("C:\\Users\\Sony\\Videos"));
               } catch (IOException ex) {
                   Logger.getLogger(VideoIndexingUI.class.getName()).log(Level.SEVERE, null, ex);
               }
          }
       }
   }
   
    private class UploadListner implements ActionListener{
    @SuppressWarnings("empty-statement")
        public void actionPerformed(ActionEvent event){
        
        UploadVideo obj= new UploadVideo(selectedFile, onlyFileName.toString());
    }
    }
    
    private class NextListner implements ActionListener{
    @SuppressWarnings("empty-statement")
        public void actionPerformed(ActionEvent event){
try{        
BufferedReader reader = null; 
String text = null;
StringBuilder line = new StringBuilder();
File TagFile=null;
    if(currentResultSize+1 >= urlList.size()){
       
        currentResultSize=urlList.size();
        System.out.println("No more results to diaplay");
    buttonLink.setVisible(false);
            buttonLink1.setVisible(false);
    buttonLink2.setVisible(false);
    buttonLink3.setVisible(false);
    buttonLink4.setVisible(false);
    resultText.setVisible(false);
resultText1.setVisible(false);
resultText2.setVisible(false);
resultText3.setVisible(false);
resultText4.setVisible(false);
    resultText.setText("No more results to diaplay");
    resultText.setVisible(true);
    }else{
        buttonLink.setVisible(false);
            buttonLink1.setVisible(false);
    buttonLink2.setVisible(false);
    buttonLink3.setVisible(false);
    buttonLink4.setVisible(false);
    resultText.setVisible(false);
resultText1.setVisible(false);
resultText2.setVisible(false);
resultText3.setVisible(false);
resultText4.setVisible(false);
if(currentResultSize+1<urlList.size()){
    
    
buttonLink.setText(urlList.get(currentResultSize+1));
buttonLink.setVisible(true);
TagFile= new File("D:\\TagData\\"+urlList.get(currentResultSize+1));
    reader=new BufferedReader (new FileReader(TagFile));
    line=new StringBuilder();
    while ((text = reader.readLine()) !=null) {
           line.append(text).append(System.getProperty ("line.separator"));
    }
    System.out.println("line.....: "+line.toString());
resultText.setText(line.toString());
resultText.setVisible(true);
currentResultSize=currentResultSize+1;
if(currentResultSize+1<urlList.size()){

buttonLink1.setText(urlList.get(currentResultSize+1));
buttonLink1.setVisible(true);
TagFile= new File("D:\\TagData\\"+urlList.get(currentResultSize+1));
    reader=new BufferedReader (new FileReader(TagFile));
    line=new StringBuilder();
    while ((text = reader.readLine()) !=null) {
           line.append(text).append(System.getProperty ("line.separator"));
    }
    System.out.println("line.....: "+line.toString());
resultText1.setText(line.toString());
resultText1.setVisible(true);
currentResultSize=currentResultSize+1;
}

if(currentResultSize+1<urlList.size()){
buttonLink2.setText(urlList.get(currentResultSize+1));
buttonLink2.setVisible(true);
TagFile= new File("D:\\TagData\\"+urlList.get(currentResultSize+1));
    reader=new BufferedReader (new FileReader(TagFile));
    line=new StringBuilder();
    while ((text = reader.readLine()) !=null) {
           line.append(text).append(System.getProperty ("line.separator"));
    }
    System.out.println("line.....: "+line.toString());
resultText2.setText(line.toString());
resultText2.setVisible(true);
currentResultSize=currentResultSize+1;
}
if(currentResultSize+1<urlList.size()){
buttonLink3.setText(urlList.get(currentResultSize+1));
buttonLink3.setVisible(true);
TagFile= new File("D:\\TagData\\"+urlList.get(currentResultSize+1));
    reader=new BufferedReader (new FileReader(TagFile));
    line=new StringBuilder();
    while ((text = reader.readLine()) !=null) {
           line.append(text).append(System.getProperty ("line.separator"));
    }
    System.out.println("line.....: "+line.toString());
resultText3.setText(line.toString());
resultText3.setVisible(true);
currentResultSize=currentResultSize+1;
}
if(currentResultSize+1<urlList.size()){
buttonLink4.setText(urlList.get(currentResultSize+1));
buttonLink4.setVisible(true);
TagFile= new File("D:\\TagData\\"+urlList.get(currentResultSize+1));
    reader=new BufferedReader (new FileReader(TagFile));
    line=new StringBuilder();
    while ((text = reader.readLine()) !=null) {
           line.append(text).append(System.getProperty ("line.separator"));
    }
    System.out.println("line.....: "+line.toString());
resultText4.setText(line.toString());
resultText4.setVisible(true);
currentResultSize=currentResultSize+1;
}

}else{
    ctrlPanel.setVisible(false);
    resultText.setText("No results found");
    resultText.setVisible(true);
    
    buttonLink.setVisible(false);
    buttonLink1.setVisible(false);
    buttonLink2.setVisible(false);
    buttonLink3.setVisible(false);
    buttonLink4.setVisible(false);

    resultText1.setVisible(false);
    resultText2.setVisible(false);
    resultText3.setVisible(false);
    resultText4.setVisible(false);

}
    
        }
}catch(Exception e){
    e.printStackTrace();
}    
    }
    }
    
    
            
    private class TagSubmitListner implements ActionListener{
    @SuppressWarnings("empty-statement")
        public void actionPerformed(ActionEvent event){
    WriteToFileExample obj= new WriteToFileExample();
        System.out.println("Author: "+ authorText.getText());
        obj.copyTagText("Author: "+authorText.getText(), onlyFileName.toString());
        System.out.println("Desc: "+descText.getText());
        obj.copyTagText("Desc: "+descText.getText(), onlyFileName.toString());
        authorText.setText("");
        descText.setText("");
        
        
    }
    
   }
    private class PrevListner implements ActionListener{
    @SuppressWarnings("empty-statement")
        public void actionPerformed(ActionEvent event){
try{        
BufferedReader reader = null; 
String text = null;
StringBuilder line = new StringBuilder();
File TagFile=null;
int tempIndex=0;
if(currentResultSize-5 <= 0){
    currentResultSize=0;
            System.out.println("No more results to diaplay");
    buttonLink.setVisible(false);
            buttonLink1.setVisible(false);
    buttonLink2.setVisible(false);
    buttonLink3.setVisible(false);
    buttonLink4.setVisible(false);
    resultText.setVisible(false);
resultText1.setVisible(false);
resultText2.setVisible(false);
resultText3.setVisible(false);
resultText4.setVisible(false);
    resultText.setText("No more results to diaplay");
    resultText.setVisible(true);
    }else{
        buttonLink.setVisible(false);
            buttonLink1.setVisible(false);
    buttonLink2.setVisible(false);
    buttonLink3.setVisible(false);
    buttonLink4.setVisible(false);
    resultText.setVisible(false);
resultText1.setVisible(false);
resultText2.setVisible(false);
resultText3.setVisible(false);
resultText4.setVisible(false);
tempIndex=currentResultSize-5;

if(currentResultSize-5-tempIndex>=0){
currentResultSize=currentResultSize-5-tempIndex;    
    
buttonLink.setText(urlList.get(currentResultSize));
buttonLink.setVisible(true);
TagFile= new File("D:\\TagData\\"+urlList.get(currentResultSize));
    reader=new BufferedReader (new FileReader(TagFile));
    line=new StringBuilder();
    while ((text = reader.readLine()) !=null) {
           line.append(text).append(System.getProperty ("line.separator"));
    }
    System.out.println("line.....: "+line.toString());
resultText.setText(line.toString());
resultText.setVisible(true);
currentResultSize=currentResultSize+1;
if(currentResultSize<=urlList.size()){

buttonLink1.setText(urlList.get(currentResultSize));
buttonLink1.setVisible(true);
TagFile= new File("D:\\TagData\\"+urlList.get(currentResultSize));
    reader=new BufferedReader (new FileReader(TagFile));
    line=new StringBuilder();
    while ((text = reader.readLine()) !=null) {
           line.append(text).append(System.getProperty ("line.separator"));
    }
    System.out.println("line.....: "+line.toString());
resultText1.setText(line.toString());
resultText1.setVisible(true);
currentResultSize=currentResultSize+1;
}

if(currentResultSize<=urlList.size()){
buttonLink2.setText(urlList.get(currentResultSize));
buttonLink2.setVisible(true);
TagFile= new File("D:\\TagData\\"+urlList.get(currentResultSize));
    reader=new BufferedReader (new FileReader(TagFile));
    line=new StringBuilder();
    while ((text = reader.readLine()) !=null) {
           line.append(text).append(System.getProperty ("line.separator"));
    }
    System.out.println("line.....: "+line.toString());
resultText2.setText(line.toString());
resultText2.setVisible(true);
currentResultSize=currentResultSize+1;
}
if(currentResultSize<=urlList.size()){
buttonLink3.setText(urlList.get(currentResultSize));
buttonLink3.setVisible(true);
TagFile= new File("D:\\TagData\\"+urlList.get(currentResultSize));
    reader=new BufferedReader (new FileReader(TagFile));
    line=new StringBuilder();
    while ((text = reader.readLine()) !=null) {
           line.append(text).append(System.getProperty ("line.separator"));
    }
    System.out.println("line.....: "+line.toString());
resultText3.setText(line.toString());
resultText3.setVisible(true);
currentResultSize=currentResultSize+1;
}
if(currentResultSize<=urlList.size()){
buttonLink4.setText(urlList.get(currentResultSize));
buttonLink4.setVisible(true);
TagFile= new File("D:\\TagData\\"+urlList.get(currentResultSize));
    reader=new BufferedReader (new FileReader(TagFile));
    line=new StringBuilder();
    while ((text = reader.readLine()) !=null) {
           line.append(text).append(System.getProperty ("line.separator"));
    }
    System.out.println("line.....: "+line.toString());
resultText4.setText(line.toString());
resultText4.setVisible(true);
currentResultSize=currentResultSize+1;
}

}else{
    ctrlPanel.setVisible(false);
    resultText.setText("No results found");
    resultText.setVisible(true);
    
    buttonLink.setVisible(false);
    buttonLink1.setVisible(false);
    buttonLink2.setVisible(false);
    buttonLink3.setVisible(false);
    buttonLink4.setVisible(false);

    resultText1.setVisible(false);
    resultText2.setVisible(false);
    resultText3.setVisible(false);
    resultText4.setVisible(false);

}
    
        }
}catch(Exception e){
    e.printStackTrace();
}    
    }
    }
    
    private class SearchListner implements ActionListener{
        @SuppressWarnings("empty-statement")
        public void actionPerformed(ActionEvent event){
            try {
//       if(null!=table){
//       remove(table);         
//       remove(scrollPane);
//       }
//       open.setVisible(true);
//       scrollPane.setVisible(true);
       
              //  Find objFind= new Find();
                TfIdfMain tfidfObj= new TfIdfMain();
              // dataObj= objFind.searchVideo(queryText.getText());
               
            StringBuilder resultBuil = new StringBuilder();
List<String>  stopWordsSet= new ArrayList<String>();
stopWordsSet.add("the");
stopWordsSet.add("is");
stopWordsSet.add("a");
stopWordsSet.add("are");

            for (String s : queryText.getText().split("\\b")) {
    if (!stopWordsSet.contains(s)) resultBuil.append(s);
}
 System.out.println("new string: "+ resultBuil);
 if(event.getActionCommand().equalsIgnoreCase("Tag based Search")){
  for (String s : queryText.getText().split("\\b")){
 
     urlList= tfidfObj.searchQuery(s,1);
 
 }   
 }else{
 for (String s : queryText.getText().split("\\b")){
 
     urlList= tfidfObj.searchQuery(s,0);
 
 }
 }
 //Collections.sort(urlList);
 // System.out.println("dataObj[0]: "+dataObj[0].getVideoName());
               
//String pathVideo="C:\\Users\\Sony\\Videos\\";
String pathVideo="C:\\Users\\Sony\\Videos";
StringBuilder sb = new StringBuilder();
BufferedReader reader = null; 
String text = null;
StringBuilder line = new StringBuilder();
//for (String s : urlList) {
//    sb.append("<br><a href=").append(pathVideo).append(">").append(s).append("</a>\n");
//    File TagFile= new File("D:\\TagData\\"+s);
//    reader=new BufferedReader (new FileReader(TagFile));
//    line=new StringBuilder();
//    while ((text = reader.readLine()) !=null) {
//           line.append(text).append(System.getProperty ("line.separator"));
//    }
//    System.out.println("line.....: "+line.toString());
//    sb.append("<br>"+line.toString());
//}

ResultsArea.setText(sb.toString());
ResultsArea.setVisible(true);
ctrlPanel.setVisible(true);
buttonLink1.setVisible(false);
    buttonLink2.setVisible(false);
    buttonLink3.setVisible(false);
    buttonLink4.setVisible(false);
    resultText.setVisible(false);
resultText1.setVisible(false);
resultText2.setVisible(false);
resultText3.setVisible(false);
resultText4.setVisible(false);
if(null!=urlList){
currentResultSize=0;    
    
buttonLink.setText(urlList.get(0));

if(urlList.size()>=2){
buttonLink1.setText(urlList.get(1));
buttonLink1.setVisible(true);
currentResultSize=1;
}

if(urlList.size()>=3){
buttonLink2.setText(urlList.get(2));
buttonLink2.setVisible(true);
currentResultSize=2;
}
if(urlList.size()>=4){
buttonLink3.setText(urlList.get(3));
buttonLink3.setVisible(true);
currentResultSize=3;
}
if(urlList.size()>=5){
buttonLink4.setText(urlList.get(4));
buttonLink4.setVisible(true);
currentResultSize=4;
}

File TagFile= new File("D:\\TagData\\"+urlList.get(0));
    reader=new BufferedReader (new FileReader(TagFile));
    line=new StringBuilder();
    while ((text = reader.readLine()) !=null) {
           line.append(text).append(System.getProperty ("line.separator"));
    }
    System.out.println("line.....: "+line.toString());
resultText.setText(line.toString());

if(urlList.size()>=2){
 TagFile= new File("D:\\TagData\\"+urlList.get(1));
    reader=new BufferedReader (new FileReader(TagFile));
    line=new StringBuilder();
    while ((text = reader.readLine()) !=null) {
           line.append(text).append(System.getProperty ("line.separator"));
    }
    System.out.println("line.....: "+line.toString());
resultText1.setText(line.toString());
resultText1.setVisible(true);
}
if(urlList.size()>=3){
 TagFile= new File("D:\\TagData\\"+urlList.get(2));
    reader=new BufferedReader (new FileReader(TagFile));
    line=new StringBuilder();
    while ((text = reader.readLine()) !=null) {
           line.append(text).append(System.getProperty ("line.separator"));
    }
    System.out.println("line.....: "+line.toString());
resultText2.setText(line.toString());
resultText2.setVisible(true);
}

if(urlList.size()>=4){
TagFile= new File("D:\\TagData\\"+urlList.get(3));
    reader=new BufferedReader (new FileReader(TagFile));
    line=new StringBuilder();
    while ((text = reader.readLine()) !=null) {
           line.append(text).append(System.getProperty ("line.separator"));
    }
    System.out.println("line.....: "+line.toString());
resultText3.setText(line.toString());
resultText3.setVisible(true);
}

if(urlList.size()>=5){
TagFile= new File("D:\\TagData\\"+urlList.get(4));
    reader=new BufferedReader (new FileReader(TagFile));
    line=new StringBuilder();
    while ((text = reader.readLine()) !=null) {
           line.append(text).append(System.getProperty ("line.separator"));
    }
    System.out.println("line.....: "+line.toString());
resultText4.setText(line.toString());
resultText4.setVisible(true);
}
buttonLink.setVisible(true);

resultText.setVisible(true);
}else{
    ctrlPanel.setVisible(false);
    resultText.setText("No results found");
    resultText.setVisible(true);
    
    buttonLink.setVisible(false);
    buttonLink1.setVisible(false);
    buttonLink2.setVisible(false);
    buttonLink3.setVisible(false);
    buttonLink4.setVisible(false);

    resultText1.setVisible(false);
    resultText2.setVisible(false);
    resultText3.setVisible(false);
    resultText4.setVisible(false);

}

//               for(int i=0; i<5;i++){
//               resultObj[i][0]=dataObj[i].getVideoName();
//               resultObj[i][1]=dataObj[i].getWordCount();
//               resultObj[i][2]=dataObj[i].getUrl();
//               }
    
               // if(null==table){
  //             table = new JTable(resultObj, columns);
              // }
                
//                table.repaint();
//                table.setVisible(true);
//                table.setFillsViewportHeight(true);
//                scrollPane = new JScrollPane(table);
//                add(scrollPane);
//
                lblHeading.setVisible(true);
//                scrollPane.setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(VideoIndexingUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
  private class BackListner implements ActionListener {
      public void actionPerformed(ActionEvent event){
          
          usernameLabel.setVisible(true);
          userText.setText("");
          userText.setVisible(true);
          passwordLabel.setVisible(true);
          passText.setText("");
          passText.setVisible(true);
          login.setVisible(true);
          lblHeading.setVisible(false);
          browseLabel.setVisible(false);
          descLabel.setVisible(false);
    descText.setVisible(false);
    authorLabel.setVisible(false);
    authorText.setVisible(false);
          button.setVisible(false);
          tagSubmit.setVisible(false);
          queryText.setVisible(false);
          search.setVisible(false);
          searchTag.setVisible(false);
          errorMsg.setVisible(false);
          back.setVisible(false);
          ctrlPanel.setVisible(false);
//          table.setVisible(false);
//          scrollPane.setVisible(false);
          ResultsArea.setVisible(false);
          buttonLink.setVisible(false);
//          remove(table);
//          remove(scrollPane);
          uploadLabel.setVisible(false);
          open.setVisible(false);
          buttonLink.setVisible(false);     
            buttonLink1.setVisible(false);
            buttonLink2.setVisible(false);
            buttonLink3.setVisible(false);
            buttonLink4.setVisible(false);

            resultText.setVisible(false);
            resultText1.setVisible(false);
            resultText2.setVisible(false);
            resultText3.setVisible(false);
            resultText4.setVisible(false);
      
      }
  }
}

