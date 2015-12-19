package com.algorepublic.matzomatch.Services;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.algorepublic.matzomatch.Utils.Constants;
import com.algorepublic.matzomatch.model.EventsModel;

/**
 * Created by ahmad on 12/3/15.
 */
public class EventService extends BaseService {
    public EventService(Context ctx, View view) {
        super((Activity) ctx, view);
    }

    public void getEvents(String token,boolean message,CallBack obj){
        String url = Constants.BASE_URL_EVENTS+"?token="+token+"&status=live&order_by=start_asc&expand=venue";
        Log.e("YRL EVENT LIST ", url);
        this.get(url,obj, EventsModel.getInstance(),message);
    }
}
