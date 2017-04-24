package mypackage;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

/**
 * Created by mikayla on 4/23/2017.
 * RESTful web service controller
 * Populates and returns an entity object. The object (once retrieve from db) will be written directly to the HTTP response as JSON.
 */


@RestController
public class RESTfulController {
    private static Entity e = null;
    private static boolean readyToProcessEntity;
    private static boolean checkedUser;
    private static String loginResult = "";
    private static boolean checkedToken;
    private static String currentUser = "";
    public static FileInputStream serviceAccount = null;

    public static void initialize(){
        try {
            serviceAccount = new FileInputStream("scavenger-6da2c-firebase-adminsdk-uhm2z-ac9e99e869.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                    .setDatabaseUrl("https://scavenger-6da2c.firebaseio.com/")
                    .build();
            FirebaseApp.initializeApp(options);
            // As an admin, the app has access to read and write all data, regardless of Security Rules
            System.out.println("Firebase connection initialized.");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    @RequestMapping("/entity")
    public Object getEntity(@RequestParam(value="tokenID", defaultValue = "") String tokenID, @RequestParam(value = "id", defaultValue = "uniqueID1") String uid) throws IOException, InterruptedException { //binds the value of the query string param id into the id param of entity method
        checkedToken = false;
        if(tokenIsValid(tokenID)) {
            readyToProcessEntity = false;
            e = new mypackage.Entity();
            getLocationInfo(uid);
            addLocationToUser(currentUser, e);
            System.out.println("value of readyToProcessEntity: " + readyToProcessEntity);
            //serviceAccount.close();
            readyToProcessEntity = false; //set it back to false for the next time someone needs to process it.
            checkedToken = false; //set it back to false for next person
            return e;
        }
        else{
            return "Token invalid. Please re-login to access the Scavenger system.";
        }
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value = "username", defaultValue = "") String username, @RequestParam(value = "password", defaultValue = "") String password) {
        //firebase lookup to make sure username/pass combo is correct
        checkedUser = false;
        String result = authenticate(username, password);
        checkedUser = false;
        return result;
    }
    @RequestMapping("/newUser")
    public User newUser(@RequestParam(value = "username", defaultValue = "") String username, @RequestParam(value = "password", defaultValue = "") String password){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        User u = new User(username, password);
        ref.child(username).setValue(u);
        return u;
    }

    // add a NEW entity to the database
    @RequestMapping(method= RequestMethod.POST, value="/entity")
    public Object addEntity(@RequestParam(value = "id") String uid, @RequestParam(value = "isA") String isA, @RequestParam(value = "roomNum") String roomNum, @RequestParam(value = "contains") String contains,
                          @RequestParam(value = "annotatedBy") String annotatedBy, @RequestParam(value = "illustratedBy") String illustratedBy) throws IOException {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("entities");
        Entity newE = new Entity(uid, roomNum, isA, contains, annotatedBy, illustratedBy);

        System.out.println("Adding new entity!");
        ref.child(uid).setValue(newE);

        return newE;
    }

    private static void getLocationInfo(String id) throws InterruptedException {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("entities");

        ref.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long count = dataSnapshot.getChildrenCount();
                System.out.println(dataSnapshot);
                mypackage.Entity entity2 = dataSnapshot.getValue(mypackage.Entity.class);

                e.setAnnotatedBy(entity2.getAnnotatedBy());
                e.setContains(entity2.getContains());
                e.setIllustratedBy(entity2.getIllustratedBy());
                e.setIsA(entity2.getIsA());
                e.setRoomNum(entity2.getRoomNum());
                System.out.println("processed entity");
                readyToProcessEntity = true;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Cancelled");
            }
        });

        while (!readyToProcessEntity) {
            System.out.print(""); //it just werks
        }

        //readyToProcessEntity now
        //formulate response to whoever requested the entity.
        System.out.println(e);

    }



    private static boolean tokenIsValid(String tokenID){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tokens");

        ref.child(tokenID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Token myT = dataSnapshot.getValue(Token.class);
                if(myT != null) {
                    System.out.println("mytoken time: " + myT.getExpireTime() + " Current time: " + (System.currentTimeMillis() / 1000L));
                    if (myT.getExpireTime() >= (System.currentTimeMillis() / 1000L)) {
                        System.out.println("Token not expired!");
                        //token is not expired, so let the request go through
                        currentUser = myT.getUsername();
                    }
                    else{
                        System.out.println("Token expired.");
                        currentUser = "Token invalid";
                    }
                }
                else{
                    System.out.println("Token not found!");
                    currentUser = "Token invalid";
                }
                checkedToken = true;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        while(!checkedToken){
            System.out.print("");
        }
        System.out.println("Checked token. " + currentUser);
        if(currentUser.equals("Token invalid")){
            return false;
        }
        else {
            return true;
        }
    }

    private static String authenticate(String username, String password){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        ref.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);
                if(user != null) {
                    if (user.getPassword().equals(password)) {
                        //correct password
                        loginResult = "Successful login.";
                    } else {
                        loginResult = "Invalid username/password combination.";
                    }
                }
                else{
                    loginResult = "Username does not exist. Please try again or create a new account.";
                }
                checkedUser = true;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        while(!checkedUser){
            System.out.print(""); //it just werks
        }
        System.out.println(loginResult);

        if(loginResult.equals("Successful login.")){
            //generate a token for them and store it in the db
            DatabaseReference tokenRef = FirebaseDatabase.getInstance().getReference("tokens");
            String randomTokenID = getSaltString() + username;
            long unixTime = System.currentTimeMillis() / 1000L;
            System.out.println("Unixtime: " + unixTime);
            unixTime += 86400; // that many seconds in a day. 24 hrs till token expires
            System.out.println("Newtime: " + unixTime);
            Token newT = new Token(randomTokenID, unixTime, username);
            System.out.println(newT);
            System.out.println("Adding new token!");
            tokenRef.child(randomTokenID).setValue(newT);
            return loginResult + " Token ID: " + randomTokenID;
        }
        //unsuccessful login, do not generate a token, just tell them it didn't work.
        else return loginResult;
    }

    /*generate random string for tokenID*/
    private static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    /*
    update their visited arraylist and change their currentlocation tothe current entity
     */
    private static void addLocationToUser(String currentUser, Entity e){
        checkedUser = false;
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        ref.child(currentUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                user.addEntity(e);
                user.setCurrentLocation(e);
                System.out.println(user);
                ref.child(currentUser).setValue(user); //update it
                checkedUser = true;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        while(!checkedUser){
            System.out.print(""); //it just werks
        }
        System.out.println("Updated user location info.");
        checkedUser = false;
    }
}
