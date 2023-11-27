package com.example.raksha;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Settings extends Fragment {
    Button editProfile;
    TextView Address,Phone,Name;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings,
                container, false);


        editProfile=view.findViewById(R.id.editProfileButton);
        Address=view.findViewById(R.id.Address_txt_profile);
        Phone=view.findViewById(R.id.phno_txt_profile);
        Name=view.findViewById(R.id.name_profile);

        SharedPreferences preferences = getActivity().getSharedPreferences("user_credentials", MODE_PRIVATE);
        String username = preferences.getString("username", null);

        SharedPreferences preferences1 = getActivity().getSharedPreferences("user_profile", MODE_PRIVATE);
        String address = preferences1.getString("address", "");
        String phone = preferences1.getString("phno", "");

        Name.setText(username);
        if(address.isEmpty() || phone.isEmpty()){

        }
        if((address!="")&&(phone!="")){
            Address.setText(address);
            Phone.setText(phone);
        }else{
        }
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.flFragment, new Edit_profile());
                fr.commit();
            }
        });









        return view;
    }
}