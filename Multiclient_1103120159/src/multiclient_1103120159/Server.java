/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclient_1103120159;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Fauzan Razandi
 */
public class Server {
    public static void main(String[] args){
    int portNumber = 2222;
    Socket clientSocket = null;
    try{
        System.out.println("The Server is Running");
        ServerSocket serverSocket = new ServerSocket(portNumber);
            while(true){
                clientSocket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(clientSocket);
                serverThread.start();         
    }}
    catch(IOException e){
        System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
        System.out.println(e.getMessage());
    }
    }
}
