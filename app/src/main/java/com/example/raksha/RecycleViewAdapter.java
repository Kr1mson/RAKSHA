package com.example.raksha;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private List<MyItem> itemList;

    public RecycleViewAdapter(List<MyItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyItem currentItem = itemList.get(position);

        // Set the data to the views in the ViewHolder
        holder.textView1.setText(currentItem.getText1());
        holder.textView2.setText(currentItem.getText2());
        holder.textView3.setText(currentItem.getText3());
        holder.textView4.setText(currentItem.getText4());
        holder.textView5.setText(currentItem.getText5());

        // Set onClickListener for the button
        holder.button.setOnClickListener(v -> {
            // Handle button click
            // You can use position to get the clicked item position
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1, textView2, textView3, textView4, textView5;
        public Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.text1);
            textView2 = itemView.findViewById(R.id.text2);
            textView3 = itemView.findViewById(R.id.text3);
            textView4 = itemView.findViewById(R.id.text4);
            textView5 = itemView.findViewById(R.id.text5);
            button = itemView.findViewById(R.id.button);
        }
    }
}
