package com.example.shaunakbasu.capstone;

import android.app.Application;

/**
 * Created by shaunak basu on 21-11-2016.
 */
public class FontApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/RobotoCondensed-Light.ttf");
    }
}
