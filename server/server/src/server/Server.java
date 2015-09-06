/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import server.ServerThread;


/**
 *
 * @author dwiharyanto
 */
public class Server {
public static  int portNumber = 1000;
    /**
     * @param args the command line arguments
     */
    

    public static void main(String[] args) {
        // TODO code application logic here
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            
            
            while(true){
            Socket clientSocket = serverSocket.accept();
            ServerThread serverThread = new ServerThread(clientSocket);
            serverThread.start();
            }
            
        } catch (Exception e) {
            System.out.println("error on "+portNumber+" or listening");
            System.out.println(e.getMessage());
        }
    }
    
}
