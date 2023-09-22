package com.example.raksha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.raksha.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    public ActivitySignupBinding binding;
    String full_name, email, password;
    FirebaseDatabase userdb;
    DatabaseReference reference;
    Button sbmt_btn;

    EditText Name_edtxt,Email_edtxt,Pswd_edtxt,RePswd_edtxt;
    CheckBox chk1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sbmt_btn=findViewById(R.id.submit_btn);
        Name_edtxt=findViewById(R.id.name_edtxt);
        Email_edtxt=findViewById(R.id.email_edtxt);
        Pswd_edtxt=findViewById(R.id.Password);
        RePswd_edtxt=findViewById(R.id.RePassword);
        chk1=findViewById(R.id.chkbox);
        sbmt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                full_name = Name_edtxt.getText().toString();
                email = Email_edtxt.getText().toString();
                password = Pswd_edtxt.getText().toString();
                if(!full_name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                    Users users = new Users(full_name, email, password);
                    userdb = FirebaseDatabase.getInstance();
                    reference = userdb.getReference("Users");
                    reference.child(full_name).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Name_edtxt.setText("");
                            Email_edtxt.setText("");
                            Pswd_edtxt.setText("");
                            Toast.makeText(Signup.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}