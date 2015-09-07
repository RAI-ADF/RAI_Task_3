/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rai_socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author hafiz
 */

public class serverThread extends Thread {

    private DataInputStream inStream = null;
    private PrintStream outStream = null;
    private Socket clientSocket = null;
    private final serverThread[] threads;
    private final int maxConnection;

    public serverThread(Socket clientSocket, serverThread[] threads) {
        this.clientSocket = clientSocket;
        this.threads = threads;
        maxConnection = threads.length;
    }

    @Override
    public void run() {
        try {
            int maksimum = this.maxConnection;
            serverThread[] trit = this.threads;

            inStream = new DataInputStream(clientSocket.getInputStream());
            outStream = new PrintStream(clientSocket.getOutputStream());
            outStream.println("Masukkan ID Anda: ");
            String id = inStream.readLine().trim();
            outStream.println("___ Selamat Datang di chatroom, " + id + "! ___");
            outStream.println("___ ketik 'quit' untuk keluar chatroom ___");
            for (int i = 0; i < maksimum; i++) {
                if (trit[i] != null && trit[i] != this) {
                    trit[i].outStream.println("___ " + id + " telah bergabung di chatroom ___");
                }
            }
            while (true) {
                String chat = inStream.readLine();
                if (chat.startsWith("quit")) {
                    break;
                }
                for (int i = 0; i < maksimum; i++) {
                    if (trit[i] != null) {
                        trit[i].outStream.println("<" + id + ">: " + chat);
                    }
                }
            }
            for (int i = 0; i < maksimum; i++) {
                if (trit[i] != null && trit[i] != this) {
                    trit[i].outStream.println("___ " + id + " telah keluar dari chatroom ___");
                }
            }
            outStream.println("___ Bye " + id + " ___");
            for (int i = 0; i < maksimum; i++) {
                if (trit[i] == this) {
                    trit[i] = null;
                }
            }
            inStream.close();
            outStream.close();
            clientSocket.close();
        } catch (IOException ex) {
            System.out.println("IO Exception - chat");
        }

    }

}   
