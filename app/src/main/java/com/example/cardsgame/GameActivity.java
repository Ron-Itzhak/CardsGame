package com.example.cardsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.sql.Time;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    private TextView player1_counter,player2_counter;
    private Button main_BTN_start,main_BTN_drawCard;
    private ImageView main_IMG_unfolded1,main_IMG_unfolded2,player1,player2;
    private int[][] images;
    private int count,player1_count,player2_count, p1_card_type, p2_card_type, p1_card_value, p2_card_value,time_counter;
    public String winner;
    public static final String WINNER_TEXT= "winnertxt";
    ///
    public ProgressBar main_progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        player1_count=0;
        player2_count=0;
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        images = new int[][]{{R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4},
                {R.drawable.img_5, R.drawable.img_6, R.drawable.img_7, R.drawable.img_8},
                {R.drawable.img_9, R.drawable.img_10, R.drawable.img_11, R.drawable.img_12},
                {R.drawable.img_13, R.drawable.img_14, R.drawable.img_15, R.drawable.img_16},
                {R.drawable.img_17, R.drawable.img_18, R.drawable.img_19, R.drawable.img_20},
                {R.drawable.img_21, R.drawable.img_22, R.drawable.img_23, R.drawable.img_24},
                {R.drawable.img_25, R.drawable.img_26, R.drawable.img_27, R.drawable.img_28},
                {R.drawable.img_29, R.drawable.img_30, R.drawable.img_31, R.drawable.img_32},
                {R.drawable.img_33, R.drawable.img_34, R.drawable.img_35, R.drawable.img_36},
                {R.drawable.img_37, R.drawable.img_38, R.drawable.img_39, R.drawable.img_40},
                {R.drawable.img_41, R.drawable.img_42, R.drawable.img_43, R.drawable.img_44},
                {R.drawable.img_45, R.drawable.img_46, R.drawable.img_47, R.drawable.img_48},
                {R.drawable.img_49, R.drawable.img_50, R.drawable.img_51, R.drawable.img_52}};

        count = 0;

        main_BTN_start=findViewById(R.id.main_BTN_start);
        main_BTN_drawCard=findViewById(R.id.main_BTN_drawCard);
        main_IMG_unfolded1=findViewById(R.id.main_IMG_unfolded1);
        main_IMG_unfolded2=findViewById(R.id.main_IMG_unfolded2);
        player1_counter=findViewById(R.id.player1_counter);
        player2_counter=findViewById(R.id.player2_counter);
        ///

        prog();

        main_BTN_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();

            }
        });
        main_BTN_drawCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             count++;
             drawCard();
             Log.d("myTag", "count"+count );

                if (p1_card_value > p2_card_value){
                    player1_count++;}
                if (p2_card_value > p1_card_value) {
                    player2_count++;
                }
                main_IMG_unfolded1.postDelayed(new Runnable() {
                    public void run() {
                        main_IMG_unfolded1.setImageResource(R.drawable.img_unfolded);
                    }
                }, 3000);

                main_IMG_unfolded2.postDelayed(new Runnable() {
                    public void run() {
                        main_IMG_unfolded2.setImageResource(R.drawable.img_unfolded);
                    }
                }, 3000);

                player1_counter.setText(""+player1_count);
                player2_counter.setText(""+player2_count);




                if (count>=26){
                    if (player1_count>player2_count){
                        winner= "winner 1";
                        winner(winner);
                        finish();
                    }
                    if (player2_count>player1_count) {
                        winner= "winner 2";
                        winner(winner);
                        finish();

                    }
                    else
                    drawCard();
                }
            }
        });
    }

    private void drawCard() {
        Random rand = new Random();
        p1_card_value =rand.nextInt(13);
        p2_card_value =rand.nextInt(13);
        Log.d("test",String.valueOf(p1_card_value));
        Log.d("test",String.valueOf(p2_card_value));
        p1_card_type =rand.nextInt(4);
        p2_card_type =rand.nextInt(4);
        main_IMG_unfolded1.setImageResource(images[p1_card_value][p1_card_type]);
        main_IMG_unfolded2.setImageResource(images[p2_card_value][p2_card_type]);

    }

    private void winner(String winner) {
        Intent intent = new Intent(this,WinnerActivity.class);
        intent.putExtra(WINNER_TEXT,winner);
        startActivity(intent);
    }

    private void startGame() {
        main_BTN_start.setVisibility(View.INVISIBLE);
        main_BTN_drawCard.setVisibility(View.VISIBLE);
    }
    private void prog(){
        main_progressBar=findViewById(R.id.Game_progressBar);
        final Timer t = new Timer();
        TimerTask tt = new TimerTask()
        {
            @Override
            public void run()
            {
                main_progressBar.setProgress(count);
                if(count==26)
                    t.cancel();
            }
        };
        t.schedule(tt,0,26);
    }
}