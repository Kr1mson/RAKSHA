package com.example.raksha;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Add_Contact extends Fragment {
    Button btn;
    EditText name_edtxt,number_edtxt;
    String name,number;
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add__contact, container, false);
        name_edtxt=view.findViewById(R.id.contact_name);
        number_edtxt=view.findViewById(R.id.contact_number);
        btn = view.findViewById(R.id.contact_save); // Access the button here
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=name_edtxt.getText().toString();
                number=number_edtxt.getText().toString();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.flFragment, new SOS());
                fr.commit();
                Toast.makeText(getActivity(),"Saved Successfully",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


}