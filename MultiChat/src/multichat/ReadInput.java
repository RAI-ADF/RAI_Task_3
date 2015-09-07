/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multichat;

import java.io.BufferedReader;
import javax.swing.JTextArea;

/**
 *
 * @author fadhlil
 */
class ReadInput extends Thread{
    private BufferedReader inputStream;
    private JTextArea chatArea;
    
    public ReadInput(BufferedReader inputStream){
        this.inputStream = inputStream;
        chatArea = null;
    }
    
    public ReadInput(BufferedReader inputStream, JTextArea chatArea){
        this.inputStream = inputStream;
        this.chatArea = chatArea;
    }
    
    @Override
    public void run(){
        try{
            String inputan;
            while((inputan = inputStream.readLine()) != null){
                if(chatArea==null){
                    System.out.println(inputan);
                    System.out.print(">> ");
                }else{
                    chatArea.append("\n" + inputan);
                }
                
            }
        }catch(Exception e){
            
        }
    }
}
