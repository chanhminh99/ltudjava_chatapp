package chanhchung.vn.ChatApp;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



import chanhchung.vn.ChatApp.GUI.LoginScreen;
import chanhchung.vn.ChatApp.GUI.ServerScreen;
import chanhchung.vn.ChatApp.io.*;
import chanhchung.vn.ChatApp.model.Account;
import chanhchung.vn.ChatApp.socket.Server;


/**
 * Hello world!
 *
 */
public class App 
{
	public static ArrayList<Account> _arr = null;
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, NoSuchFieldException, SecurityException  {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		//UIManager.put("OptionPane.minimumSize",new Dimension(500,500)); 
		UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 18));
		UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 14));
		System.setProperty("file.encoding", "UTF-8");
		java.lang.reflect.Field charset = null;
		charset = java.nio.charset.Charset.class.getDeclaredField("defaultCharset");
		charset.setAccessible(true);
		charset.set(null, null);
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ServerScreen ss = new ServerScreen();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    	
    	
    	
    	//XmlFileFactory.readListAccount();
    }
}
