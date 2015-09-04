/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author TOSHIBA
 */
public class Client {

    private BufferedReader in;
    private PrintWriter out;
    private ClientGUI gui = new ClientGUI();
    private JTextField dataField = gui.getDataField();
    private JTextArea messageArea = gui.getMessageArea();

    public Client() {
        dataField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                out.println(dataField.getText());
            }
        });
    }

    public void connectToServer() {
        try {
            String alamatServer = JOptionPane.showInputDialog(
                    gui,
                    "Masukkan alamat IP server :",
                    "Selamat datang di server kuis",
                    JOptionPane.QUESTION_MESSAGE);
            
            Socket socket = new Socket(alamatServer, 9091);
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            dataField.requestFocus();
            
            while(true){
                update();
            }
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(gui, ex.toString(), "Terjadi Kesalahan ! ", JOptionPane.ERROR_MESSAGE);
            gui.dispose();
        }
    }

    public void update() {
        try {
            String inMsg = in.readLine();
            if (inMsg != null) {
                messageArea.append(inMsg + "\n");
                dataField.setText("");
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(gui, ex.toString(), "Terjadi Kesalahan!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) throws IOException {

        Client c = new Client();
        c.gui.setLocationRelativeTo(null);
        c.gui.setVisible(true);
        c.connectToServer();
    }

}
