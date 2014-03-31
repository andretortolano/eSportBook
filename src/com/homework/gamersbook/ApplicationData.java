package com.homework.gamersbook;

import android.app.Application;
import android.content.Context;

public class ApplicationData extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        setAppContext(getApplicationContext());
    }

    private static void setAppContext(Context ctx) {
        sContext = ctx;
    }

    public static Context getAppContext() {
        return sContext;
    }
}
