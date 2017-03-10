package ranglerz.com.guardautozone;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DialerFilter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import com.google.maps.android.SphericalUtil;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

    private Button nearestLocatinoButton;
    private GoogleMap mMap;
    private TextView tvTimeAndDistance;
    private static final int REQUEST_FINE_LOCATION = 11;

    private int timer = 3;
    Handler mHandler;
    Context context;

    Location currentLocation = null;
    protected LocationManager locationManager;
    private Context mContext;
    public boolean isGPSEnabled = false;

    boolean isNetworkEnabled = false;
    // flag for GPS status
    boolean canGetLocation = false;

    Location location; // location
    double latitude; // latitude
    double longitude; // longitude

    MapHelper mapHelper;
    LatLng latLngCurrent, latLngNearest;

    private final static double thokerAddressLat = 31.4674709;
    private final static double thokerAddressLong = 74.2359862;
    private final static double shokatKhanamLat = 31.4484353;
    private final static double shokatKhanamLong = 74.2653798;
    private final static double docterHospitalLat = 31.4795896;
    private final static double docterHopitalLong = 74.2783105;


    double distanceinKM;

    PolylineOptions lineOptions;
    Polyline polyline = null;
    int speedIs10MinutsPerMeter = 12;


    GPSTracker gps;
    //****************
    Double dhaLahoreLat,dhaLahoreLng,
            thokarLahoreLat, thokarLahoreLng,
            modelTwonLhrLat, modelTwonLhrLng,
            raviRaodLhrLat, raviRoadLhrLng,
            caveloryGroudLhrLat, cavaloryGroudLhrLng,
            gujrawalaLat, gujrawalaLng,
            sialkotLat, sialkotLng,
            jhelumLat, jhelumLng,
            faislabadLat, faislabadLng,
            bheraNorthLat, bheraNorthLng,
            bheraSouthLat, bheraSouthLng,
            islamabadLat, islamabadLng,
            rawalpindiLat, rawalpindiLng,
            whaCantLat, whaCantLng,
            safariParkLat, safariParkLng,
            stargateLat, stargateLng,
            manghopirLat, manghopirLng,
            steelTownLat, steelTownLng,
            kalapulLat, kalapulLng,
            malirCantLat, malirCantLng;

    //***********



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        inisialization();

        createNetErrorDialog();

        mapFragment.getMapAsync(this);

        mapHelper = new MapHelper();
        nearestLocation();

        useHandler();

        //cLocation();

    }//end of onCreate


    public void inisialization(){
        nearestLocatinoButton = (Button) findViewById(R.id.nearestLocation);
        tvTimeAndDistance = (TextView) findViewById(R.id.total_time_distance);

        String stringDhaLahoreLat = getResources().getString(R.string.dhaLahoreLat);
        Log.d("This is Test", "Testing String " + stringDhaLahoreLat);
        dhaLahoreLat = 31.500855;
        //dhaLahoreLat = Double.parseDouble(context.getResources().getString(R.string.dhaLahoreLat));
        dhaLahoreLng = 74.420502;
        //dhaLahoreLng = Double.parseDouble(context.getResources().getString(R.string.dhaLahoreLng));

        thokarLahoreLat = 31.466606;
        //thokarLahoreLat = Double.parseDouble(context.getResources().getString(R.string.thokarLahoreLat));

        thokarLahoreLng = 74.235498;
        //thokarLahoreLng = Double.parseDouble(context.getResources().getString(R.string.thokarLahoreLng));

        modelTwonLhrLat = 31.471311;
        //modelTwonLhrLat = Double.parseDouble(context.getResources().getString(R.string.modelTwonLhrLat));


        modelTwonLhrLng = 74.319133;
        //modelTwonLhrLng = Double.parseDouble(context.getResources().getString(R.string.modelTwonLhrLng));

        raviRaodLhrLat = 31.596677;
        //raviRaodLhrLat = Double.parseDouble(context.getResources().getString(R.string.raviRaodLhrLat));

        raviRoadLhrLng = 74.297780;
        //raviRoadLhrLng = Double.parseDouble(context.getResources().getString(R.string.raviRoadLhrLng));

        caveloryGroudLhrLat = 31.501631;
        //caveloryGroudLhrLat = Double.parseDouble(context.getResources().getString(R.string.caveloryGroudLhrLat));

        cavaloryGroudLhrLng = 74.374505;
        //cavaloryGroudLhrLng = Double.parseDouble(context.getResources().getString(R.string.cavaloryGroudLhrLng));

        gujrawalaLat = 32.250412;
        //gujrawalaLat = Double.parseDouble(context.getResources().getString(R.string.gujrawalaLat));

        gujrawalaLng = 74.111567;
        //gujrawalaLng = Double.parseDouble(context.getResources().getString(R.string.gujrawalaLng));

        sialkotLat = 32.509689;
        //sialkotLat = Double.parseDouble(context.getResources().getString(R.string.sialkotLat));

        sialkotLng = 74.559416;
        //sialkotLng = Double.parseDouble(context.getResources().getString(R.string.sialkotLng));

        jhelumLat = 32.925219;
        //jhelumLat = Double.parseDouble(context.getResources().getString(R.string.jhelumLat));

        jhelumLng = 74.716175;
        //jhelumLng = Double.parseDouble(context.getResources().getString(R.string.jhelumLng));

        faislabadLat = 31.507965;
        //faislabadLat = Double.parseDouble(context.getResources().getString(R.string.faislabadLat));

        faislabadLng = 73.069979;
        //faislabadLng = Double.parseDouble(context.getResources().getString(R.string.faislabadLng));

        bheraNorthLat = 32.453655;
        //bheraNorthLat = Double.parseDouble(context.getResources().getString(R.string.bheraNorthLat));

        bheraNorthLng = 72.885129;
        //bheraNorthLng = Double.parseDouble(context.getResources().getString(R.string.bheraNorthLng));

        bheraSouthLat = 32.454647;
        //bheraSouthLat = Double.parseDouble(context.getResources().getString(R.string.bheraSouthLat));

        bheraSouthLng = 72.887927;
        //bheraSouthLng = Double.parseDouble(context.getResources().getString(R.string.bheraSouthLng));

        islamabadLat = 33.640681;
        //islamabadLat = Double.parseDouble(context.getResources().getString(R.string.islamabadLat));

        islamabadLng = 73.026125;
        //islamabadLng = Double.parseDouble(context.getResources().getString(R.string.islamabadLng));

        rawalpindiLat = 33.612354;
        //rawalpindiLat = Double.parseDouble(context.getResources().getString(R.string.rawalpindiLat));

        rawalpindiLng = 73.024193;
        //rawalpindiLng = Double.parseDouble(context.getResources().getString(R.string.rawalpindiLng));

        whaCantLat = 33.767688;
        //whaCantLat = Double.parseDouble(context.getResources().getString(R.string.whaCantLat));

        whaCantLng = 72.756487;
        //whaCantLng = Double.parseDouble(context.getResources().getString(R.string.whaCantLng));

        safariParkLat = 24.922120;
        //safariParkLat = Double.parseDouble(context.getResources().getString(R.string.safariParkLat));

        safariParkLng = 67.105894;
        //safariParkLng = Double.parseDouble(context.getResources().getString(R.string.safariParkLng));

        stargateLat = 24.887969;
        //stargateLat = Double.parseDouble(context.getResources().getString(R.string.stargateLat));

        stargateLng = 67.151895;
        //stargateLng = Double.parseDouble(context.getResources().getString(R.string.stargateLng));

        manghopirLat = 24.920069;
        //manghopirLat = Double.parseDouble(context.getResources().getString(R.string.manghopirLat));

        manghopirLng = 67.015472;
        //manghopirLng = Double.parseDouble(context.getResources().getString(R.string.manghopirLng));

        steelTownLat = 24.862040;
        //steelTownLat = Double.parseDouble(context.getResources().getString(R.string.steelTownLat));

        steelTownLng = 67.336952;
        //steelTownLng = Double.parseDouble(context.getResources().getString(R.string.steelTownLng));

        kalapulLat = 24.852262;
        //kalapulLat = Double.parseDouble(context.getResources().getString(R.string.kalapulLat));

        kalapulLng = 67.051149;
        //kalapulLng = Double.parseDouble(context.getResources().getString(R.string.kalapulLng));

        malirCantLat = 24.928387;
        //malirCantLat = Double.parseDouble(context.getResources().getString(R.string.malirCantLat));

        malirCantLng = 67.202987;
        //malirCantLng = Double.parseDouble(context.getResources().getString(R.string.malirCantLng));

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
/*
        if(checkPermission())
            mMap.setMyLocationEnabled(true);
        else askPermission();*/


        setMyLocationEnable();


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);



        GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
                //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 12.0f));
                latitude = location.getLatitude();
                longitude = location.getLongitude();

                mapHelper.setLatitude(latitude);
                mapHelper.setLongitude(longitude);
                mMap.addMarker(new MarkerOptions()
                        .position(loc).title(" " + latitude + " " + longitude))
                        .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker));




                //Toast.makeText(getApplicationContext(), "Current LatLong: " + loc, Toast.LENGTH_SHORT).show();
            }
        };
        mMap.setOnMyLocationChangeListener(myLocationChangeListener);

        mapLongClickListener();

    }


    //nearest location
    public void nearestLocation(){

        nearestLocatinoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNetErrorDialog();
                latitude = mapHelper.getLatitude();
                longitude = mapHelper.getLongitude();
                //Toast.makeText(getApplicationContext(), " Latitude: " + latitude + " Longitude: " + longitude, Toast.LENGTH_SHORT).show();

                if (polyline != null) {
                    polyline.remove();
                }
                calculateShorDistance();


            }
        });
    }


    // Check for permission to access Location
    private boolean checkPermission() {
        Log.d("MapActivity", "checkPermission()");
        // Ask for permission if it wasn't granted yet
        return (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED );
    }
    // Asks for permission
    private void askPermission() {
        Log.d("MapActivity", "askPermission()");
        ActivityCompat.requestPermissions(
                this,
                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_FINE_LOCATION
        );
    }

 /*   @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("MapActivity", "onRequestPermissionsResult()");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted
                    if (checkPermission())
                        mMap.setMyLocationEnabled(true);

                } else {
                    // Permission denied

                }
                break;
            }
        }//end of switch
    }// of onRequestPermissionResult*/


    public void setMyLocationEnable(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);
        }else {
            mMap.setMyLocationEnabled(true);


        }
    }

/*
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==REQUEST_FINE_LOCATION){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                setMyLocationEnable();
            }else {
                Toast.makeText(getApplicationContext(), "Untill You Grand Permission Map Cant Display", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }*/

    //calculation distance
    public void calculateShorDistance(){


        LatLng latLongCurrnt = new LatLng(mapHelper.getLatitude(), mapHelper.getLongitude());
        LatLng dhaLahoreLatLong = new LatLng(dhaLahoreLat, dhaLahoreLng);
        LatLng thokarLatLong = new LatLng(thokarLahoreLat, thokarLahoreLng);
        LatLng modelLatLong = new LatLng(modelTwonLhrLat, modelTwonLhrLng);
        LatLng raviRaodLatLong = new LatLng(raviRaodLhrLat, raviRoadLhrLng);
        LatLng calveloryGroudLatLong = new LatLng(caveloryGroudLhrLat, cavaloryGroudLhrLng);
        LatLng gujrawalaLatLong = new LatLng(gujrawalaLat, gujrawalaLng);
        LatLng sialkotLatLong = new LatLng(sialkotLat, sialkotLng);
        LatLng jhelumLatLong = new LatLng(jhelumLat, jhelumLng);
        LatLng faislabadLatLong = new LatLng(faislabadLat, faislabadLng);
        LatLng bheraNorthLatLong = new LatLng(bheraNorthLat, bheraNorthLng);
        LatLng bheraSouthLatLong = new LatLng(bheraSouthLat, bheraSouthLng);
        LatLng islamabadLatLong = new LatLng(islamabadLat, islamabadLng);
        LatLng rawalpindiLatLong = new LatLng(rawalpindiLat, rawalpindiLng);
        LatLng whaCantLatLong = new LatLng(whaCantLat, whaCantLng);
        LatLng safariParkLatLong = new LatLng(safariParkLat, safariParkLng);
        LatLng stargateLatLong = new LatLng(stargateLat, stargateLng);
        LatLng manghopirLatLong = new LatLng(manghopirLat, manghopirLng);
        LatLng steelTownLatLong = new LatLng(steelTownLat, steelTownLng);
        LatLng kalapulLatLong = new LatLng(kalapulLat, kalapulLng);
        LatLng malirCantLatLong = new LatLng(malirCantLat, malirCantLng);

        Double dhaDistance, thokarDistance, modelTownDistance, raviRaodDistance, calveloryGroudDistance, gujrawalaDistance, sialkotDistance, jhelumDistance,
                faislabaddistance, bheraNorthDistance, bheraSouthDistance, islamabadDistance, rawalpindiDistance, whaCantDistance, safariParkDistance, stargateDistanc,
                mangopirDistance, steelTownDistance, kalapulDistance, malirCantDistance;

        //calculating distance from current location to latselected lat long

        dhaDistance = SphericalUtil.computeDistanceBetween(latLongCurrnt, dhaLahoreLatLong);
        thokarDistance  = SphericalUtil.computeDistanceBetween(latLongCurrnt, thokarLatLong);
        modelTownDistance = SphericalUtil.computeDistanceBetween(latLongCurrnt, modelLatLong);
        raviRaodDistance = SphericalUtil.computeDistanceBetween(latLongCurrnt, raviRaodLatLong);
        calveloryGroudDistance = SphericalUtil.computeDistanceBetween(latLongCurrnt, calveloryGroudLatLong);
        gujrawalaDistance = SphericalUtil.computeDistanceBetween(latLongCurrnt, gujrawalaLatLong);
        sialkotDistance = SphericalUtil.computeDistanceBetween(latLongCurrnt, sialkotLatLong);
        jhelumDistance = SphericalUtil.computeDistanceBetween(latLongCurrnt, jhelumLatLong);
        faislabaddistance = SphericalUtil.computeDistanceBetween(latLongCurrnt, faislabadLatLong);
        bheraNorthDistance = SphericalUtil.computeDistanceBetween(latLongCurrnt, bheraNorthLatLong);
        bheraSouthDistance = SphericalUtil.computeDistanceBetween(latLongCurrnt, bheraSouthLatLong);
        islamabadDistance = SphericalUtil.computeDistanceBetween(latLongCurrnt, islamabadLatLong);
        rawalpindiDistance = SphericalUtil.computeDistanceBetween(latLongCurrnt, rawalpindiLatLong);
        whaCantDistance = SphericalUtil.computeDistanceBetween(latLongCurrnt, whaCantLatLong);
        safariParkDistance = SphericalUtil.computeDistanceBetween(latLongCurrnt, safariParkLatLong);
        stargateDistanc = SphericalUtil.computeDistanceBetween(latLongCurrnt, stargateLatLong);
        mangopirDistance = SphericalUtil.computeDistanceBetween(latLongCurrnt, manghopirLatLong);
        steelTownDistance = SphericalUtil.computeDistanceBetween(latLongCurrnt, steelTownLatLong);
        kalapulDistance = SphericalUtil.computeDistanceBetween(latLongCurrnt, kalapulLatLong);
        malirCantDistance = SphericalUtil.computeDistanceBetween(latLongCurrnt, malirCantLatLong);


//****************
        LatLng thookarLatLong = new LatLng(thokerAddressLat, thokerAddressLong);
        LatLng shoketKhanamLatLong = new LatLng(shokatKhanamLat, shokatKhanamLong);
        LatLng docterHospitalLatLong = new LatLng(docterHospitalLat, docterHopitalLong);
//*****************

        Double distaceArray[] = {dhaDistance, thokarDistance, modelTownDistance, raviRaodDistance, calveloryGroudDistance, gujrawalaDistance,
                                sialkotDistance, jhelumDistance, faislabaddistance, bheraNorthDistance, bheraSouthDistance, islamabadDistance, rawalpindiDistance, whaCantDistance, safariParkDistance,
                                stargateDistanc, mangopirDistance, steelTownDistance, kalapulDistance, malirCantDistance};
        List<Double> ditanceList = new ArrayList<Double>();
        for (int i = 0; i<distaceArray.length; i++){
            ditanceList.add(distaceArray[i]);
        }


        //minimun distance from arry list
        double nearestDistance =  Collections.min(ditanceList);

        double estimateDriveTime = nearestDistance/speedIs10MinutsPerMeter;
        double timeInMinuts = estimateDriveTime/60;
        distanceinKM = nearestDistance/1000;
        double timeroudn = round(timeInMinuts, 0);
        double rounded = round(distanceinKM, 1);
        Toast.makeText(getApplicationContext(), "Sortest Distace is " + rounded + " KM " + "\n Time To Reach: " + timeroudn, Toast.LENGTH_SHORT).show();
        if (nearestDistance == dhaDistance){
            String url = getUrl(latLongCurrnt, dhaLahoreLatLong);
            Log.d("onMapClick", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
            setingTextAndTimeInTextView(dhaDistance);

        }else if (nearestDistance == thokarDistance){
            String url = getUrl(latLongCurrnt, thokarLatLong);
            Log.d("onMapClick", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            setingTextAndTimeInTextView(thokarDistance);

            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
        }else if (nearestDistance == modelTownDistance){
            String url = getUrl(latLongCurrnt, modelLatLong);
            Log.d("onMapClick", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            setingTextAndTimeInTextView(modelTownDistance);

            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
        }else if (nearestDistance == raviRaodDistance){
            String url = getUrl(latLongCurrnt, raviRaodLatLong);
            Log.d("onMapClick", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            setingTextAndTimeInTextView(raviRaodDistance);

            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
        }else if (nearestDistance == calveloryGroudDistance){
            String url = getUrl(latLongCurrnt, calveloryGroudLatLong);
            Log.d("onMapClick", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            setingTextAndTimeInTextView(calveloryGroudDistance);

            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
        }else if (nearestDistance == gujrawalaDistance){
            String url = getUrl(latLongCurrnt, gujrawalaLatLong);
            Log.d("onMapClick", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            setingTextAndTimeInTextView(gujrawalaDistance);

            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
        }else if (nearestDistance == sialkotDistance){
            String url = getUrl(latLongCurrnt, sialkotLatLong);
            Log.d("onMapClick", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            setingTextAndTimeInTextView(sialkotDistance);

            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
        }else if (nearestDistance == jhelumDistance){
            String url = getUrl(latLongCurrnt, jhelumLatLong);
            Log.d("onMapClick", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            setingTextAndTimeInTextView(jhelumDistance);

            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
        }else if (nearestDistance == faislabaddistance){
            String url = getUrl(latLongCurrnt, faislabadLatLong);
            Log.d("onMapClick", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            setingTextAndTimeInTextView(faislabaddistance);

            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
        }else if (nearestDistance == bheraNorthDistance){
            String url = getUrl(latLongCurrnt, bheraNorthLatLong);
            Log.d("onMapClick", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            setingTextAndTimeInTextView(bheraNorthDistance);

            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
        }else if (nearestDistance == bheraSouthDistance){
            String url = getUrl(latLongCurrnt, bheraSouthLatLong);
            Log.d("onMapClick", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            setingTextAndTimeInTextView(bheraSouthDistance);

            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
        }else if (nearestDistance == islamabadDistance){
            String url = getUrl(latLongCurrnt, islamabadLatLong);
            Log.d("onMapClick", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            setingTextAndTimeInTextView(islamabadDistance);

            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
        }else if (nearestDistance == rawalpindiDistance){
            String url = getUrl(latLongCurrnt, raviRaodLatLong);
            Log.d("onMapClick", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            setingTextAndTimeInTextView(rawalpindiDistance);

            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
        }else if (nearestDistance == whaCantDistance){
            String url = getUrl(latLongCurrnt, whaCantLatLong);
            Log.d("onMapClick", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            setingTextAndTimeInTextView(whaCantDistance);

            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
        }else if (nearestDistance == safariParkDistance){
            String url = getUrl(latLongCurrnt, safariParkLatLong);
            Log.d("onMapClick", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            setingTextAndTimeInTextView(safariParkDistance);

            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
        }else if (nearestDistance == stargateDistanc){
            String url = getUrl(latLongCurrnt, stargateLatLong);
            Log.d("onMapClick", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            setingTextAndTimeInTextView(stargateDistanc);

            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
        }else if (nearestDistance == mangopirDistance){
            String url = getUrl(latLongCurrnt, manghopirLatLong);
            Log.d("onMapClick", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            setingTextAndTimeInTextView(mangopirDistance);

            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
        }else if (nearestDistance == steelTownDistance){
            String url = getUrl(latLongCurrnt, steelTownLatLong);
            Log.d("onMapClick", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            setingTextAndTimeInTextView(steelTownDistance);

            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
        }else if (nearestDistance == kalapulDistance){
            String url = getUrl(latLongCurrnt, kalapulLatLong);
            Log.d("onMapClick", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            setingTextAndTimeInTextView(kalapulDistance);

            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
        }else if (nearestDistance == mangopirDistance){
            String url = getUrl(latLongCurrnt, manghopirLatLong);
            Log.d("onMapClick", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            setingTextAndTimeInTextView(mangopirDistance);

            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
        }
    }//end of calculate distance



    //rouding double
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    //market for thokar branch
    public void addMarkerForDHALahore(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(dhaLahoreLat, dhaLahoreLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker))
                .title("Main, Airport Road, DHA, Near Divine Garden Scheme. Lahore"));

    }

    public void addMarkerForThokerLahore(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(thokarLahoreLat, thokarLahoreLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker))
                .title("Thokar Store 2km Thokar Niaz Baigm Multan Road, Lahore"));

    }
    public void addMarkerForModelTownLahore(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(modelTwonLhrLat, modelTwonLhrLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker))
                .title("G-Block, Link Road Model Town, Lahore"));

    }

    public void addMarkerForRaviRoadLahore(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(raviRaodLhrLat, raviRoadLhrLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker))
                .title("75 Ravi Raod, Lahore Near Minar-e-Pakistan"));

    }
    public void addMarkerForCaveloryGroudLahore(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(caveloryGroudLhrLat, cavaloryGroudLhrLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker))
                .title("CSD Cavalry Groud Massod Anwari Road Lahore Cantt."));

    }
    public void addMarkerForGujrawala(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(gujrawalaLat, gujrawalaLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker))
                .title("CSD Gujranwala Near Garrison Auditorium Gujranwala"));

    }
    public void addMarkerForSialkot(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(sialkotLat, sialkotLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker))
                .title("CSD Sialkot Triq Road, Sialkot Cantt"));

    }
    public void addMarkerForJhelum(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(jhelumLat, jhelumLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker))
                .title("CSD Jhelum Cant Near Maqbool Shaeed Check Post, Jhelum Cantt"));

    }
    public void addMarkerForFaislabad(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(faislabadLat, faislabadLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker))
                .title("Faisalabad Store Main Sargodha Road, Adjacent FDA City, Faisalabad"));

    }

    public void addMarkerForBheraNorth(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(bheraNorthLat, bheraNorthLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker))
                .title("Bhera Service Area, Motorway M@, Syedam, Alipur Sharif, Bhera"));

    }
    public void addMarkerForBheraSouth(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(bheraSouthLat, bheraSouthLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker))
                .title("Bhera Service Area, Motorway M@, Syedam, Alipur Sharif, Bhera"));

    }
    public void addMarkerForIslamabad(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(islamabadLat, islamabadLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker))
                .title("Plot 1-A, I-11/4 Adjacent Railway Carriage Factory, Islamabad"));

    }
    public void addMarkerForRawalpindi(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(rawalpindiLat, rawalpindiLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker))
                .title("CSD Mega Mall Wstridge 1 Steet 4 Near Pashan Shopingg Complex. Rawalpindi"));

    }
    public void addMarkerForWhaCantt(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(whaCantLat, whaCantLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker))
                .title("CSD Shoping Mall Near Laik Ali Chowk, Wha Cantt"));

    }
    public void addMarkerForSafariParkKarachi(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(safariParkLat, safariParkLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker))
                .title("NA-Class 19-219, Okewari Near Safari Park, University Road, Gushan Iqbal, Karachi"));

    }
    public void addMarkerForStargateKarachi(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(stargateLat, stargateLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker))
                .title("Stargate store Near Stargate, Shahrah e Faisal, Karachi"));
    }
    public void addMarkerForMangopirKarachi(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(manghopirLat, manghopirLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker))
                .title("D-22, Manghopir Road, SITE Area Karachi"));

    }
    public void addMarkerForSteelTownKarachi(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(steelTownLat, steelTownLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker))
                .title("CSD Shopping Mall, Stadium Road Near St. Vincent Church, Karachi"));

    }
    public void addMarkerForKalapulKarachi(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(kalapulLat, kalapulLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker))
                .title("CSD Shopping Mall Korangi Road, Near Gora Qabaristan, Karachi"));

    }
    public void addMarkerForMalirCanttKarachi(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(malirCantLat, malirCantLng))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker))
                .title("CSD Shopping Mall Near Pakistan Railway Reservation Office, Malir Cantt, Karachi"));

    }

    public void addingMarkeronMap(){

        addMarkerForDHALahore();
        addMarkerForThokerLahore();
        addMarkerForModelTownLahore();
        addMarkerForRaviRoadLahore();
        addMarkerForCaveloryGroudLahore();
        addMarkerForGujrawala();
        addMarkerForSialkot();
        addMarkerForJhelum();
        addMarkerForFaislabad();
        addMarkerForBheraNorth();
        addMarkerForBheraSouth();
        addMarkerForIslamabad();
        addMarkerForRawalpindi();
        addMarkerForWhaCantt();
        addMarkerForSafariParkKarachi();
        addMarkerForStargateKarachi();
        addMarkerForMangopirKarachi();
        addMarkerForSteelTownKarachi();
        addMarkerForKalapulKarachi();
        addMarkerForMalirCanttKarachi();

    }


    public double shortDistance(double fromLong, double fromLat, double toLong, double toLat){
        double d2r = Math.PI / 180;
        double dLong = (toLong - fromLong) * d2r;
        double dLat = (toLat - fromLat) * d2r;
        double a = Math.pow(Math.sin(dLat / 2.0), 2) + Math.cos(fromLat * d2r)
                * Math.cos(toLat * d2r) * Math.pow(Math.sin(dLong / 2.0), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = 6367000 * c;
        return Math.round(d);
    }



    //distance between two points

    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }


    //Thread for starting mainActivity
    private Runnable mRunnableStartMainActivity = new Runnable() {
        @Override
        public void run() {
            Log.d("Handler", " Calls");
            timer--;
            mHandler = new Handler();
            mHandler.postDelayed(this, 1000);

            if (timer == 0) {
                LatLng currentLatLng = new LatLng(latitude, longitude);
                mapHelper.setScr(currentLatLng);

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12.0f));

                //addMarkerForShokatKhanamBranch();
                //addMarkerForThokerBranch();
                //addMarkerForDoterHospitalBranch();
                addingMarkeronMap();


                mHandler.removeCallbacks(mRunnableStartMainActivity);

            }
        }
    };


    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                       /* if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }*/
                        //mMap.setMyLocationEnabled(true);
                        setMyLocationEnable();
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }



    //***********************

    private String getUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;


        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();
            Log.d("downloadUrl", data.toString());
            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    //map LongClick listener
    public void mapLongClickListener() {

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                createNetErrorDialog();
                LatLng selectedLatLong =  marker.getPosition();
                mapHelper.setDes(selectedLatLong);




                new AlertDialog.Builder(MapsActivity.this)
                        .setTitle("MAP")
                        .setMessage("Click OK to Draw Map From Your Current Position to this Selected Posistion")
                        .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //don some thing here for

                                if (polyline!=null){
                                    polyline.remove();
                                }

                                String url = getUrl(mapHelper.getScr(), mapHelper.getDes());
                                Log.d("onMapClick", url.toString());
                                FetchUrl FetchUrl = new FetchUrl();

                                // Start downloading json data from Google Directions API
                                FetchUrl.execute(url);


                                double selctedMarketTime =  SphericalUtil.computeDistanceBetween(mapHelper.getScr(), mapHelper.getDes());

                                double estimateDriveTime = selctedMarketTime/speedIs10MinutsPerMeter;


                                double timeInMinuts = estimateDriveTime/60;

                                double timeroudn = round(timeInMinuts, 0);
                                Double d = new Double(timeroudn);
                                int totalTime = d.intValue();

                                if (totalTime>60){

                                    String tTime =  timeConvert(totalTime);
                                    Toast.makeText(getApplicationContext(), "Time To Arive " + tTime, Toast.LENGTH_SHORT).show();
                                }


                                Toast.makeText(getApplicationContext(), "Time To Arive " + timeroudn + " minuts", Toast.LENGTH_SHORT).show();


                                Double selectedMarkerDistnace = SphericalUtil.computeDistanceBetween(mapHelper.getScr(), mapHelper.getDes());

                                double distanceInKM = selectedMarkerDistnace/1000;
                                double rounded = round(distanceInKM, 1);

                                Toast.makeText(getApplicationContext(), "Sortest Distace is " + rounded + " KM", Toast.LENGTH_SHORT).show();


                                tvTimeAndDistance.setText("Total Estimated Time Required To reach: " + timeroudn + " minuts" + "\n" +
                                        "Total Estimated Distance To Reach: " + rounded + " KM");




                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();

                return false;
            }
        });


    }//end of on mapLonclick method


    public void setingTextAndTimeInTextView(double distace){
        double distanceInKM = distace/1000;
        double rounded = round(distanceInKM, 1);

        double estimateDriveTime = distace/speedIs10MinutsPerMeter;


        double timeInMinuts = estimateDriveTime/60;

        double timeroudn = round(timeInMinuts, 0);
        Double d = new Double(timeroudn);
        int totalTime = d.intValue();

        if (totalTime>60){

            String tTime =  timeConvert(totalTime);
            Toast.makeText(getApplicationContext(), "Time To Arive " + tTime, Toast.LENGTH_SHORT).show();
        }

        tvTimeAndDistance.setText("Total Estimated Time Required To reach: " + timeroudn + " minuts" + "\n" +
                "Total Estimated Distance To Reach: " + rounded + " KM");
    }


    //converting time into hrs and day
    public String timeConvert(int time) {
        return time/24/60 + ":" + time/60%24 + ':' + time%60;
    }

    // Fetches data from url passed
    private class FetchUrl extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                Log.d("Background Task data", data.toString());
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);

        }
    }

    /**
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                Log.d("ParserTask",jsonData[0].toString());
                DataParser parser = new DataParser();
                Log.d("ParserTask", parser.toString());

                // Starts parsing data
                routes = parser.parse(jObject);
                Log.d("ParserTask","Executing routes");
                Log.d("ParserTask",routes.toString());

            } catch (Exception e) {
                Log.d("ParserTask",e.toString());
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points;
            lineOptions = null;

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(10);
                lineOptions.color(Color.RED);

                Log.d("onPostExecute","onPostExecute lineoptions decoded");

            }

            // Drawing polyline in the Google Map for the i-th route
            if(lineOptions != null) {
                polyline =  mMap.addPolyline(lineOptions);


            }
            else {
                Log.d("onPostExecute","without Polylines drawn");
            }
        }
    }


 //handler for the starign activity
    Handler newHandler;
    public void useHandler(){

        newHandler = new Handler();
        newHandler.postDelayed(mRunnableStartMainActivity, 1000);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRunnableStartMainActivity);
    }


    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    protected void createNetErrorDialog() {

        if (isNetworkAvailable()==false){


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You need a network connection to use this application. Please turn on mobile network or Wi-Fi in Settings.")
                .setTitle("Unable to connect")
                .setCancelable(false)
                .setPositiveButton("Settings",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                startActivity(i);
                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MapsActivity.this.finish();
                            }
                        }
                );
        AlertDialog alert = builder.create();
        alert.show();
    }else {
            //remainging
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(MapsActivity.this, TestAnimation.class);
        startActivity(i);
        finish();
    }

}//end of class


