package com.algorepublic.matzomatch.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.algorepublic.matzomatch.R;
import com.algorepublic.matzomatch.SwipeView;
import com.algorepublic.matzomatch.model.SwipModel;
import com.androidquery.AQuery;

import java.util.ArrayList;

/**
 * Created by waqas on 12/7/15.
 */
public class BaseFragment extends Fragment implements SwipeView.OnCardSwipedListener{

    ArrayList<SwipModel> al;
    AQuery aq;
    private int i;
    private FrameLayout contentLayout;
    private SwipeView mSwipeView;
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
        contentLayout = (FrameLayout) view.findViewById(R.id.frame);

        populateData();
        Log.e("list size", al.size() + "");
        mSwipeView = new SwipeView(getActivity(),R.id.imgSwipeLike, R.id.imgSwipeNope, (SwipeView.OnCardSwipedListener) getActivity());
        contentLayout.addView(mSwipeView);
        for (int l =0; l<8 ; l++){
            addCard(l);
        }
        aq.id(R.id.imgDisLike).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSwipeView.dislikeCard();
            }
        });
        aq.id(R.id.imgLike).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSwipeView.likeCard();
            }
        });
         return view;

    }
    private void addCard(int position) {
        Log.e("position",position+"");
        final View cardView = LayoutInflater.from(getActivity()).inflate(
                R.layout.swip_item, null);
        final ImageView imgBike = (ImageView) cardView
                .findViewById(R.id.img_main);
        final TextView title = (TextView) cardView.findViewById(R.id.title);
        final TextView dis = (TextView) cardView.findViewById(R.id.discription);
        title.setText(al.get(i).getTitle());
        dis.setText(al.get(i).getDescription());

        // Add a card to the swipe view.
        mSwipeView.addCard(cardView, position);
    }
    public void populateData(){
        SwipModel swipModel = new SwipModel();
        swipModel.setTitle("c");
        swipModel.setDescription("Description goes here");
        al.add(swipModel);
        swipModel.setTitle("python");
        swipModel.setDescription("Description goes here");
        al.add(swipModel);
        swipModel.setTitle("html");
        swipModel.setDescription("Description goes here");
        al.add(swipModel);
        swipModel.setTitle("java");
        swipModel.setDescription("Description goes here");
        al.add(swipModel);
        swipModel.setTitle("c++");
        swipModel.setDescription("Description goes here");
        al.add(swipModel);
        swipModel.setTitle("css");
        swipModel.setDescription("Description goes here");
        al.add(swipModel);
        swipModel.setTitle("java script");
        swipModel.setDescription("Description goes here");
        al.add(swipModel);
    }

    @Override
    public void onLikes() {

    }

    @Override
    public void onDisLikes() {

    }

    @Override
    public void onSingleTap() {

    }
}
