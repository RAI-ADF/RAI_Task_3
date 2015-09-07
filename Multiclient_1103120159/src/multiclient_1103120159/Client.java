/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclient_1103120159;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Fauzan Razandi
 */
public class Client {
    private BufferedReader inputStream;
    private PrintWriter outputStream;
    private String serverAddress;
    private Socket clientSocket;
    JFrame frame = new JFrame("1103120159-CHATBOX");
    JTextField messageInput = new JTextField(50);
    JTextArea messageArea = new JTextArea(10, 50);
    
    public Client(){
        messageInput.setEditable(false);
        messageArea.setEditable(false);
        frame.getContentPane().add(messageInput, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.pack();
        
      messageInput.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            outputStream.println(messageInput.getText());
            messageInput.setText("");
            }
        });
    }
    
    private String getServerAddress(){
        return JOptionPane.showInputDialog(
               frame,
               "Enter IP Address:",
               "Welcome to CHATBOX",
               JOptionPane.QUESTION_MESSAGE);
    }
    
    private String getUsername(){
        return JOptionPane.showInputDialog(
            frame,
            "Input your username:",
            "Your username is created",
            JOptionPane.PLAIN_MESSAGE);
    }
    
    private void run() throws IOException{
        serverAddress = "localhost";
        clientSocket = new Socket(serverAddress,2222);
        inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outputStream = new PrintWriter(clientSocket.getOutputStream(), true);
            while(true){
                String message = inputStream.readLine();
                if(message.startsWith("SUBMITUSERNAME")){
                    outputStream.println(getUsername());
                }else if(message.startsWith("USERNAMEACCEPTED")){
                    messageInput.setEditable(true);
                }else if(message.startsWith("MESSAGE")){
                    ReadInput readInput = new ReadInput();
                    readInput.run();
                }
            }
       
        
    }
    
    

    public static void main(String[] args) throws IOException{
        Client client = new Client();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run(); 
    }
}