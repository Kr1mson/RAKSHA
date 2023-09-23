package com.example.raksha;

import android.media.MediaPlayer;
import android.os.Bundle;
import java.io.*;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class SOS extends Fragment {
    View view;

    public SOS(){}
    private Button btn;
    private Button btn1;
    private FloatingActionButton btn2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_s_o_s, container, false);

        btn = view.findViewById(R.id.edit_button);
        btn1 = view.findViewById(R.id.edit_button1);
        btn2=view.findViewById(R.id.sos_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.flFragment, new Add_Contact());
                fr.commit();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.flFragment, new Add_Contact());
                fr.commit();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer ring = MediaPlayer.create(getActivity(), R.raw.ring);
                ring.start();
            }
        });

        return view;
    }

}