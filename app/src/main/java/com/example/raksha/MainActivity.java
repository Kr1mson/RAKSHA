package com.example.raksha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener{
    Button btn;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.Home);
        Toolbar toolbar = findViewById(R.id.topToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");



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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle menu item clicks here
        switch (item.getItemId()) {
            case R.id.logout:
                Toast.makeText(this, "Logged Out Successfuly", Toast.LENGTH_SHORT).show();
                SharedPreferences preferences = getSharedPreferences("user_credentials", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("username");
                editor.remove("pswd");
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
                return true;
            // Add more cases for other menu items
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
