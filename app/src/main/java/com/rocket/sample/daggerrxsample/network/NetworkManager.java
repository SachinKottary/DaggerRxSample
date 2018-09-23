package com.rocket.sample.daggerrxsample.network;

import com.rocket.sample.daggerrxsample.database.RealmController;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Helper class to handle network related operations
 */

public class NetworkManager {

    private static final String TAG = NetworkManager.class.getSimpleName();
    private RetrofitInterface mRetrofitInterface;

    public NetworkManager(RetrofitInterface RetrofitInterface) {
        mRetrofitInterface = RetrofitInterface;
    }

    /**
     *  Fetches api response for delivery details
     * @param offset
     * @param callback
     */
    public void getDeliverYDetails(int offset, Observer callback) {
         mRetrofitInterface.getDeliveryDetails(NetworkUtils.getUrl(NetworkUtils.REQUEST_GET_DELIVERY), NetworkUtils.getQueryDetailsForDelivery(offset))
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(callback);



    }
}
