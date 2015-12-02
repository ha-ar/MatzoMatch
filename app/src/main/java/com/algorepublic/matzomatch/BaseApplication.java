package com.algorepublic.matzomatch;

import android.app.Application;

import java.net.URL;

/**
 * Created by hasanali on 28/11/2015.
 */
public class BaseApplication extends Application {

    public static String privacy = "http://104.236.240.148/matzomatch/privacy_policy.php";
    public static String terms = "http://104.236.240.148/matzomatch/terms_of_use.php";

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
