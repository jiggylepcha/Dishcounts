package android.com.dishcounts.JavaClasses;

import android.content.Context;

import com.google.firebase.firestore.DocumentReference;


import java.util.ArrayList;
import java.util.List;

public class MakeCoupon {
    private static MakeCoupon makeCoupon;
    private List<Coupon> mCoupon = new ArrayList<>();
    private DocumentReference docRef;

    private MakeCoupon(final Context context){

    }

}
