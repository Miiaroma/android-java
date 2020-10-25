package com.example.myapplication.ui.dashboard;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DashboardFragment extends Fragment implements LocationListener, View.OnClickListener, View.OnTouchListener {

    private DashboardViewModel dashboardViewModel;
    LocationManager locationManager;
    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION =111;
    private static final String TAG = "DashboardFragment";
    Location location;
    private TextView locationLatitude;
    private TextView locationLongitude;
    private TextView locationAddress;
    private Button buttonMap;
    String currentLocation = "";




    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
           @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationLatitude = root.findViewById(R.id.text_input_latitude);
        locationLongitude = root.findViewById(R.id.text_input_longitude);
        locationAddress = root.findViewById(R.id.text_input_address);
        buttonMap = root.findViewById(R.id.button_toMap);
        buttonMap.setOnClickListener(this);
        buttonMap.setOnTouchListener(this);
        startLocationUpdates();
        return root;

    }

    @SuppressLint("SetTextI18n")
    private void startLocationUpdates() {

        if (locationManager == null){
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        }

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            requestPermissions(
            /*new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
            MY_PERMISSIONS_REQUEST_FINE_LOCATION);*/
            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FINE_LOCATION);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if(location != null){
            locationLatitude.setText(Double.toString(location.getLatitude()));
            locationLongitude.setText(Double.toString(location.getLongitude()));
            locationAddress.setText(getAddress(location));
        }
    }


    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int [] grantResults) {
        Log.e(TAG, "permsRequestCode = " +permsRequestCode);
        switch (permsRequestCode){
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION:
                if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //showExplanation("Permission granted","Location", Manifest.permission.ACCESS_FINE_LOCATION, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
                }
                else {
                    //showExplanation("Permission needed", "Location", Manifest.permission.ACCESS_FINE_LOCATION, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
                }
                break;
        }
    }

    // Function to check and request permission
    public void checkPermission(String permission, int requestCode)
    {
        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(
                getContext(),
                permission)
                == PackageManager.PERMISSION_DENIED) {
            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FINE_LOCATION);
        }
        else {
            Toast
                    .makeText(getContext(),
                            "Permission already granted",
                            Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
            locationLatitude.setText(Double.toString(location.getLatitude()));
            locationLongitude.setText(Double.toString(location.getLongitude()));
            locationAddress.setText(getAddress(location));
        }
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
    private String getAddress(Location location) {
        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    1); // Here 1 represent maxResults

            Address address = addresses.get(0);
            currentLocation = address.getAddressLine(0); // getAddressLine returns a line of the address
            // numbered by the given index

        } catch (IOException e) {
            Log.e(TAG, "Error" + e);
        }
        Log.i(TAG, currentLocation);
        return currentLocation;
    }

    public void startActivateMap(){
        Uri webPage = Uri.parse("geo:"+locationLatitude+","+locationLongitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, webPage);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_toMap:
                Log.e("test", "Button has been clicked");
                startActivateMap();
                break;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getActionMasked();
        float oldTouchValue = event.getX();
        float currentX = event.getX();
        switch (action) {
            case (MotionEvent.ACTION_MOVE) :
                //tarkistetaan suunta
                if (oldTouchValue > currentX ) {
                    Log.d(TAG, "Swiped right"); }
                startActivateMap();
                break;
            default :
                break;
        }
        return true;
    }

}





