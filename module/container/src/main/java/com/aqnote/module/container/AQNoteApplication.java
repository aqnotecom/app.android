package com.aqnote.module.container;

import android.app.Application;

/**
 * Created by madding on 2/27/17.
 */

public class AQNoteApplication extends Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Application getInstance() {
        return instance;
    }

}