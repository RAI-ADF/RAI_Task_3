/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ServerThread extends Thread {
    
    private Socket client;
    static Map<String, DataOutputStream> users = new HashMap<>();
    
    String username = "";
    DataOutputStream outputStream = null;
    BufferedReader inputStream = null;
    
    public ServerThread(Socket fromClient){
        this.client = fromClient;
    }
    
    @Override
    public void run(){
        try {
            inputStream = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outputStream = new DataOutputStream(client.getOutputStream());
            
            while (true) {                
                outputStream.writeBytes("Submit your username"+"\n");
                username = inputStream.readLine();
                if (username == null){
                    return;
                }
                synchronized(users){
                    if (!users.containsKey(username)) {
                        outputStream.writeBytes("Welcome back " + username + " :)" + "\n");
                        users.put(username, outputStream);
                        break;
                    }else{
                        outputStream.writeBytes("Username already used"+"\n");
                    }
                }
            }
            
            System.out.println("Connected with "+client.getRemoteSocketAddress());
            
            String inputan, message; 
            
            while((inputan = inputStream.readLine()) != null && (!inputan.equals("quit"))){
                message = username + " : " + inputan;
                System.out.println(message);
                for (DataOutputStream d : users.values()){
                    if(d != outputStream){
                        d.writeBytes(message+"\n");
                        d.flush();
                    }else{
                        d.writeBytes("Message sent"+"\n");
                        d.flush();
                    }
                }
            }
            
            if (username!=null){
                users.remove(username);
                System.out.println("REMOVED");
            }
            if (outputStream!=null){
                users.remove(outputStream);
            }
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
