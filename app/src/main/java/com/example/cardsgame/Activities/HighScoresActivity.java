package com.example.cardsgame.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cardsgame.R;

import Callbacks.Callback_List;
import Fragments.Fragment_List;
import Fragments.Fragment_Map;
import Utils.MySP;
import Utils.MySPV3;

public class HighScoresActivity extends AppCompatActivity {
    private FrameLayout HighScores_list_view;
    private FrameLayout HighScores_LAY_map;
    private Fragment_List fragment_list;
    private Fragment_Map fragment_map;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        findViewById();
        //MySP mysp = new MySP(this);
        //mysp.putString(MySP.MY_PREFS_NAME,"RON");


        fragment_list = new Fragment_List();
        fragment_list.setCallBack_list(callBack_list);

        getSupportFragmentManager().beginTransaction().add(R.id.HighScores_list_view, fragment_list).commit();

        //initialize map
        fragment_map = new Fragment_Map();
        getSupportFragmentManager().beginTransaction().add(R.id.HighScores_LAY_map, fragment_map).commit();


        //addAllToList();

    }


    private void findViewById(){
        HighScores_list_view=findViewById(R.id.HighScores_list_view);
        HighScores_LAY_map=findViewById(R.id.HighScores_LAY_map);

    }


    private void addAllToList(){
        String num = "";
        //MySPV3.getInstance().getString(MySPV3.KEYS.KEY_NUM,num);
       // MySPV3.getInstance().getString("KEY_USER"+num,jsonInString);
        int x = Integer.parseInt(num);
            x+=1;
            MySPV3.getInstance().getString("KEY_NUM",x+"");

           // fragment_list.addToList
        }



    private Callback_List callBack_list = new Callback_List() {
        @Override
        public void addWinnerToList(String record) {

            fragment_list.addToList(record);
        }

        @Override
        public void addMarkerToMap(double lat, double lon) {

            fragment_map.addMarker(lat, lon);
        }
    };
}



