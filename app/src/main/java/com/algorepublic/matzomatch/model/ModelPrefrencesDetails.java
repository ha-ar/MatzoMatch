package com.algorepublic.matzomatch.model;

/**
 * Created by ahmad on 12/9/15.
 */
public class ModelPrefrencesDetails {

    public String upperAge;
    public String lowerAge;
    public String prefGender;
    public String prefDistance;
    public String prefUserGender;

    public void setUpperAge(String upperAge){this.upperAge= upperAge;}
    public String getUpperAge(){return upperAge;}

    public void setLowerAge(String lowerAge){this.lowerAge = lowerAge;}
    public String getLowerAge(){return lowerAge;}

    public void setPrefGender(String prefGender){this.prefGender = prefGender;}
    public String getPrefGender(){return prefGender;}

    public void setPrefUserGender(String prefUserGender){this.prefUserGender = prefUserGender;}
    public String getPrefUserGender(){return prefUserGender;}

    public void setPrefDistance(String prefDistance){this.prefDistance = prefDistance;}
    public String getPrefDistance(){return prefDistance;}

}
