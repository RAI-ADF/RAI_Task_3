
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
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
public class ServerThread {
final Socket client;
static Map<String, DataOutputStream> users = new HashMap<>();
String username="";
DataOutputStream outputStream = null;
BufferedReader inputStream = null;

public ServerThread(Socket client)
{
	this.client=client;
}

public void run()
{
                DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
			while (true)
			{
				outputStream.writeBytes("Submit your username" + "\n");
				username = inputStream.readLine();
				if (username==null)
				{
					return;
				}
				synchronized(users)
				{
					if (!users.containKeys(username)){
						outputStream.writeBytes("username has already been used" + "\n");
						user.put(username, outputStream);
						break;
					}
				}
			}
			System.out.println("Just connected with "+ clientSocket.getRemoteSocketAddress());
			String inputLine;
			while ((inputLine=inputStream.readLine()) != null) {
				System.out.println("Client says : "+inputLine);
				outputStream.writeBytes("Message Received" + "\n");
			}
			clientSocket.close();
i
}


    
}
