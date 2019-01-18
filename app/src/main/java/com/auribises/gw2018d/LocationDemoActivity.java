package com.auribises.gw2018d;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LocationDemoActivity extends AppCompatActivity
        implements View.OnClickListener,
        LocationListener {

    TextView txtData;
    Button btnFetch;

    LocationManager locationManager;
    Geocoder geocoder;

    double latitude, longitude;

    StringBuffer addressBuffer;


    void initViews() {
        txtData = findViewById(R.id.textViewData);
        btnFetch = findViewById(R.id.buttonFetch);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        geocoder = new Geocoder(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_demo);
        initViews();
    }

    @Override
    public void onClick(View v) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Please Grant Permissions",Toast.LENGTH_LONG).show();
        }else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 100, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        /*
        double speed = location.getSpeed(); // mps -> kph
        if(speed>100){
            SmsManager smsManager = SmsManager.getDefault();
            String message = "Speed Limit Crossed 100";
            String phone = "+91 99155 71177";
            smsManager.sendTextMessage(phone,null,message,null,null);
        }
        */

        txtData.setText("Location:"+latitude+" : "+longitude);


        // Stop Getting Location Changes
        locationManager.removeUpdates(this);

        new ReverseGeocodingTask().execute();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(this);
    }

    class ReverseGeocodingTask extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                List<Address> adrsList = geocoder.getFromLocation(latitude, longitude, 2);
                if(adrsList!=null && adrsList.size()>0){

                    Address address = adrsList.get(0);
                    addressBuffer = new StringBuffer();

                    for(int i=0;i<=address.getMaxAddressLineIndex();i++){
                        addressBuffer.append(address.getAddressLine(i)+"\n");
                    }

                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            txtData.setText("Location:"+latitude+" : "+longitude+"\n"+"Address: "+addressBuffer.toString());
        }
    }
}
