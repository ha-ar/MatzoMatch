package com.algorepublic.matzomatch.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ahmad on 12/14/15.
 */
public class PeopleWhoLikesYouModel {

    private static PeopleWhoLikesYouModel _obj = null;

    public static PeopleWhoLikesYouModel getInstance (){
        if (_obj == null){
            _obj = new PeopleWhoLikesYouModel();
        }
        return _obj;
    }

    public void setList(PeopleWhoLikesYouModel obj){ _obj = obj; }

    @SerializedName("errMsg")
    public String errMsg;

    @SerializedName("matches")
    public ArrayList<Matches> matches = new ArrayList<>();

    public class Matches{

        @SerializedName("firstName")
        public String firstName;

        @SerializedName("fbId")
        public String fbId;

        @SerializedName("pPic")
        public String pPic;

        @SerializedName("sex")
        public String sex;

        @SerializedName("age")
        public String age;

        @SerializedName("sharedLikes")
        public String sharedLikes;

        @SerializedName("ldat")
        public String ldat;

        @SerializedName("imgCnt")
        public int imgCnt;
    }
}
