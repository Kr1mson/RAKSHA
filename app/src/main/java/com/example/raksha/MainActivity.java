package com.example.raksha;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.Intent;

import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener{
    Button btn;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.map_button);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.Home);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(i);
            }
        });

    }
    Map firstFragment = new Map();
    SOS secondFragment = new SOS();
    Settings thirdFragment = new Settings();

    Home fourthFragment=new Home();

    Alerts fifthFragment=new Alerts();
    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item)
    {

        switch (item.getItemId()) {
            case R.id.Settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, thirdFragment).commit();
                return true;

            case R.id.maps:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, firstFragment).commit();
                return true;

            case R.id.SOS:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, secondFragment).commit();
                return true;

            case R.id.Home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fourthFragment).commit();
                return true;

            case R.id.Alerts:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fifthFragment).commit();
                return true;
        }
        return false;
    }
}
