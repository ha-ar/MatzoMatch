package com.algorepublic.matzomatch.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmad on 12/9/15.
 */
public class ModelPreferences {

    static ModelPreferences _obj = null;

    public ModelPreferences(){

    }

    public static ModelPreferences getObj() {
        if (_obj == null){
            _obj = new ModelPreferences();
        }
        return _obj;
    }

    public void setList(ModelPreferences object){ _obj = object;}

    @SerializedName("errMsg")
    public String errMsg;

    @SerializedName("prSex")
    public String prSex;

    @SerializedName("prLAge")
    public String prLAge;

    @SerializedName("prUAge")
    public String prUAge;

    @SerializedName("prRad")
    public String prRad;

    @SerializedName("sex")
    public String sex;

}