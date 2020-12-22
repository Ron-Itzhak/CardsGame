package com.example.cardsgame.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.cardsgame.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

import Objects.DB;
import Objects.Location;
import Objects.Record;
import Utils.MySPV;

import static Utils.MySPV.KEYS.KEY_DATABASE;
import static com.example.cardsgame.Activities.GameActivity.WINNER_SCORE;
import static com.example.cardsgame.Activities.GameActivity.WINNER_TEXT;


public class WinnerActivity extends AppCompatActivity {
    private Button winner_BTN_returnToMenu, winner_BTN_highScoresActivity,winner_BTN_playAgain;
    private TextView winner_LBL_1;
    private ImageView winner_IMG_winner;
    private String text;
    private int score;
    private Record record;
    private Location currentLocation;
    private double longitude;
    private double latitude;
    private android.location.Location location;
    private LocationManager lm;
    private Gson gson;
    private String jsonInString;
    private DB dataBase;
    //public String bestProvider;
    //private FusedLocationProviderClient fusedLocationClient;
    //private LocationCallback locationCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        gson = new Gson();
        dataBase = new DB();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        findViewById();
        text = getIntent().getStringExtra(WINNER_TEXT);
        score = getIntent().getIntExtra(WINNER_SCORE, 0);
        winner_LBL_1.setText(text);
        if (text.equals("winner 2")) {
            winner_IMG_winner.setImageResource(R.drawable.man);

        }
        winner_BTN_returnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        winner_BTN_highScoresActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HighScores();
                finish();
            }
        });
        winner_BTN_playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                finish();
            }
        });
        getLocation();
    }

    private void playAgain() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);

    }
    private void HighScores() {
        Intent intent = new Intent(this, HighScoresActivity.class);
        startActivity(intent);
    }
    private void setRecord(android.location.Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        currentLocation = new Location(latitude, longitude);
        record = new Record(text, score, currentLocation);
        Log.d("pttt", "Winner activity - in setRecord - Record to string: " + record.toString());
        //from json to OBJ
        if (MySPV.getInstance() != null) {
            Log.d("pttt", "MySPV3.getInstance() not null");
            jsonInString = MySPV.getInstance().getString(KEY_DATABASE, "");
            if (jsonInString != null && jsonInString != "") {
                dataBase = gson.fromJson(jsonInString, DB.class);
            }
        }
        if (dataBase.insert_Record(record)) {
            //save
            jsonInString = gson.toJson(dataBase);
            MySPV.getInstance().putString(KEY_DATABASE, jsonInString);
            Log.d("pttt", "after enter record : " + MySPV.getInstance().getString(KEY_DATABASE, ""));
        }
    }



    ////Getlocation 3 working + Locatrion Listener
    private final android.location.LocationListener locationListener = new android.location.LocationListener() {
        @Override
        public void onLocationChanged(android.location.Location loc) {
            location=loc;
        }
    };

    private void getLocation() {
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("pttt", "checkSelfPermission ");
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);

        if (lm != null) {
            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                setRecord(location);
            }
        }
    }

    private void findViewById(){
        winner_BTN_playAgain = findViewById(R.id.winner_BTN_playAgain);
        winner_BTN_returnToMenu = findViewById(R.id.winner_BTN_returnToMenu);
        winner_BTN_highScoresActivity = findViewById(R.id.winner_BTN_highScoresActivity);
        winner_LBL_1 = findViewById(R.id.winner_LBL_1);
        winner_IMG_winner = findViewById(R.id.winner_IMG_woman);
    }

//    private void testLocation() {
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        fusedLocationClient.getLastLocation()
//                .addOnSuccessListener(new OnSuccessListener<android.location.Location>() {
//                    @Override
//                    public void onSuccess(android.location.Location location) {
//                        if (location != null) {
//                            setRecord(location);
//                        }
//                    }
//                });
//
//
//        /// add location changed fusedLocationClient.lo
//
//        locationCallback = new LocationCallback() {
//            @Override
//            public void onLocationResult(LocationResult locationResult) {
//                if (locationResult == null) {
//                    return;
//                }
//                for (android.location.Location location : locationResult.getLocations()) {
//                    //setRecord(location);
//                }
//            }
//        };
//
//
//    }



    //    /////delete unused
//    @SuppressLint("MissingPermission")
//    private void getLocationDelte() {
//        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        longitude = location.getLongitude();
//        latitude = location.getLatitude();
//    }

//    private void getLocation2Delete() {
//        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        Criteria criteria = new Criteria();
//        String provider = lm.getBestProvider(criteria, true);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//
//        if (location != null) {
//            Log.e("TAG", "GPS is on");
//            latitude = location.getLatitude();
//            longitude = location.getLongitude();
//            Toast.makeText(this, "latitude:" + latitude + " longitude:" + longitude, Toast.LENGTH_SHORT).show();
//        } else {
//            //This is what you need:
//            lm.requestLocationUpdates(bestProvider, 1000, 0, locationListener);
//            //latitude = location.getLatitude();
//            //longitude = location.getLongitude();
//
//        }
//
//
//    }
    ////-----------///



}
