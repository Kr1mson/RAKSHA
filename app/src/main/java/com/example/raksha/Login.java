package com.example.raksha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button btn;
    Button btn1;
    Button btn2;
    Button btn3;
    EditText email_edtxt;
    EditText pswd_edtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn = findViewById(R.id.user_signup_button);
        btn1=findViewById(R.id.login_btn);
        btn2=findViewById(R.id.agency_signup_button);
        btn3=findViewById(R.id.forgotpswd_button);
        email_edtxt=findViewById(R.id.email_edtxt);
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
                String email=email_edtxt.getText().toString();
                String pswd=pswd_edtxt.getText().toString();
                //email and pswd stored for comparison
                Intent i1=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i1);
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