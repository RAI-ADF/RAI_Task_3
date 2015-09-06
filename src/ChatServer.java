
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * 
 */
public class ChatServer {
  private static ServerSocket serverSocket = null;
  private static Socket userSocket = null;
  private static final int maxUser = 10;
  private static final UserThread[] threads = new UserThread[maxUser];

  public static void main(String args[]) {

    int portNumber = 2200;
    
    if (args.length < 1) {
       System.out.println("ChatServer berjalan di port : "+ portNumber);
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
        userSocket = serverSocket.accept();
        int i = 0;
        for (i = 0; i < maxUser; i++) {
          if (threads[i] == null) {
            (threads[i] = new UserThread(userSocket, threads)).start();
            break;
          }
        }
        
        if (i == maxUser) {
          PrintStream outputStream = new PrintStream(userSocket.getOutputStream());
          outputStream.println("maaf, room sudah full");
          outputStream.close();
          userSocket.close();
        }
      } catch (IOException e) {
        System.out.println(e);
      }
    }
  }
}
