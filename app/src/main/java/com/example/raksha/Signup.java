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
                if(full_name.isEmpty() || email.isEmpty() || password.isEmpty()){
                    Toast.makeText(Signup.this,"Please Fill All the Fields",Toast.LENGTH_SHORT).show();
                } else if (!password.equals(repassword)) {
                    Toast.makeText(Signup.this,"Passwords Do not match",Toast.LENGTH_SHORT).show();
                }
                else if(!full_name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Signup.this, "Account Created Successfully.",
                                                Toast.LENGTH_SHORT).show();
                                        Intent i1 = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(i1);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Signup.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }}
