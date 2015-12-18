package com.algorepublic.matzomatch.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.algorepublic.matzomatch.R;
import com.androidquery.AQuery;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by ahmad on 12/11/15.
 */
public class FragmentMap extends Fragment {

    View view;
    static String latitude,longitude ;
    static  LatLng latLng ;
     GoogleMap map;
    SupportMapFragment fm;
    MarkerOptions options;
    AQuery aq;
    FragmentManager fragmentManager;

    public static FragmentMap newInstance(String lat,String lon){
        FragmentMap fragmentMap = new FragmentMap();
        latitude = lat;
        longitude = lon;
        latLng = new LatLng(Double.valueOf(latitude) , Double.valueOf(longitude));
        return fragmentMap;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map,container,false);
        aq = new AQuery(view);
        fragmentManager = getChildFragmentManager();
        final SupportMapFragment supportMapFragment =
                (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);
        map = supportMapFragment.getMap();
        options = new MarkerOptions();
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        options.position(latLng);
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        map.addMarker(options);

        aq.id(R.id.layout_arrow).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        return view;
    }
}
