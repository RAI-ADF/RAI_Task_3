/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rai.socket.pkg2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Adam
 */
public class Client {

    public static void main(String[] args) {        
        try {
//Connect to a server via ip address
            BufferedReader keyBoard = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input Server ip : ");
            String ip = keyBoard.readLine();
            Socket connect = new Socket(ip, 1234);

//Create Input output stream
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            PrintWriter outputStream = new PrintWriter(connect.getOutputStream(), true);

            ReadInput in = new ReadInput(inputStream);
            in.start();
            

            String inputKeyboard;
            do {
                System.out.println(">> ");
                inputKeyboard = keyBoard.readLine();
                outputStream.println(inputKeyboard);
                outputStream.flush();
            } while (!inputKeyboard.equals("quit"));
            inputStream.close();
            outputStream.close();
            connect.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    }
    

