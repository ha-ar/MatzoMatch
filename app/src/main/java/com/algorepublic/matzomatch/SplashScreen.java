package com.algorepublic.matzomatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.androidquery.AQuery;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.utils.Scope;

public class SplashScreen extends AppCompatActivity implements View.OnClickListener{

    AQuery aq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slpash_screen);
        aq = new AQuery(this);
        aq.id(R.id.sign_in).clicked(this);
        aq.id(R.id.matzo_logo).animate(R.anim.top_to_center);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in:
                Log.e("linked", "in");
//                LISessionManager.getInstance(getApplicationContext()).init(SplashScreen.this, buildScope(), new AuthListener() {
//                    @Override
//                    public void onAuthSuccess() {
//                        // Authentication was successful.  You can now do
//                        // other calls with the SDK.
//                    }
//
//                    @Override
//                    public void onAuthError(LIAuthError error) {
//                        // Handle authentication errors
//                    }
//                }, true);
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                break;
        }
    }

    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.W_SHARE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Add this line to your existing onActivityResult() method
        LISessionManager.getInstance(getApplicationContext()).onActivityResult(this, requestCode, resultCode, data);
    }
}
