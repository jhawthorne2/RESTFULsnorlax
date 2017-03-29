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

		Scanner sc = new Scanner(System.in);
		//first num: IP address of the machine to which ur connecting
		//second num: port number of what ur communicating with
		//Socket sock = new Socket("143.88.64.31", 60000); //ssh server
		Socket sock = new Socket("127.0.0.1", 1342); //my machine
		Scanner sc1 = new Scanner(sock.getInputStream()); //to read from server
		PrintStream p = new PrintStream(sock.getOutputStream());
		String access = "false";
		while(access.equals("false")){
			System.out.println("Enter your username: ");
			String user = sc.nextLine();
			System.out.println("Enter your password: ");
			String pass = sc.nextLine();
			//send them to server for authentication
			p.println(user);
			p.println(pass);
			//receive if we were allowed access or not
			access = sc1.nextLine();
			if(access.equals("false")){
				System.out.println("Invalid user/pass combination. Please try again.");
			}
		}
		String URI = "";
		while(!URI.equals("exit")){
			//reached here, so access is true. prompt user for URI
			System.out.println("Enter the URI you wish to receive information on: ");
			URI = sc.nextLine();
			//send URI to server to retrieve info
			p.println(URI);
			//receive info from server on the URI
			String information = sc1.nextLine();
			System.out.println(information);
		}
		sc.close();
		sock.close();
		sc1.close();
		
	}

}
