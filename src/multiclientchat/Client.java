package multiclientchat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 *
 * @author nabilfarras Referensi http://cs.lmu.edu/~ray/notes/javanetexamples/
 */
public class Client {

    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("Chatter");
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);

    public Client() {

        // Layout GUI
        textField.setEditable(false);
        messageArea.setEditable(false);
        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.pack();

        // Add Listeners
        textField.addActionListener(new ActionListener() {
            /**
             * Responds to pressing the enter key in the textfield by sending
             * the contents of the text field to the server.    Then clear
             * the text area in preparation for the next message.
             */
            public void actionPerformed(ActionEvent e) {
                out.println(textField.getText());
                textField.setText("");
            }
        });
    }

//    private String getServerAddress() {
//        return JOptionPane.showInputDialog(
//            frame,
//            "Enter IP Address of the Server:",
//            "Welcome to the Chatter",
//            JOptionPane.QUESTION_MESSAGE);
//    }

    private String getName() {
        return JOptionPane.showInputDialog(
            frame,
            "Masukan Username untuk chat",
            JOptionPane.PLAIN_MESSAGE);
    }

    //ReadInput untuk membaca di server
    private void run() throws IOException {

        // Make connection and initialize streams
        // Bila ingin dipake untuk beda komputer masukin server address lebih dahulu
        String serverAddress = null;
        Socket socket = new Socket(serverAddress, 9090); // port dan serverAddress
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

//        Proses semua message di server
        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                out.println(getName());
            } else if (line.startsWith("NAMEACCEPTED")) {
                textField.setEditable(true);
            } else if (line.startsWith("MESSAGE")) {
                messageArea.append(line.substring(8) + "\n");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
    }
}