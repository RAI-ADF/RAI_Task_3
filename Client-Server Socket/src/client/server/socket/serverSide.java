/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client.server.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author novandypurnadrd
 */
public class serverSide {
   
    public static void main(String[] args) {
       int portNumber = 80;
       
       try{
           ServerSocket serverSocket = new ServerSocket(portNumber);
           while(true){
           Socket clientSocket = serverSocket.accept();
           ServerThread serverThread = new ServerThread(clientSocket);
           serverThread.start();
           }
           /*
           DataOutputStream outStream = new DataOutputStream(clientSocket.getOutputStream());
           BufferedReader inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
           
           System.out.println("Just Connected with" +clientSocket.getRemoteSocketAddress());
           String inputLine;
           while ((inputLine = inStream.readLine()) != null){
               System.out.println("client says :"+inputLine);
               outStream.writeBytes("messagereceived" + "\n");
           }
           clientSocket.close();
           */
       }    
       catch (IOException e){ 
           System.out.println("Exception caught when trying to listen on port" +portNumber + "or listening for a connection");
           System.out.println(e.getMessage());
       }
    }
}
