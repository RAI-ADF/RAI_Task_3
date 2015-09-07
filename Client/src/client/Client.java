/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author user
 */
public class Client {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String serverName = "127.0.0.1";
        int portNumber = 1234;
        
        try{
            BufferedReader keyBoard = new BufferedReader(new InputStreamReader(System.in));
            
//            System.out.println("Input server IP : ");
//            String ip = keyBoard.readLine();
            Socket connect = new Socket("localhost", 1234);
            
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            PrintWriter outputStream = new PrintWriter(connect.getOutputStream(), true);
            
//            System.out.println("Connected to "+connect.getRemoteSocketAddress());
           
            ChatWindow window = new ChatWindow(outputStream);
            window.setVisible(true);
            
            ReadInput in = new ReadInput(inputStream,window);
            
            in.start();
            
            String inputKeyBoard;
            do {
                System.out.print(">> ");
                inputKeyBoard = keyBoard.readLine();
                outputStream.println(inputKeyBoard);
                outputStream.flush();
            }while (!inputKeyBoard.equals("quit"));
            
        }catch (UnknownHostException e){
            System.err.println("Don't know about host "+serverName);
            System.exit(1);
        }catch (IOException e){
            System.err.println("Couldn't get I/O for the connection to " + serverName);
            System.exit(1);
        }
    }
    
}
