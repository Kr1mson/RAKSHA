package com.example.raksha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Agency_Signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] types = { "Fire Department", "Emergency Medical Services ",
            "Search and Rescue", "Disaster Response Organization",
            "Law Enforcement", "Other" };
    Button btLocation;
    TextView tvLatitude, tvLongitude;
    FusedLocationProviderClient client;
    Button btn1;
    EditText hidden1;
    EditText Agency_edtxt;
    EditText Helpline_edtxt;
    EditText AdminKey_edtxt;
    EditText Pswd_edtxt;
    EditText RePswd_edtxt;
    String a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_signup);
        btn1=findViewById(R.id.agency_signup_button);
        Agency_edtxt=findViewById(R.id.agency_edtxt);
        Helpline_edtxt=findViewById(R.id.helpline_edtxt);
        AdminKey_edtxt=findViewById(R.id.Password_adminKey);
        Pswd_edtxt=findViewById(R.id.Password_agency_signup);
        RePswd_edtxt=findViewById(R.id.RePassword_agency_signup);

        Spinner spin = findViewById(R.id.typeSpinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter ad = new ArrayAdapter(this, R.layout.spinner, types);
        ad.setDropDownViewResource(R.layout.spinner);
        spin.setAdapter(ad);
        View view;
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Sign up button
                String Agency,Helpline,AdminKey,Password,Type;
                Agency=Agency_edtxt.getText().toString();
                Helpline=Helpline_edtxt.getText().toString();
                AdminKey=AdminKey_edtxt.getText().toString();
                Password=Pswd_edtxt.getText().toString();
                Type=a;
            }
        });
        // Assign variable
        btLocation = findViewById(R.id.bt_location);
        tvLatitude = findViewById(R.id.tv_latitude);
        tvLongitude =findViewById(R.id.tv_longitude);

        // Initialize location client
        client = LocationServices.getFusedLocationProviderClient(this);

        btLocation.setOnClickListener(
                new View.OnClickListener() {
                    @Override public void onClick(View view)
                    {
                        // check condition
                        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            // When permission is granted
                            // Call method
                            getCurrentLocation();
                        }
                        else {
                            // When permission is not granted
                            // Call method
                            requestPermissions(
                                    new String[] {
                                            android.Manifest.permission
                                                    .ACCESS_FINE_LOCATION,
                                            Manifest.permission
                                                    .ACCESS_COARSE_LOCATION },
                                    100);
                        }
                    }
                });

        // Return view


    }
    @Override
    public void onItemSelected(AdapterView arg0, View arg1, int position, long id)
    {
        a=types[position];
        //Selected type stored in a
        if(a!="Other"){}
        else{
            hidden1=(EditText)findViewById(R.id.agency_signup_hidden_edtxt);
            hidden1.setVisibility(View.VISIBLE);
        }

    }
    @Override
    public void onNothingSelected(AdapterView arg0)
    {
        // Auto-generated method stub
    }
    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);
        // Check condition
        if (requestCode == 100 && (grantResults.length > 0)
                && (grantResults[0] + grantResults[1]
                == PackageManager.PERMISSION_GRANTED)) {
            // When permission are granted
            // Call method
            getCurrentLocation();
        }
        else {
            // When permission are denied
            // Display toast
            Toast
                    .makeText(this,
                            "Permission denied",
                            Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation()
    {
        // Initialize Location manager
        LocationManager locationManager
                = (LocationManager)this
                .getSystemService(
                        Context.LOCATION_SERVICE);
        // Check condition
        if (locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER)) {
            // When location service is enabled
            // Get last location
            client.getLastLocation().addOnCompleteListener(
                    new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(
                                @NonNull Task<Location> task)
                        {

                            // Initialize location
                            Location location
                                    = task.getResult();
                            // Check condition
                            if (location != null) {
                                // When location result is not
                                // null set latitude
                                tvLatitude.setText(
                                        String.valueOf(
                                                location
                                                        .getLatitude()));
                                // set longitude
                                tvLongitude.setText(
                                        String.valueOf(
                                                location
                                                        .getLongitude()));
                            }
                            else {
                                // When location result is null
                                // initialize location request
                                LocationRequest locationRequest
                                        = new LocationRequest()
                                        .setPriority(
                                                LocationRequest
                                                        .PRIORITY_HIGH_ACCURACY)
                                        .setInterval(10000)
                                        .setFastestInterval(
                                                1000)
                                        .setNumUpdates(1);

                                // Initialize location call back
                                LocationCallback
                                        locationCallback
                                        = new LocationCallback() {
                                    @Override
                                    public void
                                    onLocationResult(
                                            LocationResult
                                                    locationResult)
                                    {
                                        // Initialize
                                        // location
                                        Location location1
                                                = locationResult
                                                .getLastLocation();
                                        // Set latitude
                                        tvLatitude.setText(
                                                String.valueOf(
                                                        location1
                                                                .getLatitude()));
                                        // Set longitude
                                        tvLongitude.setText(
                                                String.valueOf(
                                                        location1
                                                                .getLongitude()));
                                    }
                                };

                                // Request location updates
                                client.requestLocationUpdates(
                                        locationRequest,
                                        locationCallback,
                                        Looper.myLooper());
                            }
                        }
                    });
        }
        else {
            // When location service is not enabled
            // open location setting
            startActivity(
                    new Intent(
                            Settings
                                    .ACTION_LOCATION_SOURCE_SETTINGS)
                            .setFlags(
                                    Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

}