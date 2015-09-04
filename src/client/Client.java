/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Dimension;
import java.awt.Toolkit;
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
    
    public Client(){
        dataField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                out.println(dataField.getText());
                String response;
                
                try{
                    response = in.readLine();
                    if(response.startsWith("MESSAGE")){
                        messageArea.append(response.substring(8) + "\n");
                        response = in.readLine();
                    }
                    if(response == null || response.equals("")){
                        System.exit(0);
                    }
                } catch (IOException e){
                    response = "Error : " + e;
                }
                
                messageArea.append(response + "\n");
                dataField.selectAll();
            }
        });
    }
    
    public void connectToServer() throws IOException{
        String alamatServer = JOptionPane.showInputDialog(
            gui,
            "Masukkan alamat IP server :",
            "Selamat datang di server kuis",
            JOptionPane.QUESTION_MESSAGE);
        
        Socket socket = new Socket(alamatServer, 9091);
        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        
        for (int i = 0; i < 5; i++) {
            messageArea.append(in.readLine() + "\n");
        }

    }
    
    public static void main(String[] args) throws IOException{
        
//        String ipServer = JOptionPane.showInputDialog("Masukkan alamat server");
//        Socket clientSocket = new Socket(ipServer, 9090);
//        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//        String jawaban = in.readLine();
//        JOptionPane.showConfirmDialog(null, jawaban);
//        System.exit(0);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        

        Client c = new Client();
        c.gui.setLocation(dim.width/2-c.gui.getSize().width/2, dim.height/2-c.gui.getSize().height/2);
        c.gui.setVisible(true);
        c.connectToServer();
    }
    
}
