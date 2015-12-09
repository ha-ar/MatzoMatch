package com.algorepublic.matzomatch.Utils;

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

    public static final String CONSUMER_KEY = "77q91a6o4acz5a"; //
    public static final String CONSUMER_SECRET = "Cny8AQBNCca6i6yU";
    public static final String OAUTH_CALLBACK_SCHEME = "x-oauthflow-linkedin";
    public static final String OAUTH_CALLBACK_HOST = "litestcalback";
    public static final String OAUTH_CALLBACK_URL = OAUTH_CALLBACK_SCHEME
            + "://" + OAUTH_CALLBACK_HOST;
}
