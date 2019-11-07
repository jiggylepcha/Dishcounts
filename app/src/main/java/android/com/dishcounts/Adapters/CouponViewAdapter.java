package android.com.dishcounts.Adapters;

import android.com.dishcounts.R;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CouponViewAdapter extends RecyclerView.Adapter<CouponViewAdapter.ViewHolder> {
    private static final String TAG = "CouponViewAdapter";
    ImageView couponImage;
    TextView discountPercentage, couponValidity, discountValue;
    LinearLayout couponLayout;

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
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            couponImage = itemView.findViewById(R.id.coupon_image);
            discountPercentage = itemView.findViewById(R.id.discount_percentage);
            discountValue = itemView.findViewById(R.id.coupon_value);
            couponValidity = itemView.findViewById(R.id.valid_value);
            couponLayout = itemView.findViewById(R.id.coupon_layout);
        }
    }
}
