package com.example.ahmedetman.peopleapitask;

import android.app.Application;
import android.content.Context;

/**
 * Created by Ahmed Etman on 5/4/2018.
 */

public class ApplicationContextProvider extends Application {

    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }
}
