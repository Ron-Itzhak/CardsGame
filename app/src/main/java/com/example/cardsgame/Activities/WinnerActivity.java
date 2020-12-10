package com.example.cardsgame.Activities;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cardsgame.R;

import static com.example.cardsgame.Activities.GameActivity.WINNER_TEXT;


public class WinnerActivity extends AppCompatActivity {

    private TextView winner_LBL_1;
    private ImageView winner_IMG_winner;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        winner_LBL_1=findViewById(R.id.winner_LBL_1);
        winner_IMG_winner=findViewById(R.id.winner_IMG_woman);
        text = getIntent().getStringExtra(WINNER_TEXT);
        winner_LBL_1.setText(text);

        if(text.equals("winner 2")){
            winner_IMG_winner.setImageResource(R.drawable.man);}
    }

}
