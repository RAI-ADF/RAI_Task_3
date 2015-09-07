/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclient_1103120159;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Fauzan Razandi
 */
public class ReadInput {
    
  
  
     public static String getServerAddress(){
        return JOptionPane.showInputDialog(
               Client.frame,
               "Enter IP Address:",
               "Welcome to CHATBOX",
               JOptionPane.QUESTION_MESSAGE);
    }
    
     
    public static String getUsername(){
        return JOptionPane.showInputDialog(
            Client.frame,
            "Input your username:",
            "Your username is created",
            JOptionPane.PLAIN_MESSAGE);
    }
    
    public static void run() throws IOException{
        Client.serverAddress = getServerAddress();
        Socket clientSocket = new Socket("localhost",2222);
        Client.inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Client.outputStream = new PrintWriter(clientSocket.getOutputStream(), true);
            while(true){
                String message = Client.inputStream.readLine();
                if(message.startsWith("USERNAME")){
                    Client.outputStream.println(getUsername());
                }else if(message.startsWith("ACCEPTEDUSERNAME")){
                    Client.messageInput.setEditable(true);
                }else if(message.startsWith("MESSAGE")){
                    Client.messageArea.append(message.substring(8) + "\n");
                }
            }
    }
    
  

}
