package dhcs.com.dishcounts.Adapters;

import dhcs.com.dishcounts.JavaClasses.Order;
import android.com.dishcounts.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderViewAdapter extends RecyclerView.Adapter<OrderViewAdapter.ViewHolder> {
    private static final String TAG = "OrderViewAdapter";
    private Context mContext;
    private ArrayList<Order> orderList;

    public OrderViewAdapter(ArrayList<Order> orderList, Context mContext){
        this.orderList = orderList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public OrderViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_orders, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "Message: " + orderList.get(position).getPost_message());
        holder.order_message.setText(orderList.get(position).getPost_message());
        holder.expiry_time.setText(orderList.get(position).getOrderDate());

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView order_message, expiry_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            order_message = itemView.findViewById(R.id.order_message);
            expiry_time = itemView.findViewById(R.id.order_time);
        }
    }
}
