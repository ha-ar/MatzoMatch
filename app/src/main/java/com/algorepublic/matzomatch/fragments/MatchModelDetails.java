package com.algorepublic.matzomatch.fragments;

/**
 * Created by ahmad on 12/9/15.
 */
public class MatchModelDetails {

    private String firstName;
    private String fbId;
    private String pic;
    private String sex;
    private String age;
    private String sharedLkes;
    private String lDte;
    private int imgCnt;

    public void setFirstName(String firstName){this.firstName = firstName;}
    public String getFirstName(){return firstName;}

    public void setFbId(String fbId){this.fbId = fbId;}
    public String getFbId(){return fbId;}

    public void setSex(String sex){this.sex = sex;}
    public String getSex(){return sex;}

    public void setPic(String pic){this.pic = pic;}
    public String getPic(){return pic;}

    public void setAge(String age){this.age = age;}
    public String getAge(){return  age;}

    public void setSharedLkes(String sharedLkes){this.sharedLkes = sharedLkes;}
    public String getSharedLkes(){return sharedLkes;}

    public void setlDte(String lDte){this.lDte = lDte;}
    public String getlDte(){return lDte;}

    public void setImgCnt(int imgCnt){this.imgCnt = imgCnt;}
    public int getImgCnt(){return imgCnt;}
}
