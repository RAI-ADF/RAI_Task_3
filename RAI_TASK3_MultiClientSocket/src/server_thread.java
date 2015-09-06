/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author acer-pc
 */
public class server_thread extends Thread {
    Socket socket;
    String message = null;
    server_thread(Socket socket){
        this.socket = socket;
    }
    public void run(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while ((message = bufferedReader.readLine()) != null) {
                printMessage();
            }
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(server_thread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private String printMessage() {
        return message;
      
    }
}
