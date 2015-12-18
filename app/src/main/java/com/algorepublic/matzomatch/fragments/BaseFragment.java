package com.algorepublic.matzomatch.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.algorepublic.matzomatch.R;
import com.algorepublic.matzomatch.Services.CallBack;
import com.algorepublic.matzomatch.Services.ProfileServices;
import com.algorepublic.matzomatch.SwipeView;
import com.algorepublic.matzomatch.Utils.Constants;
import com.algorepublic.matzomatch.Utils.TinyDB;
import com.algorepublic.matzomatch.model.LikesDislikesModel;
import com.algorepublic.matzomatch.model.MatchModel;
import com.algorepublic.matzomatch.model.ModelPreferences;
import com.algorepublic.matzomatch.model.SwipModel;
import com.androidquery.AQuery;
import com.andtinder.model.CardModel;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;
import com.skyfishjy.library.RippleBackground;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by waqas on 12/7/15.
 */
public class BaseFragment extends Fragment {

    ArrayList<SwipModel> al;
    AQuery aq;
    static final int ACTION_PICK = 3;
    private int pos,count;
    private RippleBackground rippleBackground;
    public ProfileServices profileServices;
    public ArrayList<MatchModelDetails> arrayList;
    public RelativeLayout relativeLayout;
    public LinearLayout linearLayout;
    public TinyDB tinyDB;
    public TextView textView, searchTextView;
// swipe card listners
    public static CardModel.OnCardDismissedListener listener;
    private CardContainer mCardContainer;
    SimpleCardStackAdapter adapter;
    int counter = 1;

    public static BaseFragment newInstance() {
        BaseFragment fragment = new BaseFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        aq = new AQuery(getActivity(),view);
        al = new ArrayList<SwipModel>();
        arrayList = new ArrayList<>();
        tinyDB = new TinyDB(getActivity());
        profileServices = new ProfileServices(getActivity(),view);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.layout_search);
        rippleBackground = (RippleBackground) view.findViewById(R.id.content);
        linearLayout = (LinearLayout) view.findViewById(R.id.buttons);
        linearLayout.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#BB000000")));
        textView = (TextView) view.findViewById(R.id.textView_serach);
        textView.setVisibility(View.VISIBLE);
        rippleBackground.startRippleAnimation();
        Log.e("list size", al.size() + "");
// Swipe Card Initialization

        mCardContainer = (CardContainer) view.findViewById(R.id.frame);
        adapter = new SimpleCardStackAdapter(getActivity());

        aq.id(R.id.shareHome).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] options = {"Sms", "Email", "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose Action!");
                builder.setItems(options, new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Sms")) {
                            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                            sendIntent.setData(Uri.parse("sms:"));
                            startActivityForResult(sendIntent, 2);
                            sendIntent.putExtra("sms_body", "");
                        } else if (options[item].equals("Email"))

                        {
                            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                            sendIntent.setType("plain/text");
                            sendIntent.setData(Uri.parse("info@matzoball.com"));
                            sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                            sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@matzoball.com"});
                            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Report for issues !");
                            sendIntent.putExtra(Intent.EXTRA_TEXT, "-----\nModel : Android\nOS Versoin : 5.0.1");
                            startActivity(sendIntent);
                        } else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }

                });
                builder.show();
            }

        });
        profileServices.getMatches("38453441334541372D383043342D343936312D393043422D44kmY4YZwj7fhcouS61swo4239364239464233384433kmY4YZwj7fhcouS61swo",
                "8E4A3EA7-80C4-4961-90CB-DB96B9FB38D3", 2, new CallBack(BaseFragment.this, "GetMatches"));
        // hit for discovery prefrences
        profileServices.getPreferences("8E4A3EA7-80C4-4961-90CB-DB96B9FB38D3",
                "38453441334541372D383043342D343936312D393043422D44kmY4YZwj7fhcouS61swo4239364239464233384433kmY4YZwj7fhcouS61swo",
                new CallBack(BaseFragment.this,"prefrences"));

        aq.id(R.id.imgLike).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Childrens",  "like");
                mCardContainer.like();

            }
        });
        aq.id(R.id.imgDisLike).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Childrens",  "dislike");
                mCardContainer.dislike();
            }
        });
        listener = new CardModel.OnCardDismissedListener() {
            @Override
            public void onLike() {
                Log.e("Swipeable Cards 2", "I like the card");
                Log.e("Childrens", adapter.getCount() - counter + "");
                int check = adapter.getCount()-counter;
                // like hit
//                profileServices.sendLikesDislikes(arrayList.get(check).getFbId(),"1","8E4A3EA7-80C4-4961-90CB-DB96B9FB38D3"
//                ,"38453441334541372D383043342D343936312D393043422D44kmY4YZwj7fhcouS61swo4239364239464233384433kmY4YZwj7fhcouS61swo",
//                new CallBack(BaseFragment.this,"like"));
                counter ++;
                if (check == 0){
                    Log.e("visibilty","gone");
                    linearLayout.setVisibility(View.GONE);
                    mCardContainer.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onDislike() {
                Log.e("Swipeable Cards","I dislike the card");
                Log.e("Childrens", adapter.getCount() - counter + "");
                int check = adapter.getCount()-counter;
                // dislike hit
//                profileServices.sendLikesDislikes(arrayList.get(check).getFbId(),"2","8E4A3EA7-80C4-4961-90CB-DB96B9FB38D3"
//                ,"38453441334541372D383043342D343936312D393043422D44kmY4YZwj7fhcouS61swo4239364239464233384433kmY4YZwj7fhcouS61swo",
//                new CallBack(BaseFragment.this,"Dislike"));
                        counter ++;
                if (check == 0){
                    Log.e("visibilty","gone");
                    linearLayout.setVisibility(View.GONE);
                    mCardContainer.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                }
            }
        };

         return view;
    }
    public void prefrences(Object caller,Object model){
        ModelPreferences.getObj().setList((ModelPreferences) model);
        if (ModelPreferences.getObj().errMsg.equals("Got the settings!")) {
            populatePrefrences();
            Log.e("data","populated");
        }else{
            Log.e("Something bad Happened","prefrences");
        }
    }

    public void populatePrefrences(){
        tinyDB.putString(Constants.UPPER_AGE,ModelPreferences.getObj().prUAge);
        tinyDB.putString(Constants.LOWER_AGE,ModelPreferences.getObj().prLAge);
        tinyDB.putString(Constants.DISTANCE,ModelPreferences.getObj().prRad);
        tinyDB.putString(Constants.USER_GENDER,ModelPreferences.getObj().sex);
        tinyDB.putString(Constants.PREF_GENDER,ModelPreferences.getObj().prSex);
    }

    public void like(Object caller,Object model){
        LikesDislikesModel.getInstance().setList((LikesDislikesModel) model);
//        Like sent!
        if (LikesDislikesModel.getInstance().errMsg.equals("Congrats! You got a match")){
            Log.e("Likes","send");
        } else {
            Log.e("something went wrong","");
        }
    }

    public void Dislike(Object caller,Object model){
        LikesDislikesModel.getInstance().setList((LikesDislikesModel)model);
        Log.e("dislike", "yes");
        if (LikesDislikesModel.getInstance().errMsg.equals("Like sent!")){
            Log.e("Likes","send");
        } else {
            Log.e("something went wrong","");
        }
    }

    public void GetMatches(Object caller,Object model){
        MatchModel.getInstaance().setList((MatchModel) model);
        if (MatchModel.getInstaance().matches.size() != 0){
            arrayList.clear();arrayList = getData();
            mCardContainer.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
            CardModel cardModel;
            for(int loop=0;loop<arrayList.size();loop++)
            {
                String gander;
                if (arrayList.get(loop).getSex().equals("1")){
                    gander = "Man";
                }else {
                    gander = "Woman";
                }
                cardModel = new CardModel(arrayList.get(loop).getFirstName(),
                        gander, arrayList.get(loop).getPic());
                Log.e("pic path",arrayList.get(loop).getPic());
                cardModel.setOnCardDismissedListener(listener);
                adapter.add(cardModel);
            }
            mCardContainer.setAdapter(adapter);
        }else {
            Log.e("Error","Something wrong happened");
        }
    }
    static int var;
    public static ArrayList<MatchModelDetails> getData(){
        ArrayList<MatchModelDetails> result = new ArrayList<>();
        for (var = 0;var < MatchModel.getInstaance().matches.size(); var++){
            MatchModelDetails item = new MatchModelDetails();
            item.setFirstName(MatchModel.getInstaance().matches.get(var).firstName);
            item.setFbId(MatchModel.getInstaance().matches.get(var).fbId);
            item.setAge(MatchModel.getInstaance().matches.get(var).age);
            item.setSex(MatchModel.getInstaance().matches.get(var).sex);
            item.setlDte(MatchModel.getInstaance().matches.get(var).ldat);
            item.setPic(MatchModel.getInstaance().matches.get(var).pPic);
            item.setSharedLkes(MatchModel.getInstaance().matches.get(var).sharedLikes);
            item.setImgCnt(MatchModel.getInstaance().matches.get(var).imgCnt);
            result.add(item);
        }
        return result;
    }

        // Onlike
//        profileServices.sendLikesDislikes(arrayList.get(count).getFbId(),"1","8E4A3EA7-80C4-4961-90CB-DB96B9FB38D3"
//                ,"38453441334541372D383043342D343936312D393043422D44kmY4YZwj7fhcouS61swo4239364239464233384433kmY4YZwj7fhcouS61swo",
//                new CallBack(BaseFragment.this,"like"));

    //OnDislike
//        profileServices.sendLikesDislikes(arrayList.get(count).getFbId(),"2","8E4A3EA7-80C4-4961-90CB-DB96B9FB38D3"
//                ,"38453441334541372D383043342D343936312D393043422D44kmY4YZwj7fhcouS61swo4239364239464233384433kmY4YZwj7fhcouS61swo",
//                new CallBack(BaseFragment.this,"Dislike"));

}
