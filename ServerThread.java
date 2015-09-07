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
public class ServerThread extends Thread{
    private String clientName = null;
    private DataInputStream is = null;
    private PrintStream os = null;
    private Socket clientSocket = null;
    private final ServerThread[] threads;
    private int maxClients;
    private String name;
    private static HashSet<String> names = new HashSet<String>();

  public ServerThread(Socket clientSocket, ServerThread[] threads) {
    this.clientSocket = clientSocket;
    this.threads = threads;
    maxClients = threads.length;
  }
  @Override
  public void run() {
    int maxClients = this.maxClients;
    ServerThread[] threads = this.threads;

    try {
      is = new DataInputStream(clientSocket.getInputStream());
      os = new PrintStream(clientSocket.getOutputStream());
      String name;
      while (true) {
                os.println("Enter your username : ");
                name = is.readLine().trim();
            if (name == null) {
                return;
            }
                synchronized (names) {
                    if (!names.contains(name)) {
                        out.println("Username accepted.");
                        names.add(name); 
                          break;
                    } else {
                        os.println("Username has already been used.");
                      }
                }
            }
      os.println("Welcome " + name + " to the chat room."
              + "\n'/quit' for leave conversation. \n");
      
      synchronized (this) {
        for (int i = 0; i < maxClients; i++) {
          if (threads[i] != null && threads[i] == this) {
            clientName = "@" + name;
            break;
          }
        }
        for (int i = 0; i < maxClients; i++) {
          if (threads[i] != null && threads[i] != this) {
            threads[i].os.println("" + name + " has joined.");
            out.println("" + name + " has joined.");
          }
        }
      }

      while (true) {
        String chat = is.readLine();
        if (chat.startsWith("/quit")) {
          break;
        }

        if (chat.startsWith("@")) {
          String[] words = chat.split("\\s", 2);
          if (words.length > 1 && words[1] != null) {
            words[1] = words[1].trim();
            if (!words[1].isEmpty()) {
              synchronized (this) {
                for (int i = 0; i < maxClients; i++) {
                  if (threads[i] != null && threads[i] != this
                      && threads[i].clientName != null
                      && threads[i].clientName.equals(words[0])) {
                    threads[i].os.println("<" + name + "> " + words[1]);
                    this.os.println(">" + name + "> " + words[1]);
                    break;
                  }
                }
              }
            }
          }
        } else {        
          synchronized (this) {
            for (int i = 0; i < maxClients; i++) {
              if (threads[i] != null && threads[i].clientName != null) {
                threads[i].os.println("<" + name + "> " + chat);
              }
            }
          }
        }
      }
      synchronized (this) {
        for (int i = 0; i < maxClients; i++) {
          if (threads[i] != null && threads[i] != this
              && threads[i].clientName != null) {
            threads[i].os.println("" + name + " has left.");
          }
        }
      }
      os.println("" + name + " is leaving conversation.");
      out.println("" + name + " is leaving conversation.");
      synchronized (this) {
        for (int i = 0; i < maxClients; i++) {
          if (threads[i] == this) {
            threads[i] = null;
          }
        }
      }
      is.close();
      os.close();
      clientSocket.close();
    } catch (IOException e) {
    }
  }
}
