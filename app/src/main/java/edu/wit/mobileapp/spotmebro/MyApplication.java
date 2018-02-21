package edu.wit.mobileapp.spotmebro;

/**
 * Created by Tonthatjwit.edu on 2/13/18.
 */

import android.app.Application;

public class MyApplication extends Application {

    //Preferences
    public static String Global_Preffered_Gender = "x";
    public static String Global_Gender = "x";
    public static String Global_Style = "x";

    private static MyApplication singleton;

    public static MyApplication getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }

}
