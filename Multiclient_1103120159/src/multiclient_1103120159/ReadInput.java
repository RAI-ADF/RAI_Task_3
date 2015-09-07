/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclient_1103120159;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JTextArea;

/**
 *
 * @author Fauzan Razandi
 */
public class ReadInput {
    private BufferedReader inputStream;
    private PrintWriter outputStream;
    private Socket clientSocket;
    JTextArea messageArea = new JTextArea(10, 50);
    
    public void run(){
        try{
            String message = inputStream.readLine();
            messageArea.append(message.substring(10) + "\n");
        }catch(Exception e){
            System.out.println(e);
        }
        
    }

}
