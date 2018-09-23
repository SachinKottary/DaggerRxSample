package com.rocket.sample.daggerrxsample.database;

import com.rocket.sample.daggerrxsample.model.DeliveryDetails;
import com.rocket.sample.daggerrxsample.model.LocationDetails;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 *  Used to provide access to realm database to save, update or delete data
 */

public class RealmController  {

    public Realm mRealm;

    public RealmController(Realm realm) {
        mRealm = realm;
    }

    /**
     *  Used for checking if already cached data available
     * @param offset the item position to be checked in cache
     * @return true if data available else return false
     */
    public boolean isDeliveryDetailsCached(int offset) {
       return mRealm.where(DeliveryDetailsRealm.class).equalTo("id", offset).count() > 0;
    }

    private RealmQuery<DeliveryDetailsRealm> getDeliveryDetail(int offset, int limit) {
        return mRealm.where(DeliveryDetailsRealm.class).between("id", offset, offset+limit);
    }

    /**
     *  Performs database call for the provided offset position and returns the
     *  database contents as array list
     * @param offset
     * @param limit
     * @return
     */
    public ArrayList<DeliveryDetails> getDeliveryDetailsAsArrayList(int offset, int limit) {
        ArrayList<DeliveryDetails> detailsArrayList = null;
        RealmQuery<DeliveryDetailsRealm> resultQueryList = getDeliveryDetail(offset, limit);
        if (resultQueryList != null) {
            RealmResults<DeliveryDetailsRealm> resultList = resultQueryList.findAll();
            if (resultList != null && resultList.size() > 0) {
                detailsArrayList = new ArrayList<>();
                DeliveryDetails deliveryDetails;
                LocationDetails locationDetails;
                for (int iterators = 0; iterators < resultList.size(); iterators++) {
                    deliveryDetails = new DeliveryDetails();
                    locationDetails = new LocationDetails();

                    DeliveryDetailsRealm deliveryDetailRealm = resultList.get(iterators);
                    LocationDetailsRealm locationDetailsRealm = deliveryDetailRealm.getLocationDetails();

                    deliveryDetails.setId(deliveryDetailRealm.getId());
                    deliveryDetails.setDescription(deliveryDetailRealm.getDescription());
                    deliveryDetails.setImageUrl(deliveryDetailRealm.getImageUrl());

                    locationDetails.setAddress(locationDetailsRealm.getAddress());
                    locationDetails.setLang(locationDetailsRealm.getLang());
                    locationDetails.setLat(locationDetailsRealm.getLat());

                    deliveryDetails.setLocationDetails(locationDetails);
                    detailsArrayList.add(deliveryDetails);//Add cache data to list
                }
            }
        }
        return detailsArrayList;
    }

}
