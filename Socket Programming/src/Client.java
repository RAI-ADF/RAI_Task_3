
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aridakartika
 */
public class Client {
    
    public Client(){
        try {
            BufferedReader keyBoard = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("input Ip server : ");
            String ip = keyBoard.readLine();
            Socket connect = new Socket(ip, 1234);
            
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            PrintWriter outputStream = new PrintWriter(connect.getOutputStream(),true);
            
            ReadInput in = new ReadInput(inputStream);
            in.start();
            
            String inputKeyBoard;
            do {
                System.out.println(">> ");
                inputKeyBoard = keyBoard.readLine();
                outputStream.println(inputKeyBoard);
                outputStream.flush();
                keyBoard.close();
            } while (!inputKeyBoard.equals("quit"));                 
        
        } catch (IOException e) {
            System.out.println("exception : " + e);
        }
        
        
    }
  
    
}
