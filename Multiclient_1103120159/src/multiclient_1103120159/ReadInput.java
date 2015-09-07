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
   
    BufferedReader inputStream;
    
    public ReadInput(BufferedReader inputStream){
        this.inputStream = inputStream;
    }
    public void run(){
        try{
            Client client = new Client();
            System.out.print("Chat Broadcast: ");
            String inputan = inputStream.readLine();
            System.out.println("");
            while((inputan)!= null){
                client.messageArea.append(inputan.substring(8) + "\n");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    }

}
