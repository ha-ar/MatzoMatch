package com.algorepublic.matzomatch.Services;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.algorepublic.matzomatch.Utils.Constants;
import com.algorepublic.matzomatch.model.LoginModel;

import java.util.HashMap;

/**
 * Created by waqas on 12/11/15.
 */
public class LoginService extends BaseService {
    public LoginService(Context ctx) {
        super((Activity)ctx);
    }



    public void loginLinkdin (String firstName,String lastName,String email,String sex,String city,String country,String lat,String lon,String tagline,String persDecs,String prefSex,
                     String lowerAge,String uperAge,String radius,String likes
            ,String qbid,String deviceType,String authType,String fbid,
                      String pushToken,String devId,String industry,CallBack obj){
        String Url = Constants.BASE_URL+"login";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("ent_first_name",firstName);
        params.put("ent_last_name",lastName);
        params.put("ent_email",email);
        params.put("ent_sex",sex);
        params.put("ent_city",city);
        params.put("ent_country",country);
        params.put("ent_curr_lat",lat);
        params.put("ent_curr_long",lon);
        params.put("ent_tag_line",tagline);
        params.put("ent_pers_desc",persDecs);
        params.put("ent_pref_sex",prefSex);
        params.put("ent_pref_lower_age",lowerAge);
        params.put("ent_pref_upper_age",uperAge);
        params.put("ent_pref_radius",radius);
        params.put("ent_likes",likes);
        params.put("ent_qbid",qbid);
        params.put("ent_device_type",deviceType);
        params.put("ent_auth_type",authType);
        params.put("ent_fbid",fbid);
        params.put("ent_push_token",pushToken);
        params.put("ent_dev_id",devId);
        params.put("ent_industry", industry);



        this.post(Url, params, obj, LoginModel.getInstance(), false);

        Log.e("Url", Url);
    }
}
