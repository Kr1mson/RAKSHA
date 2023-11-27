package com.example.raksha;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class Alerts extends Fragment {

    String a;
    View view;
    Button submit;
    EditText agency_name;
    EditText mob;
    EditText type;
    EditText address;
    EditText other;
    DatabaseReference databaseReference;

    public Alerts(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_alerts, container, false);

        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance("https://raksha-52b01-default-rtdb.firebaseio.com").getReference("user_alerts");

        // Initialize UI components
        agency_name = view.findViewById(R.id.agency_nm_edtxt);
        mob = view.findViewById(R.id.numberedtxt);
        type = view.findViewById(R.id.type_edtxt);
        address = view.findViewById(R.id.address_edtxt);
        other = view.findViewById(R.id.other_edtxt);
        submit = view.findViewById(R.id.submitalert_btn);

        // Set onClickListener for the submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAlertToFirebase();
            }
        });

        return view;
    }

    private void saveAlertToFirebase() {
        // Get user input
        String agencyName = agency_name.getText().toString();
        String mobile = mob.getText().toString();
        String alertType = type.getText().toString();
        String alertAddress = address.getText().toString();
        String otherDetails = other.getText().toString();

        // Create an Alert object (you might want to create a class for this)
        Alert_Helper alert = new Alert_Helper(agencyName, mobile, alertType, alertAddress, otherDetails);

        // Save the alert to Firebase
        databaseReference = FirebaseDatabase.getInstance("https://raksha-52b01-default-rtdb.firebaseio.com").getReference("Alerts");
        databaseReference.child(mobile).setValue(alert).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                agency_name.setText("");
                mob.setText("");
                type.setText("");
                address.setText("");
                other.setText("");
            }
        });
    }
}