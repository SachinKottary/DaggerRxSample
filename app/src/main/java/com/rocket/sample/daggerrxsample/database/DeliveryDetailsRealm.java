package com.rocket.sample.daggerrxsample.database;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 *  Realm database used for saving delivery data
 */

public class DeliveryDetailsRealm extends RealmObject {

    @PrimaryKey
    private int id;
    private String description;
    private String imageUrl;
    private LocationDetailsRealm locationDetails;
    private int trayType;//0 for delivery data and 1 for Progress bar


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocationDetailsRealm getLocationDetails() {
        return locationDetails;
    }

    public void setLocationDetails(LocationDetailsRealm locationDetails) {
        this.locationDetails = locationDetails;
    }

    public int getTrayType() {
        return trayType;
    }

    public void setTrayType(int trayType) {
        this.trayType = trayType;
    }

}
