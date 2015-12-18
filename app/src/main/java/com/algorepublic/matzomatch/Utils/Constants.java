package com.algorepublic.matzomatch.Utils;

import com.linkedin.platform.utils.Scope;

/**
 * Created by hasanali on 12/06/2015.
 */
public class Constants {
    public static int STATUS_OK = 200, INTERNET_FAILURE = 101;
    public static String GENERAL_ERROR = "There was some error in server please try again later.";
    public static String Android_KEY = "cBAgUoz0I9MoiGZ1tTy";
    public static String BASE_URL = "http://matzoball.org/matzomatch/process.php/";
    public static String BASE_URL_LOGIN = "http://rest.campusbooks.com/v1/user";
    public static String BASE_URL_EVENTS="https://www.eventbriteapi.com/v3/users/me/owned_events/";
    public static String BASE_URL_LIKE_DISLIKES = "http://matzoball.org/matzomatch/process.php/inviteAction";
    public static String BASE_URL_PROFILES = "http://matzoball.org/matzomatch/process.php/findMatches";
    public static String DEVICE_ID="deviceId";
    public static String LOGIN="http://matzoball.org/matzomatch/process.php/login";

    public static final String CONSUMER_KEY = "77q91a6o4acz5a"; //
    public static final String CONSUMER_SECRET = "Cny8AQBNCca6i6yU";
    public static final String OAUTH_CALLBACK_SCHEME = "x-oauthflow-linkedin";
    public static final String OAUTH_CALLBACK_HOST = "litestcalback";
    public static final String OAUTH_CALLBACK_URL = OAUTH_CALLBACK_SCHEME
            + "://" + OAUTH_CALLBACK_HOST;

    public static String UPPER_AGE ="uperAge";
    public static String LOWER_AGE = "LowerAge";
    public static String USER_GENDER = "UserGender";
    public static String PREF_GENDER = "PrefGender";
    public static String DISTANCE = "Distance";
    public static final String personByIdBaseUrl = "https://api.linkedin.com/v1/people/id=";
    public static final String shareBaseUrl = "https://api.linkedin.com/v1/people/~/shares";
    public static final String personProjection = ":(id,first-name,last-name)";
    public static final Scope scope = Scope.build(Scope.R_BASICPROFILE, Scope.W_SHARE);
    public static String FirstName = "FirstName";
    public static String LastName = "LastName";
    public static String HeadLine = "HeadliNE";
    public static String Photo = "Picture";
    public static String Lat = "latitude";
    public static String Lon = "longitutde";
    public static String CountryName = "contryName";
    public static String City = "city";
    public static String AndroidId="AndroidId";
    public static String LinkdinId="linkdinId";
    public static String NoOfLinkdinConnections="noOfLinkdinConnections";
    public static String LoginAppToken="Loginapptoken";
    public static String Industry="noOfLinkdinConnections";

}
