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
    String full_name, phone, password, repassword;
    FirebaseDatabase userdb;
    DatabaseReference reference;
    Button sbmt_btn,btn2;

    EditText Name_edtxt,Pswd_edtxt,RePswd_edtxt, Phone_edtxt;
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
        btn2=findViewById(R.id.go_back_login_btn);
        Name_edtxt=findViewById(R.id.name_edtxt);
        Phone_edtxt=findViewById(R.id.Phone_edtxt);
        Pswd_edtxt=findViewById(R.id.Password);
        RePswd_edtxt=findViewById(R.id.RePassword);
        chk1=findViewById(R.id.chkbox);
        sbmt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                full_name = Name_edtxt.getText().toString();
                phone = Phone_edtxt.getText().toString();
                password = Pswd_edtxt.getText().toString();
                repassword = RePswd_edtxt.getText().toString();
                if(full_name.equals("")||phone.equals("")||password.equals("")||repassword.equals("")){
                    Toast.makeText(Signup.this, "Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.equals(repassword)) {
                        User_Helper userHelper = new User_Helper(full_name,password,phone);
                        userdb = FirebaseDatabase.getInstance("https://raksha-52b01-default-rtdb.firebaseio.com");
                        reference = userdb.getReference("Users");
                        reference.child(phone).setValue(userHelper).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Name_edtxt.setText("");
                                Pswd_edtxt.setText("");
                                Phone_edtxt.setText("");
                                Toast.makeText(Signup.this, "User Registered Successfuly", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                                finish();

                            }
                        });
                    }
                    else {
                        Toast.makeText(Signup.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }

                }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });
    }}
