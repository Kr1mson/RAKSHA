package com.example.raksha;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class Agency_Home extends Fragment {
    private List<MyItem> itemList;
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_agency__home,
                container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        itemList = new ArrayList<>();
        itemList.add(new MyItem("Name", "Mobile Number", "Type", "Address", "Other"));
        itemList.add(new MyItem("Name", "Mobile Number", "Type", "Address", "Other"));
        adapter = new RecycleViewAdapter(itemList);
        recyclerView.setAdapter(adapter);


        return view;
    }
    private List<String> generateDummyData() {
        List<String> dummyData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dummyData.add("Item " + (i + 1));
        }
        return dummyData;
    }
}