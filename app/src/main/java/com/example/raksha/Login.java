package com.example.raksha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    DatabaseReference reference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://raksha-sih-login-default-rtdb.firebaseio.com/");
    FirebaseDatabase userdb;
    Button btn;
    Button btn1;
    Button btn2;
    Button btn3;
    EditText ph_edtxt;
    EditText pswd_edtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn = findViewById(R.id.user_signup_button);
        btn1=findViewById(R.id.login_btn);
        btn2=findViewById(R.id.agency_signup_button);
        btn3=findViewById(R.id.forgotpswd_button);
        ph_edtxt=findViewById(R.id.ph_edtxt);
        pswd_edtxt=findViewById(R.id.Password_login);
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

                String phone=ph_edtxt.getText().toString();
                String pswd=pswd_edtxt.getText().toString();
                userdb = FirebaseDatabase.getInstance();
                DatabaseReference userRef = reference.child(phone);
                //reference = userdb.getReference("Users");
                //email and pswd stored for comparison
                if(phone.isEmpty() || pswd.isEmpty()){
                    Toast.makeText(Login.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                // Get the user's password from the database.
                                final String getPswd = snapshot.child("password").getValue(String.class);

                                // Check if the password is correct.
                                if (getPswd != null && getPswd.equals(pswd)) {
                                    Toast.makeText(Login.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                                    Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(i1);
                                } else {
                                    Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Login.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                                Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i1);
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
                Intent i2=new Intent(getApplicationContext(), MainActivity.class);
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