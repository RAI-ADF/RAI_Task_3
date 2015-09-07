/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package raitask3;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import static java.lang.System.out;
import java.net.Socket;
import java.util.HashSet;

/**
 *
 * @author auliamarchita
 */
public class ServerThread extends Thread {
    private Socket clientSocket = null;
    private final ServerThread[] threads;
    private final int maxConnection;
    private DataInputStream is = null;
    private PrintStream os = null;
    private String username;
    private static HashSet<String> usernames = new HashSet<String>();

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

            is = new DataInputStream(clientSocket.getInputStream());
            os = new PrintStream(clientSocket.getOutputStream());
            
            while (true) {
                os.println("Enter your username : ");
                username = is.readLine().trim();
            if (username == null) {
                return;
            }
                synchronized (usernames) {
                    if (!usernames.contains(username)) {
                        out.println("Username accepted.");
                        usernames.add(username); 
                          break;
                    } else {
                        os.println("Username has already been used.");
                      }
                }
            }
            os.println("Welcome " + username + " to the chat room.");
            os.println("'quit' for leave conversation. \n");
            for (int i = 0; i < max; i++) {
                if (thread[i] != null && thread[i] != this) {
                    thread[i].os.println("" + username + " has joined.");
                    out.println("" + username + " has joined.");
                }
            }
            while (true) {
                String chat = is.readLine();
                if (chat.startsWith("quit")) {
                    break;
                }
                for (int i = 0; i < max; i++) {
                    if (thread[i] != null) {
                        thread[i].os.println("<" + username + "> : " + chat);
                    }
                }
            }
            for (int i = 0; i < max; i++) {
                if (thread[i] != null && thread[i] != this) {
                    thread[i].os.println("" + username + " has left.");
                }
            }
            os.println("" + username + " leave conversation.");
            out.println("" + username + " leave conversation.");
            for (int i = 0; i < max; i++) {
                if (thread[i] == this) {
                    thread[i] = null;
                }
            }
            is.close();
            os.close();
            clientSocket.close();
        } catch (IOException e) {
        }
    }
}
