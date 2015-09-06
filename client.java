package chat_client;
/**
 *
 * @author Puji Muharani
 */
import java.net.*;
import java.io.*;
import java.util.*;

public class client extends javax.swing.JFrame {
    String username, address = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 1234;
    Boolean isConnected = false;
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    
    public void ListenThread(){
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }
    
    public void userAdd(String data) {
         users.add(data);
    }
    
    public void userRemove(String data) {
         chatclient.append(data + " is now offline.\n");
    }
    
    public void writeUsers() {
         String[] tempList = new String[(users.size())];
         users.toArray(tempList);
         for (String token:tempList){
         }
    }
    
    public void sendDisconnect() {
        String bye = (username + ": :Disconnect");
        try{
            writer.println(bye); 
            writer.flush(); 
        } 
        catch (Exception e) {
            chatclient.append("Could not send Disconnect message.\n");
        }
    }

    public void Disconnect() {
        try{
            chatclient.append("Disconnected.\n");
            socket.close();
        } 
        catch(Exception ex) {
            chatclient.append("Failed to disconnect. \n");
        }
        isConnected = false;
        txtusername.setEditable(true);
    }
    
    public client() {
        initComponents();
    }
    
    public class IncomingReader implements Runnable{
        @Override
        public void run() {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";
            try {
                while ((stream = reader.readLine()) != null) {
                    data = stream.split(":");
                    if (data[2].equals(chat)) {
                        chatclient.append(data[0] + ": " + data[1] + "\n");
                        chatclient.setCaretPosition(chatclient.getDocument().getLength());
                    }
                    else if (data[2].equals(connect)){
                        chatclient.removeAll();
                        userAdd(data[0]);
                    } 
                    else if (data[2].equals(disconnect)){
                         userRemove(data[0]);
                    } 
                    else if (data[2].equals(done)){
                        writeUsers();
                        users.clear();
                    }
                }
           }
            catch(Exception ex) { }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        labelhost = new javax.swing.JLabel();
        txthost = new javax.swing.JTextField();
        labelport = new javax.swing.JLabel();
        txtport = new javax.swing.JTextField();
        labelusername = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        btnconnect = new javax.swing.JButton();
        btndis = new javax.swing.JButton();
        btnunique = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatclient = new javax.swing.JTextArea();
        kolomketik = new javax.swing.JTextField();
        btnsend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client's Chat");
        setName("client"); // NOI18N
        setResizable(false);

        labelhost.setText("Host IP");

        txthost.setText("localhost");
        txthost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthostActionPerformed(evt);
            }
        });

        labelport.setText("Port ");

        txtport.setText("2222");
        txtport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtportActionPerformed(evt);
            }
        });

        labelusername.setText("Username");

        txtusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusernameActionPerformed(evt);
            }
        });

        btnconnect.setText("Connect");
        btnconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconnectActionPerformed(evt);
            }
        });

        btndis.setText("Disconnect");
        btndis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndisActionPerformed(evt);
            }
        });

        btnunique.setText("Unique Username");
        btnunique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnuniqueActionPerformed(evt);
            }
        });

        chatclient.setColumns(20);
        chatclient.setRows(5);
        jScrollPane1.setViewportView(chatclient);

        btnsend.setText("SEND");
        btnsend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelhost, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelport, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtport, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txthost, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelusername)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnunique, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnconnect, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btndis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(kolomketik)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnsend)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelhost)
                            .addComponent(txthost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelusername)
                            .addComponent(txtusername))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelport)
                            .addComponent(txtport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnunique)))
                    .addComponent(btnconnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btndis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kolomketik, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnsend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1)))
                .addGap(18, 18, 18))
        );

        getAccessibleContext().setAccessibleName("Client's Chat");

        pack();
    }// </editor-fold>                        

    private void txthostActionPerformed(java.awt.event.ActionEvent evt) {                                        
       
    }                                       

    private void txtportActionPerformed(java.awt.event.ActionEvent evt) {                                        
   
    }                                       

    private void txtusernameActionPerformed(java.awt.event.ActionEvent evt) {                                            
    
    }                                           

    private void btnconnectActionPerformed(java.awt.event.ActionEvent evt) {                                           
        if (isConnected == false){
            username = txtusername.getText();
            txtusername.setEditable(false);
            try{
                socket = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(socket.getOutputStream());
                writer.println(username + ":has connected.:Connect");
                writer.flush(); 
                isConnected = true; 
            } 
            catch (Exception ex){
                chatclient.append("Cannot Connect! Try Again. \n");
                txtusername.setEditable(true);
            }
            ListenThread();
        } 
        else if (isConnected == true){
            chatclient.append("You are already connected. \n");
        }
    }                                          

    private void btndisActionPerformed(java.awt.event.ActionEvent evt) {                                       
        sendDisconnect();
        Disconnect();
    }                                      

    private void btnuniqueActionPerformed(java.awt.event.ActionEvent evt) {                                          
        txtusername.setText("");
        if (isConnected == false){
            String anon="anon";
            Random generator = new Random(); 
            int i = generator.nextInt(999) + 1;
            String is=String.valueOf(i);
            anon=anon.concat(is);
            username=anon;
            
            txtusername.setText(anon);
            txtusername.setEditable(false);

            try{
                socket = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(socket.getOutputStream());
                writer.println(anon + ":has connected.:Connect");
                writer.flush(); 
                isConnected = true; 
            } 
            catch (Exception ex){
                chatclient.append("Cannot Connect! Try Again. \n");
                txtusername.setEditable(true);
            }
            ListenThread();
            } 
        else if (isConnected == true){
            chatclient.append("You are already connected. \n");
        }        
    }                                         

    private void btnsendActionPerformed(java.awt.event.ActionEvent evt) {                                        
        String nothing = "";
        if ((kolomketik.getText()).equals(nothing)){
            kolomketik.setText("");
            kolomketik.requestFocus();
        } 
        else {
            try {
               writer.println(username + ":" + kolomketik.getText() + ":" + "Chat");
               writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                chatclient.append("Message was not sent. \n");
            }
            kolomketik.setText("");
            kolomketik.requestFocus();
        }
        kolomketik.setText("");
        kolomketik.requestFocus();
    }                                       

    public static void main(String args[]){
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() 
            {
                new client().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton btnconnect;
    private javax.swing.JButton btndis;
    private javax.swing.JButton btnsend;
    private javax.swing.JButton btnunique;
    private javax.swing.JTextArea chatclient;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kolomketik;
    private javax.swing.JLabel labelhost;
    private javax.swing.JLabel labelport;
    private javax.swing.JLabel labelusername;
    private javax.swing.JTextField txthost;
    private javax.swing.JTextField txtport;
    private javax.swing.JTextField txtusername;
    // End of variables declaration                   
}
