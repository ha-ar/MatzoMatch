package com.algorepublic.matzomatch;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.algorepublic.matzomatch.adapter.CustomPagerAdapter;
import com.androidquery.AQuery;
import com.linkedin.platform.LISession;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.utils.Scope;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

public class SplashScreen extends AppCompatActivity implements View.OnClickListener{

    AQuery aq;
    CirclePageIndicator circlePageIndicator;
    ViewPager pager;
    ArrayList<IntroItems> arrayList;
    CustomPagerAdapter adapter;
    MyTimer timer;
    int position;

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
        pager.setAdapter(adapter);
        circlePageIndicator.setViewPager(pager);
        timer = new MyTimer(3000,3000);
        timer.start();

        position=0;
        setUpdateState();
    }

    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.W_SHARE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in:

//                LISessionManager.getInstance(getApplicationContext()).init(SplashScreen.this, buildScope(), new AuthListener() {
//                    @Override
//                    public void onAuthSuccess() {
////                        Log.e("TAG", "onAuthSuccess");
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
//                        Scope scope =buildScope();
//
//                    }
//
//                    @Override
//                    public void onAuthError(LIAuthError error) {
//                        Log.e("TAG","onAuthError");
//                        Log.e("Error", error.toString());
//                        // Handle authentication errors
//                    }
//                }, true);
//                break;
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


    }
}