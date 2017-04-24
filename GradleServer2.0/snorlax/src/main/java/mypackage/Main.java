package mypackage; /**
 * Created by malas_000 on 3/30/2017.
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        RESTfulController.initialize();
        SpringApplication.run(Main.class, args); //launch the application
/*
        try{


            FileInputStream serviceAccount = new FileInputStream("scavenger-6da2c-firebase-adminsdk-uhm2z-ac9e99e869.json");
            // Initialize the app with admin privileges
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                    .setDatabaseUrl("https://scavenger-6da2c.firebaseio.com/")
                    .build();
            FirebaseApp.initializeApp(options);
            System.out.println("Here!");
            // As an admin, the app has access to read and write all data, regardless of Security Rules
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("entities");
            /*
            //code for adding entities - ref.setValue will replace everything in the database with that entities hashmap. ref.child("new id").setValue will add a new entity
            HashMap<String, mypackage.Entity> entities = new HashMap<String, mypackage.Entity>();
            entities.put("uniqueID1", new mypackage.Entity("435", "room", "berndok", "Room 435 in Building 4, office of Doc OK", "pic.jpg"));
            entities.put("uniqueID2", new mypackage.Entity("123", "room", "mikaylat", "Room 123 in Building 4, office of Mikayla T", "pic2.jpg"));

            ref.setValue(entities);

            ref.child("uniqueID3").setValue(new mypackage.Entity("321", "room", "tutors", "Room 321 in Building 4, math tutoring lab", "pic3.jpg"));
            */
/*
            int serverRunning = 1;
            while(serverRunning == 1){
                readyToProcessEntity = false;
                //receive http request
                //canAccessE = false;

                e = new mypackage.Entity();
                getLocationInfo("uniqueID1");
                System.out.println("value of readyToProcessEntity: " + readyToProcessEntity);


                serverRunning = 0;
                System.out.println("donezo");

            }
            System.out.println("Yep");

        }
        catch(Exception e){

            System.out.println(e.toString());
        }

    }

    private static void getLocationInfo(String id) throws InterruptedException {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("entities");

        ref.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long count = dataSnapshot.getChildrenCount();
                System.out.println(dataSnapshot);
                mypackage.Entity entity2 = dataSnapshot.getValue(mypackage.Entity.class);
                //System.out.print(entity2);

                e.setAnnotatedBy(entity2.getAnnotatedBy());
                e.setContains(entity2.getContains());
                e.setIllustratedBy(entity2.getIllustratedBy());
                e.setIsA(entity2.getIsA());
                e.setRoomNum(entity2.getRoomNum());
                System.out.println("Here1");
                readyToProcessEntity = true;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Cancelled");
            }
        });

        while(!readyToProcessEntity){
            System.out.print("");
        }
        System.out.println("Here2");
        //readyToProcessEntity now
        //formulate response to whoever requested the entity.
        System.out.println(e);

        //when done sending response over http:
        readyToProcessEntity = false; //set it back to false for the next time someone needs to process it.
*/
    }
}

