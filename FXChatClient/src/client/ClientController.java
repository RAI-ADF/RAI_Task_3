package client;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Optional;

public class ClientController extends Thread{
    @FXML Text txtTitle;
    @FXML TextArea txtChat;
    @FXML Button btnDisconnect;
    @FXML ImageView logo;
    @FXML Pane root;
    @FXML TextField txtMsg;

    private String server_ip;
    private Socket server;
    private String msgInput;

    BufferedReader in;
    DataOutputStream out;

//    public void animate(){
//        logo.setLayoutX(64);
//        logo.setLayoutY(104);
//        logo.setFitWidth(192);
//        logo.setFitHeight(192);
//
//        FadeTransition fadeLogo = new FadeTransition(Duration.millis(500),logo);
//        fadeLogo.setFromValue(0.0);
//        fadeLogo.setToValue(1.0);
//
//        TranslateTransition moveLogo = new TranslateTransition(Duration.millis(500),logo);
//        moveLogo.setToX(-104.0);
//        moveLogo.setToY(-148.0);
//        ScaleTransition scaleLogo = new ScaleTransition(Duration.millis(500),logo);
//        scaleLogo.setToX(0.38);
//        scaleLogo.setToY(0.38);
//
//        FadeTransition fadeTitle = new FadeTransition(Duration.millis(200),txtTitle);
//        fadeTitle.setFromValue(0.0);
//        fadeTitle.setToValue(1.0);
//
//        FadeTransition fadeChat = new FadeTransition(Duration.millis(200),txtChat);
//        fadeChat.setFromValue(0.0);
//        fadeChat.setToValue(1.0);
//
//        FadeTransition fadeBtn = new FadeTransition(Duration.millis(200),btnDisconnect);
//        fadeBtn.setFromValue(0.0);
//        fadeBtn.setToValue(1.0);
//
//        FadeTransition fadeInput = new FadeTransition(Duration.millis(200),txtIsi);
//        fadeInput.setFromValue(0.0);
//        fadeInput.setToValue(1.0);
//
//        SequentialTransition st = new SequentialTransition(fadeLogo,scaleLogo,moveLogo,fadeTitle,fadeChat,fadeBtn,fadeInput);
//        st.setDelay(Duration.millis(1000));
//        st.play();
//    }

    public void showServerSetup(){
        TextInputDialog inputIPDialog = new TextInputDialog();
        inputIPDialog.setTitle("FX Chat Client");
        inputIPDialog.setHeaderText("Input Server IP Address to log into chat room.");
        inputIPDialog.setContentText("Server IP");
        Optional<String> result = inputIPDialog.showAndWait();
        if (result.isPresent()){
            server_ip = result.get();
        }
    }
    public void initialize(){
        showServerSetup();
        this.start();
    }

    @Override
    public void run(){
        try {
            txtChat.appendText("Server IP : ");
            txtChat.appendText(server_ip + "\n");
            server = new Socket(server_ip,8001);
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            out = new DataOutputStream(server.getOutputStream());
            ReadInput read = new ReadInput(in, this);
            read.start();
            txtMsg.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.ENTER) {
                        try {
                            msgInput = txtMsg.getText() + "\n";
                            if (msgInput.equals("quit")) {
                                server.close();
                                out.close();
                                in.close();
                            }
                        out.writeBytes(msgInput);
                            out.flush();
                            txtMsg.clear();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan!");
            System.err.println(e.getMessage());
        }
    }


    public TextArea getTxtChat(){
        return txtChat;
    }
}
