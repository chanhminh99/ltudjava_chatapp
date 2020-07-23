package chanhchung.vn.ChatApp.GUI;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import chanhchung.vn.ChatApp.io.XmlFileFactory;
import chanhchung.vn.ChatApp.model.Account;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ứng  dụng Chat- HCMUS");
		lblNewLabel.setBounds(246, 60, 250, 30);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		JLabel userLabel = new JLabel("Tài khoản:");
		userLabel.setBounds(216, 103, 80, 30);
	    JLabel passLabel = new JLabel("Mật khẩu:");
	    passLabel.setBounds(216, 146, 70, 30);
	    final JTextField userText = new JTextField(20);
	    userText.setBounds(291, 103, 150, 30);
	    final JPasswordField passText = new JPasswordField(20);
	    passText.setBounds(291, 146, 150, 30);

	    JButton btn_logion = new JButton();
	    // Xử lí login
	    btn_logion.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		String user = userText.getText();
	    		String password = passText.getText();
	    		JOptionPane jop = new JOptionPane();
	    		try {
					boolean isOk = checkLogin(user,password);
					if (isOk) {
						// Khởi tạo màn hình chat ở đây nha
						System.out.println("Đăng nhập thành công!");
						dispose();
					} else {
						jop.showMessageDialog(null, "Đăng nhập thất bại. Vui lòng kiểm tra lại!");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    });
	    btn_logion.setBounds(318, 225, 86, 37);
	    btn_logion.setText("Login");
	    
	    contentPane.add(userLabel);
	    contentPane.add(passLabel);
	    contentPane.add(userText);
	    contentPane.add(passText);
	    contentPane.add(btn_logion);
	    
	    JLabel lblBnChaC = new JLabel("Bạn chưa có tài khoản?");
	    lblBnChaC.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lblBnChaC.setBounds(246, 196, 158, 16);
	    contentPane.add(lblBnChaC);
	    
	    JLabel lblngK =  new JLabel("<HTML><U>Đăng kí</U></HTML>");
	    lblngK.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    lblngK.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lblngK.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		RegisterScreen item = new RegisterScreen();
	    		item.setVisible(true);
	    		dispose();
	    	}
	    });
	    lblngK.setBounds(410, 189, 62, 30);
	    contentPane.add(lblngK);
	    
//	    Font font = lblngK.getFont();
//	    Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
//	    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	    
	    setLocationRelativeTo(null);
	    setVisible(true);
	}

	protected boolean checkLogin(String user, String password) {
		ArrayList<Account> _arr = XmlFileFactory.readListAccount();
		for(Account item : _arr) {
			if (item.get_username().compareToIgnoreCase(user) == 0 && item.get_password().compareToIgnoreCase(password) == 0) {
				return true;
			}
		}
		return false;
	}
}
