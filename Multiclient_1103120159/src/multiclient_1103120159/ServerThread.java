/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclient_1103120159;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzan Razandi
 */
public class ServerThread extends Thread{
    private Socket clientSocket;
    private String username;
    private static HashSet<String> users = new HashSet<String>();
    private static HashSet<PrintWriter> message = new HashSet<PrintWriter>();
    private BufferedReader inputStream;
    private PrintWriter outputStream;
   
    String input;
    
    public ServerThread(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
    
    public void run(){
        try {
            System.out.println("Connection Start");
            inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outputStream = new PrintWriter(clientSocket.getOutputStream());
           
            while(true){
                outputStream.println("Input Username : ");
                username = inputStream.readLine();
                if(username == null){
                    return;
                }
                synchronized (users){
                    if(!users.contains(username)){
                        users.add(username);
                        outputStream.println("Start your chat. Type quit to end the chat.");
                        break;
                    }else{
                        outputStream.println("Username has already been used \n");
                        break;
                    }
                }
            }
            
                outputStream.println("Name Accepted");
                message.add(outputStream);
                
                while(true){
                    input = inputStream.readLine();
                    if(input == null){
                        return;
                    }
                    for(PrintWriter user: message){
                        user.println(username + ": " + input);
                    }
                }
                    
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if(username !=null){
                users.remove(username);
            }
            if(outputStream != null){
                message.remove(outputStream);
            }
            
            try{
                clientSocket.close();
            }
            catch(IOException e){
                
            }
        }
       
    }
}
