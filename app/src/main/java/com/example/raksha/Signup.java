package com.example.raksha;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.raksha.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class Signup extends AppCompatActivity {
    public ActivitySignupBinding binding;
    FirebaseAuth mAuth;
    Users users;
    String full_name, email, password, repassword;
    FirebaseDatabase userdb;
    DatabaseReference reference;
    Button sbmt_btn;

    EditText Name_edtxt,email_edtxt,Pswd_edtxt,RePswd_edtxt;
    CheckBox chk1;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i1 = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i1);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        super.onCreate(savedInstanceState);
        mAuth= FirebaseAuth.getInstance();
        users = new Users(this);

        setContentView(R.layout.activity_signup);
        sbmt_btn=findViewById(R.id.submit_btn);
        Name_edtxt=findViewById(R.id.name_edtxt);
        email_edtxt=findViewById(R.id.Email_edtxt);
        Pswd_edtxt=findViewById(R.id.Password);
        RePswd_edtxt=findViewById(R.id.RePassword);
        chk1=findViewById(R.id.chkbox);
        sbmt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                full_name = Name_edtxt.getText().toString();
                email = email_edtxt.getText().toString();
                password = Pswd_edtxt.getText().toString();
                repassword = RePswd_edtxt.getText().toString();
                if(full_name.equals("")||email.equals("")||password.equals("")||repassword.equals("")){
                    Toast.makeText(Signup.this, "Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    if( users.checkuser(email)){
                        Toast.makeText(Signup.this,"User already exists",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(password.equals(repassword)){
                        boolean registrationsuccess = users.addText(full_name,email,password);
                        if(registrationsuccess){
                            Toast.makeText(Signup.this,"User Registered Successfuly",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(Signup.this,"User Registered Successfully",Toast.LENGTH_SHORT).show();
                            Intent i2 = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i2);
                            finish();
                        }
                    }
                    else{
                        Toast.makeText(Signup.this,"Passwords do not match",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }}
