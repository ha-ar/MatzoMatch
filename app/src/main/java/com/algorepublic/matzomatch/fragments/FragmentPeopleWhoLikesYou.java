package com.algorepublic.matzomatch.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.algorepublic.matzomatch.R;
import com.algorepublic.matzomatch.Services.CallBack;
import com.algorepublic.matzomatch.Services.ProfileServices;
import com.algorepublic.matzomatch.adapter.AdapterPeopleWhoLikesYou;
import com.algorepublic.matzomatch.model.EventModelDetails;
import com.algorepublic.matzomatch.model.EventsModel;
import com.algorepublic.matzomatch.model.PeopleWhoLikesModelDetails;
import com.algorepublic.matzomatch.model.PeopleWhoLikesYouModel;
import com.androidquery.AQuery;

import java.util.ArrayList;

/**
 * Created by ahmad on 12/11/15.
 */
public class FragmentPeopleWhoLikesYou extends Fragment {

    View view;
    AQuery aq;
    ArrayList<PeopleWhoLikesModelDetails> arrayList;
    AdapterPeopleWhoLikesYou adapter;
    public static ListView listView;
    ProfileServices profileServices;

    public static FragmentPeopleWhoLikesYou newInstance(){
        FragmentPeopleWhoLikesYou fragmentPeopleWhoLikesYou = new FragmentPeopleWhoLikesYou();
        return fragmentPeopleWhoLikesYou;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_people_who_likes_you,container,false);
        aq = new AQuery(view);
        listView = (ListView) view.findViewById(R.id.people_list);
        profileServices = new ProfileServices(getActivity(),view);
        profileServices.getPeopleWhoLikesYou("38453441334541372D383043342D343936312D393043422D44kmY4YZwj7fhcouS61swo4239364239464233384433kmY4YZwj7fhcouS61swo"
        ,"8E4A3EA7-80C4-4961-90CB-DB96B9FB38D3",1,new CallBack(FragmentPeopleWhoLikesYou.this,"AllPeople"));
        return view;
    }
    public void AllPeople(Object caller,Object model){
        PeopleWhoLikesYouModel.getInstance().setList((PeopleWhoLikesYouModel) model);
        if (PeopleWhoLikesYouModel.getInstance().errMsg.equals("Matches Found!")){
            Log.e("Array Size", " " + PeopleWhoLikesYouModel.getInstance().matches.size());
            arrayList = getList();
            adapter = new AdapterPeopleWhoLikesYou(getActivity(),arrayList);
            listView.setAdapter(adapter);
        }
    }
    static int inner, outer, smaller;
    public static ArrayList<PeopleWhoLikesModelDetails> getList(){
        ArrayList<PeopleWhoLikesModelDetails> result = new ArrayList<>();
        for (outer = 0; outer < PeopleWhoLikesYouModel.getInstance().matches.size();outer++){
            PeopleWhoLikesModelDetails eventItem = new PeopleWhoLikesModelDetails();
            eventItem.setFirstName(PeopleWhoLikesYouModel.getInstance().matches.get(outer).firstName);
            eventItem.setAge(PeopleWhoLikesYouModel.getInstance().matches.get(outer).age);
            eventItem.setFbId(PeopleWhoLikesYouModel.getInstance().matches.get(outer).fbId);
            eventItem.setGender(PeopleWhoLikesYouModel.getInstance().matches.get(outer).sex);
            eventItem.setPic(PeopleWhoLikesYouModel.getInstance().matches.get(outer).pPic);
            eventItem.setlDate(PeopleWhoLikesYouModel.getInstance().matches.get(outer).ldat);
            eventItem.setSharedLikes(PeopleWhoLikesYouModel.getInstance().matches.get(outer).sharedLikes);
            eventItem.setImgCnt(PeopleWhoLikesYouModel.getInstance().matches.get(outer).imgCnt);
            result.add(eventItem);
        }
        return result;
    }
}
