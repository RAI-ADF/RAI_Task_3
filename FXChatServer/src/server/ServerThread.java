package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rizki on 04/09/15.
 */
public class ServerThread extends Thread {
    final Socket client;
    static Map<String, DataOutputStream> users = new HashMap<String, DataOutputStream>();
    String username="";
    DataOutputStream outputStream = null;
    BufferedReader inputReader = null;
    ServerController serverController;

    public ServerThread(Socket client, ServerController serverController){
        this.client = client;
        this.serverController = serverController;
    }

    @Override
    public void run(){
        try {
            outputStream = new DataOutputStream(client.getOutputStream());
            inputReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while (true){
                outputStream.writeBytes("submit your username : \n");
                username = inputReader.readLine();
                if (username==null)
                    return;
                synchronized (users){
                    if (!users.containsKey(username)){
                        outputStream.writeBytes("username available, added!\n");
                        users.put(username, outputStream);
                        serverController.getTxtLog().appendText("users connected : " + users.keySet().toString() + "\n");
                        serverController.getTxtClients().setText(String.valueOf(users.size()));
                        break;
                    }
                    else{
                        outputStream.writeBytes("username not available, use other username.\n");
                    }
                }
            }
            String userInput, message;
            while ((userInput=inputReader.readLine()) != null && !userInput.equals("quit")){
                message = username + " : " + userInput;
                serverController.getTxtLog().appendText(message + "\n");
                for (DataOutputStream d : users.values()){
                    d.writeBytes(message + "\n");
                    d.flush();
                }
            }
            serverController.getTxtLog().appendText(username + " disconnected\n");
            users.remove(username);
            users.remove(outputStream);
            client.close();
            serverController.getTxtLog().appendText("users connected : " + users.keySet().toString() + "\n");
            serverController.getTxtClients().setText(String.valueOf(users.size()));
        }
        catch (Exception e){

        }
    }
}
