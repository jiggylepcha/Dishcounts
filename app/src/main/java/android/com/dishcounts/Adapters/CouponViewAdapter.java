package android.com.dishcounts.Adapters;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "calling onBindViewHolder" );
        holder.discountPercentage.setText(couponList.get(position).getDiscountPercentage());
        holder.discountValue.setText(couponList.get(position).getDiscountUpto());
        holder.couponLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
