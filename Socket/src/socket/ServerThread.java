/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package socket;

/**
 *
 * @author Adam
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adam
 * original copy from 
 * http://cs.lmu.edu/~ray/notes/javanetexamples/
 */
public class ServerThread extends Thread {
 
        private static HashSet<String> names = new HashSet<String>();
        private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
        private String name;
        private Socket Clientsocket;
        private BufferedReader in;
        private PrintWriter out;
        
        public ServerThread(Socket socket){
            this.Clientsocket=socket;
        }
        
        public void run() {
            try {

                
                in = new BufferedReader(new InputStreamReader(
                    Clientsocket.getInputStream()));
                out = new PrintWriter(Clientsocket.getOutputStream(), true);

             
                while (true) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (names) {
                        if (!names.contains(name)) {
                            names.add(name);
                            break;
                        }
                    }
                }

                
                out.println("NAMEACCEPTED");
                writers.add(out);

       
                while (true) {
                    String input = in.readLine();
                    if (input == null) {
                        return;
                    }
                    for (PrintWriter writer : writers) {
                        writer.println("MESSAGE " + name + ": " + input);
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {

                if (name != null) {
                    names.remove(name);
                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    Clientsocket.close();
                } catch (IOException e) {
                }
            }
        }
        
    
}
