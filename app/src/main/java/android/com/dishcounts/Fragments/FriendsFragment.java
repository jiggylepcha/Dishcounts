package android.com.dishcounts.Fragments;

import android.com.dishcounts.Activities.FindFriendActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.com.dishcounts.R;
import android.widget.Button;


public class FriendsFragment extends Fragment {
    Button find;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_friends, container, false);
        find = v.findViewById(R.id.button11);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFind();
            }
        });
        return v;
    }

    public void openFind(){
        Intent intent = new Intent(this.getContext(), FindFriendActivity.class);
        startActivity(intent);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


    }
}
