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
import javax.swing.JOptionPane;

/**
 *
 * @author Surya Saputra
 */
public class ReadInput {    
    /**
     * Prompt for and return the address of the server.
     */
    private static String getServerAddress() {
        return JOptionPane.showInputDialog(
            Client.frame,
            "Enter IP Address of the Server:",
            "Welcome to the Chatter",
            JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Prompt for and return the desired screen name.
     */
    private static String getName() {
        return JOptionPane.showInputDialog(
            Client.frame,
            "Choose a screen name:",
            "Screen name selection",
            JOptionPane.PLAIN_MESSAGE);
    }
    
    static void run() throws IOException {

        // Make connection and initialize streams
        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress, 9001);
        Client.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Client.out = new PrintWriter(socket.getOutputStream(), true);

        // Process all messages from server, according to the protocol.
        while (true) {
            String line = Client.in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                Client.out.println(getName());
            } else if (line.startsWith("NAMEACCEPTED")) {
                Client.textField.setEditable(true);
            } else if (line.startsWith("MESSAGE")) {
                Client.messageArea.append(line.substring(8) + "\n");
            }
        }
    }
}
