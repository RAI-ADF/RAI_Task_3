/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package raitask3;

import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

/**
 *
 * @author auliamarchita
 */
public class Server {
  private static ServerSocket serverSocket = null;
  private static Socket clientSocket = null;
  private static final int maxClients = 5;
  private static final ServerThread[] threads = new ServerThread[maxClients];

  public static void main(String args[]) {

    int portNumber = 7777;
    if (args.length < 1) {
      System.out.println("Connected with port "+ portNumber);
    } else {
      portNumber = Integer.valueOf(args[0]).intValue();
    }

    try {
      serverSocket = new ServerSocket(portNumber);
    } catch (IOException e) {
      System.out.println(e);
    }

    while (true) {
      try {
        clientSocket = serverSocket.accept();
        int i = 0;
        for (i = 0; i < maxClients; i++) {
          if (threads[i] == null) {
            (threads[i] = new ServerThread(clientSocket, threads)).start();
            break;
          }
        }
        if (i == maxClients) {
          PrintStream os = new PrintStream(clientSocket.getOutputStream());
          os.println("Server too busy.");
          os.close();
          clientSocket.close();
        }
      } catch (IOException e) {
      }
    }
  }  
}
