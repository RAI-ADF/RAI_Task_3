/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client.server.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author novandypurnadrd
 */
public class ClientServerSocket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String serverName = "192.168.1.108";
        int portNumber = 80;
        
        try{
            BufferedReader keyBoard = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input Server IP : ");
            String ip = keyBoard.readLine();
            Socket connect = new Socket(ip,80);

            
            //Socket clientSocket = new Socket(serverName,portNumber);
            
            
                       
            BufferedReader inputStream = new BufferedReader( new InputStreamReader(connect.getInputStream()));
            PrintWriter outputStream = new PrintWriter(connect.getOutputStream(),true);
            ReadInput in = new ReadInput(inputStream);
            in.start();
        
            
           String inputKeyBoard;
            do{
                System.out.println(">>");
                inputKeyBoard = keyBoard.readLine();
                outputStream.println(inputKeyBoard);
                outputStream.flush();
                
            }while (!inputKeyBoard.equals("quit"));
        
        }
        catch(UnknownHostException e){
            System.err.println("Dont know about Host" + serverName);
            System.exit(1);
        } catch (IOException ex) {
            Logger.getLogger(ClientServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
