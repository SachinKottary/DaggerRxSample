package com.rocket.sample.daggerrxsample.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rocket.sample.daggerrxsample.R;
import com.rocket.sample.daggerrxsample.model.DeliveryDetails;
import com.rocket.sample.daggerrxsample.model.RxEvents;
import com.rocket.sample.daggerrxsample.utils.Constants;
import com.rocket.sample.daggerrxsample.utils.DialogUtils;
import com.rocket.sample.daggerrxsample.utils.Utils;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

import static com.rocket.sample.daggerrxsample.utils.FragmentUtils.FRAGMENT_DELIVERY_LIST;


public class HomeActivity extends BaseActivity {

    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCompositeDisposable = new CompositeDisposable();
        Observable<Object> clickObserver = mRxBus.getBus().share();
        mCompositeDisposable.add(clickObserver.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object data) {
                if (data instanceof RxEvents) {
                    if (RxEvents.EVENT_DELIVERY_ITEM_SELECTED.equals(((RxEvents) data).getFlag())) {
                        DeliveryDetails deliveryDetails = (DeliveryDetails) ((RxEvents) data).getData();
                        if (Utils.isInternetOn(HomeActivity.this)) {
                            Intent mapIntent = new Intent(HomeActivity.this, MapsActivity.class);
                            mapIntent.putExtra(Constants.DELIVERY_DETAILS, deliveryDetails);
                            startActivity(mapIntent);
                        } else {
                            DialogUtils.showNetworkErrorDialog(HomeActivity.this, getResources());
                        }
                    }
                }

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                DialogUtils.showAlertDialog(HomeActivity.this, getResources(),
                        getString(R.string.something_error));
            }
        }));
        setCurrentFragment(null, FRAGMENT_DELIVERY_LIST, FRAG_ADD, R.id.contentPanel);//Load default fragment
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}
