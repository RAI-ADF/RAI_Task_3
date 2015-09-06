package chat_server;

/**
 *
 * @author Puji Muharani
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ServerThread extends Thread{
    final Socket client;
    static Map<String, DataOutputStream> users = new HashMap<>();
    String username = "";
    DataOutputStream outputStream = null;
    BufferedReader inputStream = null;
    public ServerThread(Socket Client){
        super("serving client");
        this.client = Client;
    }
    public void run(){
        try {
            outputStream = new DataOutputStream(client.getOutputStream());
            inputStream = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("Just connect with "+client.getRemoteSocketAddress());
            String inputLine;
            while((inputLine=inputStream.readLine()) != null){
                System.out.println("client saya: "+inputLine);
                outputStream.writeBytes("message received. \n");
            }
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
