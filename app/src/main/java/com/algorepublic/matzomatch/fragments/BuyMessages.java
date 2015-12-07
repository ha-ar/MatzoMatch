package com.algorepublic.matzomatch.fragments;

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
public class BuyMessages extends Fragment {

    View view;
    AQuery aq;
    public static BuyMessages newInstance() {
        return new BuyMessages();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.buy_messages,container,false);

        return view;
    }
}
