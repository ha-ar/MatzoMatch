package com.algorepublic.matzomatch.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.algorepublic.matzomatch.R;
import com.androidquery.AQuery;

/**
 * Created by ahmad on 12/2/15.
 */
public class AppSettings extends Fragment {

    View view;
    AQuery aq;

    public static AppSettings newInstence(){
        AppSettings appSettings = new AppSettings();
        return appSettings;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.app_settings,container,false);
        aq = new AQuery(view);
        aq.id(R.id.help).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setType("plain/text");
                sendIntent.setData(Uri.parse("info@matzoball.com"));
                sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "info@matzoball.com" });
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "SettingNewMessageKey");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "SettingBodyKey");
                startActivity(sendIntent);
            }
        });
        aq.id(R.id.privacy).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(), ActivityWebLink.class);
                intent.putExtra("Key","privacy");
                startActivity(intent);
            }
        });
        aq.id(R.id.terms).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(), ActivityWebLink.class);
                intent.putExtra("Key","terms");
                startActivity(intent);
            }
        });
        return view;
    }
}
