package com.rocket.sample.daggerrxsample.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LocationDetails implements Parcelable{

    @SerializedName("lat")
    private double mLat;
    @SerializedName("lng")
    private double mLang;
    @SerializedName("address")
    private String mAddress;

    public LocationDetails() {

    }

    protected LocationDetails(Parcel in) {
        mLat = in.readDouble();
        mLang = in.readDouble();
        mAddress = in.readString();
    }

    public static final Creator<LocationDetails> CREATOR = new Creator<LocationDetails>() {
        @Override
        public LocationDetails createFromParcel(Parcel in) {
            return new LocationDetails(in);
        }

        @Override
        public LocationDetails[] newArray(int size) {
            return new LocationDetails[size];
        }
    };

    public double getLat() {
        return mLat;
    }

    public void setLat(double lat) {
        this.mLat = lat;
    }

    public double getLang() {
        return mLang;
    }

    public void setLang(double lang) {
        this.mLang = lang;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(mLat);
        parcel.writeDouble(mLang);
        parcel.writeString(mAddress);
    }
}
