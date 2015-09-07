/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclient_1103120159;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzan Razandi
 */
public class Client {
    public static void main(String[] args){
        try {
            BufferedReader keyBoard = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Input Server IP : ");
            String ip = keyBoard.readLine();
            Socket connect = new Socket("localhost",2222);
            
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            PrintWriter outputSream = new PrintWriter (connect.getOutputStream(), true);
    
            ReadInput in = new ReadInput(inputStream);
            in.run();
            
            String inputKeyBoard;
            do{
                System.out.println(">> ");
                inputKeyBoard = keyBoard.readLine();
                outputSream.println(inputKeyBoard);
                outputSream.flush();
            }while(!inputKeyBoard.equals("quit"));
            
        } catch (IOException e) {
            System.out.println("Failed to Connect " + e);
        }
        
    }
    
    
}
