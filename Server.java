/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rai_task3;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Taufiq
 */
public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket;
        int port = 9091;
        
        try {
            serverSocket = new ServerSocket(port);
            
        System.out.println("Server is on");
            while(true){
                Socket clientSocket = serverSocket.accept();
                new ServerThread(clientSocket).start();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
