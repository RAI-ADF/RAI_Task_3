/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rai_socket;

import java.io.IOException;
import static rai_socket.Client.inStream;

/**
 *
 * @author hafiz
 */
public class ReadInput implements Runnable{
    @Override
    public void run() {
        String respon;
        try {
            while ((respon = inStream.readLine()) != null) {
                System.out.println(respon);
                if (respon.indexOf("GoodBye") != -1) {
                    break;
                }
            }
            boolean close = true;
        } catch (IOException ex) {
            System.out.println("Exception");
        }
    }
}
