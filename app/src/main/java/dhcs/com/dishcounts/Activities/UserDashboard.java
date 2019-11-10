package dhcs.com.dishcounts.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import dhcs.com.dishcounts.Fragments.AddNewFragment;
import dhcs.com.dishcounts.Fragments.CouponFragment;
import dhcs.com.dishcounts.Fragments.FriendsFragment;
import dhcs.com.dishcounts.Fragments.ProfileFragment;
import dhcs.com.dishcounts.JavaClasses.Logs;

import android.os.Bundle;
import android.com.dishcounts.R;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

public class UserDashboard extends AppCompatActivity {

    private ActionBar toolbar;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        Bundle extras = getIntent().getExtras();
        Boolean showToast;

        if (extras != null){
            showToast =  extras.getBoolean("showToast");
            if (showToast){
                Toast.makeText(this, "Coupon Added Succesfully!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Post Added Succesfully!", Toast.LENGTH_SHORT).show();
            }
        }

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        toolbar = getSupportActionBar();
        toolbar.hide();
        toolbar.setTitle("Coupon");


        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CouponFragment()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.coupons:

                    String email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
                    Logs.recordLog(email,"Coupon Tab");

                    toolbar.setTitle("Coupon");
                    fragment = new CouponFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.add_new:
                    email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
                    Logs.recordLog(email,"Add New Tab");

                    toolbar.setTitle("Add New");
                    fragment = new AddNewFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.friends:
                    email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
                    Logs.recordLog(email,"Friends Tab");

                    toolbar.setTitle("Friends");
                    fragment = new FriendsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.profile:
                    email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
                    Logs.recordLog(email,"Profile Tab");

                    toolbar.setTitle("Profile");
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
