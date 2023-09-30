package com.example.raksha;

import static android.Manifest.permission.CALL_PHONE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Home extends Fragment {

    public Home(){}
    View view;
    TextView txt1,txt2;
    private FloatingActionButton btn2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);
        btn2=view.findViewById(R.id.sos_button);
        txt1=view.findViewById(R.id.hiddentxt1);
        txt2=view.findViewById(R.id.hiddentxt2);
        SharedPreferences sf =getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        String Name =sf.getString("Name", "");
        String Number =sf.getString("Number", "");
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+Number));
                if (ContextCompat.checkSelfPermission(getContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(i);
                    txt1.setVisibility(View.VISIBLE);
                    txt2.setVisibility(View.VISIBLE);

                } else {
                    requestPermissions(new String[]{CALL_PHONE}, 1);
                }

            }
        });
        return view;

    }
}