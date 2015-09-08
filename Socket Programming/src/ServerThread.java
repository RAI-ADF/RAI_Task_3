
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aridakartika
 */
public class ServerThread extends Thread{
    final Socket client;
    static Map<String, DataOutputStream> users = new HashMap<>();
    String username = "";
    DataOutputStream outputStream = null;
    BufferedReader inputStream = null;
    
    public ServerThread(Socket client){
        this.client = client;
    }
    
    public void run(){
        try {
            inputStream = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outputStream = new DataOutputStream(client.getOutputStream());
            while (true) {
                //outputStream.writeBytes("submit your username "+ "\n");
                username = inputStream.readLine();
                if (username == null) {
                    return;
                }
                synchronized (users){
                    if (!users.containsKey(username)) {
                        users.put(username, outputStream);
                        System.out.println("username "+ username +" is connected");
                        break;
                    } 
                    //outputStream.writeBytes("username has already been used, try another username" + "\n");
                }
            }
            String inputan, message;
            while ((inputan = inputStream.readLine()) != null && !inputan.equals("quit")) {
                message = username + " said : " + inputan + "\n";
                System.out.println(message);
                for (DataOutputStream d : users.values()) {
                    d.writeBytes(message + "\n");
                    d.flush();
                }
            }
            if (username != null) {
                users.remove(username);
            }
            if (outputStream != null){
                users.remove(outputStream);
            }
            client.close();
        } catch(IOException e){
            System.out.println("exception : " + e);
        } 
    }
}
