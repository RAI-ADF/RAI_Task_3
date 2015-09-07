/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package raitask3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import static java.lang.System.out;
import java.net.Socket;

/**
 *
 * @author auliamarchita
 */
public class Client implements Runnable {
    static Socket clientSocket = null;
    static BufferedReader inLine = null;
    static boolean close = false;
    private static PrintStream os = null;
    private static DataInputStream is = null;
    
    public static void main(String[] args) { 
        try {
            int port = 7777;
            String serverName = "localhost";
            
            if (args.length < 2) {
                out.println("Connected to port " + port + "\n");
            } else {
              serverName = args[0];
                port = Integer.valueOf(args[1]).intValue();
            }
            clientSocket = new Socket(serverName, port);
            inLine = new BufferedReader(new InputStreamReader(System.in));
            os = new PrintStream(clientSocket.getOutputStream());
            is = new DataInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
        }
       if (clientSocket != null && os != null && is != null) {
            try {
                new Thread(new Client()).start();
                while (!close) {
                    try {
                        os.println(inLine.readLine().trim());
                    } catch (IOException e) {
                    }
                }
                os.close();
                is.close();
                clientSocket.close();
            } catch (IOException e) {
            } 
        }
    }

 @Override
    public void run() {
        String respon;
        try {
            while ((respon = is.readLine()) != null) {
                System.out.println(respon);
            }
            close = true;
        } catch (IOException e) {
          }
}}
