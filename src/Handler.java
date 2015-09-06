
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Observable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 *
 */


public class Handler extends Observable {
    Socket socket;
    OutputStream outputStream;

    @Override
    public void notifyObservers(Object arg) {
        super.setChanged();
        super.notifyObservers(arg);
    }

    public Handler(String server, int port) throws IOException {
        socket = new Socket(server, port);
        outputStream = socket.getOutputStream();

        Thread ThreadHandler = new Thread() {
            @Override
            public void run() {
                try {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null)
                        notifyObservers(line);
                } catch (IOException ex) {
                    notifyObservers(ex);
                }
            }
        };
        ThreadHandler.start();
    }

    private static final String CRLF = "\r\n"; 

    public void send(String text) {
        try {
            outputStream.write((text + CRLF).getBytes());
            outputStream.flush();
        } catch (IOException ex) {
            notifyObservers(ex);
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException ex) {
            notifyObservers(ex);
        }
    }

    static class main {

        public main(String[] args) {
            String server = args[0];
            int port =2200;
            Handler access = null;
            try {
                access = new Handler(server, port);
            } catch (IOException ex) {
                System.out.println("Cannot connect to " + server + ":" + port);
                ex.printStackTrace();
                System.exit(0);
            }
            new ChatGUI(access).setVisible(true);
        }
    }
}

    
