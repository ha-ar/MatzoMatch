package com.algorepublic.matzomatch.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ahmad on 12/9/15.
 */
public class MatchModel {

    private static MatchModel _obj = null;

    public MatchModel(){

    }

    public static MatchModel getInstaance (){
        if (_obj == null){
            _obj = new MatchModel();
        }
        return _obj;
    }

    public void setList(MatchModel obj){
        _obj = obj;
    }

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
