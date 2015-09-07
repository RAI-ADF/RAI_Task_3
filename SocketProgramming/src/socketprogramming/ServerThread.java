/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package socketprogramming;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author notebook
 */
public class ServerThread extends Thread{
    final Socket client;
    static Map<String, DataOutputStream> users = new HashMap<>();
    String username = "";
    DataOutputStream outStream = null;
    BufferedReader inStream = null;
    private Socket clientSocket = null;

    public ServerThread(Socket client) {
        this.client = client;
    }

    public void run() {
        try (
                BufferedReader inStream  = new BufferedReader(new InputStreamReader(client.getInputStream()));
                DataOutputStream outStream = new DataOutputStream(client.getOutputStream());)
            {
                while (true){
                    outStream.writeBytes("Masukkan username"+"\n");
                    username = inStream.readLine();
                    if(username == null){
                        return;
                    }
                    synchronized (users){
                        if(users.containsKey(username)){
                            outStream.writeBytes("Maaf, username yang anda masukkan telah terdaftar"+"\n");
                        }else{
                            users.put(username, outStream);
                            break;
                        }
                    }
                }
                String inputan, message;
                while((inputan=inStream.readLine()) != null&& !inputan.equals("quit")){
                    message = username+" said : "+inputan;
                    System.out.println(message);
                    for (DataOutputStream d: users.values()){
                        d.writeBytes(message+"\n");
                        d.flush();
                    }
                    
                }
                if (username!=null){
                    users.remove(username);
                }
                if(outStream != null){ 
                    users.remove(outStream);
                }
                client.close();
        } catch (IOException e) {
            System.out.println("ee"+e.getMessage());
            e.printStackTrace();
        }
    }
}
