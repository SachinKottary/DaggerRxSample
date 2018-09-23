package com.rocket.sample.daggerrxsample.ui.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.rocket.sample.daggerrxsample.DaggerRxApplication;
import com.rocket.sample.daggerrxsample.database.RealmController;
import com.rocket.sample.daggerrxsample.interfaces.FragmentInteractionListener;
import com.rocket.sample.daggerrxsample.network.NetworkManager;
import com.rocket.sample.daggerrxsample.rx.RxBus;
import com.rocket.sample.daggerrxsample.utils.Utils;

import javax.inject.Inject;

/**
 *  Provides base implementation for all the fragments
 */
public abstract class BaseFragment extends Fragment {
    @Inject
    public RxBus mRxBus;
    @Inject
    public NetworkManager mNetworkManager;
    @Inject
    public RealmController mRealmController;
    private BroadcastReceiver mNetworkChangeReceiver;
    protected FragmentInteractionListener mRSFragmentInteractionListener;
    private boolean onResume = true;
    private boolean mIsNetDisconnected;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((DaggerRxApplication) getActivity().getApplicationContext()).getAppComponent().inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNetworkChangeReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (Utils.isInternetOn(context)) {
                    if (mIsNetDisconnected) {//To avoid multiple callback when network not present and fragment is loaded
                        mIsNetDisconnected = false;
                        onNetworkConnected();
                    }
                    hideNoNetworkDialog();
                } else {
                    mIsNetDisconnected = true;
                    onNetworkDisConnected();
                }
            }
        };
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mRSFragmentInteractionListener = (FragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement FragmentInteractionListener ");
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        onResume = false;
        getActivity().unregisterReceiver(mNetworkChangeReceiver);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (Utils.isInternetOn(getContext())) {
            if (handleNetworkState()) {
                if (mIsNetDisconnected) onNetworkConnected();
                mIsNetDisconnected = false;
                hideNoNetworkDialog();
            }
        } else {//Display network dialog if internet not present
            mIsNetDisconnected = true;
            if (handleNetworkState() && !onResume) {
                showNoNetworkDialog();
            } else {
                onNetworkDisConnected();
            }
        }
        getActivity().registerReceiver(mNetworkChangeReceiver, new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION));

    }

    /**
     * Should handle network related changes from base fragment or not
     * @return
     */
    public abstract boolean handleNetworkState();

    public abstract void onNetworkDisConnected();
    public abstract void onNetworkConnected();

    public void showNoNetworkDialog()  {
    }

    public void hideNoNetworkDialog() {
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mRSFragmentInteractionListener != null)
            mRSFragmentInteractionListener = null;
    }
}
