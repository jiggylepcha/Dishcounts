package android.com.dishcounts.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.com.dishcounts.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FindFriendActivity extends AppCompatActivity {
    Button post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);
        getSupportActionBar().hide();
        post = findViewById(R.id.button24);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPost();
            }
        });
    }
    public void openPost(){
        Intent intent = new Intent(this, PostActivity.class);
        startActivity(intent);
    }
}
