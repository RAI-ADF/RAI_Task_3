/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.IOException;
import static socket.Client.inStream;

/**
 *
 * @author MM Lab
 */
public class ReadInput implements Runnable{
    @Override
    public void run() {
        String respon;
        try {
            while ((respon = inStream.readLine()) != null) {
                System.out.println(respon);
            }
            boolean close = true;
        } catch (IOException ex) {
            System.out.println("Error: "+ex);
        }
    }
}
