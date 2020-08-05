package chanhchung.vn.ChatApp.model;

public class Message {
	
	public Message(String content, boolean isFile, String fromUser) {
		super();
		this.content = content;
		this.isFile = isFile;
		this.fromUser = fromUser;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isFile() {
		return isFile;
	}
	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	
	
	
	private String content;
	private boolean isFile;
	private String fromUser;
}
