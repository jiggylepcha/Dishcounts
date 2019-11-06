package android.com.dishcounts.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.com.dishcounts.R;
import android.os.Bundle;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        getSupportActionBar().hide();
    }
}
