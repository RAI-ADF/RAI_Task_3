/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rai.socket.pkg2;

import java.io.BufferedReader;
import java.io.InputStream;
/**
 *
 * @author Adam
 */
public class ReadInput extends Thread{

  private BufferedReader inputStream;

    public ReadInput(BufferedReader inputReader) {
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        try {
            String inputan;
            while ((inputan = inputStream.readLine()) != null) {
                System.out.println(inputan);
                System.out.println(">>");
            }
        } catch (Exception e) {
            System.out.println("something error");
        }
    }
    
}

