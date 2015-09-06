package server;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController extends Thread{
    @FXML TextArea txtLog;
    @FXML Text txtClients;

    private final int maxClient=10;
    private int numClient;
    private ServerSocket server;
    private final int port=8001;

    public ServerController(){
        try {
            numClient=0;
            server = new ServerSocket(port);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
        txtLog.appendText("Welcome to FX Chat ServerController!\n\n");
        txtLog.appendText("Server started on port " + server.getLocalPort() + "\n");
    }

    public void stopServer(){
        if (server!=null) {
            System.out.println("ServerController stopped... [SUCCESS]");
            this.interrupt();
            Platform.exit();
            System.exit(1);
        }
    }

    public TextArea getTxtLog() {
        return txtLog;
    }

    public Text getTxtClients() {
        return txtClients;
    }

    @Override
    public void run(){
        while (numClient < maxClient){
            try {
                Socket client = server.accept();
                ServerThread thread = new ServerThread(client,this);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
