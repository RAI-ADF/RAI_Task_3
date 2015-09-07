/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketRAI;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class ServerThread extends Thread {

    /**
     * @param args the command line arguments
     */
    final Socket client;
    private Socket clientSocket;
    static Map<String, DataOutputStream> users = new HashMap<>();
    String username = "";
    DataOutputStream outputstream = null;
    BufferedReader inputstream = null;

//    public ServerThread(Socket fromclient){
//        super("serving client");
//        this.clientSocket=fromclient;
//    }
    public ServerThread(Socket fromclient) {
        this.client = fromclient;
    }

    @Override
    public void run() {
        try {
            inputstream = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outputstream = new DataOutputStream(client.getOutputStream());
            System.out.println("ini output:"+ outputstream);
            while (true) {
                System.out.println("submit ");
                outputstream.writeBytes("submit user name " + "\n");
                username = inputstream.readLine();

                if (username == null) {
                    return;
                }
                synchronized (users) {
                    if (!users.containsKey(username)) {
                        users.put(username, outputstream);
                        break;
                    }
                    outputstream.writeBytes("username has taken " + "\n");
                }
            }

            String inputan, message;
            while ((inputan = inputstream.readLine()) != null && !inputan.equals("quit")) {
                message = username + " said " + inputan;
                System.out.println(message);
                for (DataOutputStream d : users.values()) {
                    d.writeBytes(message + "\n");
                    outputstream.writeBytes("username has taken " + "\n");
                    d.flush();
                }
                
            }
            
            if (username != null) {
                users.remove(username);
            }
            if (outputstream != null) {
                users.remove(outputstream);
            }

            clientSocket.close();
        } catch (IOException ioe) {
            System.out.println("something error happen");
        }
        
        
//        ===============================================

                
//        ==============================================
    }

}
