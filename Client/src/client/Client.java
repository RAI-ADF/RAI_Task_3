/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Luthfi Rendragiri
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            
            BufferedReader Keyboard = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Masukkan IP : ");
            String ip = Keyboard.readLine();
            Socket connect = new Socket(ip, 1234);
            
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            PrintWriter outputStream = new PrintWriter(connect.getOutputStream(), true);
            
            ReadInput in = new ReadInput(inputStream);
            in.start();
            
            String inputKeyboard;
            do {
                System.out.print(">> ");
                inputKeyboard = Keyboard.readLine();
                outputStream.println(inputKeyboard);
                outputStream.flush();
            }   while (!inputKeyboard.equals("quit"));
        } catch (Exception e) {
            
        }
    }
    
}
