package com.example.my_project_01.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.my_project_01.R;
import com.example.my_project_01.ui.notifications.BuyCart;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BuyList_inStyle extends AppCompatActivity {
    List<FetchData> fetchDataList;
    DatabaseReference databaseReference;
    ImageView buy_in_image;
    TextView buy_in_til, buy_in_det, buy_in_pri, buy_in_cart, buy_in_buy;
int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buylist_in_style);
        findId();
        position = getIntent().getIntExtra("position",-1);
        Log.e("0000000",""+position);

        buy_in_cart.setOnClickListener(l1);
        fetchDataList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("recyclerview");
        Log.e("0000001",""+databaseReference);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    FetchData fetchData = ds.getValue(FetchData.class);
                    fetchDataList.add(fetchData);
                }
                buy_in_til.setText(fetchDataList.get(position).getTitle());
                buy_in_det.setText(fetchDataList.get(position).getDet2());
                buy_in_pri.setText(fetchDataList.get(position).getPrice());
                Glide.with(buy_in_image.getContext()).load(fetchDataList.get(position).getImage()).into(buy_in_image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void findId() {
        buy_in_image=findViewById(R.id.buy_in_image);
        buy_in_til=findViewById(R.id.buy_in_til);
        buy_in_det=findViewById(R.id.buy_in_det);
        buy_in_cart=findViewById(R.id.buy_in_cart);
        buy_in_buy=findViewById(R.id.buy_in_buy);
        buy_in_pri=findViewById(R.id.buy_in_pri);
    }
    View.OnClickListener l1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(BuyList_inStyle.this, BuyCart.class);
            startActivity(intent);
        }
    };

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 什麼都不用寫
        } else {
            // 什麼都不用寫
        }
    }
}