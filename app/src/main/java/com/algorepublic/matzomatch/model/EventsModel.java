package com.algorepublic.matzomatch.model;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ahmad on 12/3/15.
 */
public class EventsModel {

    private static EventsModel _obj = null;

    private EventsModel(){

    }

    public static EventsModel getInstance(){
        if (_obj == null){
            _obj = new EventsModel();
        }
        return _obj;
    }

    public void setList(EventsModel obj){
        _obj = obj;
    }

        @SerializedName("events")
       public ArrayList<Events> events = new ArrayList<>();


    public class Events{

        @SerializedName("id")
        public String id;

        @SerializedName("url")
        public String url;

        @SerializedName("name")
        ArrayList<Name> name = new ArrayList<>();

        @SerializedName("description")
        ArrayList<Description> description = new ArrayList<>();

        @SerializedName("logo")
        ArrayList<Logo> logo = new ArrayList<>();

        @SerializedName("start")
        ArrayList<Start> start = new ArrayList<>();

        @SerializedName("end")
        ArrayList<End> end = new ArrayList<>();

        @SerializedName("venue")
        ArrayList<Venue> venue = new ArrayList<>();
    }
    public class Name{
        @SerializedName("text")
        public String text;
    }
    public class Description{
        @SerializedName("url")
        public String url;
    }
    public class Logo{
        @SerializedName("url")
        public String url;
    }
    public class Start{
        @SerializedName("timezone")
        public String timezone;

        @SerializedName("local")
        public String local;

        @SerializedName("utc")
        public String utc;
    }
    public class End{
        @SerializedName("timezone")
        public String timezone;

        @SerializedName("local")
        public String local;

        @SerializedName("utc")
        public String utc;
    }
    public class Venue{
        @SerializedName("id")
        public String id;

        @SerializedName("name")
        public String name;

        @SerializedName("address")
        ArrayList<Address> address = new ArrayList<>();
    }
    public class Address{
        @SerializedName("address_1")
        public String address_1;

        @SerializedName("city")
        public String city;

        @SerializedName("region")
        public String region;

        @SerializedName("postal_code")
        public String postal_code;

        @SerializedName("country")
        public String country;

        @SerializedName("latitude")
        public String latitude;

        @SerializedName("longitude")
        public String longitude;
    }

}
