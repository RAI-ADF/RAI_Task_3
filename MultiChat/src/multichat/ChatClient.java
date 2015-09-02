/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multichat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author fadhlil
 */
public class ChatClient {
    public static void main(String[] args) {
        String serverName = "200.200.200.78";
        int port = 2000;
        try {
            Socket clientSocket = new Socket(serverName,port);
            PrintWriter outStream = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader keyBoard = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("connected to " + clientSocket.getRemoteSocketAddress());
            String userInput;
            while((userInput = keyBoard.readLine()) != null){
                outStream.println(userInput);
                String message = inStream.readLine();
                System.out.println("reply from server: " + message);
            }
        }catch(UnknownHostException e){
            System.out.println("dont know about host "+ serverName);
            System.exit(1);
        }catch(IOException e){
            System.out.println("couldnt get i/o for connection to " + serverName);
            System.exit(1);
        }
        
    }
}
