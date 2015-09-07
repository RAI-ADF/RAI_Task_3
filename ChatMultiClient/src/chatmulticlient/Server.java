/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatmulticlient;

/**
 *
 * @author NendiJuned
 */

import java.net.ServerSocket;

public class Server {
    
    private static final int PORT = 2000;
    
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        try (ServerSocket listener = new ServerSocket(PORT)) {
            while (true) {
                new ServerThread(listener.accept()).start();
            }
        }
    }
}
