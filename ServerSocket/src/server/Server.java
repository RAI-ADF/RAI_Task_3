/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author user
 */
public class Server {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        int portNumber = 1234;
        
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            
            while(true){
                Socket clientSocket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(clientSocket);
                serverThread.start();
            }

        } catch(IOException e) {
            System.out.println("Exception cought when trying to listen on port "+portNumber+" or istening for a connection");
            System.out.println(e.getMessage());
        }
    }
    
}
