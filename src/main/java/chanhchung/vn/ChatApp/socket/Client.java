package chanhchung.vn.ChatApp.socket;

import java.awt.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

import chanhchung.vn.ChatApp.GUI.ChatFrameScreen;
import chanhchung.vn.ChatApp.model.Message;




public class Client implements Runnable {
	private InetAddress host;
	private int port;
	//private static String username;
	
	private Thread thread = null;
	public Socket client = null;
	public ReadClient read = null;
	//public WriteClient write = null;
	private DataOutputStream dos = null;
	private DataInputStream dis = null;
	private String username;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Client(InetAddress host, int port ,String username) {

		this.host=host;
		this.port=port;
		this.username= username;
	
	}
	
	public void start() throws IOException{
		
		if(thread == null){
			thread = new Thread(this);
			thread.start();	
		}
	}
	
	public void stop() throws IOException{
		if(thread != null) {
			thread.interrupt(); thread = null;
			dos = new DataOutputStream(client.getOutputStream());
			dos.write(6);
			dos.writeUTF(username);
			client.close();
			System.out.println("Da ngat ket noi " + client);
		}
	}
	
	public void restart() throws IOException {
		start();
	}
	
	public synchronized void handle(String from , String hostUser,String sms) throws IOException {
		dos = new DataOutputStream(client.getOutputStream());
		dos.writeByte(3);
		dos.writeUTF(from);
		dos.writeUTF(hostUser);
		dos.writeUTF(sms);
		dos.flush();
	}
	
	public void kickAll(String hostUser) throws IOException {
		dos = new DataOutputStream(client.getOutputStream());
		dos.writeByte(4);
		dos.writeUTF(hostUser);
		dos.flush();
			
	}
	
	public void killUser(String hostUser,String kick) throws IOException {
		dos = new DataOutputStream(client.getOutputStream());
		dos.writeByte(5);
		dos.writeUTF(hostUser);
		dos.writeUTF(kick);
		dos.flush();
	}
	
	
	// Byte: 1 Create
	// Byte: 2 Acess
	// Byte: 3 SendMessage
	// Byte: 4 Kich all member
	public void accessRoom(String hostUser) throws IOException {
		dos = new DataOutputStream(client.getOutputStream());
		dos.writeByte(2);
		dos.writeUTF(username);
		dos.writeUTF(hostUser);
		dos.flush();
		
	}
	
	public void createRoom() throws IOException {
		dos = new DataOutputStream(client.getOutputStream());
		dos.writeByte(1);
		dos.writeUTF(username);
		dos.flush();
		
	}
	
	public void sendMessage(String host,String sms) throws IOException {
		dos = new DataOutputStream(client.getOutputStream());
		dos.writeByte(3);
		dos.writeUTF(username);
		dos.writeUTF(host);
		dos.writeUTF(sms);
		dos.flush();
		
	}
	
	public void receiveMess(ChatFrameScreen chatFrameScreen) {
		read = new ReadClient(client,chatFrameScreen);
		read.start();
	}
	
	

	private  void execute() throws IOException {
		client = new Socket(host, port);
		// Dang ki username den server
		dos = new DataOutputStream(client.getOutputStream());
		dos.writeUTF(username);
		
	}
	


	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


 class ReadClient extends Thread {
	private Socket client;
	private DataInputStream dis= null;
	private ChatFrameScreen cfs = null;
	//private boolean isDone = false;

	public ReadClient(Socket socket, ChatFrameScreen chatFrameScreen) {
		this.client = socket;
		this.cfs = chatFrameScreen;
	}
	
	@Override
	public void run() {
		super.run();
		
		try {
			dis = new DataInputStream(client.getInputStream());
			
			while(true) {
				byte messageByte = dis.readByte();
				if (messageByte == 3)  {
					String user = dis.readUTF(); // Gui tu ai
					String sms = dis.readUTF();
					System.out.println(user + " is sending: " + sms);
					Message model = new Message(sms, false, user);
					cfs._chats.add(model);
					cfs.renderChat(cfs.username);
				} else if (messageByte == 4) { // Kick all
					JOptionPane jop = new JOptionPane();
					JOptionPane.showMessageDialog(null, "Nhóm đã giải tán!");
					cfs.dispose();
				} else if (messageByte == 5) { // Kick ne
					String userOut = dis.readUTF();
					String sms = userOut + " đã rời khỏi phòng";
					Message model = new Message(sms, false, "Server");
					cfs._chats.add(model);
					cfs.renderChat(cfs.username);
				} else {
					// Khi nao thi ngung doc ? Out room
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				dis.close();
				client.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}

//class WriteClient extends Thread {
//	private Socket client;
//	private String username;
//	private String hostUser;
//	private Scanner sc = new Scanner(System.in);
//	DataOutputStream dos = null;
//	private ChatFrameScreen cfs = null;
//	
//	
//	public WriteClient(Socket s, String name,String to,ChatFrameScreen cfs) {
//		this.client = s;
//		this.username=name;
//		this.hostUser=to;
//		this.cfs = cfs;
//	}
//	
//	
//	
//	
//	public void sendMessage(String from,String to,String mess) throws IOException {
//		dos.writeByte(3); //flag: chat 1 vs 1
//		dos.writeUTF(from);
//		dos.writeUTF(to);
//		dos.writeUTF(mess);
//		dos.flush();
//	}
//	
//	
//	
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		super.run();
//		try {
//			dos = new DataOutputStream(client.getOutputStream());
//			while(true) {
//				 // user name & messing
//				
////				String sms = sc.nextLine();
//				sendMessage(username, hostUser, cfs.message);
//				
//				
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			try {
//				dos.close();
//				client.close();
//				sc.close();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
//	}
//}