package com.example.raksha;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Agency_Home extends Fragment {
    private List<MyItem> itemList;
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agency__home, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        databaseReference = FirebaseDatabase.getInstance("https://raksha-52b01-default-rtdb.firebaseio.com").getReference("Alerts");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        itemList = new ArrayList<>();
        adapter = new RecycleViewAdapter(itemList);
        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Alert_Helper alert = postSnapshot.getValue(Alert_Helper.class);
                    if (alert != null) {
                        String agency_name = alert.getAgencyName();
                        String mobile = "Phone Number:- "+alert.getMobile();
                        String type = "Type:- "+alert.getAlertType();
                        String address= "Address:- "+alert.getAlertAddress();
                        String other = "Others:- "+alert.getOtherDetails();
                        MyItem myItem = new MyItem(agency_name, mobile, type, address, other);
                        itemList.add(myItem);
                    }
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });

        return view;
    }
}