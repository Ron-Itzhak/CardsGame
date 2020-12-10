package com.example.cardsgame.Activities;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cardsgame.Activities.GameActivity;
import com.example.cardsgame.Activities.HighScoresActivity;
import com.example.cardsgame.R;

import Utils.Sound;

public class MainActivity extends AppCompatActivity {
   private Button main_BTN_cardGame,main_BTN_Instructions,main_BTN_HighScores;
   private  MediaPlayer player;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        findViewById();
        Sound.play_sound(this,player,R.raw.song_entrance);
        Sound.maxVolume(this);


        main_BTN_cardGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardGame();
                Sound.stop_sound(player);

            }
        });


        main_BTN_HighScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HighScores();
                Sound.stop_sound(player);

            }
        });
    }






    private void findViewById(){
        main_BTN_cardGame=findViewById(R.id.main_BTN_cardGame);
        main_BTN_Instructions=findViewById(R.id.main_BTN_Instructions);
        main_BTN_HighScores=findViewById(R.id.main_BTN_HighScores);


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
        Sound.play_sound(this,player,R.raw.song_entrance);
    }
}
