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
    Socket clientSocket;
    String username;
    static HashSet<String> users = new HashSet<String>();
    static HashSet<PrintWriter> message = new HashSet<PrintWriter>();
    BufferedReader inputStream;
    PrintWriter outputStream;
   
  
    
    public ServerThread(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
    
    public void run(){
        try {
            inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outputStream = new PrintWriter(clientSocket.getOutputStream(), true);
            System.out.println("Client Connected");
           
           
            while(true){
                outputStream.println("USERNAME");
                username = inputStream.readLine();
                if(username == null){
                    return;
                }
                synchronized (users){
                    if(!users.contains(username)){
                        users.add(username);
                        break;
                    }
                }
            }
              
                outputStream.println("ACCEPTEDUSERNAME");
                message.add(outputStream);
                
                while(true){
                    String input = inputStream.readLine();
                    if(input == null){
                        return;
                    }
                    for(PrintWriter user: message){
                        user.println("MESSAGE " + username + ": "+ input);
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
