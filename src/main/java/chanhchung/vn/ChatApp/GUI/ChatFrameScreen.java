package chanhchung.vn.ChatApp.GUI;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import chanhchung.vn.ChatApp.model.Message;
import chanhchung.vn.ChatApp.socket.Client;
import javax.swing.JLabel;
import java.awt.Image;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JProgressBar;



public class ChatFrameScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtChat;
	private DefaultListModel<Message> modelChat;
	public  ArrayList<Message> _chats;
	JOptionPane jop;
	public String username;

	/**
	 * Launch the application.
	 */
	JScrollPane scrollPane;
	JList list;
	
	public void renderChat(String username) {
		Message model = _chats.get(_chats.size() -1);
		modelChat.addElement(model);
		list= new JList<>(modelChat);
		scrollPane.setViewportView(list);
		list.setCellRenderer(new MessageRenderer(username));
	}
	
		
	
	public ChatFrameScreen(final String host, final Client c) {
		username = c.getUsername();
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Người dùng: " + c.getUsername());
		setBounds(100, 100, 560, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		scrollPane = new JScrollPane();
		_chats = new ArrayList<>();
		modelChat = new DefaultListModel<>();
		
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				e.getAdjustable().setValue(e.getAdjustable().getMaximum());
			}
		});
		
		txtChat = new JTextField();
		
		txtChat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String content = txtChat.getText();
				if(content.compareToIgnoreCase("") != 0) {
					try {
						
						c.handle(c.getUsername(), host, content);
						
						Message model = new Message(content, false, c.getUsername());
						_chats.add(model);
						
						renderChat(username);

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					txtChat.setText("");
				}
				
			}
		});

		txtChat.setForeground(Color.BLACK);
		txtChat.setText("");
		txtChat.setColumns(30);
		
		JButton btnFile = new JButton("File");
		
		JLabel lblHost = new JLabel("");
		lblHost.setFont(new Font("Ubuntu Mono", Font.PLAIN, 30));
		lblHost.setText("Chat room: " + host);
		

		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 495, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtChat, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnFile, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHost))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(lblHost, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtChat, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
						.addComponent(btnFile))
					.addContainerGap())
		);
		
		
		
		JButton btnHaha = new JButton("");
		Image img_haha = new ImageIcon(this.getClass().getResource("/happy.png")).getImage();
		img_haha = img_haha.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		
		btnHaha.setIcon(new ImageIcon(img_haha));
		btnHaha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtChat.setText(txtChat.getText() + ":v");
			}
		});
		panel.add(btnHaha);
		
		JButton btnSad = new JButton("");
		btnSad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtChat.setText(txtChat.getText() + ":(");
			}
		});
		Image img_sad = new ImageIcon(this.getClass().getResource("/unhappy.png")).getImage();
		img_sad = img_sad.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		
		btnSad.setIcon(new ImageIcon(img_sad));
		panel.add(btnSad);
		
		JButton btnAngry = new JButton("");
		Image img_angry = new ImageIcon(this.getClass().getResource("/mad.png")).getImage();
		img_angry = img_angry.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		btnAngry.setIcon(new ImageIcon(img_angry));
		
		btnAngry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtChat.setText(txtChat.getText() + ":x");
			}
		});
		panel.add(btnAngry);
		
		JButton btnWow = new JButton("");
		btnWow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtChat.setText(txtChat.getText() + ":o");
			}
		});
		Image img_wow = new ImageIcon(this.getClass().getResource("/surprised.png")).getImage();
		img_wow = img_wow.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		btnWow.setIcon(new ImageIcon(img_wow));
		panel.add(btnWow);
		
		JButton btnHeart = new JButton("");
		btnHeart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtChat.setText(txtChat.getText() + ":3");
			}
		});
		Image img_heart = new ImageIcon(this.getClass().getResource("/heart.png")).getImage();
		img_heart = img_heart.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		btnHeart.setIcon(new ImageIcon(img_heart));
		panel.add(btnHeart);
		contentPane.setLayout(gl_contentPane);
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				try {
			
					if(c.getUsername().equalsIgnoreCase(host)) // Chu phong out 
					{
						// Kich out
						c.kickAll(host);
						jop = new JOptionPane();
						jop.showMessageDialog(null, "Giải tán phòng!");
						
						
					} else {
						c.killUser(host, c.getUsername());
						c.stop();
						c.run();
						jop = new JOptionPane();
						jop.showMessageDialog(null, "Rời phòng thành công!");
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
