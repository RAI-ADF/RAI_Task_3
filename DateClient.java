package JRK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

public class DateClient {

    /* MENJALANKAN APLIKASI CLIENT*/
    public static void main(String[] args) throws IOException {
        String serverAddress = JOptionPane.showInputDialog(
            "IP Address \n" +
            "running port 11 :");
        Socket s = new Socket(serverAddress, 11);
        BufferedReader input =
            new BufferedReader(new InputStreamReader(s.getInputStream()));
        String answer = input.readLine();
        JOptionPane.showMessageDialog(null, answer);
        System.exit(0);
    }
}
