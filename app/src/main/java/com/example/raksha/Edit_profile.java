package com.example.raksha;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Edit_profile extends Fragment {
    Button save;
    EditText address;
    EditText phone;
    TextView name;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile,
                container, false);
        save=view.findViewById(R.id.profile_save);
        address=view.findViewById(R.id.address_edt_profile);
        phone=view.findViewById(R.id.phno_edt_profile);
        name=view.findViewById(R.id.name_profile);

        SharedPreferences preferences = getActivity().getSharedPreferences("user_credentials", MODE_PRIVATE);
        String username = preferences.getString("username", null);

        name.setText(username);

        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(address.getText().length() == 0 | phone.getText().length()==0){
                    Toast.makeText(getContext(), "Please fill all Details", Toast.LENGTH_SHORT).show();
                }
                else{
                    SharedPreferences preferences1 = requireActivity().getSharedPreferences("user_profile", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences1.edit();
                    editor.putString("address",address.getText().toString() );
                    editor.putString("phno",phone.getText().toString() );
                    editor.apply();
                    Toast.makeText(getContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.flFragment, new Settings());
                    fr.commit();
                }
            }
        });
















        return view;
    }
}