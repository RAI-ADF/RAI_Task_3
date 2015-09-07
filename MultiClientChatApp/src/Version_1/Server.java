/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Version_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author mickeyMice
 * Using tutorial on youtube with some change, link :https://www.youtube.com/watch?v=LpzKG5jPzjI by varietytubechan
 */
public class Server extends javax.swing.JFrame {

    ArrayList cos;
    ArrayList<String> onlineUsers = new ArrayList();
    Thread starter;

    /**
     * Creates new form Server
     */
    public Server() {
        initComponents();
    }

    public class ClientHandler implements Runnable {

        BufferedReader reader;
        Socket socket;
        PrintWriter client;

        public ClientHandler(Socket clientSocket, PrintWriter user) {
            client = user;
            try {
                socket = clientSocket;
                InputStreamReader in = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(in);
            } catch (Exception e) {
                System.out.println("Error on streamreader");
            }
        }

        public void run() {
            String message;
            String[] data;
            String connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try {
                while ((message = reader.readLine()) != null) {
                    logarea.append("Received " + message + "\n");
                    data = message.split("@");
                    for (String token : data) {
                        System.out.println(token);
                    }

                    if (data[2].equals(connect)) {
                        tellEveryone((data[0] + "@" + data[1] + "@" + chat));
//                        userAdd(data[0]);
                    } else if (data[2].equals(disconnect)) {
                        tellEveryone((data[0] + "@ has disconnected. @" + data[1] + "@" + chat));
//                        userRemove(data[0]);
                    } else if (data[2].equals(chat)) {
                        tellEveryone(message);
                    } else {
                        System.out.println("No condition were set");
                    }
                }
            } catch (Exception e) {
                System.out.println("lost connection");
                cos.remove(client);
            }
        }
    }


//    public void userRemove(String data) {
//        String message;
//        String add = "@ @Connect", done = "Server@ @Done";
//        onlineUsers.remove(data);
//        String[] tempList = new String[(onlineUsers.size())];
//        onlineUsers.toArray(tempList);
//
//        for (String token : tempList) {
//            message = (token + add);
//            tellEveryone(message);
//        }
//        tellEveryone(done);
//    }

    public class ServerStart implements Runnable {

        public void run() {
            cos = new ArrayList();
            try {
                ServerSocket ss = new ServerSocket(1912);
                while (true) {
                    Socket clientSocket = ss.accept();
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                    cos.add(writer);

                    Thread listener = new Thread(new ClientHandler(clientSocket, writer));
                    listener.start();
                    System.out.println("Got a connection");
                }
            } catch (Exception e) {
                System.out.println("Error making a connection");
            }
        }
    }

    public void tellEveryone(String message) {
        Iterator it = cos.iterator();

        while (it.hasNext()) {
            try {
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                System.out.println("Sending" + message);
                writer.flush();
            } catch (Exception ex) {
                System.out.println("error telling everyone");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        logarea = new javax.swing.JTextArea();
        btnservice = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        logarea.setColumns(20);
        logarea.setRows(5);
        jScrollPane1.setViewportView(logarea);

        btnservice.setText("START SERVICE");
        btnservice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnserviceActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Server Side");

        jLabel2.setText("Log's detail :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(btnservice, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 125, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnservice, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnserviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnserviceActionPerformed
        // TODO add your handling code here:
        if (btnservice.getText().equals("START SERVICE")) {
            starter = new Thread(new ServerStart());
            starter.start();
            logarea.append("Service start \n");
            btnservice.setText("STOP SERVICE");
        } else {
            starter.stop();
            logarea.append("Service stoped \n");
            btnservice.setText("START SERVICE");
        }
    }//GEN-LAST:event_btnserviceActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnservice;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea logarea;
    // End of variables declaration//GEN-END:variables
}
