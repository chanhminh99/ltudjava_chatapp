package chanhchung.vn.ChatApp.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import chanhchung.vn.ChatApp.io.XmlFileFactory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterScreen extends JFrame {

	private JPanel contentPane;
	private JTextField userText;
	private JPasswordField passText;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public RegisterScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblngKTi = new JLabel("Đăng kí tài khoản");
		lblngKTi.setBounds(5, 5, 422, 25);
		lblngKTi.setHorizontalAlignment(SwingConstants.CENTER);
		lblngKTi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblngKTi);
		
		final JLabel lblTnngNhp = new JLabel("Tên đăng nhập:");
		lblTnngNhp.setBounds(61, 68, 91, 16);
		contentPane.add(lblTnngNhp);
	    
	    JLabel lblMtKhu = new JLabel("Mật khẩu:");
	    lblMtKhu.setBounds(61, 107, 77, 16);
	    contentPane.add(lblMtKhu);
		
		userText = new JTextField();
		userText.setBounds(164, 65, 127, 22);
		contentPane.add(userText);
		userText.setColumns(10);
		
		
		
		JButton btnngK = new JButton("Đăng ký");
		btnngK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = userText.getText();
				String pass = passText.getText();
				XmlFileFactory.addNewAccount(user, pass);
				
			}
		});
		

	    passText = new JPasswordField(20);
	    passText.setBounds(164, 100, 127, 23);
	    contentPane.add(passText);
		btnngK.setBounds(174, 136, 97, 25);
		contentPane.add(btnngK);
		
		JButton btnTrLi = new JButton("Trở lại");
		btnTrLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginScreen login = new LoginScreen();
				dispose();
			}
		});
		btnTrLi.setBounds(15, 8, 97, 25);
		contentPane.add(btnTrLi);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
