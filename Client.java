import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		String id;
		Scanner sc = new Scanner(System.in);
		//first num: IP address of the machine to which ur connecting
		//second num: port number of what ur communicating with
		//Socket sock = new Socket("143.88.64.31", 60000);
		Socket sock = new Socket("127.0.0.1", 1342); //my machine
		Scanner sc1 = new Scanner(sock.getInputStream());
		System.out.println("Enter a location ID: ");
		id = sc.nextLine();
		PrintStream p = new PrintStream(sock.getOutputStream());
		p.println(id);
		String temp = sc1.nextLine();
		System.out.println(temp);
	}

}
