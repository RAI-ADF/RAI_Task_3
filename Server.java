package rai_socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static ServerSocket serverSocket = null;
    private static Socket clientSocket = null;
    private static final int maxConnection = 5;
    private static final serverThread[] thread = new serverThread[maxConnection];

    public static void main(String[] args) {
        int port = 8000;
        if (args.length < 1) {
            System.out.println("Terhubung ke port " + port);
        } else {
            port = Integer.valueOf(args[0]).intValue();
        }
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            System.out.println("IO Exception - koneksi port");
        }

        while (true) {
            try {
                clientSocket = serverSocket.accept();
                int max = 0;
                for (int i = 0; i < maxConnection; i++) {
                    if (thread[i] == null) {
                        (thread[i] = new serverThread(clientSocket, thread)).start();
                        break;
                    }
                }
                if (max == maxConnection) {
                    try (PrintStream out = new PrintStream(clientSocket.getOutputStream())) {
                        out.println("Server penuh");
                    }
                    clientSocket.close();
                }

            } catch (IOException ex) {
                System.out.println("IO Exception = koneksi client");
            }

        }
    }

}


