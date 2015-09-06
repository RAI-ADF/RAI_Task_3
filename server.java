package chat_server;

/**
 *
 * @author Puji Muharani
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class server extends javax.swing.JFrame{
   ArrayList clientOutputStreams;
   ArrayList<String> users;

   public class ClientHandler implements Runnable{
       BufferedReader reader;
       Socket socket;
       PrintWriter client;

       public ClientHandler(Socket clientSocket, PrintWriter user){
            client = user;
            try {
                socket = clientSocket;
                InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception ex){
                chatserver.append("Error! \n");
            }
       }

       @Override
       public void run(){
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat" ;
            String[] data;
            try{
                while ((message = reader.readLine()) != null){
                    chatserver.append("Received: " + message + "\n");
                    data = message.split(":");                    
                    //for (String token:data){
                    //    chatserver.append(token + "\n");
                    //}
                    if (data[2].equals(connect)){
                        tellEveryone((data[0] + ":" + data[1] + ":" + chat));
                        userAdd(data[0]);
                    } 
                    else if (data[2].equals(disconnect)){
                        tellEveryone((data[0] + ": disconnected." + ":" + chat));
                        userRemove(data[0]);
                    } 
                    else if (data[2].equals(chat)){
                        tellEveryone(message);
                    } 
                    else{
                        chatserver.append("Belum pernah terhubung. \n");
                    }
                } 
             } 
             catch (Exception ex){
                chatserver.append("Terputus... \n");
                ex.printStackTrace();
                clientOutputStreams.remove(client);
             } 
	} 
    }

    public server(){
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        chatserver = new javax.swing.JTextArea();
        btnstart = new javax.swing.JButton();
        btnstop = new javax.swing.JButton();
        btnusers = new javax.swing.JButton();
        btnclear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server's Chat");
        setName("server"); // NOI18N
        setResizable(false);

        chatserver.setColumns(20);
        chatserver.setRows(5);
        jScrollPane1.setViewportView(chatserver);

        btnstart.setText("START");
        btnstart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnstartActionPerformed(evt);
            }
        });

        btnstop.setText("STOP");
        btnstop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnstopActionPerformed(evt);
            }
        });

        btnusers.setText("USERS");
        btnusers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnusersActionPerformed(evt);
            }
        });

        btnclear.setText("CLEAR");
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnstart, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnstop, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnusers, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnclear, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnclear, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(btnstart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnusers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnstop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("Server's Chat");

        pack();
    }// </editor-fold>                        

    private void btnstopActionPerformed(java.awt.event.ActionEvent evt) {                                        
        try{
            Thread.sleep(5000);
        } 
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
        tellEveryone("Server: berhenti dan semua user terputus.\n:Chat");
        chatserver.append("Server berhenti... \n");
        chatserver.setText("");
    }                                       

    private void btnstartActionPerformed(java.awt.event.ActionEvent evt) {                                         
        Thread starter = new Thread(new ServerStart());
        starter.start();
        
        chatserver.append("Server dimulai... \n");
    }                                        

    private void btnusersActionPerformed(java.awt.event.ActionEvent evt) {                                         
        chatserver.append("\nUsers : \n");
        for (String current_user : users){
            chatserver.append(current_user);
            chatserver.append("\n");
        }    
    }                                        

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {                                         
        chatserver.setText("");
    }                                        

    public static void main(String args[]){
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                new server().setVisible(true);
            }
        });
    }
    
    public class ServerStart implements Runnable {
        @Override
        public void run(){
            clientOutputStreams = new ArrayList();
            users = new ArrayList();  
            try{
                ServerSocket serverSocket = new ServerSocket(1234);
                while (true){
                    Socket clientSock = serverSocket.accept();
                    PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
                    clientOutputStreams.add(writer);
                    Thread listener = new Thread(new ClientHandler(clientSock, writer));
                    listener.start();
                    chatserver.append("Terhubung.\n");
                }
            }
            catch (Exception ex){
                chatserver.append("Error! \n");
            }
        }
    }
    
    public void userAdd (String data){
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        chatserver.append("Before " + name + " added. \n");
        users.add(name);
        chatserver.append("After " + name + " added. \n");
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList){
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public void userRemove (String data){
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        users.remove(name);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList){
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public void tellEveryone(String message){
	Iterator it = clientOutputStreams.iterator();
        while (it.hasNext()){
            try{
                PrintWriter writer = (PrintWriter) it.next();
		writer.println(message);
		chatserver.append("Sending: " + message + "\n");
                writer.flush();
                chatserver.setCaretPosition(chatserver.getDocument().getLength());
            } 
            catch (Exception ex){
		chatserver.append("Error! \n");
            }
        } 
    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btnstart;
    private javax.swing.JButton btnstop;
    private javax.swing.JButton btnusers;
    private javax.swing.JTextArea chatserver;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration                   
}
