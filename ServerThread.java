/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rai_task3;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Taufiq
 */
public class ServerThread extends Thread {
    private Socket clientSocket = null;
    static Map<String, DataOutputStream> users = new HashMap<>();
    
    String username = "";
    
    public ServerThread (Socket clientSocket){
        this.clientSocket = clientSocket;
    }
    
    @Override
    public void run(){
        try {
            BufferedReader inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream outStream = new DataOutputStream(clientSocket.getOutputStream());
            
            System.out.println("Client " + clientSocket.getRemoteSocketAddress()+" terhubung");
            
            
            while (true) {
                outStream.writeBytes("Masukkan username : "+ "\n");
                username = inStream.readLine();
                if (username == null) {
                    return;
                }
                synchronized (users){
                    if (!users.containsKey(username)) {
                        users.put(username, outStream);
                        System.out.println("User "+ username +" terhubung");
                        break;
                    }
                    outStream.writeBytes("Username telah digunakan, coba yang lain" + "\n");
                }
            }
            
            String inputan, message;
            while ((inputan = inStream.readLine()) != null && !inputan.equals("quit")) {
                message = "<" + username + "> " + inputan + "\n";
                System.out.println(message);
                for (DataOutputStream d : users.values()) {
                    d.writeBytes(message + "\n");
                    d.flush();
                }
            }
            if (username != null) {
                users.remove(username);
            }
            if (outStream != null){
                users.remove(outStream);
            }
            clientSocket.close();
            
            
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } 
            
    }
    
}
