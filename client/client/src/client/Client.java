/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author dwiharyanto
 */
public class Client {
       public  static int portNumber = 1000;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
          try {
              BufferedReader keyBoard = new BufferedReader(new InputStreamReader(System.in));
              String ip = "127.0.0.1";
              Socket clientSocket = new Socket(ip,portNumber);
              
              PrintWriter outStream = new PrintWriter(clientSocket.getOutputStream(),true);
              BufferedReader intStream = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));
              
              
              
              ReadInput in = new ReadInput(intStream);
              in.start();
              
              String userInput ;
              do {
                  System.out.println(">> ");
                  userInput = keyBoard.readLine();
                  outStream.println(userInput);
                  outStream.flush();
              }
              while (!userInput.equals("quit"));
        } catch (Exception e) {
              System.exit(1);
        }
    }
    
}
