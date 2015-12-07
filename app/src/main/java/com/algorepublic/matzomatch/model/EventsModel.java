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
        public Name name = new Name();

        @SerializedName("description")
        public Description description = new Description();

        @SerializedName("logo")
        public Logo logo = new Logo();

        @SerializedName("start")
        public Start start = new Start();

        @SerializedName("end")
        public End end = new End();

        @SerializedName("venue")
        public Venue venue = new Venue();
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

        @SerializedName("latitude")
        public String latitude;

        @SerializedName("longitude")
        public String longitude;

        @SerializedName("address")
        public Address address = new Address();
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

    }

}
