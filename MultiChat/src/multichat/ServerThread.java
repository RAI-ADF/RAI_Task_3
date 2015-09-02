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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fadhlil
 */
public class ServerThread extends Thread{
    private Socket clientSocket = null;
    public ServerThread(Socket fromClient){
        super("serving client");
        this.clientSocket = fromClient;
    }
    
    public void run()
    {
        DataOutputStream outStream = null;
        try {
            outStream = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("connected with: " + clientSocket.getRemoteSocketAddress());
            String inputLine;
            while((inputLine = inStream.readLine()) != null){
                System.out.println("client says: " + inputLine);
                outStream.writeBytes("message received" + "\n");
            }   clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                outStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
