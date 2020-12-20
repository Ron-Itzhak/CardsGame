package com.example.cardsgame.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.cardsgame.Activities.GameActivity;
import com.example.cardsgame.Activities.HighScoresActivity;
import com.example.cardsgame.R;

import Utils.Sound;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    //private static final String TAG = "";
    private Button main_BTN_cardGame,main_BTN_Instructions,main_BTN_HighScores;
    private ImageView Main_straight_flush;
    private TextView Main_instructions_txt;
   private  MediaPlayer player;
    private static final int LOCATION_REQUEST_CODE = 101;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        findViewById();
        checkPermission();


        if(player==null) {
            Sound.play_sound(this, player, R.raw.song_entrance);
            // Sound.maxVolume(this);
        }



        main_BTN_cardGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sound.stop_sound(player);
                cardGame();

            }
        });


        main_BTN_HighScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sound.stop_sound(player);
                HighScores();

            }
        });



        main_BTN_Instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main_straight_flush.setVisibility(View.INVISIBLE);
                Main_instructions_txt.setVisibility(View.VISIBLE);
            }
        });
    }

    private void findViewById(){
        main_BTN_cardGame=findViewById(R.id.main_BTN_cardGame);
        main_BTN_Instructions=findViewById(R.id.main_BTN_Instructions);
        main_BTN_HighScores=findViewById(R.id.main_BTN_HighScores);
        main_BTN_Instructions=findViewById(R.id.main_BTN_Instructions);
        Main_straight_flush=findViewById(R.id.Main_straight_flush);
        Main_instructions_txt=findViewById(R.id.Main_instructions_txt);
    }

    private void cardGame() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private void HighScores() {
        Intent intent = new Intent(this, HighScoresActivity.class);
        startActivity(intent);
    }



    @Override
    protected void onStop() {
        super.onStop();
        Sound.stop_sound(player);
        //Sound.pause_sound(player);
    }

    @Override
    protected void onPause()
    {        super.onPause();
        Sound.stop_sound(player);

        //soundManager.releaseResources();
    }


    @Override
    protected void onResume() {
        super.onResume();
        //Sound.play_sound(this,player,R.raw.song_entrance);
    }



    private void checkPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=getPackageManager().PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))
                Log.d(TAG, "askLocationPermission");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_REQUEST_CODE);
        }
    }
}
