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
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author erpin
 */
public class client {

    /**

     * @param args the command line arguments

     */

    public static void main(String[] args) throws UnknownHostException, IOException {

        String temp = "";

        do {
            try {
                Socket client = new Socket(InetAddress.getLocalHost(),1234);
                InputStream clientin = client.getInputStream();
                OutputStream clientout = client.getOutputStream();
                PrintWriter pw = new PrintWriter(clientout, true);
                BufferedReader br = new BufferedReader(new InputStreamReader(clientin));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Type a message for the server: ");
                temp = stdIn.readLine();
                pw.println(temp);                
                System.out.println("Server message: ");
                System.out.println(br.readLine());
                pw.close();
                br.close();
                client.close();
            } catch (ConnectException ce) {
                System.out.println("Tidak bisa terhubung ke server.");
            } catch (IOException ie) {
                System.out.println("I/O Error.");
            }
        }
        while (!temp.matches("EXIT"));
    }
}
