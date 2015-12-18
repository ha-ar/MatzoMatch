package com.algorepublic.matzomatch.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by waqas on 12/11/15.
 */
public class LoginModel {
    public static LoginModel _obj = null;

    public LoginModel(){

    }

    public static LoginModel getInstance(){
        if (_obj == null){
            _obj = new LoginModel();
        }
        return _obj;
    }

    public void setList(LoginModel obj)
    {_obj = obj;}

    @SerializedName("errNum")
    public String errNum;

    @SerializedName("errFlag")
    public String errFlag;

    @SerializedName("errMsg")
    public String errMsg;

//    @SerializedName("test")
//    public int test;

    @SerializedName("token")
    public String token;


    @SerializedName("expiryLocal")
    public String expiryLocal;

    @SerializedName("expiryGMT")
    public String expiryGMT;

    @SerializedName("profilePic")
    public String profilePic;



    @SerializedName("flag")
    public String flag;

    @SerializedName("joined")
    public String joined;


    @SerializedName("fbid")
    public String fbid;


    @SerializedName("Reverse_Likes")
    public String   Reverse_Likes;

    @SerializedName("Message_Counter")
    public String Message_Counter;


}
