package com.example.raksha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.raksha.databinding.ActivitySignupBinding;

public class Signup extends AppCompatActivity {
    Button btn;
    EditText Name_edtxt,Email_edtxt,Pswd_edtxt,RePswd_edtxt;
    CheckBox chk1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btn=findViewById(R.id.submit_btn);
        Name_edtxt=findViewById(R.id.name_edtxt);
        Email_edtxt=findViewById(R.id.email_edtxt);
        Pswd_edtxt=findViewById(R.id.Password);
        RePswd_edtxt=findViewById(R.id.RePassword);
        chk1=findViewById(R.id.chkbox);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });

    }
}