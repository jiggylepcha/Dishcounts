package dhcs.com.dishcounts.JavaClasses;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Logs {

    public static void recordLog(String email,String message)
    {
        Map<String,Object> logData=new HashMap<>();
        logData.put("User Email",email);
        logData.put("Event",message);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("/logs")
                .add(logData);
    }
}
