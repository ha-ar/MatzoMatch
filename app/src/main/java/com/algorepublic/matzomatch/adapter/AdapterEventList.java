package com.algorepublic.matzomatch.adapter;

import android.content.Context;
import android.os.Handler;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.algorepublic.matzomatch.R;
import com.algorepublic.matzomatch.model.EventModelDetails;
import com.androidquery.AQuery;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ahmad on 12/3/15.
 */
public class AdapterEventList extends BaseAdapter {

    public LayoutInflater inflater;
    public Context ctx;
    AQuery aq;
    public static AQuery aQuery;
    ArrayList<EventModelDetails> arrayList;
    private List<ViewHolder> lstHolders;
    public static long timeStamp;

    public AdapterEventList (Context ctx,ArrayList<EventModelDetails> list){
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
            view = inflater.inflate(R.layout.event_rows,null);
            holder = new ViewHolder();
            holder.address = (TextView) view.findViewById(R.id.event_address);
            holder.day = (TextView) view.findViewById(R.id.days);
            holder.hour = (TextView) view.findViewById(R.id.hours);
            holder.min = (TextView) view.findViewById(R.id.min);
            holder.sec = (TextView) view.findViewById(R.id.sec);
            view.setTag(holder);
        }else
        {
            holder = (ViewHolder) view.getTag();
        }
        aq = new AQuery(view);
        aQuery = new AQuery(view);
        Log.e("address", arrayList.get(position).getNameText());
        holder.address.setText(arrayList.get(position).getNameText());
        aq.id(R.id.event_image).image(arrayList.get(position).getLogoUrl());
        Log.e("time log",arrayList.get(position).getStartUtu()+"");
        startUpdateTimer(holder, arrayList.get(position).getStartUtu());
//        long millisSinceUnixEpoch = new DateTime( "2014-10-23T00:35:14.800Z" ).getMillis();

        return view;
    }

    private void startUpdateTimer(final ViewHolder holder1, String time) {
        convertTime(time);
         final Runnable updateRemainingTimeRunnable = new Runnable() {
            @Override
            public void run() {
//            synchronized (lstHolders) {
                long currentTime = System.currentTimeMillis();
                    holder1.updateTimeRemaining(currentTime);
//            }
            }
        };
        Timer tmr = new Timer();
        tmr.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.post(updateRemainingTimeRunnable);
            }
        }, 1000, 1000);

    }

    public void convertTime(String timeCreated){
//        Log.e("function call", "yes");
        Log.e("date",""+ com.algorepublic.matzomatch.Utils.DateUtils.parseIso8601DateTime(timeCreated)+"");
        timeStamp = com.algorepublic.matzomatch.Utils.DateUtils.parseIso8601DateTime(timeCreated).getTime();
//        Log.e("date",""+ timeStamp+"");
    }

    static class ViewHolder{
        TextView address;
        TextView day,hour,min,sec;
        EventModelDetails eventModelDetails;

        public void setData(EventModelDetails item) {
            eventModelDetails = item;
//            tvProduct.setText(item.name);
            updateTimeRemaining(System.currentTimeMillis());
        }

        public void updateTimeRemaining(long currentTime) {
            long timeDiff = timeStamp - currentTime;
            if (timeDiff > 0) {
                int seconds = (int) (timeDiff / 1000) % 60;
                int minutes = (int) ((timeDiff / (1000 * 60)) % 60);
                int hours = (int) ((timeDiff / (1000 * 60 * 60)) % 24);
                int days = (int) ((timeDiff/(1000*60*60*24))%7);
//                Log.e("vales", seconds+" "+minutes+" "+hours+" "+days+" ");
//                aQuery.id(R.id.days).text(String.valueOf(days));
//                aQuery.id(R.id.hours).text(String.valueOf(hours));
//                aQuery.id(R.id.min).text(String.valueOf(minutes));
//                aQuery.id(R.id.sec).text(String.valueOf(seconds));

                day.setText(String.valueOf(days));
                hour.setText(String.valueOf(hours));
                min.setText(String.valueOf(minutes));
                sec.setText(String.valueOf(seconds));
            } else {
//                tvTimeRemaining.setText("Expired!!");
            }
        }
    }
}
