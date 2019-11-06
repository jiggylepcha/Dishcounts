package android.com.dishcounts.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.com.dishcounts.R;
import android.os.Bundle;

public class AddManualCouponActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_manual_coupon);
        getSupportActionBar().hide();
    }
}
