package com.example.cardsgame.Activities;

import android.app.Application;

import Utils.MySPV;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MySPV.init(this);

    }

}
