package com.algorepublic.matzomatch.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.algorepublic.matzomatch.R;
import com.algorepublic.matzomatch.Services.CallBack;
import com.algorepublic.matzomatch.Services.EventService;
import com.algorepublic.matzomatch.model.EventsModel;
import com.androidquery.AQuery;

import java.io.ObjectInput;

/**
 * Created by ahmad on 12/3/15.
 */
public class FragmentEventList extends Fragment {

    View view;
    AQuery aq;
    EventService eventService;
    Arra

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_eventlist,container,false);
        aq = new AQuery(view);
        eventService = new EventService(getActivity(),view);
        eventService.getEvents("HFT37NCNTKBJZRP2I4ZP", true, new CallBack(FragmentEventList.this, "AllEvents"));
        return view;
    }
    public void AllEvents(Object caller,Object model){
        EventsModel.getInstance().setList((EventsModel) model);
        if (EventsModel.getInstance().events.size() != 0){
            Log.e("Array Size", " " + EventsModel.getInstance().events.size());
        }
    }
}
