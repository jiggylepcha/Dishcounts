package android.com.dishcounts.Adapters;

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

    private ArrayList<String> percentage = new ArrayList<>();
    private ArrayList<String> date = new ArrayList<>();
    private ArrayList<String> discountValue = new ArrayList<>();
    private Context mContext;

    public CouponViewAdapter(ArrayList<String> percentage, ArrayList<String> date, ArrayList<String> discountValue, Context mContext){
        this.percentage = percentage;
        this.date = date;
        this.discountValue = discountValue;
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
        Log.d(TAG, "calling onBindViewHolder");
        holder.discountPercentage.setText(percentage.get(position));
        holder.couponValidity.setText(date.get(position));
        holder.couponLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return percentage.size();
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
