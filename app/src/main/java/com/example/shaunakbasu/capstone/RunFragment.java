package com.example.shaunakbasu.capstone;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by shaunak basu on 19-11-2016.
 */
public class RunFragment extends Fragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,
        LocationListener{

    //,ResultCallback<LocationSettingsResult>

    GoogleMap googleMap;
    boolean mapReady=false;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        rootView=inflater.inflate(R.layout.fragment_map,container,false);


        return rootView;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState){

        super.onActivityCreated(savedInstanceState);
        googleApiClient=new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        MapFragment mapFragment=(MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    public void onMapReady(GoogleMap map){
        Log.v("MAP IS READY!","!!!!!!!!!!");
        googleMap=map;
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mapReady=true;
    }
    @Override
    public void onStart(){
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    public void onStop(){
        if(googleApiClient.isConnected())
            googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle){
        Log.v("In onConnected","!!!!!");
        locationRequest= LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);

        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION);

        if(permissionCheck!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    0);
        }
        else{

            googleMap.setMyLocationEnabled(true);
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,locationRequest,this);

        }

    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.i(TAG, "User agreed to make required location settings changes.");
                        startLocationUpdates();
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.i(TAG, "User chose not to make required location settings changes.");
                        break;
                }
                break;
        }
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        googleMap.setMyLocationEnabled(true);
                        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
                        //location_other=LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                        //textView.setText(String.valueOf(location_other.getLatitude()));
                        //longitude.setText(String.valueOf(location_other.getLongitude()));
                    }catch (SecurityException e){
                        e.printStackTrace();
                    }

                } else {

                   Snackbar.make(rootView,"No permission",Snackbar.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onLocationChanged(Location location){
        Log.v("onLocationchd IS READY!","!!!!!!!!!!");
        String latitude=String.valueOf(location.getLatitude());
        Log.v("MAP IS READY!",latitude);
        String longitude=String.valueOf(location.getLongitude());
        Log.v("MAP IS READY!",longitude);
        LatLng marker = new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));
        CameraPosition target=CameraPosition.builder().target(marker).zoom(16).tilt(65).bearing(112).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(target),2000,null);
        MarkerOptions start=new MarkerOptions().position(marker).title(getString(R.string.marker_start));
        googleMap.addMarker(start);
        //textView.setText(Double.toString(location.getLatitude()));
        //longitude.setText(Double.toString(location.getLongitude()));

    }

    @Override
    public void onConnectionSuspended(int period){
        //Log.v(LOG_TAG,":Connection Suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult){
       // Log.v(LOG_TAG,": Connection Failed");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MapFragment f = (MapFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.map);
        if (f != null)
            getActivity().getFragmentManager().beginTransaction().remove(f).commit();
    }
}
