/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client.server.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author novandypurnadrd
 */
public class ServerThread extends Thread {
    int portNumber =80;
    final Socket client = null;
    static Map<String, DataOutputStream> users = new HashMap<>();
    String username="";
    DataOutputStream outputStream = null;
    BufferedReader inputStream = null;
    private Socket clientSocket = null;
    public ServerThread (Socket fromClient) {
        this.clientSocket = fromClient;
    }
    
   /* public ServerThread(Socket client){
        this.client = client;
    }*/
    
    @Override
    public void run() {
        
        try{
          // inputStream = new BufferedReader (new InputStreamReader(client.getInputStream()));
          //outputStream = new DataOutputStream(client.getOutputStream());
           DataOutputStream outStream = new DataOutputStream(clientSocket.getOutputStream());
           BufferedReader inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
          
           
           
           while(true){
              outStream.writeBytes("Submit Your Username" + "\n");
              username = inStream.readLine();
              if(username == null){
              return;
              }
           synchronized (users){
               if (!users.containsKey(username)){
               outStream.writeBytes("username has already been used"+ "\n");
               users.put(username, outStream);
               
               break;

               }
           }
        }
           String inputan, message;
           while ((inputan = inStream.readLine()) != null && !inputan.equals("quit")){
               message =  username + "said :" +inputan;
               System.out.println(message);
               for (DataOutputStream d: users.values()){
               d.writeBytes(message+"\n");
               d.flush();
               }
           }
           
    
           
           if(username != null){users.remove(username);}
           if(outputStream != null){users.remove(outputStream);}
           clientSocket.close();
    }catch(IOException e){
    
            System.out.println("Exception caught when tring to listen on port" +portNumber +"or listening for a connection");
            System.out.println(e.getMessage());
    }
    }
    
}
