package android.com.dishcounts.Fragments;

import android.com.dishcounts.Activities.CouponActivity;
import android.com.dishcounts.Adapters.CouponViewAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.com.dishcounts.R;
import android.widget.Button;

import java.util.ArrayList;

public class CouponFragment extends Fragment {
    private static final String TAG = "CouponFragment";

    private ArrayList<String> percentage = new ArrayList<>();
    private ArrayList<String> validity = new ArrayList<>();
    private ArrayList<String> value = new ArrayList<>();
    Button coupon;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_coupon, container, false);
        initCouponPercentage();
        initRecyclerView(v);

        return v;
    }

    private void initRecyclerView(View v) {
        RecyclerView couponList = v.findViewById(R.id.coupon_recycler_view);
        CouponViewAdapter adapter = new CouponViewAdapter(percentage, validity, value, getContext());
        couponList.setLayoutManager(new LinearLayoutManager(getContext()));
        couponList.setAdapter(adapter);
    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


    }

    private void initCouponPercentage(){
        Log.d(TAG, "Preparing the recycler view");
        percentage.add("100%");
        validity.add("12/9");

        percentage.add("20");
        validity.add("12/12");

        percentage.add("40");
        validity.add("4/8");

        percentage.add("35");
        validity.add("6/7");

        percentage.add("50");
        validity.add("9/11");

        percentage.add("10");
        validity.add("1/11");

    }
}
