package com.example.raksha;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.os.Bundle;
import android.content.SharedPreferences;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
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
        Context context;
        view = inflater.inflate(R.layout.fragment_add__contact, container, false);
        name_edtxt=view.findViewById(R.id.contact_name);
        number_edtxt=view.findViewById(R.id.contact_number);
        btn = view.findViewById(R.id.contact_save); // Access the button here
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=name_edtxt.getText().toString();
                number=number_edtxt.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(number)) {
                    // if the input is empty we are displaying a toast message.
                    Toast.makeText(getContext(), "Please enter contact details", Toast.LENGTH_SHORT).show();
                } else {
                    // if the input is not empty we are calling a method to save
                    // data to shared prefs.
                    saveMessage(name,number);
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.flFragment, new SOS());
                    fr.commit();
                    Toast.makeText(getActivity(),"Saved Successfully",Toast.LENGTH_SHORT).show();
                }




            }
        });

        return view;
    }
    private void saveMessage(String name,String number) {
        SharedPreferences sf =getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sf.edit();
        editor.putString("Name",name);
        editor.putString("Number",number);
        editor.commit();
        // on below line we are displaying a toast message after adding data to shared prefs.
        Toast.makeText(getContext(), "Message saved to Shared Preferences", Toast.LENGTH_SHORT).show();
        // after that we are setting our edit text to empty
        name_edtxt.setText("");
        number_edtxt.setText("");
    }


}