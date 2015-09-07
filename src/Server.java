import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int portnumber = 1234;
        try {
            ServerSocket serverSocket = new ServerSocket(portnumber);                       
           while(true){
                Socket clientsocket = serverSocket.accept();
                
                System.out.println(clientsocket);
                ServerThread Sthread = new ServerThread(clientsocket);
                Sthread.start();                
               }
            
        } catch (IOException ex) {
            System.out.println("exception on port "+portnumber);
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }    
}