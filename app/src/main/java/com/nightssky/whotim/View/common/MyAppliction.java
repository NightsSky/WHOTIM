package com.nightssky.whotim.View.common;

import android.app.Application;

/**
 * Created by user on 2017/5/11.
 */

public class MyAppliction extends Application {
    public static  MyAppliction application = null;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static MyAppliction getApplication() {
        return application;
    }
}
