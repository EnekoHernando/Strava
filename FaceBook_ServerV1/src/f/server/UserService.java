package f.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.StringTokenizer;

public class UserService extends Thread{
	private DataInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;
	private String DELIMETER = "#";
	private Map<String, String> rU;
	
	public UserService(Socket socket, Map<String, String> registeredUsers) {
		try {
			System.out.println("SOCKET RECEIVE");
			this.tcpSocket =socket;
			this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.rU = registeredUsers;
			this.start();
		} catch (Exception e) {
			System.err.println("# UserService - TCPConnection IO error:" + e.getMessage());
		}
	}
	
	@Override
	public void run() {
		try {
			String data = this.in.readUTF();
			System.out.println("Received data: " + data);
			StringTokenizer tokenizer = new StringTokenizer(data, DELIMETER);
			String email = tokenizer.nextToken();
			String password = tokenizer.nextToken();
			String type = tokenizer.nextToken();
			switch (type) {
			case "Log in":
				data = logIn(email, password);
				this.out.writeUTF(data);
				break;
			default:
				data = signIn(email, password);
				this.out.writeUTF(data);
				break;
			}
		} catch (EOFException e) {
			System.err.println("   # UserService - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # UserService - TCPConnection IO error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # UserService - TCPConnection IO error:" + e.getMessage());
			}
		}
	}
	public String logIn(String email, String password) {
		if(this.rU.containsKey(email)) {
			if(this.rU.get(email).equals(password)){
				return "OK";
			}
		}
		return "ERROR";
	}
	public String signIn(String email, String password) {
		if(!this.rU.containsKey(email)) {
			this.rU.put(email, password);
			return "OK";
		}
		return "ERROR";
	}
}
