/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multichat;

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
 * @author fadhlil
 */
public class ServerThread extends Thread{
    //private Socket clientSocket = null;
    final Socket client;
    static Map<String, DataOutputStream> users = new HashMap<>();
    String username = "";
    DataOutputStream outputStream = null;
    BufferedReader inputStream = null;
    //public ServerThread(Socket fromClient){
    public ServerThread(Socket client){
        super("serving client");
        //this.fromClient = fromClient;
        this.client = client;
    }
    
    public void run()
    {
        
        
        DataOutputStream outStream = null;
        try {
            inputStream = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //outStream = new DataOutputStream(clientSocket.getOutputStream());
            outputStream = new DataOutputStream(client.getOutputStream());
            //BufferedReader inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //System.out.println("connected with: " + clientSocket.getRemoteSocketAddress());
            
            while (true) {            
                //outputStream.writeBytes("submit your username\n");
                username = inputStream.readLine();
                if(username==null){
                    return;
                }
                synchronized (users){
                    if(!users.containsKey(username)){
                        outputStream.writeBytes("you joined the chat room\n");
                        users.put(username,outputStream);
                        break;
                    }else{
                        outputStream.writeBytes("username has already been used\n");
                    }
                }
            }
            
            System.out.println("connected with: " + client.getRemoteSocketAddress());
            String inputLine,message;
            while((inputLine = inputStream.readLine()) != null && !inputLine.equals("quit")){
                message = username + "said : " +inputLine;
                System.out.println(message);
                for(DataOutputStream d : users.values()){
                    d.writeBytes(message+"\n");
                    d.flush();
                }
                //System.out.println("client says: " + inputLine);
                //outStream.writeBytes("message received" + "\n");
            }
            if(username!=null){
                users.remove(username);
            }
            if(outputStream!=null){
                users.remove(outputStream);
            }
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }/* finally {
            try {
                //outStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    }
}
