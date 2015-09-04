/*
 * RAI Task 3 - Socket Programming
 *
 * Dieka Nugraha Karyana
 * 1103120057
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author TOSHIBA
 */
public class Server {
    
    
    public static void main(String[] args) {
        
        try {
            final int port = 9091;
            
            ServerSocket serverSocket = new ServerSocket(port);
            
            System.out.println("Server dinyalakan pada : " + new Date().toString());
            System.out.println("Menunggu Client Terhubung........");
            
            while(true){
                Socket clientSocket = serverSocket.accept();
                new ServerThread(clientSocket).start();
            }
            
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        
    }
    
    
    
}
