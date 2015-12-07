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
   static BufferedReader inputStream;
   static PrintWriter outputStream;
   static String serverAddress;
   static JFrame frame = new JFrame("1103120159-CHATBOX");
   static JTextField messageInput = new JTextField(40);
   static JTextArea messageArea = new JTextArea(8, 40);
    
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
    
    public static void main(String[] args) throws IOException{
        Client client = new Client();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        ReadInput.run();
        
    }
}