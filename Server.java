
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
public static void main(String[] args) {
	int portNumber=3541;
	try (
		ServerSocket serversocket = new ServerSocket(portNumber);
		Socket clientSocket = serversocket.accept();
		DataOutputStream outStream = new DataOutputStream(clientSocket.getOutputStream());
		BufferedRreader inStream = new BufferedReader(
			new InputStreamReader(clientSocket.getInputStream()));) {
				System.out.println("Anda terhubung dengan klien" + clientSocket.getRemoteSocketAddress());
			String inputKata;
			while ((inputKata = inStream.readKata())!=null) {
				System.out.println("Klien : " +inputKata);
				outStream.writeBytes("Pesan Diterima" + "\n");
			}
			clientSocket.close();
		} catch (IOException e) {
			System.out.println("Terjadi kesalahan ketika mencoba terhubung ke port " +portNumber+ );
			System.out.println(e.getMessage());
		}
}
	