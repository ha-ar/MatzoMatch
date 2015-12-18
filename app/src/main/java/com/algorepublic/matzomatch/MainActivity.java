package com.algorepublic.matzomatch;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.algorepublic.matzomatch.Utils.TinyDB;
import com.algorepublic.matzomatch.fragments.AppSettings;
import com.algorepublic.matzomatch.fragments.BaseFragment;
import com.algorepublic.matzomatch.fragments.BuyMessages;
import com.algorepublic.matzomatch.fragments.DiscoveryPreferences;
import com.algorepublic.matzomatch.fragments.FragmentEventList;
import com.algorepublic.matzomatch.fragments.FragmentPeopleWhoLikesYou;
import com.algorepublic.matzomatch.fragments.MyProfile;
import com.androidquery.AQuery;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

public class MainActivity extends BaseActivity
        implements SwipeView.OnCardSwipedListener {
    static RelativeLayout toolbar;


    private int i;
    AQuery aq;
    MenuDrawer mDrawerLeft;
    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aq = new AQuery(MainActivity.this);
        tinyDB = new TinyDB(getApplicationContext());
        toolbar = (RelativeLayout) findViewById(R.id.toolbar_actionbar);

        mDrawerLeft = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND, Position.LEFT, MenuDrawer.MENU_DRAG_CONTENT);
        mDrawerLeft.setContentView(R.layout.activity_main);
        mDrawerLeft.setMenuView(R.layout.layout_dropdownmenu);
        mDrawerLeft.setDrawOverlay(true);
        mDrawerLeft.setSlideDrawable(R.drawable.menu);
        mDrawerLeft.setDrawerIndicatorEnabled(true);
        mDrawerLeft.setAllowIndicatorAnimation(true);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, BaseFragment.newInstance())
                    .commit();
        }
        aq.id(R.id.toolbar_title).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLeft.openMenu();
            }
        });

        aq.id(R.id.start_matching).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLeft.closeMenu();
                aq.id(R.id.calander).visibility(View.VISIBLE);
                aq.id(R.id.messages).visibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, BaseFragment.newInstance())
                        .commit();
            }
        });
        aq.id(R.id.my_profile).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLeft.closeMenu();
                aq.id(R.id.calander).visibility(View.GONE);
                aq.id(R.id.messages).visibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, MyProfile.newInstance())
                        .commit();
            }
        });

        aq.id(R.id.app_settings1).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLeft.closeMenu();
                aq.id(R.id.calander).visibility(View.GONE);
                aq.id(R.id.messages).visibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, AppSettings.newInstence()).commit();
            }
        });
        aq.id(R.id.my_discovery_preference).clicked(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                mDrawerLeft.closeMenu();
                aq.id(R.id.calander).visibility(View.GONE);
                aq.id(R.id.messages).visibility(View.GONE);
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.container, DiscoveryPreferences.newInstance())
                        .commit();
            }
        });

        aq.id(R.id.seeWhoLikeYou).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.container, FragmentPeopleWhoLikesYou.newInstance())
                        .commit();
            }
        });


        aq.id(R.id.buy_messages).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLeft.closeMenu();
                aq.id(R.id.calander).visibility(View.GONE);
                aq.id(R.id.messages).visibility(View.GONE);
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.container, BuyMessages.newInstance())
                        .commit();
            }
        });
        aq.id(R.id.calander).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new FragmentEventList()).commit();
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
