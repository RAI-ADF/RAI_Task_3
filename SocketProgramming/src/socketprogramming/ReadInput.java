/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package socketprogramming;

import java.io.BufferedReader;

/**
 *
 * @author notebook
 */
public class ReadInput extends Thread {
     private BufferedReader inStream;
    
    public ReadInput(BufferedReader inStream){
        this.inStream=inStream;
    }
    
    public void run(){
        try{
            String inputan;
            while ((inputan = inStream.readLine()) != null){
                System.out.println(inputan);
                System.out.println(">> ");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
