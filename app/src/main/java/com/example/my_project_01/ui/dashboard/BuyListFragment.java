package com.example.my_project_01.ui.dashboard;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_project_01.MainActivity;
import com.example.my_project_01.R;
import com.example.my_project_01.databinding.FragmentBuylistBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuyListFragment extends Fragment {

    private FragmentBuylistBinding binding;
    private ImageView buy_change;
    private boolean temp = false;

    List<FetchData> fetchDataList;
    DatabaseReference databaseReference;
    MyBuyListAdapter myBuyListAdapter;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        Map<String, Object> user = new HashMap<>();
//        user.put("first", "Alan");
//        user.put("middle", "Mathison");
//        user.put("last", "Turing");
//        user.put("born", 1912);
//
//// Add a new document with a generated ID
//        db.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("aaaaaaa", "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("aaaaaaa", "Error adding document", e);
//                    }
//                });

        binding = FragmentBuylistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        buy_change = root.findViewById(R.id.buy_change);
        recyclerView=root.findViewById(R.id.recycleView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fetchDataList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("recyclerview");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds:snapshot.getChildren()){
                        FetchData fetchData = ds.getValue(FetchData.class);
                        fetchDataList.add(fetchData);
                        System.out.println("0000000");
                    }
                    myBuyListAdapter = new MyBuyListAdapter(fetchDataList);
                    recyclerView.setAdapter(myBuyListAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        buy_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (temp == false) {
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    buy_change.setImageResource(R.drawable.icon);
                    temp = true;
                } else if (temp == true) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    buy_change.setImageResource(R.drawable.github);
                    temp = false;
                }
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}