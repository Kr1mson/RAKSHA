package com.example.raksha;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import static android.Manifest.permission.CALL_PHONE;
import android.net.Uri;
import android.os.Bundle;
import java.io.*;
import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class SOS extends Fragment {
    View view;

    public SOS(){}
    private TextView name,number;
    private Button btn;
    private Button btn1;
    private FloatingActionButton btn2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_s_o_s, container, false);
        name=view.findViewById(R.id.contact_name);
        number=view.findViewById(R.id.contact_number);
        btn = view.findViewById(R.id.edit_button);
        btn1 = view.findViewById(R.id.delete_button);
        btn2=view.findViewById(R.id.sos_button);
        SharedPreferences sf =getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        String Name =sf.getString("Name", "");
        String Number =sf.getString("Number", "");
        if((Name!="")&&(Number!="")){
        name.setText(Name);
        number.setText(Number);}

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
                SharedPreferences sf =getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sf.edit();
                editor.clear();
                editor.commit();
                name.setText("No Contact Saved");
                number.setText("");


            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+Number));
                if (ContextCompat.checkSelfPermission(getContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(i);
                } else {
                    requestPermissions(new String[]{CALL_PHONE}, 1);
                }

            }
        });

        return view;
    }

}