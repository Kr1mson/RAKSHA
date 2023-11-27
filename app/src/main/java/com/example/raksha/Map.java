package com.example.raksha;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.ArrayList;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.fragment.app.Fragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Map extends Fragment {

    // Initialize variables
    Button btLocation;
    Button btlocation2;

    TextView Agency_name, Agency_type, Agency_helpline, Agency_lat, Agency_long;
    FusedLocationProviderClient client;
    GoogleMap googleMap;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Initialize view
        View view = inflater.inflate(R.layout.fragment_map,
                container, false);


        SupportMapFragment supportMapFragment=(SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        btLocation = view.findViewById(R.id.bt_location);
        btlocation2 = view.findViewById(R.id.bt_location2);
        Agency_name=view.findViewById(R.id.agency_name);
        Agency_helpline=view.findViewById(R.id.agency_helpline);
        Agency_lat=view.findViewById(R.id.agency_lat);
        Agency_long=view.findViewById(R.id.agency_long);



        // Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // When map is loaded
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                btlocation2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Firebase code to retrieve the data
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://raksha-52b01-default-rtdb.firebaseio.com").getReference("Agency_Details");

                        if (googleMap != null) {
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                        Agency_UserHelper agency = postSnapshot.getValue(Agency_UserHelper.class);
                                        if (agency != null) {
                                            String latitudeString = agency.getLatitude();
                                            String longitudeString = agency.getLongitude();
                                            try {
                                                double latitude = Double.parseDouble(latitudeString);
                                                double longitude = Double.parseDouble(longitudeString);
                                                LatLng latLng = new LatLng(latitude, longitude);
                                                googleMap.addMarker(new MarkerOptions().position(latLng).title(agency.getAg_name()));
                                            } catch (NumberFormatException e) {
                                                // Handle the exception appropriately
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    // Handle error
                                }
                            });
                        }
                    }
                });

                googleMap.setMyLocationEnabled(true); // Enable the My Location layer
                googleMap.getUiSettings().setMyLocationButtonEnabled(true); // Enable the My Location button

                // Handle the case where the user's location is unavailable
                client.getLastLocation().addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            Location location = task.getResult();
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();

                            // Create a LatLng object for the user's location
                            LatLng userLocation = new LatLng(latitude, longitude);

                            // Add a marker for the user's location
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(userLocation);
                            markerOptions.title("Your Location");
                            googleMap.addMarker(markerOptions);

                            // Move the camera to the user's location
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15)); // You can adjust the zoom level as needed
                        } else {
                            // Handle the case where the user's location is unavailable
                            Toast.makeText(getActivity(), "Unable to get your location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                // Set a click listener for the map if you want to add markers on click (you can keep your existing code for this)
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        // Your existing code for adding markers on map click
                    }
                });
            }
        });



        // Assign variable


        // Initialize location client
        client = LocationServices
                .getFusedLocationProviderClient(
                        getActivity());


        btLocation.setOnClickListener(
                new View.OnClickListener() {
                    @Override public void onClick(View view)
                    {
                        // check condition
                        if (ContextCompat.checkSelfPermission(
                                getActivity(),
                                Manifest.permission
                                        .ACCESS_FINE_LOCATION)
                                == PackageManager
                                .PERMISSION_GRANTED
                                && ContextCompat.checkSelfPermission(
                                getActivity(),
                                Manifest.permission
                                        .ACCESS_COARSE_LOCATION)
                                == PackageManager
                                .PERMISSION_GRANTED) {
                            // When permission is granted
                            // Call method
                            getCurrentLocation();
                        }
                        else {
                            // When permission is not granted
                            // Call method
                            requestPermissions(
                                    new String[] {
                                            Manifest.permission
                                                    .ACCESS_FINE_LOCATION,
                                            Manifest.permission
                                                    .ACCESS_COARSE_LOCATION },
                                    100);
                        }
                    }
                });
        searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle search query text change
                // You may want to implement live search suggestions here
                return true;
            }
        });

        // Return view
        return view;
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
                    .makeText(getActivity(),
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
                = (LocationManager)getActivity()
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
    private void performSearch(String query) {
        if (!query.isEmpty()) {
            // Perform a Firebase query to find the agency by name
            DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://raksha-52b01-default-rtdb.firebaseio.com").getReference("Agency_Details");
            databaseReference.orderByChild("ag_name").equalTo(query).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Check if the query result is not empty
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Agency_UserHelper agency = snapshot.getValue(Agency_UserHelper.class);
                            if (agency != null) {
                                // Display agency details in TextViews
                                displayAgencyDetails(agency);
                            }
                        }
                    } else {
                        // Agency not found
                        Toast.makeText(requireContext(), "Agency not found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle error
                }
            });
        } else {
            // Empty query, do something or show a message
            Toast.makeText(requireContext(), "Please enter an agency name", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayAgencyDetails(Agency_UserHelper agency) {
        // Display agency details in TextViews
        Agency_name.setText("Agency Name: " + agency.getAg_name());
        Agency_helpline.setText("Helpline: " + agency.getH_no());
        Agency_lat.setText("Latitude: " + agency.getLatitude());
        Agency_long.setText("Longitude: " + agency.getLongitude());
    }
}
