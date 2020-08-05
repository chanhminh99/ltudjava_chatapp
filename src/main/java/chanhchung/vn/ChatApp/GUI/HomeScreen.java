package chanhchung.vn.ChatApp.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import chanhchung.vn.ChatApp.socket.Client;
import chanhchung.vn.ChatApp.socket.Server;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class HomeScreen extends JFrame {

	private JPanel contentPane;
	private JTextField jtf1 = new JTextField();
	
	JLabel lblOnline = new JLabel("Online");
	JButton btnRefresh = new JButton("Refresh");
	JPanel panel_1 = new JPanel();
	
	
	JScrollPane userScrollPane = new JScrollPane();
	JList<String> userList;
	JOptionPane jop;
	
	
	private DefaultListModel<String> modelUser;
	private JButton btnLogout = new JButton("");
	
	public ArrayList<String> getListUserOnline() {
			
			
		
		ArrayList<String> _rs = new ArrayList<>();
			
		if(Server._listUser.size() > 0) { 
			for(String item : Server._listUser.keySet()) {
					_rs.add(item);
			}
		}
		
		
		return _rs;
	}
	
	
	public void userOnlineRender() {
		ArrayList<String> _render = getListUserOnline();
		if(_render.size() > 0) {
			modelUser = new DefaultListModel<String>();
			
			for (String i : _render) {
				modelUser.addElement(i);
			}
			userList = new JList<>(modelUser);			
			userList.setSelectedIndex(0);
			userScrollPane.setViewportView(userList);
		} else {
			System.out.println("Danh sach trong");
		}
	}
	
	
	

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public HomeScreen(final String username) throws IOException {
		setResizable(false);
		
		final Client c = new Client(InetAddress.getLocalHost(), Server.port, username);
		//ChatFrameScreen cfs = new ChatFrameScreen(username, c);
		c.start();
		
		Image img_logout = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
		img_logout = img_logout.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		
		btnLogout.setIcon(new ImageIcon(img_logout));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					c.stop();
					LoginScreen login = new LoginScreen();
					dispose();
					jop = new JOptionPane();
					jop.showMessageDialog(null, "Đăng xuất thành công!");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 607, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		lblOnline.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnline.setForeground(Color.RED);
		lblOnline.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Server._listUser == null) {
					System.out.println("ok");
				} else {
					userOnlineRender();
				}
			}
		});
		
		Font fieldFont = new Font("Arial", Font.PLAIN, 20);
		btnRefresh.setFont(fieldFont);
		btnRefresh.setBackground(Color.white);
		btnRefresh.setForeground(Color.BLACK.brighter());
		btnRefresh.setBorder(BorderFactory.createBevelBorder(0));
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				userOnlineRender();
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOnline, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(userScrollPane, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(18)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(18))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblOnline)
							.addGap(13)
							.addComponent(btnRefresh)
							.addGap(18)
							.addComponent(userScrollPane, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JButton btnJohn = new JButton("Join");
		btnJohn.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnJohn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String selectedUser = userList.getSelectedValue();
					
					if(selectedUser.compareToIgnoreCase(username) == 0) {
						try {
							boolean check = checkToCreate(username);
							if(check) {
								//c.stop();
								//c.run();
								c.createRoom();
								ChatFrameScreen cfs = new ChatFrameScreen(username, c);
								c.receiveMess(cfs);
							} else {
								jop = new JOptionPane();
								jop.showMessageDialog(null, "Bạn đã tạo phòng rồi");
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						boolean check = checkToAccess(selectedUser);
						if(check) {
							//c.stop();
						//	c.run();
							c.accessRoom(selectedUser);
							ChatFrameScreen cfs = new ChatFrameScreen(selectedUser, c);
							c.receiveMess(cfs);
						} else {
							jop = new JOptionPane();
							jop.showMessageDialog(null, "Phòng chưa được host tạo");
						}
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		JLabel lblUsername = new JLabel("");
		lblUsername.setFont(new Font("Ubuntu Mono", Font.PLAIN, 30));
		lblUsername.setText("Username: " + username);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hostUser = userList.getSelectedValue();
				System.out.println(hostUser);
				if(hostUser.compareToIgnoreCase(username) == 0) {
					try {
						boolean check = checkToCreate(hostUser);
						if (check) {
						//	c.stop();
						//	c.run();
							c.createRoom();
							ChatFrameScreen cfs = new ChatFrameScreen(username, c);
							c.receiveMess(cfs);
						} else {
							jop = new JOptionPane();
							jop.showMessageDialog(null, "Bạn đã tạo phòng rồi");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					jop = new JOptionPane();
					jop.showMessageDialog(null, "Vui lòng ấn button John !");
				}
				
				
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(39)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblUsername)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGap(18)
							.addComponent(btnLogout, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(31))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnJohn, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
							.addComponent(btnCreate)
							.addGap(94))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnLogout, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblUsername, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(99)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnJohn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(121, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
		
		userOnlineRender();
		setLocationRelativeTo(null);
		setVisible(true);
	}


	protected boolean checkToAccess(String host) {
		if(Server._listRoom.containsKey(host)) {
			return true;
		}
		return false;
	}


	protected boolean checkToCreate(String hostname) {
		if(!Server._listRoom.containsKey(hostname)) {
			return true;
		}
		return false;
	}

	
}
