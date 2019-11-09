package android.com.dishcounts.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.com.dishcounts.JavaClasses.Logs;
import android.com.dishcounts.R;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddManualCouponActivity extends AppCompatActivity {

    EditText couponType;
    EditText platform;
    EditText discount;
    EditText limit;
    EditText validity;
    EditText otherDetails;

    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_manual_coupon);
        getSupportActionBar().hide();

        couponType = findViewById(R.id.couponType);
        platform = findViewById(R.id.platform);
        discount = findViewById(R.id.discount);
        limit = findViewById(R.id.limit);
        validity = findViewById(R.id.validity);
        otherDetails = findViewById(R.id.otherDetails);


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                myCalendar.set(Calendar.HOUR_OF_DAY,24);
                myCalendar.set(Calendar.MINUTE,00);
                updateLabel();
            }

        };

        validity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddManualCouponActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        validity.setText(sdf.format(myCalendar.getTime()));
    }

    public void showErrorMessage(String text){
        Toast.makeText(this,text, Toast.LENGTH_LONG).show();

    }

    public void submitManualCoupon(View v){

        String email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
        Logs.recordLog(email,"Added Coupon Manually");

        String couponTypeStr = couponType.getText().toString();
        if (couponTypeStr.compareTo("") == 0){
            showErrorMessage("Enter Coupon Type (BOGO or STRAIGHT DISCOUNT)");
            return;
        }
        String platformStr = platform.getText().toString();
        if (platformStr.compareTo("") == 0){
            showErrorMessage("Enter Platform (Zomato, Swiggy, UberEats )");
            return;
        }
        String discountStr = discount.getText().toString();
        String limitStr = limit.getText().toString();

        String otherDetailsStr = otherDetails.getText().toString();

        Map<String, Object> coupon = new HashMap<>();
        coupon.put("coupon_type", couponTypeStr);
        coupon.put("platform", platformStr);
        coupon.put("discount_percent", discountStr);
        coupon.put("discount_upto", limitStr);
        coupon.put("other_details", otherDetailsStr);
        coupon.put("valid_till", myCalendar.getTime());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("/all_coupons")
                    .add(coupon)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("FIREBASECOUPON", "DocumentSnapshot added with ID: " + documentReference.getId());
                            Intent intent = new Intent(getBaseContext(), UserDashboard.class);
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
