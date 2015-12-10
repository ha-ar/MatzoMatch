package com.algorepublic.matzomatch.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
import com.skyfishjy.library.RippleBackground;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by waqas on 12/7/15.
 */
public class BaseFragment extends Fragment implements SwipeView.OnCardSwipedListener{

    ArrayList<SwipModel> al;
    AQuery aq;
    private int count=0,pos=0;
    private FrameLayout contentLayout;
    private SwipeView mSwipeView;
    private RippleBackground rippleBackground;
    public ProfileServices profileServices;
    public ArrayList<MatchModelDetails> arrayList;
    public RelativeLayout relativeLayout;
    public TinyDB tinyDB;

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
        contentLayout = (FrameLayout) view.findViewById(R.id.frame);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.layout_search);
        rippleBackground = (RippleBackground) view.findViewById(R.id.content);
        rippleBackground.startRippleAnimation();
        Log.e("list size", al.size() + "");
        mSwipeView = new SwipeView(getActivity(),R.id.imgSwipeLike, R.id.imgSwipeNope, (SwipeView.OnCardSwipedListener) getActivity());
        contentLayout.addView(mSwipeView);

        aq.id(R.id.imgDisLike).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrayList.size() == 0|| arrayList.isEmpty()){
                    return;
                }
                Log.e("dislike","yes");
                mSwipeView.dislikeCard();
                if (pos == arrayList.size() - 1) {
                    contentLayout.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                    return;
                }
                profileServices.sendLikesDislikes(arrayList.get(count).getFbId(),"2","8E4A3EA7-80C4-4961-90CB-DB96B9FB38D3"
                ,"38453441334541372D383043342D343936312D393043422D44kmY4YZwj7fhcouS61swo4239364239464233384433kmY4YZwj7fhcouS61swo",
                        new CallBack(BaseFragment.this,"Dislike"));
                count++;
                pos++;
                addCard(0);
            }
        });
        aq.id(R.id.imgLike).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrayList.size() == 0 || arrayList.isEmpty()) {
                    return;
                }
                mSwipeView.likeCard();
                if (pos == arrayList.size() - 1) {
                    contentLayout.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                    return;
                }
                profileServices.sendLikesDislikes(arrayList.get(count).getFbId(), "1", "8E4A3EA7-80C4-4961-90CB-DB96B9FB38D3"
                        , "38453441334541372D383043342D343936312D393043422D44kmY4YZwj7fhcouS61swo4239364239464233384433kmY4YZwj7fhcouS61swo",
                        new CallBack(BaseFragment.this, "like"));
                count++;
                pos++;
                addCard(0);
            }
        });
        profileServices.getMatches("38453441334541372D383043342D343936312D393043422D44kmY4YZwj7fhcouS61swo4239364239464233384433kmY4YZwj7fhcouS61swo",
                "8E4A3EA7-80C4-4961-90CB-DB96B9FB38D3",2, new CallBack(BaseFragment.this,"GetMatches"));

        // hit for discovery prefrences
        profileServices.getPreferences("8E4A3EA7-80C4-4961-90CB-DB96B9FB38D3",
                "38453441334541372D383043342D343936312D393043422D44kmY4YZwj7fhcouS61swo4239364239464233384433kmY4YZwj7fhcouS61swo",
                new CallBack(BaseFragment.this,"prefrences"));

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
        if (LikesDislikesModel.getInstance().errMsg.equals("Like sent!")){
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
            arrayList = getData();
            contentLayout.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
            if (arrayList.size() <3){
                for (int l =0; l<arrayList.size() ; l++){
                    addCard(l);
                    pos++;
                }
            } else {
                for (int k =0; k < 3 ; k++){
                    addCard(k);
                    pos++;
                }
            }
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

    private void addCard(int position) {
        Log.e("position",position+"");
        final View cardView = LayoutInflater.from(getActivity()).inflate(
                R.layout.swip_item, null);
        final ImageView img = (ImageView) cardView
                .findViewById(R.id.img_main);
        final TextView dis = (TextView) cardView.findViewById(R.id.discription);
        dis.setText(arrayList
                .get(pos).getFirstName());
        Picasso.with(getActivity()).load(arrayList.get(pos).getPic()).into(img);
//        img.setImageBitmap(getBitmapFromURL(arrayList.get(pos).getPic()));
        // Add a card to the swipe view.
        mSwipeView.addCard(cardView, position);
    }
    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }
//    public void populateData(){
//        SwipModel swipModel = new SwipModel();
//        swipModel.setTitle("c");
//        swipModel.setDescription("Description goes here");
//        al.add(swipModel);
//        swipModel.setTitle("python");
//        swipModel.setDescription("Description goes here");
//        al.add(swipModel);
//        swipModel.setTitle("html");
//        swipModel.setDescription("Description goes here");
//        al.add(swipModel);
//    }

    @Override
    public void onLikes() {
        if (arrayList.size() == 0|| arrayList.isEmpty()){
            return;
        }
        mSwipeView.likeCard();
        if (pos == arrayList.size()-1){
            contentLayout.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
            return;
        }
        profileServices.sendLikesDislikes(arrayList.get(count).getFbId(),"1","8E4A3EA7-80C4-4961-90CB-DB96B9FB38D3"
                ,"38453441334541372D383043342D343936312D393043422D44kmY4YZwj7fhcouS61swo4239364239464233384433kmY4YZwj7fhcouS61swo",
                new CallBack(BaseFragment.this,"like"));
        count++;
        pos++;
        addCard(0);
    }

    @Override
    public void onDisLikes() {
        if (arrayList.size() == 0|| arrayList.isEmpty()){
            return;
        }
        Log.e("dislike","yes");
        mSwipeView.dislikeCard();
        if (pos == arrayList.size() - 1) {
            contentLayout.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
            return;
        }
        profileServices.sendLikesDislikes(arrayList.get(count).getFbId(),"2","8E4A3EA7-80C4-4961-90CB-DB96B9FB38D3"
                ,"38453441334541372D383043342D343936312D393043422D44kmY4YZwj7fhcouS61swo4239364239464233384433kmY4YZwj7fhcouS61swo",
                new CallBack(BaseFragment.this,"Dislike"));
        count++;
        pos++;
        addCard(0);
    }

    @Override
    public void onSingleTap() {

    }
}
