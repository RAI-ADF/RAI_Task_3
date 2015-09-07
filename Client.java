
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
public static void main(String[] args, String serverName) throws IOException {
	String serverIP = "192.168.155.111";
	int portNumber=3541;
	try (
		Socket clientSocket = new Socket(serverName, portNumber);
		PrintWriter outstream = new PrintWriter(clientSocket.getOutputStream(),true);
		BufferedReader inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in)){
		System.out.println("Terhubung ke " + clientSocket.getRemoteSocketAddress());
		String inputClient;
		while ((userInput = KeyBoard.readLine())!= null) {
			outStream.println(inputClient);
			System.out.println("Server membalas : " +message);                     
		}
            }catch (UnknownHostException e) {
                System.err.println("Host tidak diketahui" +serverIP);
                System.exit(1);
            }catch (IOException e) {
                System.err.println("Tidak dapat melakukan koneksi ke" +serverIP);
                System.exit(1);
                 }       
}
}

			