package com.example.raksha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
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
    DatabaseReference reference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://raksha-sih-login-default-rtdb.firebaseio.com/");
    FirebaseDatabase userdb;
    FirebaseAuth mAuth ;
    Button btn;
    Button btn1;
    Button btn2;
    Button btn3;
    EditText email_edtxt;
    EditText pswd_edtxt;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        btn = findViewById(R.id.user_signup_button);
        btn1=findViewById(R.id.login_btn);
        btn2=findViewById(R.id.agency_signup_button);
        btn3=findViewById(R.id.forgotpswd_button);
        email_edtxt=findViewById(R.id.em_edtxt);
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
                String email = String.valueOf(email_edtxt);
                String password = String.valueOf(pswd_edtxt);
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                    Intent i1 = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(i1);
                                    finish();
                                    // Sign in success, update UI with the signed-in user's information
                                } else {
                                    Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                    Intent i1 = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(i1);
                                    finish();;
                                }
                            }
                        });
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