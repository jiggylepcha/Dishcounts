package dhcs.com.dishcounts.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.DialogTitle;

import android.com.dishcounts.R;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CouponActivity extends AppCompatActivity {
    TextView code;
    Button copy;
    public static final String TAG = "COUPONACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        getSupportActionBar().hide();
        code = findViewById(R.id.textView16);
        copy = findViewById(R.id.copy);

        Bundle extras = getIntent().getExtras();
        TextView discountPercent = findViewById(R.id.textView21);
        discountPercent.setText(extras.getString("discount_percent") + "%");

        TextView discountValue = findViewById(R.id.textView22);
        discountValue.setText(extras.getString("discount_value"));

        TextView couponValidity = findViewById(R.id.textView24);
        couponValidity.setText(extras.getString("valid_till"));

        code.setText(extras.getString("coupon_code"));

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", code.getText());
                clipboard.setPrimaryClip(clip);
                Log.d(TAG, "Code copied to clipboard");
                Toast.makeText(getApplicationContext(), "Copied to clipboard!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
