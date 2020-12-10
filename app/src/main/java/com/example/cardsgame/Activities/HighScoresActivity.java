package com.example.cardsgame.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cardsgame.R;

import Utils.MySP;

public class HighScoresActivity extends AppCompatActivity {


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        MySP mysp = new MySP(this);
        mysp.putString(MySP.MY_PREFS_NAME,"RON");

    }

    }
