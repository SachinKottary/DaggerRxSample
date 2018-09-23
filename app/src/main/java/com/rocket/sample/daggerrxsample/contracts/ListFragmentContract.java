package com.rocket.sample.daggerrxsample.contracts;

import com.rocket.sample.daggerrxsample.interfaces.BasePresenter;
import com.rocket.sample.daggerrxsample.interfaces.BaseView;
import com.rocket.sample.daggerrxsample.model.DeliveryDetails;

import java.util.ArrayList;

/**
 * Contract class for List fragment contains methods calls
 * used for communication between presenter and view
 */
public interface ListFragmentContract {
    interface View extends BaseView {
        void addDeliveryDetails(ArrayList<DeliveryDetails> deliveryDetailList);

    }

    interface Presenter extends BasePresenter {
        void loadDeliveryDetails(int offset);
    }
}
