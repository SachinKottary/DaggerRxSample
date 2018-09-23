package com.rocket.sample.daggerrxsample.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DeliveryDetails implements Parcelable {

    @SerializedName("id")
    private int mId;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("imageUrl")
    private String mImageUrl;
    @SerializedName("location")
    private LocationDetails mLocationDetails;
    int mTrayType;//0 for delivery data and 1 for Progress bar

    public DeliveryDetails() {
    }

    public DeliveryDetails(int trayType) {
        mTrayType = trayType;
    }


    protected DeliveryDetails(Parcel in) {
        mId = in.readInt();
        mDescription = in.readString();
        mImageUrl = in.readString();
        mLocationDetails = in.readParcelable(LocationDetails.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mDescription);
        dest.writeString(mImageUrl);
        dest.writeParcelable(mLocationDetails, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DeliveryDetails> CREATOR = new Creator<DeliveryDetails>() {
        @Override
        public DeliveryDetails createFromParcel(Parcel in) {
            return new DeliveryDetails(in);
        }

        @Override
        public DeliveryDetails[] newArray(int size) {
            return new DeliveryDetails[size];
        }
    };

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public LocationDetails getLocationDetails() {
        return mLocationDetails;
    }

    public void setLocationDetails(LocationDetails mLocationDetails) {
        this.mLocationDetails = mLocationDetails;
    }

    public int getTrayType() {
        return mTrayType;
    }

    public void setTrayType(int mTrayType) {
        this.mTrayType = mTrayType;
    }
}
