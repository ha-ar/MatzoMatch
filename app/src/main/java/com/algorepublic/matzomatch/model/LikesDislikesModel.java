package com.algorepublic.matzomatch.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmad on 12/8/15.
 */
public class LikesDislikesModel {

    public static LikesDislikesModel _obj = null;

    public LikesDislikesModel(){

    }

    public static LikesDislikesModel getInstance(){
        if (_obj == null){
            _obj = new LikesDislikesModel();
        }
        return _obj;
    }

    public void setList(LikesDislikesModel obj){_obj = obj;}

    @SerializedName("errNum")
    public String errNum;

    @SerializedName("errFlag")
    public String errFlag;

    @SerializedName("errMsg")
    public String errMsg;

    @SerializedName("test")
    public int test;

}
