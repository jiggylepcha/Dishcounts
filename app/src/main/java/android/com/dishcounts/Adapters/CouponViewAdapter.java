package android.com.dishcounts.Adapters;

import android.app.Activity;
import android.com.dishcounts.Activities.CouponActivity;
import android.com.dishcounts.JavaClasses.Coupon;
import android.com.dishcounts.R;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CouponViewAdapter extends RecyclerView.Adapter<CouponViewAdapter.ViewHolder> {
    private static final String TAG = "CouponViewAdapter";

    private ArrayList<Coupon> couponList;
    private Context mContext;

    public CouponViewAdapter(ArrayList<Coupon> coupons, Context mContext){
        this.couponList = coupons;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_coupons, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "Platform: " +couponList.get(position).getPlatform());
        Log.d(TAG, "Discount Percentage: " +couponList.get(position).getDiscount_percent());
        Log.d(TAG, "Discount Upto: " +couponList.get(position).getDiscountUpto());
        holder.discountPercentage.setText(couponList.get(position).getDiscount_percent()+"%");
        if(couponList.get(position).getDiscountUpto().equals("NA"))
            holder.discountValue.setText(couponList.get(position).getDiscountUpto());
        else
            holder.discountValue.setText("Rs."+couponList.get(position).getDiscountUpto());
        holder.couponValidity.setText(couponList.get(position).getDate());
        if (couponList.get(position).getPlatform().equals("ZOMATO")){
            holder.couponImage.setImageResource(R.drawable.zomato_logo);
        }
        else if (couponList.get(position).getPlatform().equals("SWIGGY")){
            holder.couponImage.setImageResource(R.drawable.swiggy_logo);
        }
        else{
            holder.couponImage.setImageResource(R.drawable.uber_eats_logo);
        }

        holder.couponLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent couponIntent = new Intent(mContext, CouponActivity.class);
                couponIntent.putExtra("discount_percent", couponList.get(position).getDiscount_percent());
                couponIntent.putExtra("platform", couponList.get(position).getPlatform());
                couponIntent.putExtra("valid_till", couponList.get(position).getDate());
                couponIntent.putExtra("coupon_code", couponList.get(position).getCouponCode());
                couponIntent.putExtra("discount_value", couponList.get(position).getDiscountUpto());
                couponIntent.putExtra("coupon_type", couponList.get(position).getCouponType());
                mContext.startActivity(couponIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        ImageView couponImage;
        TextView discountPercentage, couponValidity, discountValue;
        CardView couponLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            couponImage = itemView.findViewById(R.id.coupon_image);
            discountPercentage = itemView.findViewById(R.id.discount_percentage);
            discountValue = itemView.findViewById(R.id.coupon_value);
            couponValidity = itemView.findViewById(R.id.valid_value);
            couponLayout = itemView.findViewById(R.id.coupon_card);
        }
    }
}
