public class ReadInput {
private BufferedReader inputStream;
public ReadInput(BufferedReader inputStream) {
	this.inputStream=inputStream;
	}

public void run() {
	try {
		String input;
		while ((input=inputStream.readLine())!=null){
			System.out.println(input);
			}
		}
	}
}
		