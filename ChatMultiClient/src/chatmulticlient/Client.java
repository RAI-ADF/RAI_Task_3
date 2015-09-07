/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatmulticlient;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author NendiJuned
 */
public class Client {
    static BufferedReader inputStream;
    static PrintWriter outputStream;
    JFrame frame = new JFrame("Chat Multi-client");
    JTextField txtChat = new JTextField(40);
    Button btnSend = new Button("Send");
    JTextArea txtChatRoom = new JTextArea(8, 40);

    public Client() {

        // Layout GUI
        txtChat.setEditable(false);
        txtChatRoom.setEditable(false);
        frame.getContentPane().add(txtChat, "South");
        frame.getContentPane().add(new JScrollPane(txtChatRoom), "Center");
        frame.pack();

        // Add Listeners
        txtChat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputStream.println(txtChat.getText());
                txtChat.setText("");
            }
        });
    }

        String getServerAddress() {
            return JOptionPane.showInputDialog(
                frame,
                "Enter IP Address of the Server:",
                "Input IP Address",
                JOptionPane.QUESTION_MESSAGE);
        }

        String getName() {
            return JOptionPane.showInputDialog(
                frame,
                "Choose a username:",
                "Create your Username",
                JOptionPane.PLAIN_MESSAGE);
        }

    void run() throws IOException {

        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress, 2000);
        inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outputStream = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
            String line = inputStream.readLine();
            if (line.startsWith("SUBMITNAME")) {
                outputStream.println(getName());
            } else if (line.startsWith("NAMEACCEPTED")) {
                txtChat.setEditable(true);
            } else if (line.startsWith("MESSAGE")) {
                txtChatRoom.append(line.substring(8) + "\n");
            }
        }
    }
    
    /**
     * Runs the client as an application with a closeable frame.
     * @param args
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
    }
}
