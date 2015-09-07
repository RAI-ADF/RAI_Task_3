/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package socketprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import userInterface.joinChat;
import userInterface.chatWindow;

/**
 *
 * @author notebook
 */
public class Client {
    public static void main(String[] args){
        String serverName="127.0.0.1";
        int portNumber=1437;
        try{
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in)); 
            System.out.print("Masukkan IP Server : ");
            
            String IP = keyboard.readLine();
            Socket connect = new Socket(IP,1437);
            
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            PrintWriter outputStream= new PrintWriter(connect.getOutputStream(),true);
            
            ReadInput in = new ReadInput(inputStream);
            in.start();
            
            String inputKeyBoard;
            do{
                System.out.println(">> ");
                inputKeyBoard= keyboard.readLine();
                outputStream.println(inputKeyBoard);
                outputStream.flush();
            }while (!inputKeyBoard.equals("quit"));
            System.out.println("User telah keluar");
        }catch (UnknownHostException e){
            System.err.println("Host not Found. Name :"+ serverName);
            System.exit(1);
        }catch(IOException e){
            System.err.println("Couldn't connect the I/O to "+ serverName);
        }
    }
    
}
