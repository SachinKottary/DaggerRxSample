package com.rocket.sample.daggerrxsample.presenters;

import com.rocket.sample.daggerrxsample.R;
import com.rocket.sample.daggerrxsample.contracts.ListFragmentContract;
import com.rocket.sample.daggerrxsample.database.DeliveryDetailsRealm;
import com.rocket.sample.daggerrxsample.database.LocationDetailsRealm;
import com.rocket.sample.daggerrxsample.database.RealmController;
import com.rocket.sample.daggerrxsample.model.DeliveryDetails;
import com.rocket.sample.daggerrxsample.model.LocationDetails;
import com.rocket.sample.daggerrxsample.network.NetworkManager;
import com.rocket.sample.daggerrxsample.utils.Constants;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class ListFragmentPresenter implements ListFragmentContract.Presenter {

    private ListFragmentContract.View mView;
    private NetworkManager mNetworkManager;
    private RealmController mRealmController;
    private CompositeDisposable mDisposable;

    public ListFragmentPresenter(ListFragmentContract.View view, NetworkManager networkManager, RealmController realmController) {
        mView = view;
        mNetworkManager = networkManager;
        mRealmController = realmController;
        mDisposable = new CompositeDisposable();
    }

    /**
     *  Makes call to fetch delivery data from cache or API
     * @param offset
     */
    @Override
    public void loadDeliveryDetails(int offset) {
        if (mRealmController.isDeliveryDetailsCached(offset)) {
            fetchDeliveryDetailFromCache(offset);
        } else {
            fetchDeliveryDetailFromServer(offset);
        }
    }

    /**
     *  API call to fetch delivery details from server
     * @param offset position to fetch from
     */
    private void fetchDeliveryDetailFromServer(int offset) {
        if (mNetworkManager != null) {
            mNetworkManager.getDeliverYDetails(offset, new Observer() {
                @Override
                public void onSubscribe(Disposable d) {
                    mDisposable.add(d);
                }

                @Override
                public void onNext(Object value) {
                    if (mView != null) {
                        mView.addDeliveryDetails((ArrayList<DeliveryDetails>) value);
                        saveApiResponseInRealm((ArrayList<DeliveryDetails>) value);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    if (mView != null) {
                        mView.showError(R.string.network_error);
                    }
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }

    /**
     * Retrieves cached delivery details from realm database
     * @param offset
     */
    private void fetchDeliveryDetailFromCache(final int offset) {
        Single.just(mRealmController.getDeliveryDetailsAsArrayList(offset, Constants.DELIVERY_DEFAULT_LIMIT))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable.add(d);
            }

            @Override
            public void onSuccess(Object value) {
                if (mView != null && value instanceof ArrayList<?>) {
                    mView.addDeliveryDetails((ArrayList<DeliveryDetails>) value);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView != null) {
                    mView.showError(R.string.something_error);
                }
            }
        });
    }

    @Override
    public void start() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    /**
     *  Caches the api response details to database
     * @param apiResultList
     */
    private void saveApiResponseInRealm(ArrayList<DeliveryDetails> apiResultList) {
        if (apiResultList != null) {
            for (int iterators = 0; iterators < apiResultList.size(); iterators++) {
                DeliveryDetails details = apiResultList.get(iterators);
                LocationDetails locationDetails = details.getLocationDetails();

                DeliveryDetailsRealm deliveryDetailsRealm = new DeliveryDetailsRealm();
                LocationDetailsRealm locationDetailsRealm = new LocationDetailsRealm();


                deliveryDetailsRealm.setId(details.getId());
                deliveryDetailsRealm.setDescription(details.getDescription());
                deliveryDetailsRealm.setImageUrl(details.getImageUrl());

                locationDetailsRealm.setAddress(locationDetails.getAddress());
                locationDetailsRealm.setLang(locationDetails.getLang());
                locationDetailsRealm.setLat(locationDetails.getLat());

                deliveryDetailsRealm.setLocationDetails(locationDetailsRealm);

                mRealmController.mRealm.beginTransaction();
                mRealmController.mRealm.copyToRealm(deliveryDetailsRealm);
                mRealmController.mRealm.commitTransaction();
            }
        }
    }
}
