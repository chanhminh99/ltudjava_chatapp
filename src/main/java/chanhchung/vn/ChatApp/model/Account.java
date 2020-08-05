package chanhchung.vn.ChatApp.model;

public class Account 
{
	private String _username;
	private String _password;
	
	public Account(String _username, String _password) {
		super();
		this._username = _username;
		this._password = _password;
	}
	
	
	public String get_username() {
		return _username;
	}
	public void set_username(String _username) {
		this._username = _username;
	}
	public String get_password() {
		return _password;
	}
	public void set_password(String _password) {
		this._password = _password;
	}
	
}
