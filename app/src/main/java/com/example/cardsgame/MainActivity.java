package com.example.cardsgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
   Button main_BTN_cardGame,main_BTN_Instructions,main_BTN_HighScores;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        findViewById();
        main_BTN_cardGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardGame();
            }
        });
    }






    private void findViewById(){
        main_BTN_cardGame=findViewById(R.id.main_BTN_cardGame);
        main_BTN_Instructions=findViewById(R.id.main_BTN_Instructions);
        main_BTN_HighScores=findViewById(R.id.main_BTN_HighScores);


    }

    private void cardGame() {
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);
    }

}
