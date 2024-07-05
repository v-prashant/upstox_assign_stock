package com.example.trendingapp.ui;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.trendingapp.R;

import java.io.File;
import java.lang.ref.WeakReference;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class Application extends android.app.Application {

    private static Application application;
    
    public static Application getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

}
