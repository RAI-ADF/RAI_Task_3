/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author MM Lab
 */
public class Server {
    private static ServerSocket serverSocket = null;
    private static Socket clientSocket = null;

    public static void main(String[] args) {
        System.out.println("==Server Initiation==");
        
        Scanner s = new Scanner(System.in);
        System.out.print("Input Port: ");
        int port = s.nextInt();
        System.out.print("Input Max Clients: ");
        int maxConnection = s.nextInt();
        ServerThread[] thread = new ServerThread[maxConnection];
        
        if (args.length < 1) {
            System.out.println("((Connected to port "+port+"))");
        } else {
            port = Integer.valueOf(args[0]).intValue();
        }
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            System.out.println("Error: "+ex);
        }

        while (true) {
            try {
                clientSocket = serverSocket.accept();
                int max = 0;
                for (int i = 0; i < maxConnection; i++) {
                    if (thread[i] == null) {
                        (thread[i] = new ServerThread(clientSocket, thread)).start();
                        break;
                    }
                }
                if (max == maxConnection) {
                    PrintStream out = new PrintStream(clientSocket.getOutputStream());
                    out.println("Sorry, server full");
                    clientSocket.close();
                }

            } catch (IOException ex) {
                System.out.println("Error: "+ex);
            }

        }
    }
}
