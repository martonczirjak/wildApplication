package com.plixapp.czirjak.wildjavaapplication;

import android.app.Application;

import io.realm.Realm;

public class PlixApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
