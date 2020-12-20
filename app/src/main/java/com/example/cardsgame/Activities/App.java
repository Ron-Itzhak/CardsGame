package com.example.cardsgame.Activities;

import android.app.Application;

import Utils.MySPV3;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MySPV3.init(this);

    }

}
