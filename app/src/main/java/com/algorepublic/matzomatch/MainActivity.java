package com.algorepublic.matzomatch;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.algorepublic.matzomatch.model.SwipModel;
import com.androidquery.AQuery;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity
        implements SwipeView.OnCardSwipedListener {


    ArrayList<SwipModel> al;
    private FrameLayout contentLayout;
    private SwipeView mSwipeView;
    private int i;
    AQuery aq;
    MenuDrawer mDrawerLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDrawerLeft = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND, Position.LEFT, MenuDrawer.MENU_DRAG_CONTENT);
        mDrawerLeft.setContentView(R.layout.activity_main);
        mDrawerLeft.setMenuView(R.layout.layout_dropdownmenu);
        mDrawerLeft.setDrawOverlay(true);
        mDrawerLeft.setSlideDrawable(R.drawable.menu);
        mDrawerLeft.setDrawerIndicatorEnabled(true);
        mDrawerLeft.setAllowIndicatorAnimation(true);


        al = new ArrayList<SwipModel>();
        aq = new AQuery(MainActivity.this);
        contentLayout = (FrameLayout) findViewById(R.id.frame);

        populateData();
        Log.e("list size", al.size() + "");
        mSwipeView = new SwipeView(this,R.id.imgSwipeLike, R.id.imgSwipeNope,this);
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

    }

    @Override
    public void onLikes() {
//        mSwipeView.removeFocusedCard();
    }

    @Override
    public void onDisLikes() {
//        mSwipeView.removeFocusedCard();
    }

    @Override
    public void onSingleTap() {

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

    private void addCard(int position) {
        Log.e("position",position+"");
        final View cardView = LayoutInflater.from(this).inflate(
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

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {

            mDrawerLeft.toggleMenu();

        }
        return super.onOptionsItemSelected(item);
    }
}
