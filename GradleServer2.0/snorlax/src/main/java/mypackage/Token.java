package mypackage;

/**
 * Created by mikayla on 4/23/2017.
 */
public class Token {
    public Token(String tokenID, long expireTime, String username) {
        this.tokenID = tokenID;
        this.expireTime = expireTime;
        this.username = username;
    }

    private String tokenID; //generate random token length n

    public String getTokenID() {
        return tokenID;
    }

    public void setTokenID(String tokenID) {
        this.tokenID = tokenID;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private long expireTime;
    private String username;

    public Token(){

    }




}
