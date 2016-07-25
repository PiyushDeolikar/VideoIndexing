/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videoindexing;

/**
 *
 * @author Sony
 */
import java.io.IOException;
import java.awt.Desktop;
import java.io.File;
import java.net.URI;

public class OpenFolderInJava 
{
   public static void main(String args[])
	{
	try
  					{
			
						URI uri = new File("I:\\songs\\Preetesh\\English\\hello.mp3").getCanonicalFile().toURI();
						
							if (Desktop.isDesktopSupported()) 
							{
							Desktop.getDesktop().browse(uri);
							} 
				
							else 
							{
							System.out.println("File does not exists!");
							}
  			
						
					}
					catch(Exception ert)
					{
                                        ert.printStackTrace();
                                        }
	}
}

