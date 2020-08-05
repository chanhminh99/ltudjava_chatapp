package chanhchung.vn.ChatApp.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import chanhchung.vn.ChatApp.GUI.ServerScreen;


public class Server implements Runnable {
	
	private static int PORTNUMBER= 8888;
	
	public static int port = PORTNUMBER ;
	
	private ServerSocket server = null;
	private static Thread thread = null;
	
	//public static ArrayList<Socket> _arrS = null;
	
	public static HashMap<String , Socket > _listUser = null;
	
	public static HashMap<String, ArrayList<String>> _listRoom= null;
	
	
	public void start(){
		if(thread == null)
			thread = new Thread(this); thread.start();
	}
	
	public void stop(){
		if(thread != null)
			thread.interrupt(); thread = null;
	}
	

	@SuppressWarnings("static-access")
	public Server(int port) {
		super();
		this.port = port;
	}


	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.execute();
	}
	

	
	public void execute() {
		try {
			//_arrS = new ArrayList<>();
			_listUser = new HashMap<>();
			_listRoom = new HashMap<>();
			server = new ServerSocket(port);
			//WriteServer write = new WriteServer();
			//write.start();
			System.out.println("Server is listening....");
			while(true) {
				Socket s = server.accept();
				System.out.println("Da ket noi voi " + s);
				DataInputStream dis = new DataInputStream(s.getInputStream());
				// Xu li client ket noi vao
				String username = dis.readUTF();
				
				Server._listUser.put(username, s);
				ReadServer r = new ReadServer(s);
				r.start();
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	


class ReadServer extends Thread { 
	
	private Socket server;
	private DataInputStream dis = null; // nhan tu client
	private DataOutputStream dos =null;// gui cho client 
	
	public ReadServer(Socket server) {
		super();
		this.server = server;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		
		try {
			dis = new DataInputStream(server.getInputStream());
			while(true)
			{
				byte messageByte = dis.readByte(); 
				if(messageByte == 1) { // Tao phong
					String hostUser = dis.readUTF();
					ArrayList<String> _members = new ArrayList<>();
					Server._listRoom.put(hostUser, _members);
					System.out.println("Tao phong ne");
				} else if (messageByte == 2) { // Vao phong
					String username = dis.readUTF();
					String hostUser = dis.readUTF();
					Server._listRoom.get(hostUser).add(username);
					
					System.out.println("Add vao ne");
				} else if (messageByte == 3) { // Gui mess
					String username = dis.readUTF();
					String host = dis.readUTF();
					String sms = dis.readUTF();
					
					ArrayList<String> _members = Server._listRoom.get(host);
					
					if(username.equalsIgnoreCase(host)) { // Host send to member
						System.out.println("host gui" + _members.size());
						
						for(String item : _members) {
							Socket s = Server._listUser.get(item);
							dos = new DataOutputStream(s.getOutputStream());
							dos.writeByte(3);
							dos.writeUTF(username);
							dos.writeUTF(sms);
							dos.flush();
						}
					} else { // Member send
						for(Entry<String,Socket> item : Server._listUser.entrySet()) {
							String user =item.getKey();
							Socket s = item.getValue();
							if(s.getPort() != server.getPort() && (_members.contains(user) || user.compareToIgnoreCase(host) == 0)) {
								dos = new DataOutputStream(s.getOutputStream());
								dos.writeByte(3);
								dos.writeUTF(username);
								dos.writeUTF(sms);
								dos.flush();
							}
						}
					}
					
				} else if (messageByte == 4) { // Kick all
					String host = dis.readUTF();
					ArrayList<String> _members = Server._listRoom.get(host);
					for(Entry<String, Socket> item : Server._listUser.entrySet() ) {
						String user =item.getKey();
						Socket s = item.getValue();
						if(s.getPort() != server.getPort() && (_members.contains(user))) {
							dos = new DataOutputStream(s.getOutputStream());
							dos.writeByte(4);
						}
					}
					Server._listRoom.remove(host);
				} else if(messageByte == 5) { // Kick user
					String host = dis.readUTF();
					String kick = dis.readUTF();
					Server._listRoom.get(host).remove(kick);
					//Server._listUser.remove(kick);
					ArrayList<String> _members = Server._listRoom.get(host);
					System.out.println(_members.size());
					
					for(Entry<String, Socket> item : Server._listUser.entrySet() ) {
						Socket s = item.getValue();
						String user = item.getKey();
						if(s.getPort() != server.getPort() && (_members.contains(user) || user.compareToIgnoreCase(host) == 0  )) {
							dos = new DataOutputStream(s.getOutputStream());
							dos.writeByte(5);
							dos.writeUTF(kick);
						}
					}
					server.close(); // Ngung lai socket
				} else if (messageByte == 6) { // Stop
					String user= dis.readUTF();
					boolean check = Server._listUser.remove(user, Server._listUser.get(user));
					if(check) 
						System.out.println("ok");
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			try {
				dis.close();
				server.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}

class WriteServer extends Thread { // Code nay khong dung
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		DataOutputStream dos = null;
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			String sms = "Server:" + sc.nextLine();
			try {
				for(Entry<String, Socket> item : Server._listUser.entrySet()) {
					dos = new DataOutputStream(item.getValue().getOutputStream());
					dos.writeUTF(sms);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					dos.close();
					sc.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}		
	}
}