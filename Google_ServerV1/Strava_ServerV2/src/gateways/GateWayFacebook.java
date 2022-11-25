package gateways;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class GateWayFacebook implements IGateWay{
	private static GateWayFacebook instance;
	private String serverIP;
	private int serverPort;
	private static String DELIMITER = "#";
	
	public static GateWayFacebook getInstance(String ip, int fport) {
		if(instance==null) {
			instance = new GateWayFacebook(ip, fport);
		}
		return instance;
	}
	private GateWayFacebook(String ip, int fport) {
		this.serverIP = ip;
		this.serverPort = fport;
	}
	@Override
	public boolean logIn(String email, String password) {
		String message = email+DELIMITER+password+DELIMITER+"Log in";
		String answer = null;
		try (Socket socket = new Socket(this.serverIP, this.serverPort)) {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(message);
			answer = in.readUTF();
		} catch (UnknownHostException e) {
			System.err.println("# Face. SocketClient: Socket error: " + e.getMessage());	
		}catch (EOFException e) {
			System.err.println("# Face. SocketClient: EOF error: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("# Face. ScocketClient: IO error: " + e.getMessage());
		}
		return (answer.equals("OK")) ? true : false; 
	}
	@Override
	public boolean register(String email, String password) {
		String message = email+DELIMITER+password+DELIMITER+"Register";
		String answer = null;
		try (Socket socket = new Socket(this.serverIP, this.serverPort)) {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(message);
			answer = in.readUTF();
		} catch (UnknownHostException e) {
			System.err.println("# Face. SocketClient: Socket error: " + e.getMessage());	
		}catch (EOFException e) {
			System.err.println("# Face. SocketClient: EOF error: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("# Face. SocketClient: IO error: " + e.getMessage());
		}
		return (answer.equals("OK")) ? true : false;
	}
}
