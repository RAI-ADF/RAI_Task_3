
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/*
 * To change this license header, chooutputStreame License Headers in Project Properties.
 * To change this template file, chooutputStreame Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * 
 */
public class UserThread extends Thread{
  private String userName = null;
  private DataInputStream is = null;
  private PrintStream os = null;
  private Socket userSocket = null;
  private final UserThread[] threads;
  private int maxUser;

  public UserThread(Socket userSocket, UserThread[] threads) {
    this.userSocket = userSocket;
    this.threads = threads;
    maxUser = threads.length;
  }

  public void run() {
      
      
    int maxUser = this.maxUser;
    
    
    UserThread[] threads = this.threads;

    try {
      is = new DataInputStream(userSocket.getInputStream());
      os = new PrintStream(userSocket.getOutputStream());
      String name;
      name = is.readLine().trim();

      os.println("Selamat Datang " + name + " dalam chat room tubes JRK\n ketikkan '/quit' untuk meninggalkan room.");
      synchronized (this) {
        for (int i = 0; i < maxUser; i++) {
          if (threads[i] != null && threads[i] == this) {
            userName = name;
            break;
          }
        }
        
        for (int i = 0; i < maxUser; i++) {
          if (threads[i] != null && threads[i] != this) {
            threads[i].os.println("*====== " + name
                + " telah bergabung dalam chat room ======*");
          }
        }
      }

      while (true) {
        String chatLine = is.readLine();
        if (chatLine.startsWith("/quit")) {
          break;
        }
      if (chatLine != null) {
          synchronized (this) {
            for (int i = 0; i < maxUser; i++) {
              if (threads[i] != null && threads[i].userName != null) {
                threads[i].os.println("<" + name + "> " + chatLine);
              }
            }
          }
          
        } else {
          break;
        }
      }
      synchronized (this) {
        for (int i = 0; i < maxUser; i++) {
          if (threads[i] != null && threads[i] != this
              && threads[i].userName != null) {
            threads[i].os.println(name + " telah meninggalkan chatroom");
          }
        }
      }

      is.close();
      os.close();
      userSocket.close();
    } catch (IOException e) {
    }
  }
}

