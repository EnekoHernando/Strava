package f.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;


public class FacebookServer {
	private static int numClients = 0;
	private final static Map<String, String> registeredUser = new HashMap<>();
	public static void main(String args[]) {
		if (args.length < 1) {
			System.err.println(" # Usage: FaceBook [PORT]");
			System.exit(1);
		}
		
		//args[1] = Server socket port
		int serverPort = Integer.parseInt(args[0]);
		
		try (ServerSocket tcpServerSocket = new ServerSocket(serverPort);) {
			System.out.println(" - FacebookServer: Waiting for connections '" + tcpServerSocket.getInetAddress().getHostAddress() + ":" + tcpServerSocket.getLocalPort() + "' ...");
			
			while (true) {
				new UserService(tcpServerSocket.accept(), registeredUser);
				System.out.println(" - FacebookServer: New client connection accepted. Client number: " + ++numClients);
			}
		} catch (IOException e) {
			System.err.println("# FacebookServer: IO error:" + e.getMessage());
		}
	}
}
