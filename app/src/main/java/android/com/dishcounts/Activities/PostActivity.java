package android.com.dishcounts.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.com.dishcounts.R;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PostActivity extends AppCompatActivity {

    EditText postMessage;
    EditText timeout;
    Button submitPostButton;

    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        getSupportActionBar().hide();

        postMessage = findViewById(R.id.postMessage);
        timeout = findViewById(R.id.timeout);
        submitPostButton = findViewById(R.id.submitPostButton);

        submitPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitPost();
            }
        });


        final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                myCalendar.set(Calendar.HOUR_OF_DAY,i);
                myCalendar.set(Calendar.MINUTE,i1);
                updateLabel();
            }
        };

        timeout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new TimePickerDialog(PostActivity.this, time, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), false).show();
            }
        });
    }


    private void updateLabel() {
        String myFormat = "hh:mm"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        timeout.setText(sdf.format(myCalendar.getTime()));
    }

    public void showErrorMessage(String text){
        Toast.makeText(this,text, Toast.LENGTH_LONG).show();

    }

    public void submitPost(){
        String postMessageStr = postMessage.getText().toString();
        if (postMessageStr.compareTo("") == 0){
            showErrorMessage("Enter Message");
        }

        Map<String, Object> post = new HashMap();
        post.put("post_message", postMessageStr);
        post.put("timeout", myCalendar.getTime());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("/all_posts")
                .add(post)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("SMDFIREBASE", "DocumentSnapshot added with ID: " + documentReference.getId());
                        Intent intent = new Intent(getBaseContext(), FindFriendActivity.class);
                        intent.putExtra("showToast", true);
                        startActivity(intent);
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