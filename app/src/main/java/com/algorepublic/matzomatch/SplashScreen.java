package com.algorepublic.matzomatch;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.algorepublic.matzomatch.adapter.CustomPagerAdapter;
import com.androidquery.AQuery;
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

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in:
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                break;
        }
    }

    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.W_SHARE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
}
