package com.example.raksha;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView name,mobno,type,address,other;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.text1);
        mobno = itemView.findViewById(R.id.text2);
        type = itemView.findViewById(R.id.text3);
        address = itemView.findViewById(R.id.text4);
        other = itemView.findViewById(R.id.text5);
    }
}