package android.com.dishcounts.Fragments;
import android.com.dishcounts.Activities.AddManualCouponActivity;
import android.com.dishcounts.JavaClasses.MessageExtractor;
import android.com.dishcounts.JavaClasses.SMSObject;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.provider.Telephony;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.com.dishcounts.R;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddNewFragment extends Fragment {
    Button add;
    Button fetchInbox;


    TextView loadingText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_new, container, false);
        add = v.findViewById(R.id.button31);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdd();
            }
        });


        loadingText = v.findViewById(R.id.loadingText);


        fetchInbox = v.findViewById(R.id.button30);
        fetchInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMessagesFromInbox();
            }
        });
        return v;
    }

    public void openAdd(){
        Intent intent = new Intent(this.getContext(), AddManualCouponActivity.class);
        startActivity(intent);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


    }

    private List<Map<String, Object>> getSMS() {
        List<String> lstSms = new ArrayList<String>();
        ContentResolver cr = getActivity().getApplicationContext().getContentResolver();
        SMSObject sms_object;
        List<Map<String, Object>> list_messages = new ArrayList<Map<String, Object>>();

        Cursor c = cr.query(Telephony.Sms.Inbox.CONTENT_URI, // Official CONTENT_URI from docs
                new String[] { Telephony.Sms.Inbox.BODY }, // Select body text
                null,
                null,
                Telephony.Sms.Inbox.DEFAULT_SORT_ORDER); // Default sort order

        int totalSMS = c.getCount();

        if (c.moveToFirst()) {
            for (int i = 0; i < totalSMS; i++) {
                lstSms.add(c.getString(0));
                String text = "";
                try{
                    text = c.getString(c.getColumnIndexOrThrow("body"));
                }
                catch (Exception e){};
                if (text.compareTo("") != 0){
                    try {
                        Map<String, Object> couponData = MessageExtractor.couponExtractor(text);
                        if (couponData != null){
                            list_messages.add(couponData);
                        }
                    } catch (ParseException e) {

                    }

                }
                

//                list_messages.add(sms_object);
                c.moveToNext();
            }
        } else {
            throw new RuntimeException("You have no SMS in Inbox");
        }
        c.close();

        return list_messages;
    }


    public void getMessagesFromInbox(){

        loadingText.setText("Loading Messages...");

        List<Map<String, Object>> sms = getSMS();

        final String lastSMSText = (String) sms.get(sms.size() -1).get("message");

        for (final Map<String, Object> single_sms : sms){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("/all_coupons")
                    .add(single_sms)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("FIREBASECOUPON", "DocumentSnapshot added with ID: " + documentReference.getId());
                            String temp = (String) single_sms.get("message");
                            if (temp.compareTo(lastSMSText) == 0){
                                loadingText.setText("");
                            }
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
