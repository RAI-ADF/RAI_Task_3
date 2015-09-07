package testing;

/**
 *
 * @author Thinkpad
 */
import java.io.*;
import java.net.*;
import java.util.HashSet;

public class Server {
    private static final int PORT = 8080;
    private static HashSet<String> names = new HashSet<String>();
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new ServerThread(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }
}
