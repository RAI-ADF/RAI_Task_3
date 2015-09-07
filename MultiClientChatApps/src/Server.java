
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void listenServer()
    {
		int portNumber=2000;
	try
	(
		ServerSocket serverSocket = new ServerSocket(portNumber);
		while(true)
		{
			Socket clientSocket = serverSocket.accept();
			ServerThread serverThread = new ServerThread(clientSocket);
			serverThread.start();
		}
		}catch (IOException e){
			System.out.println("caught when trying to listen on port "+portNumber);
			System.out.println(e.getMessage());
		}
    }