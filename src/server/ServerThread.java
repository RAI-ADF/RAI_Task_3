/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dieka
 */
public class ServerThread extends Thread {

    private Socket socket = null;
    static Map<String, DataOutputStream> users = new HashMap<>();

    public ServerThread(Socket soc) {
        this.socket = soc;
    }

    @Override
    public void run() {

        BufferedReader in = null;
        DataOutputStream out = null;
        String username = "";

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());

            System.out.println("Terhubung dengan : " + socket.getRemoteSocketAddress());
            out.writeBytes("Masukkan Username : \n");

            while (true) {

                username = in.readLine();
                if (username == null) {
                    return;
                }

                synchronized (users) {
                    if (!users.containsKey(username)) {
                        users.put(username, out);
                        out.writeBytes("Silahkan untuk memulai chat, " + username + " ketikkan 'quit' untuk keluar \n");
                        System.out.println(socket.getRemoteSocketAddress() + " menggunakan username " + username);
                    } else {
                        out.writeBytes("Username yang anda masukkan sudah ada. memutuskan sambungan....\n");
                        break;
                    }
                }

                while (username != "") {
                    String chat = in.readLine();
                    System.out.println(username + " : '" + chat + "'");

                    if (chat != null) {
                        if (!"".equals(chat)) {
                            for (DataOutputStream d : users.values()) {
                                d.writeBytes(username + " : " + chat + " \n");
                            }
                        } else {
                            for (DataOutputStream d : users.values()) {
                                d.writeBytes(username + " meninggalkan percakapan \n");
                            }
                            out.writeBytes("close\n");
                            users.remove(username);
                            break;
                        }
                    }

                }

                
            }

        } catch (IOException ex) {
            System.out.println(ex.toString() + " -- " + socket.getRemoteSocketAddress());
        } finally {
            System.out.println("Terputus dengan client : " + socket.getRemoteSocketAddress() + " " + username);
            username = "";

        }

    }
}
