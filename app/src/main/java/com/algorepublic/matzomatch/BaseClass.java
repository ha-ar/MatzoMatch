package com.algorepublic.matzomatch;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by hasanali on 28/11/2015.
 */
public class BaseClass extends Application {
    public SharedPreferences appSharedPrefs;
    public SharedPreferences.Editor prefsEditor;
    private String FirstName = "FirstName";
    private String LastName = "LastName";
    public static String privacy = "http://104.236.240.148/matzomatch/privacy_policy.php";
    public static String terms = "http://104.236.240.148/matzomatch/terms_of_use.php";

    @Override
    public void onCreate() {
        super.onCreate();

    }
    public void setFirstName(String firstName) {
        prefsEditor.putString(FirstName, firstName).commit();
    }

    public String getFirstName() {
        return appSharedPrefs.getString(FirstName, "");
    }

    public void setLastName(String lastName){
        prefsEditor.putString(LastName,lastName).commit();
        Log.e("TAG","setLastName" +  prefsEditor.putString(LastName,lastName).commit());
    }

    public String getLastName(){
        Log.e("TAG","getLastName" + getLastName().toString());
        return appSharedPrefs.getString(LastName,"");

    }
}
