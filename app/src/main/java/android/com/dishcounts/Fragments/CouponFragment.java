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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.OrderBy;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class CouponFragment extends Fragment {
    private static final String TAG = "CouponFragment";

    private ArrayList<Coupon> couponArrayList;
    private SwipeRefreshLayout swipeRefreshLayout;
    CouponViewAdapter adapter;

    FirebaseFirestore db;
    CollectionReference couponCollection;

    TextView couponnum;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_coupon, container, false);
        couponnum = v.findViewById(R.id.textView2);
        couponArrayList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        couponCollection = db.collection("all_coupons");

        final RecyclerView couponList = v.findViewById(R.id.coupon_recycler_view);
        couponList.setHasFixedSize(true);
        couponList.setLayoutManager(new LinearLayoutManager(getContext()));


//        initCouponPercentage();

        final Query couponQuery = couponCollection
                .orderBy("valid_till", Query.Direction.DESCENDING);
        couponQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                        Map<String, Object> couponMap = documentSnapshot.getData();
                        Coupon coupon=new Coupon((String)couponMap.get("coupon_type"),(String)couponMap.get("coupon_code"),"",(String)couponMap.get("discount_upto"),(String)couponMap.get("platform"),(Date)couponMap.get("valid_till"),(String)couponMap.get("message"));
                        if(!couponMap.get("discount_percent").equals("NA"))
                        {
                            coupon.setDiscount_percent((String)couponMap.get("discount_percent"));
                        }
                        else if(!couponMap.get("cashback_percent").equals("NA"))
                        {
                            coupon.setDiscount_percent((String)couponMap.get("cashback_percent"));
                        }
                        else if(!couponMap.get("discount_cash").equals("NA"))
                        {
                            int value=Integer.parseInt((String)couponMap.get("discount_cash"));
                            if(value<=100)
                            coupon.setDiscount_percent((String)couponMap.get("discount_cash"));
                            else
                                coupon.setDiscount_percent("75");
                        }
                        else if(!couponMap.get("cashback_cash").equals("NA"))
                        {
                            int value=Integer.parseInt((String)couponMap.get("cashback_cash"));
                            if(value<=100)
                                coupon.setDiscount_percent((String)couponMap.get("cashback_cash"));
                            else
                                coupon.setDiscount_percent("75");
                        }
                        else
                        {
                            continue;
                        }

                        //Coupon coupon=new Coupon(couponMap.get("coupon_type"),);


                        //Coupon coupon = documentSnapshot.toObject(Coupon.class);
                        couponArrayList.add(coupon);
                        //Log.d(TAG, "PERCENT: "+coupon.getDiscountPercentage());

                    }
                    adapter = new CouponViewAdapter(couponArrayList, getContext());
                    couponList.setAdapter(adapter);
                }
                else {

                }
            }
        });
        if (couponArrayList.size()==1)
            couponnum.setText(couponArrayList.size()+" coupon");
        else
            couponnum.setText(couponArrayList.size()+" coupons");
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
