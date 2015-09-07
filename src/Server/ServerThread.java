package Server;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Surya
 */

public class ServerThread extends Thread {
    private Socket socket = null;
    static Map<String, DataOutputStream> users = new HashMap<>();
    private String username;
    
    public ServerThread(Socket socket){
        this.socket = socket;
    }
    
    @Override
    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            System.out.println("Connected to " + socket.getRemoteSocketAddress());
            out.writeBytes("Masukan Username : \n");
            
            // Wait for username
            while (true) {
                username = in.readLine();                
                if (username == null) {
                    return;
                }

                synchronized (users) {
                    if (!users.containsKey(username)) {
                        users.put(username, out);
                        out.writeBytes("Welcome " + username + ", type 'quit' to exit \n");
                        System.out.println(socket.getRemoteSocketAddress() + " : " + username);
                    } else {
                        out.writeBytes("Username not available");
                        break;
                    }
                }
            }
            
            // Chat loop
            while (!username.isEmpty()) {
                String msg = in.readLine();
                
                if (msg == null) continue;
                
                if (!msg.equals("quit")) {
                    for (DataOutputStream d : users.values()) {
                        d.writeBytes(username + " : " + msg + " \n");
                    }
                } else {
                    for (DataOutputStream d : users.values()) {
                        d.writeBytes(username + " leaving conversation \n");
                    }
                    users.remove(username);
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
