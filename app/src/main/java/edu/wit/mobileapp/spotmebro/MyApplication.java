package edu.wit.mobileapp.spotmebro;

/**
 * Created by Tonthatjwit.edu on 2/13/18.
 */

import android.app.Application;

public class MyApplication extends Application {

    //Preferences
    public static String Global_Preffered_Gender = "";
    public static String Global_Gender = "";
    public static String Global_Style = "";

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
