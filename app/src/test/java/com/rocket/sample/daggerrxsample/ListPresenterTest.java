package com.rocket.sample.daggerrxsample;

import com.rocket.sample.daggerrxsample.contracts.ListFragmentContract;
import com.rocket.sample.daggerrxsample.database.RealmController;
import com.rocket.sample.daggerrxsample.model.DeliveryDetails;
import com.rocket.sample.daggerrxsample.network.NetworkManager;
import com.rocket.sample.daggerrxsample.network.NetworkUtils;
import com.rocket.sample.daggerrxsample.network.RetrofitInterface;
import com.rocket.sample.daggerrxsample.presenters.ListFragmentPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ListPresenterTest {

    @Mock
    private ListFragmentContract.View mView;
    @Mock
    private NetworkManager mNetworkManager;
    @Mock
    private RealmController mRealmController;
    @Mock
    private Observer mObserver;
    @Mock
    ListFragmentPresenter mPresenter;
    @Mock
    private RetrofitInterface mRetrofitInterface;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkDeliveryAPI() {

        ArrayList<DeliveryDetails> deliveryDetailsArrayList = new ArrayList<>();
        when(mRetrofitInterface.getDeliveryDetails(NetworkUtils.getUrl(NetworkUtils.REQUEST_GET_DELIVERY), NetworkUtils.getQueryDetailsForDelivery(0)))
                .thenReturn(Observable.just(deliveryDetailsArrayList));
        when(mRealmController.isDeliveryDetailsCached(0)).thenReturn(false);
//        mPresenter.loadDeliveryDetails(0);
/*        ListFragmentPresenter presenter = new ListFragmentPresenter(mView, mNetworkManager, mRealmController);
        presenter.loadDeliveryDetails(0);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
//        InOrder inOrder = Mockito.inOrder(mView);
//        inOrder.verify(mView, times(1)).addDeliveryDetails(deliveryDetailsArrayList);
//        InOrder presenterOrder = Mockito.inOrder(mPresenter);
//        verify(mView, times(1)).addDeliveryDetails(deliveryDetailsArrayList);
//        presenterOrder.verify(mPresenter, times(1)).saveApiResponseInRealm(deliveryDetailsArrayList);
    }
}
