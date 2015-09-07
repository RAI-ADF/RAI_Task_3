
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aridakartika
 */
public class Server {
    public static void main(String[] args) {
        int portNumber = 1234;
        
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            
            DataOutputStream outStream = new DataOutputStream
            (clientSocket.getOutputStream());
            BufferedReader inStream = new BufferedReader
            (new InputStreamReader(clientSocket.getInputStream()));{
            
            System.out.println("just connected with " + clientSocket.getRemoteSocketAddress());
            String inputLine;
            while((inputLine = inStream.readLine()) != null) {
                System.out.println("client says: " + inputLine);
                outStream.writeBytes("message received " + "\n");
            }
        }   clientSocket.close();
        } catch(IOException e){
            System.out.println("Exception caught when trying to listen on port "+
                    portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
