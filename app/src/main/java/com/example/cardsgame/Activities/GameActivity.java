package com.example.cardsgame.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cardsgame.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    private static final long DELAY = 3000;
    private TextView player1_counter,player2_counter;
    private Button main_BTN_start,main_BTN_drawCard;
    private ImageView main_IMG_unfolded1,main_IMG_unfolded2,player1,player2;
    private int[][] images;
    private int count,player1_count,player2_count, p1_card_type, p2_card_type, p1_card_value, p2_card_value,time_counter;
    public String winner;
    public static final String WINNER_TEXT= "winnertxt";
    public static final String WINNER_SCORE= "winnerscore";
    public  static final int PROGMULT = 4;
    public MediaPlayer player;
    private Timer carousalTimer;


    ///
    public ProgressBar main_progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        player1_count=0;
        player2_count=0;
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        images = new int[][]{{R.drawable.poker_1, R.drawable.poker_2, R.drawable.poker_3, R.drawable.poker_4},
                {R.drawable.poker_5, R.drawable.poker_6, R.drawable.poker_7, R.drawable.poker_8},
                {R.drawable.poker_9, R.drawable.poker_10, R.drawable.poker_11, R.drawable.poker_12},
                {R.drawable.poker_13, R.drawable.poker_14, R.drawable.poker_15, R.drawable.poker_16},
                {R.drawable.poker_17, R.drawable.poker_18, R.drawable.poker_19, R.drawable.poker_20},
                {R.drawable.poker_21, R.drawable.poker_22, R.drawable.poker_23, R.drawable.poker_24},
                {R.drawable.poker_25, R.drawable.poker_26, R.drawable.poker_27, R.drawable.poker_28},
                {R.drawable.poker_29, R.drawable.poker_30, R.drawable.poker_31, R.drawable.poker_32},
                {R.drawable.poker_33, R.drawable.poker_34, R.drawable.poker_35, R.drawable.poker_36},
                {R.drawable.poker_37, R.drawable.poker_38, R.drawable.poker_39, R.drawable.poker_40},
                {R.drawable.poker_41, R.drawable.poker_42, R.drawable.poker_43, R.drawable.poker_44},
                {R.drawable.poker_45, R.drawable.poker_46, R.drawable.poker_47, R.drawable.poker_48},
                {R.drawable.poker_49, R.drawable.poker_50, R.drawable.poker_51, R.drawable.poker_52}};

        count = 0;

        main_BTN_start=findViewById(R.id.main_BTN_start);
        main_BTN_drawCard=findViewById(R.id.main_BTN_drawCard);
        main_IMG_unfolded1=findViewById(R.id.main_IMG_unfolded1);
        main_IMG_unfolded2=findViewById(R.id.main_IMG_unfolded2);
        player1_counter=findViewById(R.id.player1_counter);
        player2_counter=findViewById(R.id.player2_counter);
        ///

        progressBarAction();

        main_BTN_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startGame();
                play_shuffle();
                startCounting();


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
                        winner(winner,player1_count);
                        finish();
                    }
                    if (player2_count>player1_count) {
                        winner= "winner 2";
                        winner(winner,player2_count);
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

    private void winner(String winner,int count) {
        Intent intent = new Intent(this, WinnerActivity.class);
        intent.putExtra(WINNER_TEXT,winner);
        intent.putExtra(WINNER_SCORE,count);
        startActivity(intent);
    }

    private void startGame() {
     main_BTN_start.setVisibility(View.INVISIBLE);
       /// main_BTN_drawCard.setVisibility(View.VISIBLE);

        {
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
            }, 1500);

            main_IMG_unfolded2.postDelayed(new Runnable() {
                public void run() {
                    main_IMG_unfolded2.setImageResource(R.drawable.img_unfolded);
                }
            }, 1500);

            player1_counter.setText(""+player1_count);
            player2_counter.setText(""+player2_count);




            if (count>=26){
                if (player1_count>player2_count){
                    winner= "winner 1";
                    winner(winner,player1_count);
                    finish();
                }
                if (player2_count>player1_count) {
                    winner= "winner 2";
                    winner(winner,player2_count);
                    finish();

                }
                else
                    drawCard();
            }
        }
    }

    private void progressBarAction(){
        main_progressBar=findViewById(R.id.Game_progressBar);
        final Timer t = new Timer();
        TimerTask tt = new TimerTask()
        {
            @Override
            public void run()
            {
                main_progressBar.setProgress(count*PROGMULT);
                if(count==26)
                    t.cancel();
            }
        };
        t.schedule(tt,50,13);
    }

    private void play_shuffle() {
        if(player==null)
            player = MediaPlayer.create(this,R.raw.shuffling_cards);
        player.start();
//        Sound.maxVolume(this);
    }
    private void startCounting() {
        carousalTimer = new Timer();
        carousalTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startGame()  ;                  }
                });
            }
        }, 0, DELAY);
    }

    private void stopCounting() {
        carousalTimer.cancel();
    }


    @Override
    protected void onStop() {
        super.onStop();
        stopCounting();

    }

    @Override
    protected void onResume() {
        super.onResume();
        main_BTN_start.setVisibility(View.INVISIBLE);
    }
}