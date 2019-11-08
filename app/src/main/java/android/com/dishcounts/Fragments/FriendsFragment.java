package android.com.dishcounts.Fragments;

import android.com.dishcounts.Activities.FindFriendActivity;
import android.com.dishcounts.Activities.PostActivity;
import android.com.dishcounts.Adapters.OrderViewAdapter;
import android.com.dishcounts.JavaClasses.Order;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.com.dishcounts.R;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public class FriendsFragment extends Fragment {

    private ArrayList<Order> orderArrayList;
    Button find;
    FirebaseFirestore db;
    CollectionReference orderCollection;
    private SwipeRefreshLayout swipeRefreshLayout;
    OrderViewAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_friends, container, false);
        find = v.findViewById(R.id.button11);

        orderArrayList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        orderCollection = db.collection("all_posts");

        final RecyclerView orderRecyclerView = v.findViewById(R.id.order_recycler_view);
        orderRecyclerView.setHasFixedSize(true);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final Query orderQuery = orderCollection.orderBy("timeout", Query.Direction.DESCENDING);
        orderQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                        Map<String, Object> orderMap = documentSnapshot.getData();
                        Order order = new Order((String)orderMap.get("post_message"), (Date)orderMap.get("timeout"));
                        orderArrayList.add(order);
                    }
                }
                adapter = new OrderViewAdapter(orderArrayList, getContext());
                orderRecyclerView.setAdapter(adapter);
            }
        });


        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPost();
            }
        });
        return v;
    }


    public void openPost(){
        Intent intent = new Intent(this.getContext(), PostActivity.class);
        startActivity(intent);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
}
