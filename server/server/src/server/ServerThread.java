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
 * @author dwiharyanto
 */
public class ServerThread extends Thread{
    final Socket clientSocket ;
    static  Map<String, DataOutputStream> users = new HashMap<>();
    String username= "";
    DataOutputStream outStream = null;
    BufferedReader intStream = null;

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    
    public ServerThread(Socket fromClient){
        this.clientSocket = fromClient;
    }
    @Override
    public void run(){
        try {
            
             intStream = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
            outStream = new  DataOutputStream(clientSocket.getOutputStream());
           
            while (true) {                
                outStream.writeBytes("submit your username "+"\n");
                username = intStream.readLine();
                if (username== null){
                    return;
                }
                synchronized (users){
                 if (!users.containsKey(username)) {
                     outStream.writeBytes("username has already ben used  "+"\n");
                      users.put(username,outStream);
                      break;
                 }
                }
            }
            
            String inputan , message;
            while ((inputan = intStream.readLine()) !=null && !inputan.equals("quit")) {                
                message = username + " said " + inputan;
                System.out.println(message);
                for (DataOutputStream d : users.values()){
                    d.writeBytes(message+" \n");
                    d.flush();
                }
            }
            
          if (username != null) {users.remove(username);}
          if (outStream != null) {users.remove(outStream);}
             clientSocket.close();
        } catch (Exception e) {
        }  
    }
}
