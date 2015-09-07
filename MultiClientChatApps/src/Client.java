
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Client {
    
    public void runClient(){
	String serverName = "10.14.211.121";
	int portNumber = 2000;
	try
	(
		Socket clientSocket = new Socket(serverName, portNumber);
		PrintWriter outStream = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		BufferedReader keyBoard = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Connected to "+clientSocket.getRemoteSocketAddress());
			String userInput;
			while((userInput=keyBoard.readLine()) != null) {
				outStream.println(userInput);
				String message = inStream.readLine();
				System.out.println("reply from server: "+message);
			}
		}catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + serverName);
			System.exit(1);
		}
	}
		
	
}
