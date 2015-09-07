/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author erpin
 */
public class server {
    
    public static void main (String[]args) throws IOException {
        ServerSocket server = null;
        Socket client;
        try {
            server = new ServerSocket(1234);
        } catch (IOException ie) {
            System.out.println("TIDAK BISA BUKA SOCKET.");
            System.exit(1);
        }
        String temp;
        
        while (true){
            try {
                client = server.accept();
                OutputStream clientout = client.getOutputStream();
                PrintWriter pw = new PrintWriter(clientout, true);
                InputStream clientin = client.getInputStream();
                BufferedReader br = new BufferedReader (new InputStreamReader (clientin));
                temp = br.readLine();
                System.out.println(temp);
                br.close();
                pw.close();
                clientin.close();
                clientout.close();
            } catch (IOException ie){
                ie.printStackTrace();
        } 
                }
    }
}
