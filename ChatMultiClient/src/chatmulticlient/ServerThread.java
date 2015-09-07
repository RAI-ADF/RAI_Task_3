/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatmulticlient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;

/**
 *
 * @author NendiJuned
 */
public class ServerThread extends Thread{
    private String username;
    private final Socket client;
    private static HashSet<String> names = new HashSet<String>();
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
    private BufferedReader inputStream;
    private PrintWriter outputStream;

    public ServerThread(Socket client) {
        this.client = client;
    }

    public void run() {
        try {
            //membuat socket baru
            inputStream = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outputStream = new PrintWriter(client.getOutputStream(), true);

            //dapat melakukan penambahan user
            while (true) {
                outputStream.println("SUBMITNAME");
                username = inputStream.readLine();
                if (username == null) {
                    return;
                }
                synchronized (names) {
                    if (!names.contains(username)) {
                        names.add(username);
                        break;
                    }
                }
            }

            outputStream.println("NAMEACCEPTED");
            writers.add(outputStream);

            while (true) {
                String input = inputStream.readLine();
                if (input == null) {
                    return;
                }
                for (PrintWriter writer : writers) {
                    writer.println("MESSAGE " + username + ": " + input);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (username != null) {
                names.remove(username);
            }
            if (outputStream != null) {
                writers.remove(outputStream);
            }
            try {
                client.close();
            } catch (IOException e) {
            }
        }
    }
}