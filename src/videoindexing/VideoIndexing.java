package videoindexing;



import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class VideoIndexing {
    
    
    
    public static void main(String[] args) {
    JFrame.setDefaultLookAndFeelDecorated(true);
    JDialog.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("Video Search Engine");
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    frame.setLocation(500, 200);
        try {   
            frame.getContentPane().add(new VideoIndexingUI());
        } catch (URISyntaxException ex) {
            Logger.getLogger(VideoIndexing.class.getName()).log(Level.SEVERE, null, ex);
        }
    frame.setResizable(false);
    frame.pack();
    frame.setVisible(true);    
        
    }


}

