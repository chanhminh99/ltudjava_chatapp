package chanhchung.vn.ChatApp.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import chanhchung.vn.ChatApp.io.XmlFileFactory;
import chanhchung.vn.ChatApp.socket.Server;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServerScreen extends JFrame {

	private JPanel contentPane;
	JOptionPane jop;
	public static Server s = null;
	/**
	 * 
	 * Launch the application.
	 */
	


	/**
	 * Create the frame.
	 * @param s 
	 */
	public ServerScreen() {
		setTitle("Chat-App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(s == null) {
					s = new Server(Server.port);
					s.start();
					jop = new JOptionPane();
					jop.showMessageDialog(null, "Server start completely. Ok!");
				} else {
					jop = new JOptionPane();
					jop.showMessageDialog(null, "Server is running... Error!");
				}
			}
		});
		contentPane.add(btnStart);
		
		JButton btnNewClient = new JButton("New Client");
		btnNewClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(s==null) {
					jop = new JOptionPane();
					jop.showMessageDialog(null, "Server is not running...You must Start the server before create new client!");
				} else {
					LoginScreen ls = new LoginScreen();
				}
				
				
			}
		});
		contentPane.add(btnNewClient);
		
		setVisible(true);
	}

}
