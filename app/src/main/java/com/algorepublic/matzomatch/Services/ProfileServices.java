package com.algorepublic.matzomatch.Services;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.algorepublic.matzomatch.Utils.Constants;
import com.algorepublic.matzomatch.model.LikesDislikesModel;
import com.algorepublic.matzomatch.model.MatchModel;

import java.util.HashMap;

/**
 * Created by ahmad on 12/8/15.
 */
public class ProfileServices extends BaseService {

    public ProfileServices(Context ctx, View view){
        super((Activity)ctx,view);
    }
    public void sendLikesDislikes(String fbid,String action,String devId,String token,CallBack obj){
        String Url = Constants.BASE_URL_LIKE_DISLIKES;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("ent_invitee_fbid",fbid);
        params.put("ent_user_action",action);
        params.put("ent_dev_id",devId);
        params.put("ent_sess_token",token);
        Log.e("Url", Url);
        this.post(Url, params, obj, LikesDislikesModel.getInstance(), true);
    }

    public void getMatches(String token,String deviceId,int recentLIkes,CallBack obj){
        String Url = Constants.BASE_URL_PROFILES;
        HashMap<String, String> params = new HashMap<>();
        params.put("ent_sess_token", token);
        params.put("ent_dev_id",deviceId);
        params.put("ent_Rec_like", String.valueOf(recentLIkes));
        Log.e("Url", Url);
        this.post(Url,params,obj, MatchModel.getInstaance(),true);
    }

    public void getPreferences(CallBack obj){
        String Url = Constants.BASE_URL+"getPreferences";
        HashMap<String, String> params = new HashMap<>();
        params.put("ent_dev_id","");
        params.put("ent_sess_token","");
        Log.e("Url", Url);
        this.post(Url, params, obj,ModelPreferences.getObj(),true);
    }

    public void updatePreferences(String deviceID,String token,String userGender,String lowerAge,String prefGender,
                                  String upperAge,String distance,String distanceType,CallBack obj){
        String Url = Constants.BASE_URL+"updatePreferences";
        HashMap<String, String> params = new HashMap<>();
        params.put("ent_dev_id", deviceID);
        params.put("ent_sess_token", token);
        params.put("ent_sex",userGender);
        params.put("ent_pref_sex",prefGender);
        params.put("ent_pref_lower_age",lowerAge);
        params.put("ent_pref_upper_age",upperAge);
        params.put("ent_pref_radius",distance);
        params.put("ent_distance_type",distanceType);
        Log.e("Url", Url);
        this.post(Url, params, obj,ModelPreferences.getObj(),true);
    }
}
