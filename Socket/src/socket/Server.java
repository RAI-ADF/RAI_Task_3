/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

/**
 *
 * @author Adam
 * original copy from 
 * http://cs.lmu.edu/~ray/notes/javanetexamples/
 */
public class Server {


    private static final int PORT = 9001;


    private static HashSet<String> names = new HashSet<String>();


    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();


    public static void main(String[] args) throws Exception {
        System.out.println("Server ON ");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new ServerThread(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }

   
    
}