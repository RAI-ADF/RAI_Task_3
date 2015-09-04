/*
 * Reused Code from From JarKom(Jaringan Komputer) Task with 
 * modification for RAI task
 *
 * Dieka Nugraha Karyana
 * 1103120057
 */
package server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TOSHIBA
 */
public class Server {
    
    public static void main(String[] args) throws IOException{
        
        ServerSocket listener = new ServerSocket(9091);
        
        System.out.println("Server dinyalakan pada : " + new Date().toString());
        System.out.println("Menunggu Client terhubung....... \n");
        
        int clientNumber = 0;
        
        try{
            while(true){
                
                new ClientHandler(listener.accept(), clientNumber++).start();
            }
        } finally {
            listener.close();
        }
    }

    private static class ClientHandler extends Thread{
        private Socket socket;
        private int clientNumber;
        
        public ClientHandler(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            System.out.println("Terhubung dengan client no: "+ clientNumber +" di: " + socket);
        }
        
        @Override
        public void run(){
            
            try{
                
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                
                out.println("Selamat datang, anda adalah client no " + clientNumber);
                out.println("ketikkan jawaban pada kotak dibawah dan tekan enter untuk mengirim jawaban");
                out.println("ketik keluar untuk mengakhiri \n");
                
                while(true){
                    BufferedReader b = new BufferedReader(new FileReader("quiz.txt"));
                    String set=null;
                    int benar=0,jumlah=0;
                    
                    while((set = b.readLine()) != null){
                        jumlah++;
                        String[] pertanyaan = set.split("\\|");
                        out.println(pertanyaan[0]);

                        String input = in.readLine();
                        if(input.toLowerCase().equals(pertanyaan[1])){
                            out.println("MESSAGE Anda Benar!");
                            benar++;
                        } else if(input == null || input.toLowerCase().equals("keluar")) {
                            System.out.println("Client " + clientNumber + " keluar dari kuis");
                            break;
                        } else {
                            out.println("MESSAGE Anda Salah");
                        }                       
                    }
                    out.println("Pertanyaan sudah habis");
                    out.println("Benar : " + benar + " Total : " + jumlah + " Skor : " + (benar/jumlah)*100);
                    out.println("anda telah keluar dari server\n");
                    b.close();
                    break;
                }
                
            }  catch (IOException ex) {
                
                System.out.println("Terjadi error ketika me-handling client no " + clientNumber +"\n"+ex.toString());
                
            } finally {
                
                try{
                    socket.close();
                    
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("koneksi dengan cient no " + clientNumber+ " diputus");
            }
            
        }
    }
    
}
