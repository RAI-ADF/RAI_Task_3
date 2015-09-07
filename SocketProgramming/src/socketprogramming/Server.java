/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package socketprogramming;

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
 * @author notebook
 */
public class Server {
    public static void main(String[] args){
        int portNumber = 1437;
        try{
            ServerSocket server = new ServerSocket(portNumber);
            
            while (true){
                Socket client = server.accept();
                ServerThread group = new ServerThread(client);
                group.start();
            }
        } catch (IOException ex) {
            System.out.println("Exception caught when listening to port "+ portNumber +" or listening for a connnection");
            System.out.println("e.getMessage");
        }
    }
}
