package mypackage;

import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

/*

 */

import com.google.firebase.database.IgnoreExtraProperties;

/**
 *  representing a mypackage.User stored in the Firebase Database.
 */

@IgnoreExtraProperties
public class User {

    private String username;
    private String password;
    private ArrayList<Entity> visited = new ArrayList<Entity>();
    private Entity currentLocation = null;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(mypackage.User.class)
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, ArrayList<Entity> visited, Entity currentLocation) {
        this.username = username;
        this.password = password;
        this.visited = visited;
        this.currentLocation = currentLocation;
    }


    public ArrayList<Entity> getVisited() {
        return visited;
    }

    public void addEntity(Entity e) {
        boolean contains = false;
        for(int i = 0; i < this.visited.size(); i++){
            if(visited.get(i).getRoomNum().equals(e.getRoomNum())){
                contains = true;
            }
        }
        if(!contains){
            this.visited.add(e);
        }
    }

    public Entity getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Entity currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

}