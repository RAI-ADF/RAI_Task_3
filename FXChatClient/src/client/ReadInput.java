package client;

import java.io.BufferedReader;import java.lang.*;import java.lang.Override;import java.lang.String;import java.lang.System;import java.lang.Thread;

/**
 * Created by rizki on 04/09/15.
 */
public class ReadInput extends Thread{
    private BufferedReader input;
    private ClientController controller;
    public ReadInput(BufferedReader input, ClientController controller){
        this.input = input;
        this.controller = controller;
    }

    @Override
    public void run(){
        String inputMsg;
        try {
            while ((inputMsg=input.readLine()) != null){
                controller.getTxtChat().appendText(inputMsg + "\n");
            }
        } catch (java.lang.Exception e){
            e.printStackTrace();
        }
    }
}
