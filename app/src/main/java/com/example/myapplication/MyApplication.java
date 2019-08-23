package com.example.myapplication;

import android.app.Application;

import com.example.mylibrary.LibraryClass;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LibraryClass.printString(this);
    }
}
