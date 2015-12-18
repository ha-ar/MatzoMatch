package com.algorepublic.matzomatch.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.algorepublic.matzomatch.R;
import com.androidquery.AQuery;

/**
 * Created by ahmad on 12/7/15.
 */
public class FragmentEventDetails extends Fragment {

    View view;
    static String location,longitude,latitude,text,ticketUrl, date;
    AQuery aq;

    public static FragmentEventDetails newInstance(String loc,String logi,String lati,String txt,String tktUrl, String Dte){
        FragmentEventDetails fragmentEventDetails = new FragmentEventDetails();
        location = loc;
        longitude = logi;
        latitude = lati;
        text = txt;
        ticketUrl = tktUrl;
        date = Dte;
       Log.e("TAG",location);
        Log.e("TAG",longitude);
        Log.e("TAG",longitude);
        Log.e("TAG",text);
        Log.e("TAG",ticketUrl);
        Log.e("TAG",date);
        return fragmentEventDetails;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.event_info,container,false);
        aq = new AQuery(view);
        aq.id(R.id.title).text(text);
        aq.id(R.id.address).text(location);
        aq.id(R.id.date).text(date);
        aq.id(R.id.buy_ticket).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityWebLink.class);
                intent.putExtra("Key", ticketUrl);
                startActivity(intent);
            }
        });

        aq.id(R.id.layout_address).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.container,FragmentMap.newInstance(latitude,longitude)).commit();
            }
        });

        return view;
    }
}
