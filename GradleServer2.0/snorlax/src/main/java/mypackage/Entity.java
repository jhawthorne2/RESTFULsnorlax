package mypackage;

public class Entity {


    private String roomNum;
    private String isA;
    private String contains;
    private String annotatedBy;
    private String illustratedBy;

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public String uid;

    public String getRoomNum() {
        return roomNum;
    }

    public String getIsA() {
        return isA;
    }

    public String getContains() {
        return contains;
    }

    public String getAnnotatedBy() {
        return annotatedBy;
    }

    public String getIllustratedBy() {
        return illustratedBy;
    }


    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public void setIsA(String isA) {
        this.isA = isA;
    }

    public void setContains(String contains) {
        this.contains = contains;
    }

    public void setAnnotatedBy(String annotatedBy) {
        this.annotatedBy = annotatedBy;
    }

    public void setIllustratedBy(String illustratedBy) {
        this.illustratedBy = illustratedBy;
    }

    public Entity() {
        // Default constructor required for calls to DataSnapshot.getValue(mypackage.Entity.class)
    }

    public Entity(String uid, String roomNum, String isA, String contains, String annotatedBy, String illustratedBy) {
        this.uid = uid;
        this.roomNum = roomNum;
        this.isA = isA;
        this.contains = contains;
        this.annotatedBy = annotatedBy;
        this.illustratedBy = illustratedBy;

    }

    public Entity(String uid){
        this.uid = uid;
    }

    public String toString(){
        return "mypackage.Entity {roomNum='" + roomNum + "', isA='" + isA + "', contains='" + contains + "', annotatedBy='" + annotatedBy +"', illustratedBy='"+illustratedBy
                + "'}\n";
    }
}