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
		Database db = new Database();
		System.out.println("Waiting for clients");
		//use same port num as the one in the client
		//ServerSocket s1 = new ServerSocket(60000);
		ServerSocket s1 = new ServerSocket(1342);
		Socket ss = s1.accept(); //accept client's request
		//get data client is sending using scanner
		Scanner sc = new Scanner(ss.getInputStream());
		PrintStream p = new PrintStream(ss.getOutputStream());
		
		String access = "false";
		while(access.equals("false")){			
			String user = sc.nextLine();
			String pass = sc.nextLine();
			
			access = db.authenticate(user, pass);
			//send info on access back to client "true" "false"
			p.println(access);
		}
		String URI = "";
		while(!URI.equals("exit")){
			//wait for URI from client
			URI = sc.nextLine();
			
			
			//lookup id in database to get information on it
			String info = db.getLocationInfo(URI);
			
			//pass result to client
			p.println(info); //pass info to client
		}
		s1.close();
		sc.close();
		
	}
	
	

}
