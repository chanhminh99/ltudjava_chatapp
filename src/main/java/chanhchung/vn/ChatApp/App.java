package chanhchung.vn.ChatApp;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



import chanhchung.vn.ChatApp.GUI.LoginScreen;
import chanhchung.vn.ChatApp.io.*;


/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args)  {

    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen login = new LoginScreen();
//					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    	
    	XmlFileFactory.readListAccount();
    }
}
