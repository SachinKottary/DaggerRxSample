package com.rocket.sample.daggerrxsample.database;


import io.realm.RealmObject;

/**
 *  Realm database used for saving location details
 */

public class LocationDetailsRealm extends RealmObject {

    private double lat;
    private double lang;
    private String address;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLang() {
        return lang;
    }

    public void setLang(double lang) {
        this.lang = lang;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
