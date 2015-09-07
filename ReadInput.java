/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rai_task3;

import java.io.BufferedReader;

/**
 *
 * @author Taufiq
 */
public class ReadInput extends Thread {
    private BufferedReader inputStream;
    
    public ReadInput (BufferedReader inputStream){
        this.inputStream = inputStream;
    }
    
    @Override
    public void run(){
        try{
            String inputan;
            while ((inputan = inputStream.readLine()) != null) {
                System.out.println(inputan);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
