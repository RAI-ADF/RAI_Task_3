/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatmulticlient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Surya Saputra
 */
public class Client {
    static BufferedReader in;
    static PrintWriter out;
    static JFrame frame = new JFrame("Chat Box");
    static JTextField textField = new JTextField(40);
    static JTextArea messageArea = new JTextArea(8, 40);

    public Client() {

        // Layout GUI
        textField.setEditable(false);
        messageArea.setEditable(false);
        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.pack();

        // Add Listeners
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                out.println(textField.getText());
                textField.setText("");
            }
        });
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        Client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Client.frame.setVisible(true);
        ReadInput.run();
    }
}
