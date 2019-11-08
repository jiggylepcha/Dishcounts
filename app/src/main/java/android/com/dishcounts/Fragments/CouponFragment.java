package android.com.dishcounts.Fragments;

import android.com.dishcounts.Activities.CouponActivity;
import android.com.dishcounts.Adapters.CouponViewAdapter;
import android.com.dishcounts.JavaClasses.Coupon;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.com.dishcounts.R;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CouponFragment extends Fragment {
    private static final String TAG = "CouponFragment";

    private ArrayList<Coupon> couponArrayList;
    private SwipeRefreshLayout swipeRefreshLayout;
    CouponViewAdapter adapter;

    FirebaseFirestore db;
    CollectionReference couponCollection;

    Button coupon;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_coupon, container, false);

        couponArrayList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        couponCollection = db.collection("all_coupons");

        RecyclerView couponList = v.findViewById(R.id.coupon_recycler_view);
        couponList.setHasFixedSize(true);
        couponList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CouponViewAdapter(couponArrayList, getContext());
        couponList.setAdapter(adapter);

//        initCouponPercentage();

        final Query couponQuery = couponCollection
                .orderBy("valid_till");
        couponQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                        Coupon coupon = documentSnapshot.toObject(Coupon.class);
                        couponArrayList.add(coupon);
                    }
                }
                else {

                }
            }
        });

        Log.d(TAG, "coupon "+couponArrayList);
        return v;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


    }


    private void initCouponPercentage(){
        Log.d(TAG, "Preparing the recycler view");

        final Query couponQuery = couponCollection
                .orderBy("valid_till");
        couponQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                        Coupon coupon = documentSnapshot.toObject(Coupon.class);
                        couponArrayList.add(coupon);
                    }
                }
                else {

                }
            }
        });

        Log.d(TAG, "coupon "+couponArrayList);

    }
}
