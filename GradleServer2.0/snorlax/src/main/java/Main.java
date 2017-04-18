/**
 * Created by malas_000 on 3/30/2017.
 */

import java.io.FileInputStream;
import com.google.firebase.auth.*;
import com.google.firebase.database.*;
import com.google.firebase.*;

public class Main {
    public static void main(String[] args) {
        try{

            FileInputStream serviceAccount = new FileInputStream("C:\\Users\\malas_000\\Documents\\snorlax/scavengerserver-firebase-adminsdk-qh4li-dbf0d16986.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                    .setDatabaseUrl("https://scavengerserver.firebaseio.com/")
                    .build();

            FirebaseApp defaultApp = FirebaseApp.initializeApp(options);

            System.out.println(defaultApp.getName());  // "[DEFAULT]"

            // Retrieve services by passing the defaultApp variable...
            FirebaseAuth defaultAuth = FirebaseAuth.getInstance(defaultApp);
            FirebaseDatabase defaultDatabase = FirebaseDatabase.getInstance(defaultApp);

            System.out.println(defaultAuth.toString());

//            FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
//                @Override
//                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                    FirebaseUser user = firebaseAuth.getCurrentUser();
//                    if (user != null) {
//                        // User is signed in
//                        Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
//                    } else {
//                        // User is signed out
//                        Log.d(TAG, "onAuthStateChanged:signed_out");
//                    }
//                    // ...
//                }
//            };

            //FirebaseUser user = defaultAuth.getCurrentUser();

            //createUserWithEmailAndPassword(email, password).catch(function(error) {
                // Handle Errors here.
                //var errorCode = error.code;
                //var errorMessage = error.message;
                // ...
            //});

            while(true) {

            }
        }
        catch(Exception e){

            System.out.println(e.toString());
        }

    }
}
