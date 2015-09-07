/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;

/**
 *
 * @author user
 */
public class ReadInput extends Thread{
    public ChatWindow cw;
            
    private BufferedReader inputStream;
    
    public ReadInput (BufferedReader inputStream, ChatWindow cw) {
        this.inputStream = inputStream;
        this.cw = cw;
    }
    
    @Override
    public void run() {
        try{
            String inputan;
            while ((inputan = inputStream.readLine()) != null){
                System.out.println(inputan);
                cw.setText(">> "+inputan);
            }
        }catch(Exception e){
            System.out.println("Error");
        }
    }
}
