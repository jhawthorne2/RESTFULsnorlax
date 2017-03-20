import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("hello world");
		//use same port num as the one in the client
		//ServerSocket s1 = new ServerSocket(60000);
		ServerSocket s1 = new ServerSocket(1342);
		Socket ss = s1.accept(); //accept client's request
		//get data client is sending using scanner
		Scanner sc = new Scanner(ss.getInputStream()); 
		String id = sc.nextLine();
		
		//lookup id in database to get information on it
		String info = id + " Location information.";
		
		//pass result to client
		PrintStream p = new PrintStream(ss.getOutputStream());
		p.println(info); //pass info to client
	}

}
