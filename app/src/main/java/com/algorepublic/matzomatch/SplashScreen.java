package com.algorepublic.matzomatch;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.algorepublic.matzomatch.Services.CallBack;
import com.algorepublic.matzomatch.Services.LoginService;
import com.algorepublic.matzomatch.Utils.Constants;
import com.algorepublic.matzomatch.Utils.TinyDB;
import com.algorepublic.matzomatch.adapter.CustomPagerAdapter;
import com.algorepublic.matzomatch.model.LoginModel;
import com.androidquery.AQuery;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.LatLng;
import com.linkedin.platform.APIHelper;
import com.linkedin.platform.LISession;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class SplashScreen extends AppCompatActivity implements View.OnClickListener {

    AQuery aq;
    CirclePageIndicator circlePageIndicator;
    ViewPager pager;
    ArrayList<IntroItems> arrayList;
    CustomPagerAdapter adapter;
    MyTimer timer;
    int position;
    TinyDB tinyDB;
    public LocationManager locationManager;
    private Location location;
    List<Address> addresses;
    double lon;
    double lat;
    private LoginService obj;
    //    private Timer timers;
    ImageView imageView;
//    private LIOauthService oAuthService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_slpash_screen);
        aq = new AQuery(this);
        obj = new LoginService(this);

        aq.id(R.id.sign_in).clicked(this);
//        aq.id(R.id.matzo_logo).animate(R.anim.top_to_center);
        imageView= (ImageView) findViewById(R.id.matzo_logo);
        Glide.with(SplashScreen.this).load(R.drawable.output).asGif().into(imageView);
        aq.id(R.id.circularIndicator).visibility(View.GONE);
        aq.id(R.id.pager).visibility(View.GONE);
        aq.id(R.id.sign_in).visibility(View.GONE);

        new CountDownTimer(5500, 5500) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {


            }

            @Override
            public void onFinish() {
                aq.id(R.id.matzo_logo).visibility(View.GONE);
                aq.id(R.id.circularIndicator).visibility(View.VISIBLE);
                aq.id(R.id.pager).visibility(View.VISIBLE);
                aq.id(R.id.sign_in).visibility(View.VISIBLE);
            }

        }.start();

//            timers=new Timer(1000,1000);
//        timers.start();
        circlePageIndicator = (CirclePageIndicator) findViewById(R.id.circularIndicator);
        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new CustomPagerAdapter(this);
        tinyDB = new TinyDB(SplashScreen.this);

        Log.e("TAGtinyDB",tinyDB.getString(Constants.LoginAppToken).toString());
        if (!tinyDB.getString(Constants.LoginAppToken).isEmpty()){
            Log.e("TAGtinyDB",tinyDB.getString(Constants.LoginAppToken).toString());
            startActivity(new Intent(SplashScreen.this, MainActivity.class));
        }
        pager.setAdapter(adapter);
        circlePageIndicator.setViewPager(pager);
        timer = new MyTimer(3000, 3000);
        timer.start();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            lat = location.getLatitude();
            lon = location.getLongitude();
            tinyDB.putDouble(Constants.Lat, lat);
            tinyDB.putDouble(Constants.Lon, lon);
            LatLng latLng = new LatLng(lat, lon);
            Log.e("latlongs", latLng + "this");
        } catch (SecurityException e) {
        } catch (Exception e){ }
        Geocoder gcd = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(lat, lon, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        if (addresses.size() > 0) {
        Log.e("LAT", addresses.get(0).getCountryName());
        Log.e("LAT", addresses.get(0).getLocality());
        tinyDB.putString(Constants.CountryName, addresses.get(0).getCountryName());
        tinyDB.putString(Constants.City, addresses.get(0).getLocality());
//        }
        final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);


        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String deviceId = deviceUuid.toString();
        tinyDB.putString(Constants.AndroidId, deviceId);

        Log.e("ANDROIDID1", tinyDB.getString(Constants.AndroidId));
        position=0;
//      setUpdateState();
    }

    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.W_SHARE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in:
                LISessionManager.getInstance(getApplicationContext()).init(SplashScreen.this, buildScope(), new AuthListener() {
                    @Override
                    public void onAuthSuccess() {
                        setUpdateState();
                        if (tinyDB.getString(Constants.FirstName)!=null) {
                            Log.e("TAG", tinyDB.getString(Constants.FirstName));
                            Log.e("TAG", tinyDB.getString(Constants.LastName));
                            Log.e("TAG", tinyDB.getString(Constants.LinkdinId));
                            Log.e("TAG", tinyDB.getString(Constants.City));
                            Log.e("TAG", tinyDB.getString(Constants.CountryName));
                            Log.e("TAG", tinyDB.getString(Constants.Lat));
                            Log.e("TAG", tinyDB.getString(Constants.Lon));
                            Log.e("TAG", tinyDB.getString(Constants.HeadLine));
                            Log.e("TAG", tinyDB.getString(Constants.AndroidId));
                            Log.e("TAG", tinyDB.getString(Constants.Industry));

                            obj.loginLinkdin(tinyDB.getString(Constants.FirstName),
                                    tinyDB.getString(Constants.LastName),
                                    tinyDB.getString(Constants.LinkdinId),
                                    "3",
                                    tinyDB.getString(Constants.City),
                                    tinyDB.getString(Constants.CountryName),
                                    tinyDB.getString(Constants.Lat),
                                    tinyDB.getString(Constants.Lon),
                                    tinyDB.getString(Constants.HeadLine),
                                    "null",
                                    "3",
                                    "18",
                                    "58",
                                    "100",
                                    "null",
                                    "qbid",
                                    "2",
                                    "1",
                                    tinyDB.getString(Constants.LinkdinId),
                                    "null",
                                    tinyDB.getString(Constants.AndroidId),
                                    tinyDB.getString(Constants.Industry),
                                    new CallBack(SplashScreen.this, "loginLinkdin"));
                        }
                    }

                    @Override
                    public void onAuthError(LIAuthError error) {
                        Log.e("TAG", "onAuthError");
                        Log.e("Error", error.toString());
                        // Handle authentication errors
                    }
                }, true);
                break;


        }
    }
    public void loginLinkdin(Object caller, Object model) {
        Log.e("TAGloginLinkdin", "loginLinkdin");
        LoginModel.getInstance().setList((LoginModel) model);
        tinyDB.putString(Constants.LoginAppToken, LoginModel.getInstance().token);
        Log.e("logintoken", LoginModel.getInstance().errMsg);

        if (tinyDB.getString(Constants.LoginAppToken)!=null){
            startActivity(new Intent(SplashScreen.this,MainActivity.class));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("TAG", "onActivityResult");
        LISessionManager.getInstance(getApplicationContext()).onActivityResult(this, requestCode, resultCode, data);


    }

    public class MyTimer extends CountDownTimer {

        public MyTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            if (position == 3) {
                position = 0;
            }
            pager.setCurrentItem(position);
            pager.setAnimationCacheEnabled(true);
            position++;
            timer.start();
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }
    }


    private void setUpdateState() {
        LISessionManager sessionManager = LISessionManager.getInstance(getApplicationContext());
        LISession session = sessionManager.getSession();
        final boolean accessTokenValid = session.isValid();

        Log.e("TAG12", String.valueOf(accessTokenValid));

        if (accessTokenValid==true) {
            Log.e("TAG", "setUpdateState111111111");
            String url = "https://api.linkedin.com/v1/people/~:(id,firstName,lastName,positions,num-connections,headline,pictureUrl,industry)";
            APIHelper.getInstance(this).getRequest(this, url, new ApiListener() {

                @Override
                public void onApiSuccess(ApiResponse apiResponse) {

                    Log.e("accessTokenValid1", "/" + String.valueOf(apiResponse));
                    try {
                        Log.e("name:", "" + apiResponse.getResponseDataAsJson().get("firstName").toString());
                        tinyDB.putString(Constants.FirstName, apiResponse.getResponseDataAsJson().get("firstName").toString());
                        tinyDB.putString(Constants.LastName, apiResponse.getResponseDataAsJson().get("lastName").toString());
                        tinyDB.putString(Constants.HeadLine, apiResponse.getResponseDataAsJson().get("headline").toString());
                        tinyDB.putString(Constants.Photo, apiResponse.getResponseDataAsJson().get("pictureUrl").toString());
                        tinyDB.putString(Constants.LinkdinId, apiResponse.getResponseDataAsJson().get("id").toString());
//                        tinyDB.putString(Constants.Photo,apiResponse.getResponseDataAsJson().get("num-Connection").toString());
                        tinyDB.putString(Constants.Industry, apiResponse.getResponseDataAsJson().get("industry").toString());
                        Log.e("TAG1", tinyDB.getString(Constants.FirstName));
                        Log.e("TAG1", tinyDB.getString(Constants.LastName));
                        Log.e("TAG1", tinyDB.getString(Constants.LinkdinId));
                        Log.e("TAG1", tinyDB.getString(Constants.City));
                        Log.e("TAG1", tinyDB.getString(Constants.CountryName));
                        Log.e("TAG1", tinyDB.getString(Constants.Lat));
                        Log.e("TAG1", tinyDB.getString(Constants.Lon));
                        Log.e("TAG1", tinyDB.getString(Constants.HeadLine));
                        Log.e("TAG1", tinyDB.getString(Constants.AndroidId));
                        Log.e("TAG1", tinyDB.getString(Constants.Industry));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onApiError(LIApiError apiError) {
                    Log.e("accessTokenValid", "/" + apiError.toString());
                }
            });

        }
    }
}