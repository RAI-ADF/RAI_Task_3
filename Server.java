package JRK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
        System.out.println("server is running");
        int clientNumber = 0;
        ServerSocket listener = new ServerSocket(179);
        try {
            while (true) {
                new Capitalizer(listener.accept(), clientNumber++).start();
            }
        } finally {
            listener.close();
        }
    }

    
    private static class Capitalizer extends Thread {
        private Socket socket;
        private int clientNumber;

        public Capitalizer(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            log("new connection with # " + clientNumber + " at " + socket);
        }

       
        public void run() {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // PEMBUKA PESAN KE CLIENT.
                out.println("u're a client T#" + clientNumber + ".");
                out.println("for exit usiong . + enter\n");

                while (true) {
                    String input = in.readLine();
                    if (input == null || input.equals(".")) {
                        break;
                    }
                    out.println(input.toUpperCase());
                }
            } catch (IOException e) {
                log("client active# " + clientNumber + ": " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    log("error when trying to exit the program");
                }
                log("connect with # " + clientNumber + " is closed");
            }
        }

        /*PROSES PENULISAN PESAN */
        private void log(String message) {
            System.out.println(message);
        }
    }
}
