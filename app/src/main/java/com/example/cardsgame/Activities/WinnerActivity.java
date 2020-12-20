package com.example.cardsgame.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.cardsgame.R;
import com.google.android.gms.location.LocationListener;
import com.google.gson.Gson;

import Objects.DB;
import Objects.Location;
import Objects.Record;
import Utils.MySPV3;

import static Utils.MySPV3.KEYS.KEY_DATABASE;
import static androidx.core.location.LocationManagerCompat.isLocationEnabled;
import static com.example.cardsgame.Activities.GameActivity.WINNER_SCORE;
import static com.example.cardsgame.Activities.GameActivity.WINNER_TEXT;


public class WinnerActivity extends AppCompatActivity {

    private TextView winner_LBL_1;
    private ImageView winner_IMG_winner;
    private String text;
    private int score;
    private Record record;
    private Location lct;
    private double longitude;
    private double latitude;
    private android.location.Location location;
    private LocationManager lm;
    private Gson gson;
    private String jsonInString;
    private DB dataBase;
    public String bestProvider;
    //public Criteria criteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gson = new Gson();
        dataBase= new DB();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        winner_LBL_1 = findViewById(R.id.winner_LBL_1);
        winner_IMG_winner = findViewById(R.id.winner_IMG_woman);
        text = getIntent().getStringExtra(WINNER_TEXT);
        score = getIntent().getIntExtra(WINNER_SCORE, 0);
        winner_LBL_1.setText(text);

        if (text.equals("winner 2")) {
            winner_IMG_winner.setImageResource(R.drawable.man);

        }
        getLocation3();



        //MySPV3.getInstance().putString(MySPV3.KEYS.KEY_USER_USER_NAME,jsonInString);

    }

    private void setRecord(android.location.Location location) {

        Log.d("pttt", "Winner activity - setRecord entered");

        latitude = location.getLatitude();
        longitude = location.getLongitude();

        lct = new Location(longitude, latitude);
        record = new Record(text, score, lct);
        Log.d("pttt", "Winner activity - in setRecord - Record to string: " + record.toString());

        //from json to OBJ
        if(MySPV3.getInstance()!=null){
            Log.d("pttt", "MySPV3.getInstance() not null");

            jsonInString=MySPV3.getInstance().getString(KEY_DATABASE,"");
            if(jsonInString!=null&&jsonInString!=""){
                dataBase = gson.fromJson(jsonInString, DB.class);}
        }

        if(dataBase.insert_Record(record)){
            //save
            jsonInString = gson.toJson(dataBase);
            MySPV3.getInstance().putString(KEY_DATABASE,jsonInString);

            Log.d("pttt", "after enter record : "+MySPV3.getInstance().getString(KEY_DATABASE,""));

        }



        //lct = new Location(longitude, latitude);
        //record = new Record(text, score, lct);
        //jsonInString = gson.toJson(record);
       // Log.d("pttt", "Winner activity - data json: " + jsonInString);
        //Log.d("pttt", "Winner activity - data Record to string: " + record.toString());

    }

/////delete unused
    @SuppressLint("MissingPermission")
    private void getLocation() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        longitude = location.getLongitude();
        latitude = location.getLatitude();
    }

    private void getLocation2() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = lm.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (location != null) {
            Log.e("TAG", "GPS is on");
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            Toast.makeText(this, "latitude:" + latitude + " longitude:" + longitude, Toast.LENGTH_SHORT).show();
        } else {
            //This is what you need:
            lm.requestLocationUpdates(bestProvider, 1000, 0, locationListener);
            //latitude = location.getLatitude();
            //longitude = location.getLongitude();

        }


    }

///delete

    private final android.location.LocationListener locationListener = new android.location.LocationListener() {
        @Override
        public void onLocationChanged(android.location.Location location) {
            setRecord(location);
        }
    };

    private void getLocation3() {

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("pttt", "checkSelfPermission " );

            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);
       // record = new Record(text, score, lct);

    }





    }
