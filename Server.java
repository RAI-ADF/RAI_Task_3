
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package raitask3;

import java.io.IOException;
import java.io.PrintStream;
import static java.lang.System.out;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author auliamarchita
 */
public class Server {
    private static ServerSocket serverSocket = null;
    private static Socket clientSocket = null;
    private static final int maxClients = 10;
    private static final ServerThread[] thread = new ServerThread[maxClients];

    public static void main(String[] args) {
        int port = 7777;
        if (args.length < 1) {
            out.println("Connected with port " + port);
        } else {
            port = Integer.valueOf(args[0]).intValue();
        }
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
        }

        while (true) {
            try {
            clientSocket = serverSocket.accept();
                int max = 0;
                for (int i = 0; i < maxClients; i++) {
                    if (thread[i] == null) {
                        (thread[i] = new ServerThread(clientSocket, thread)).start();
                        break;
                    }
                }
                if (max == maxClients) {
                    PrintStream os = new PrintStream(clientSocket.getOutputStream());
                    out.println("Server busy.");
                    os.close();
                    clientSocket.close();
        }
            } catch (IOException e) {
            }

        }
    }
}
