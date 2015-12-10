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
import android.util.Log;
import android.view.View;

import com.algorepublic.matzomatch.Utils.Constants;
import com.algorepublic.matzomatch.Utils.TinyDB;
import com.algorepublic.matzomatch.adapter.CustomPagerAdapter;
import com.androidquery.AQuery;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SplashScreen extends AppCompatActivity implements View.OnClickListener{

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
//    private LIOauthService oAuthService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slpash_screen);
        aq = new AQuery(this);
        aq.id(R.id.sign_in).clicked(this);
        aq.id(R.id.matzo_logo).animate(R.anim.top_to_center);
        circlePageIndicator = (CirclePageIndicator) findViewById(R.id.circularIndicator);
        pager = (ViewPager)findViewById(R.id.pager);
        adapter = new CustomPagerAdapter(this);
        tinyDB = new TinyDB(SplashScreen.this);
        pager.setAdapter(adapter);
        circlePageIndicator.setViewPager(pager);
        timer = new MyTimer(3000,3000);
        timer.start();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try{
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            lat = location.getLatitude();
            lon = location.getLongitude();
            tinyDB.putDouble(Constants.Lat,lat);
            tinyDB.putDouble(Constants.Lon,lon);
            LatLng latLng = new LatLng(lat, lon);
            Log.e("latlongs", latLng + "this");
        } catch (SecurityException e){

        }
        Geocoder gcd = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(lat, lon, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses.size() > 0)
        {
            Log.e("LAT",addresses.get(0).getCountryName());
            Log.e("LAT",addresses.get(0).getLocality());
            tinyDB.putString(Constants.CountryName, addresses.get(0).getCountryName());
            tinyDB.putString(Constants.City,addresses.get(0).getLocality());
        }
        position=0;
        setUpdateState();
    }

    private static Scope buildScope() {
        return Scope.build(Scope.R_FULLPROFILE, Scope.W_SHARE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in:

                LISessionManager.getInstance(getApplicationContext()).init(SplashScreen.this, buildScope(), new AuthListener() {
                    @Override
                    public void onAuthSuccess() {
                        startActivity(new Intent(SplashScreen.this,MainActivity.class));
                    }


                    //
                    @Override
                    public void onAuthError(LIAuthError error) {
                        Log.e("TAG","onAuthError");
                        Log.e("Error", error.toString());
                        // Handle authentication errors
                    }
                }, true);
                break;


        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("TAG", "onActivityResult");
        LISessionManager.getInstance(getApplicationContext()).onActivityResult(this, requestCode, resultCode, data);


    }

    public class MyTimer extends CountDownTimer
    {

        public MyTimer(long startTime, long interval)
        {
            super(startTime, interval);
        }
        @Override
        public void onFinish()
        {
            if(position==3)
            {
                position=0;
            }
            pager.setCurrentItem(position);
            pager.setAnimationCacheEnabled(true);
            position++;
            timer.start();
        }
        @Override
        public void onTick(long millisUntilFinished)
        {
        }
    }


    private void setUpdateState() {
        LISessionManager sessionManager = LISessionManager.getInstance(getApplicationContext());
        LISession session = sessionManager.getSession();
        boolean accessTokenValid = session.isValid();
        session.getAccessToken();

        Log.e("accessTokenValid", session.getAccessToken().toString());

        if (accessTokenValid) {
//            String url = Constants.personByIdBaseUrl + "P0B0FP6TSt" + Constants.personProjection;

            String url = "https://api.linkedin.com/v1/people/~:(id,first-name,last-name,headline,pictureUrl)";
            APIHelper.getInstance(this).getRequest(this, url, new ApiListener() {

                @Override
                public void onApiSuccess(ApiResponse apiResponse) {

                    Log.e("accessTokenValid1", "/" + apiResponse.getResponseDataAsJson());
                    try {
                        Log.e("name:", "" + apiResponse.getResponseDataAsJson().get("firstName").toString());
                        tinyDB.putString(Constants.FirstName, apiResponse.getResponseDataAsJson().get("firstName").toString());
                        tinyDB.putString(Constants.LastName,apiResponse.getResponseDataAsJson().get("lastName").toString());
                        tinyDB.putString(Constants.HeadLine,apiResponse.getResponseDataAsJson().get("headline").toString());
                        tinyDB.putString(Constants.Photo,apiResponse.getResponseDataAsJson().get("pictureUrl").toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    try {
                        JSONObject s = apiResponse.getResponseDataAsJson();
                        String headline = s.has("headline") ? s.getString("headline") : "";
                        String pictureUrl = s.has("pictureUrl") ? s.getString("pictureUrl") : null;
                        JSONObject location = s.getJSONObject("location");
                        String locationName = location != null && location.has("name") ? location.getString("name") : "";

                        Log.e("accessTokenValid1111111", locationName + "/" + headline +"/ "+ pictureUrl);
                        if (pictureUrl != null) {
                            //new FetchImageTask(attendeeImageView).execute(pictureUrl);
                        } else {
                            //attendeeImageView.setImageResource(R.drawable.ghost_person);
                        }
                    } catch (JSONException e) {

                    }

                }

                @Override
                public void onApiError(LIApiError apiError) {
                    Log.e("accessTokenValid",  "/"+apiError.toString() );
                }
            });

        }
    }
}