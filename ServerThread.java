/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author MM Lab
 */
public class ServerThread extends Thread {
    private DataInputStream inStream = null;
    private PrintStream outStream = null;
    private Socket clientSocket = null;
    private final ServerThread[] threads;
    private final int maxConnection;

    public ServerThread(Socket clientSocket, ServerThread[] threads) {
        this.clientSocket = clientSocket;
        this.threads = threads;
        maxConnection = threads.length;
    }

    @Override
    public void run() {
        try {
            int max = this.maxConnection;
            ServerThread[] thread = this.threads;

            inStream = new DataInputStream(clientSocket.getInputStream());
            outStream = new PrintStream(clientSocket.getOutputStream());
            outStream.print(">Enter your username: ");
            String id = inStream.readLine().trim();
            outStream.println("==send 'Exit' fot left conversation==");
            for (int i = 0; i < max; i++) {
                if (thread[i] != null && thread[i] != this) {
                    thread[i].outStream.println("((" + id + " joined the chat))");
                }
            }
            while (true) {
                String chat = inStream.readLine();
                if (chat.startsWith("Exit")) {
                    break;
                }
                for (int i = 0; i < max; i++) {
                    if (thread[i] != null) {
                        thread[i].outStream.println("[ " + id + " ]: " + chat);
                    }
                }
            }
            for (int i = 0; i < max; i++) {
                if (thread[i] != null && thread[i] != this) {
                    thread[i].outStream.println("((" + id + " left the chat))");
                }
            }
            for (int i = 0; i < max; i++) {
                if (thread[i] == this) {
                    thread[i] = null;
                }
            }
            inStream.close();
            outStream.close();
            clientSocket.close();
        } catch (IOException ex) {
            System.out.println("Error: "+ex);
        }

    }

}
