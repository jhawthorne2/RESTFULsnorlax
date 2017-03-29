import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;


public class Database {	
	Hashtable<String, String> users; //username, pass
	Hashtable<String, String> locations; //ID, filename
	ArrayList<String> admins;
	
	public Database(){
		users = new Hashtable<String, String>();
		locations = new Hashtable<String, String>();
		admins = new ArrayList<String>();
		try {
			readFiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void readFiles() throws IOException{
		FileReader frdrLoc = new FileReader("locations.txt");
		BufferedReader brdrLoc = new BufferedReader(frdrLoc);
		String location;
		while((location = brdrLoc.readLine()) != null) {
	        String[] text = location.split(" ");
	        //text[0] is ID, text[1] is filename to look in
	        //open text[1] to get its info
	        locations.put(text[0], text[1]);
	    } 		
		brdrLoc.close();
		
		FileReader frdrUsers = new FileReader("users.txt");
		BufferedReader brdrUsers = new BufferedReader(frdrUsers);
		String user;
		while((user = brdrUsers.readLine()) != null){
			String[] text = user.split(" ");
			//text[0] is username, text[1] is pass, text[2] is a boolean indicating admin status
			this.users.put(text[0], text[1]);
			if(text[2].equals("true")){ //if they are admin, add username to admin list
				this.admins.add(text[0]);
			}
		}
		brdrUsers.close();
	}
	
	public String authenticate(String username, String pass){
		if(this.users.containsKey(username)){
			if(this.users.get(username).equals(pass)){
				return "true";
			}
		}
		return "false";
	}
	
	public boolean isAdmin(String username){
		if(this.admins.contains(username)){
			return true;
		}
		return false;
	}
	
	public String getLocationInfo(String URI) throws IOException{
		//open the file specified in the locations hashtable for that URI
		if(this.locations.containsKey(URI)){
			FileReader frdr = new FileReader(this.locations.get(URI));
			BufferedReader brdr = new BufferedReader(frdr);

			String wholeFile = "";
			String location;
			while((location = brdr.readLine()) != null) {
		        wholeFile = wholeFile + location + " ";
		    } 		
			brdr.close();
			return wholeFile;
		}
		
		return "Invalid URI";
	}
}
