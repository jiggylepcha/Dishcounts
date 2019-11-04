package android.com.dishcounts.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.com.dishcounts.Fragments.AddNewFragment;
import android.com.dishcounts.Fragments.CouponFragment;
import android.com.dishcounts.Fragments.FriendsFragment;
import android.com.dishcounts.Fragments.ProfileFragment;
import android.os.Bundle;
import android.com.dishcounts.R;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserDashboard extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        toolbar = getSupportActionBar();
        toolbar.setTitle("Coupon");
        loadFragment(new CouponFragment());
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.coupons:
                    toolbar.setTitle("Coupon");
                    fragment = new CouponFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.add_new:
                    toolbar.setTitle("Add New");
                    fragment = new AddNewFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.friends:
                    toolbar.setTitle("Friends");
                    fragment = new FriendsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.profile:
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
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
