package Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Surya
 */

public class Server {
    public static void main(String [] args) {
        try {
            ServerSocket server = new ServerSocket(45678);
            
            while (true) {
                Socket client = server.accept();
                new ServerThread(client).start();
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
}
