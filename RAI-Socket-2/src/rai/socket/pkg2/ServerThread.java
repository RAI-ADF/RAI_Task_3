/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rai.socket.pkg2;

/**
 *
 * @author Adam
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Adam
 */
public class ServerThread extends Thread {

    /**
     * @param args the command line arguments
     */
    final Socket client;
    private Socket clientSocket;
    static Map<String, DataOutputStream> users = new HashMap<>();
    String username= "";
    DataOutputStream outputstream = null;
    BufferedReader inputstream = null;
    
//    public ServerThread(Socket fromclient){
//        super("serving client");
//        this.clientSocket=fromclient;
//    }
    
    public ServerThread(Socket fromclient){
        this.client=fromclient;
    }
    
    
    @Override
    public void run(){
            try{
                
            inputstream = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outputstream = new DataOutputStream(client.getOutputStream());
            
            while(true){
                outputstream.writeBytes("submit user name "+"\n");
                username=inputstream.readLine();
                
                if(username==null){
                    return;
                }
                synchronized(users){
                    if(!users.containsKey(username)){
                        outputstream.writeBytes("username udah diambil "+"\n");
                        users.put(username, outputstream);
                        break;
                    }
                }
            }
            
            String inputan , message;
            while((inputan = inputstream.readLine())!= null && !inputan.equals("quit")){
                message = username+" said "+inputan;
                System.out.println(message);
                for(DataOutputStream d : users.values()){
                    d.writeBytes(message+"\n");
                    d.flush();
                }
            }
                
            if(username !=null){
                users.remove(username);
            }
            if(outputstream !=null){
                users.remove(outputstream);
            }
            
            
            clientSocket.close();
            }catch(IOException ioe){
                
            }
    }

    
}

