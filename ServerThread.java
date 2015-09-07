
import java.net.Socket;

public class ServerThread extends Thread {
	private Socket clientSocket=null;
	public ServerThread(Socket fromClient) {
		super("serving client");
		this.clientSocket=fromClient;
	}
	public void run() {
	}
}
