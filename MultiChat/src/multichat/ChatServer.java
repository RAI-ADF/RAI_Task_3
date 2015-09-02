/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multichat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.IIOException;

/**
 *
 * @author fadhlil
 */
public class ChatServer {  
    
    public static void main(String[] args){
        int port = 2000;
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            
            while (true) {                
                Socket clientSocket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(clientSocket);
                serverThread.start();
            }
        }catch(IOException e){
            System.out.println("Exception cought when trying to listen on port 2000 or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
