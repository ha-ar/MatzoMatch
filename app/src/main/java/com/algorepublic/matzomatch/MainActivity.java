package com.algorepublic.matzomatch;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.algorepublic.matzomatch.fragments.AppSettings;
import com.algorepublic.matzomatch.fragments.DiscoveryPreferences;
import com.algorepublic.matzomatch.fragments.FragmentEventList;
import com.algorepublic.matzomatch.model.SwipModel;
import com.androidquery.AQuery;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

import java.util.ArrayList;

public class MainActivity extends BaseActivity
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
        aq = new AQuery(MainActivity.this);
        mDrawerLeft = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND, Position.LEFT, MenuDrawer.MENU_DRAG_CONTENT);
        mDrawerLeft.setContentView(R.layout.activity_main);
        mDrawerLeft.setMenuView(R.layout.layout_dropdownmenu);
        mDrawerLeft.setDrawOverlay(true);
        mDrawerLeft.setSlideDrawable(R.drawable.menu);
        mDrawerLeft.setDrawerIndicatorEnabled(true);
        mDrawerLeft.setAllowIndicatorAnimation(true);

        aq.id(R.id.layout_profile).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDailog();
            }
        });
        aq.id(R.id.layout_explorar).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "http://www.3embed.com");
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out this site!");
                startActivity(Intent.createChooser(intent, "Share"));
            }
        });
        aq.id(R.id.layout_list).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().add(R.id.container,new DiscoveryPreferences()).commit();
            }
        });
        aq.id(R.id.layout_external).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new AppSettings()).commit();
            }
        });
        al = new ArrayList<SwipModel>();
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

        aq.id(R.id.button).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().add(R.id.container,new FragmentEventList()).commit();
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
    public void showDailog() {
        // custom dialog
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_popup);
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView report = (TextView) dialog.findViewById(R.id.report_issue);
        TextView suggestions = (TextView) dialog.findViewById(R.id.make_suggestion);
        TextView partner = (TextView) dialog.findViewById(R.id.partner_us);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setType("plain/text");
                sendIntent.setData(Uri.parse("info@matzoball.com"));
                sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@matzoball.com"});
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Report for issues !");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "-----\nModel : Android\nOS Versoin : 5.0.1");
                startActivity(sendIntent);
            }
        });
        suggestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setType("plain/text");
                sendIntent.setData(Uri.parse("info@matzoball.com"));
                sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "info@matzoball.com" });
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Make a suggestion");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "-----\n" +
                        "Model : Android\n" +
                        "OS Versoin : 5.0.1");
                startActivity(sendIntent);
            }
        });
        partner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setType("plain/text");
                sendIntent.setData(Uri.parse("info@matzoball.com"));
                sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "info@matzoball.com" });
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Partner with us");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "-----\n" +
                        "Model : Android\n" +
                        "OS Versoin : 5.0.1");
                startActivity(sendIntent);
            }
        });
        Button close = (Button) dialog.findViewById(R.id.cancel);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
//                 Crouton.makeText(getActivity(), "Book is saved locally", Style.ALERT).show();
            }
        });
    }
}
