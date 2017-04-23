import java.io.FileInputStream;

/*

 */

import com.google.firebase.database.IgnoreExtraProperties;

/**
 *  representing a User stored in the Firebase Database.
 */

@IgnoreExtraProperties
public class User {

    private String uid;
    private String password;

    public String getUid() {
        return uid;
    }

    public String getPassword() {
        return password;
    }

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String uid, String password) {
        this.uid = uid;
        this.password = password;
    }

}