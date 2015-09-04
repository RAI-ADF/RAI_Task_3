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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Socket socket;

    public Client() {
        dataField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                if (!"".equals(dataField.getText())) {

                    if ("quit".equals(dataField.getText())) {
                        dataField.setEnabled(false);
                        JOptionPane.showMessageDialog(gui, "Anda telah meninggalkan percakapan", "Chatting Client", JOptionPane.INFORMATION_MESSAGE);
                        gui.dispose();
                        out.println("");
                    } else {
                        out.println(dataField.getText());
                    }

                }
            }
        });
    }

    public void connectToServer() {
        try {
            String alamatServer = JOptionPane.showInputDialog(
                    gui,
                    "Masukkan alamat IP server :",
                    "Selamat datang di chatting server",
                    JOptionPane.QUESTION_MESSAGE);

            socket = new Socket(alamatServer, 9091);
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            dataField.requestFocus();

            update();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(gui, ex.toString(), "Terjadi Kesalahan ! ", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void update() {
        while (true) {
            try {
                String inMsg = "";
                inMsg = in.readLine();

                if (!"close".equals(inMsg)) {
                    messageArea.append(inMsg + "\n");
                    dataField.setText("");
                } else {
                    gui.dispose();
                    socket.close();
                    break;
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(gui, ex.toString(), "Terjadi Kesalahan!", JOptionPane.ERROR_MESSAGE);
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        Client c = new Client();
        c.gui.setLocationRelativeTo(null);
        c.gui.setVisible(true);
        c.connectToServer();
    }

}
