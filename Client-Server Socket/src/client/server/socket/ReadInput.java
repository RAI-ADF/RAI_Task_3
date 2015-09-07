/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client.server.socket;

import java.io.BufferedReader;

/**
 *
 * @author novandypurnadrd
 */
public class ReadInput extends Thread {
    
    private final BufferedReader inputStream;
    public ReadInput(BufferedReader inputStream){
        this.inputStream = inputStream;
    }
    
    @Override
    public void run(){
    try{
        String inputan;
        while ((inputan = inputStream.readLine()) != null ){
            System.out.println(inputan);
            System.out.println(">>");
        }
    }
    catch(Exception e){
    
    }
    }
    
}
