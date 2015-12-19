package com.algorepublic.matzomatch.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.algorepublic.matzomatch.R;
import com.algorepublic.matzomatch.Services.CallBack;
import com.algorepublic.matzomatch.Services.EventService;
import com.algorepublic.matzomatch.adapter.AdapterEventList;
import com.algorepublic.matzomatch.model.EventModelDetails;
import com.algorepublic.matzomatch.model.EventsModel;
import com.androidquery.AQuery;

import java.util.ArrayList;

/**
 * Created by ahmad on 12/3/15.
 */
public class FragmentEventList extends Fragment {

    View view;
    AQuery aq;
    EventService eventService;
    ArrayList<EventModelDetails> arrayList;
    AdapterEventList adapter;
    public static ListView listView;

    public static FragmentEventList newInstance(){
        FragmentEventList fragmentEventList = new FragmentEventList();
        return fragmentEventList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_eventlist,container,false);
        aq = new AQuery(view);
        arrayList = new ArrayList<>();
        eventService = new EventService(getActivity(),view);
        listView = (ListView) view.findViewById(R.id.event_list);
        eventService.getEvents("HFT37NCNTKBJZRP2I4ZP", true, new CallBack(FragmentEventList.this, "AllEvents"));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int postion, long l) {
                String longitude = arrayList.get(postion).getVenueLongitude();
                String latitude = arrayList.get(postion).getVenueLatitude();
                String location = arrayList.get(postion).getVenueAddress()+","+" "+arrayList.get(postion)
                        .getVenueCity()+","+" "+arrayList.get(postion).getVenueRegion();
                String text = arrayList.get(postion).getNameText();
                String ticketUrl = arrayList.get(postion).getEventUrl();
                String date = String.valueOf(com.algorepublic.matzomatch.Utils.DateUtils
                        .parseIso8601DateTime(arrayList.get(postion).getStartUtu()))+" "+
                String.valueOf(com.algorepublic.matzomatch.Utils.DateUtils
                        .parseIso8601DateTime(arrayList.get(postion).getStartUtu()).getTime());
                getFragmentManager().beginTransaction().replace(R.id.container,FragmentEventDetails.newInstance(
                        location,longitude,latitude,text,ticketUrl,date
                )).commit();
            }
        });

        return view;
    }

    public void AllEvents(Object caller,Object model){
        EventsModel.getInstance().setList((EventsModel) model);
        if (EventsModel.getInstance().events.size() != 0){
            Log.e("Array Size", " " + EventsModel.getInstance().events.size());
            arrayList = getList();
            adapter = new AdapterEventList(getActivity(),arrayList);
            listView.setAdapter(adapter);
        }
    }
    static int inner, outer, smaller;
    public static ArrayList<EventModelDetails> getList(){
        ArrayList<EventModelDetails> result = new ArrayList<>();
        for (outer = 0; outer < EventsModel.getInstance().events.size();outer++){
                    EventModelDetails eventItem = new EventModelDetails();
                    eventItem.setEventId(EventsModel.getInstance().events.get(outer).id);
                    eventItem.setEventUrl(EventsModel.getInstance().events.get(outer).url);
                    eventItem.setNameText(EventsModel.getInstance().events.get(outer).name.text);
                    eventItem.setDescriptionUrl(EventsModel.getInstance().events.get(outer).description.url);
                    eventItem.setLogoUrl(EventsModel.getInstance().events.get(outer).logo.url);
                    eventItem.setStartTimeZone(EventsModel.getInstance().events.get(outer).start.timezone);
                    eventItem.setStartLocal(EventsModel.getInstance().events.get(outer).start.local);
                    eventItem.setStartUtu(EventsModel.getInstance().events.get(outer).start.utc);
                    eventItem.setEndTimeZone(EventsModel.getInstance().events.get(outer).end.timezone);
                    eventItem.setEndLocal(EventsModel.getInstance().events.get(outer).end.local);
                    eventItem.setEndUtu(EventsModel.getInstance().events.get(outer).end.utc);
                    eventItem.setVenueId(EventsModel.getInstance().events.get(outer).venue.id);
                    eventItem.setVenueName(EventsModel.getInstance().events.get(outer).venue.name);
                    eventItem.setVenueAddress(EventsModel.getInstance().events.get(outer).venue.address.address_1);
                    eventItem.setVenueCity(EventsModel.getInstance().events.get(outer).venue.address.city);
                    eventItem.setVenueCountry(EventsModel.getInstance().events.get(outer).venue.address.country);
                    eventItem.setVenueRegion(EventsModel.getInstance().events.get(outer).venue.address.region);
                    eventItem.setVenuePostalCode(EventsModel.getInstance().events.get(outer).venue.address.postal_code);
                    eventItem.setVenueLatitude(EventsModel.getInstance().events.get(outer).venue.latitude);
                    eventItem.setVenueLongitude(EventsModel.getInstance().events.get(outer).venue.longitude);
                    result.add(eventItem);
                }
        return result;
    }
}
