package com.algorepublic.matzomatch.adapter;

import android.content.Context;
import android.media.Image;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.algorepublic.matzomatch.R;
import com.algorepublic.matzomatch.model.EventModelDetails;
import com.algorepublic.matzomatch.model.PeopleWhoLikesModelDetails;
import com.androidquery.AQuery;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ahmad on 12/14/15.
 */
public class AdapterPeopleWhoLikesYou extends BaseAdapter {

    public LayoutInflater inflater;
    public Context ctx;
    AQuery aq;
    ArrayList<PeopleWhoLikesModelDetails> arrayList;

    public AdapterPeopleWhoLikesYou (Context ctx,ArrayList<PeopleWhoLikesModelDetails> list){
        this.ctx=ctx;
        arrayList = list;
        inflater = LayoutInflater.from(ctx);
//        startUpdateTimer();
    }
    private Handler mHandler = new Handler();

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            view = inflater.inflate(R.layout.people_who_likes_you_row,null);
            holder = new ViewHolder();
            holder.name = (TextView) view.findViewById(R.id.person_name);
            holder.age = (TextView) view.findViewById(R.id.person_age);
            holder.gender = (TextView) view.findViewById(R.id.person_gender);
            holder.genderImage = (ImageView) view.findViewById(R.id.person_gender_image);
            holder.imageView = (ImageView) view.findViewById(R.id.person_image);
            view.setTag(holder);
        }else
        {
            holder = (ViewHolder) view.getTag();
        }
        aq = new AQuery(view);
        Log.e("address", arrayList.get(position).getFirstName());
        holder.name.setText(arrayList.get(position).getFirstName());
        Picasso.with(ctx).load(arrayList.get(position).getPic()).into(holder.imageView);
        if (Integer.valueOf(arrayList.get(position).getGender()) == 1) {
            holder.gender.setText("Industry");
            Picasso.with(ctx).load(R.drawable.male_icon).into(holder.genderImage);
        }else {
            holder.gender.setText("Industry");
            Picasso.with(ctx).load(R.drawable.female_icon).into(holder.genderImage);
        }
        holder.age.setText(arrayList.get(position).getAge());

        return view;
    }

    static class ViewHolder{
        TextView name, age, gender;
        ImageView imageView, genderImage;
    }
}
