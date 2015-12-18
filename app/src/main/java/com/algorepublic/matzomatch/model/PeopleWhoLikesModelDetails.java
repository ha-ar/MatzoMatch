package com.algorepublic.matzomatch.model;

/**
 * Created by ahmad on 12/14/15.
 */
public class PeopleWhoLikesModelDetails {

    public String errMsg;
    public String firstName;
    public String pic;
    public String fbId;
    public String gender;
    public String age;
    public String sharedLikes;
    public String lDate;
    public int imgCnt;

    public void setErrMsg(String errMsg){this.errMsg = errMsg;}
    public String getErrMsg(){return errMsg;}

    public void setFirstName(String firstName){this.firstName = firstName;}
    public String getFirstName(){return firstName;}

    public void setPic(String pic){this.pic = pic;}
    public String getPic(){return pic;}

    public void setFbId(String fbId){this.fbId = fbId;}
    public String getFbId(){return fbId;}

    public void setGender(String gender){this.gender = gender;}
    public String getGender(){return gender;}

    public void setAge(String age){this.age = age;}
    public String getAge(){return  age;}

    public void setSharedLikes(String sharedLikes){this.sharedLikes = sharedLikes;}
    public String getSharedLikes(){return sharedLikes;}

    public void setlDate(String lDate){this.lDate = lDate;}
    public String getlDate(){return lDate;}

    public void setImgCnt(int imgCnt){this.imgCnt = imgCnt;}
    public int getImgCnt(){return imgCnt;}
}
