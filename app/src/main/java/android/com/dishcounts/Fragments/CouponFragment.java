package android.com.dishcounts.Fragments;

import android.com.dishcounts.Activities.CouponActivity;
import android.com.dishcounts.Adapters.CouponViewAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.com.dishcounts.R;
import android.widget.Button;

public class CouponFragment extends Fragment {
    Button coupon;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_coupon, container, false);
        RecyclerView couponList = v.findViewById(R.id.coupon_recycler_view);
        CouponViewAdapter adapter = new CouponViewAdapter();
        couponList.setAdapter(adapter);
        coupon = v.findViewById(R.id.coupon_layout);
        coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCoupon();
            }
        });
        return v;
    }

    public void openCoupon(){
        Intent intent = new Intent(this.getContext(), CouponActivity.class);
        startActivity(intent);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


    }
}
