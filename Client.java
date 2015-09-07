/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rai_task3;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Taufiq
 */
public class Client {
    private BufferedReader inStream;
    private PrintWriter outStream;
    int portNum = 9091;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Client c = new Client();
        c.connectChat();
    }
    
    private void connectChat(){
        try {
            
            BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Masukkan alamat server : ");
            String serverIP = key.readLine();
            Socket connSocket = new Socket(serverIP, portNum);
            
            inStream = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
            outStream = new PrintWriter(connSocket.getOutputStream(), true);
            
            ReadInput read = new ReadInput (inStream);
            read.start();
            String inputKey = "";
            while(!"/quit".equals(inputKey)){
                inputKey = key.readLine();
                outStream.println(inputKey);
                outStream.flush();
            }
            inStream.close();
            outStream.close();
            connSocket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
