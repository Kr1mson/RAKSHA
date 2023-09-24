package com.example.raksha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.raksha.databinding.ActivityAgencySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Agency_Signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] types = { "Fire Department", "Emergency Medical Services ",
            "Search and Rescue", "Disaster Response Organization",
            "Law Enforcement", "Other" };
    ActivityAgencySignupBinding binding;
    FirebaseDatabase agencydb;
    DatabaseReference ag_ref;
    AgenecyUsers agu;
    Button btn1;
    EditText hidden1;
    EditText Agency_edtxt;
    EditText Helpline_edtxt;
    EditText AdminKey_edtxt;
    EditText Pswd_edtxt;
    EditText RePswd_edtxt;
    EditText Type_edtxt;
    String a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAgencySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_agency_signup);
        btn1=findViewById(R.id.agency_signup_button);
        Agency_edtxt=findViewById(R.id.agency_edtxt);
        Helpline_edtxt=findViewById(R.id.helpline_edtxt);
        AdminKey_edtxt=findViewById(R.id.Password_adminKey);
        Pswd_edtxt=findViewById(R.id.Password_agency_signup);
        RePswd_edtxt=findViewById(R.id.RePassword_agency_signup);
        Type_edtxt = findViewById(R.id.agency_signup_hidden_edtxt);
        agu = new AgenecyUsers(this);



        Spinner spin = findViewById(R.id.typeSpinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter ad = new ArrayAdapter(this, R.layout.spinner, types);
        ad.setDropDownViewResource(R.layout.spinner);
        spin.setAdapter(ad);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Agency,Helpline,AdminKey,Password,repswd;
                Agency=Agency_edtxt.getText().toString();
                Helpline=Helpline_edtxt.getText().toString();
                AdminKey=AdminKey_edtxt.getText().toString();
                Password=Pswd_edtxt.getText().toString();
                repswd= RePswd_edtxt.getText().toString();



                if(Agency.equals("")||Helpline.equals("")||AdminKey.equals("")||Password.equals("")){
                    Toast.makeText(Agency_Signup.this, "Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    if( agu.checkuser(Helpline)){
                        Toast.makeText(Agency_Signup.this,"User already exists",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(Password.equals(repswd)){
                        boolean registrationsuccess = agu.addText(Agency, Helpline, AdminKey, Password);
                        if(registrationsuccess){
                            Toast.makeText(Agency_Signup.this,"User Registered Successfuly",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(Agency_Signup.this,"User Registration Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Agency_Signup.this,"Passwords do not match",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView arg0, View arg1, int position, long id)
    {
        a=types[position];
        //Selected type stored in a
        if(!Objects.equals(a, "Other")){}
        else{
            hidden1=(EditText)findViewById(R.id.agency_signup_hidden_edtxt);
            hidden1.setVisibility(View.VISIBLE);
        }

    }
    @Override
    public void onNothingSelected(AdapterView arg0)
    {
        // Auto-generated method stub
    }

}