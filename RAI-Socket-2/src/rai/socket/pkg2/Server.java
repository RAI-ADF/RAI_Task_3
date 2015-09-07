/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rai.socket.pkg2;

/**
 *
 * @author Adam
 */
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
 * @author Adam
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int portnumber = 1234;
        try {
            ServerSocket serverSocket = new ServerSocket(portnumber);
           
            
           while(true){
               System.out.println("server is on");
                Socket clientsocket = serverSocket.accept();
                ServerThread Sthread = new ServerThread(clientsocket);
                Sthread.start();
                
               }
            
        } catch (IOException ex) {
            System.out.println("exception on port "+portnumber);
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}