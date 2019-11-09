package android.com.dishcounts.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.com.dishcounts.R;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_profile, container, false);
        TextView user_email = v.findViewById(R.id.textView12);
        TextView user_name = v.findViewById(R.id.textView11);
        ImageView user_photo = v.findViewById(R.id.imageView7);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri profile_photo = user.getPhotoUrl();

        user_email.setText(email);
        user_name.setText(name);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


    }
}
