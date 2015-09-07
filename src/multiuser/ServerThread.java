package multiuser;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

/**
 * Handles new user coming from socket that passed from Server. New users need
 * to give their username. This class has all the users synchronized. Every
 * message from client will be sent back to all available users.
 *
 * @see Server
 * @see Client
 * @author ade darma
 */
public class ServerThread extends Thread {

    private Socket clientSocket = null;
    private static Map<String, DataOutputStream> users = new HashMap<>();

    private String username = "";
    private DataOutputStream outputStream = null;
    private BufferedReader reader = null;

    
    public ServerThread(Socket client) {
        clientSocket = client;
    }

    @Override
    public void run() {
        try {
            outputStream = new DataOutputStream(clientSocket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while (true) {
                // outputStream.writeBytes("your username:\n");
                username = reader.readLine();
                if ("".equals(username)) {
                    return;
                }
                synchronized (users) {
                    if (!users.containsKey(username)) {
                        outputStream.writeBytes("username has already been used " + "\n");
                        users.put(username, outputStream);
                        break;
                    }
                }
            }

            String line = "", message = "";
            while ((line = reader.readLine()) != null && !line.equals("quit")) {
                message = username + " said: " + line;
                System.out.println(message);
                for (DataOutputStream d : users.values()) {
                    d.writeBytes(message + " \n");
                    d.flush();
                }
            }
            if (username != null) {
                users.remove(username);
            }
            if (outputStream != null) {
                users.remove(outputStream);
            }
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
