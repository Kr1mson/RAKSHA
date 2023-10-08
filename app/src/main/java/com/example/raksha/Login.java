package com.example.raksha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    DatabaseReference reference;
    FirebaseDatabase userdb;
    FirebaseAuth mAuth ;
    Button btn;
    Users users;
    Button btn1;
    Button btn2;
    Button btn3;
    EditText phone_edtxt;
    EditText pswd_edtxt;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        btn = findViewById(R.id.user_signup_button);
        btn1=findViewById(R.id.login_btn);
        btn2=findViewById(R.id.agency_signup_button);
        btn3=findViewById(R.id.forgotpswd_button);
        phone_edtxt=findViewById(R.id.ph_edtxt);
        pswd_edtxt=findViewById(R.id.Password_login);
        users = new Users(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Signup button
                Intent i = new Intent(getApplicationContext(),Signup.class);
                startActivity(i);

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Login Button
                String phone = phone_edtxt.getText().toString();
                String password = pswd_edtxt.getText().toString();

                if(phone.isEmpty()||password.isEmpty()){
                    Toast.makeText(Login.this, "Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    reference = FirebaseDatabase.getInstance("https://raksha-52b01-default-rtdb.firebaseio.com").getReference("Users");
                    reference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(phone)){
                                final String getpassword = snapshot.child("phone").child("password").getValue(String.class);
                                if(getpassword.equals(password)){
                                    Toast.makeText(Login.this,"Logged in Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(Login.this,"Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(Login.this,"User Not Found Please Signup", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Agency signup Button
                Intent i2=new Intent(getApplicationContext(), Agency_Signup.class);
                startActivity(i2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Forgot pswd button
                Toast.makeText(Login.this, "Forgot pswd", Toast.LENGTH_SHORT).show();
            }
        });


    }
}