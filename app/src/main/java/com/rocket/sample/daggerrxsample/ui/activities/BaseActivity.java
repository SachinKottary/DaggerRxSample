package com.rocket.sample.daggerrxsample.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


import com.rocket.sample.daggerrxsample.DaggerRxApplication;
import com.rocket.sample.daggerrxsample.interfaces.FragmentInteractionListener;
import com.rocket.sample.daggerrxsample.rx.RxBus;
import com.rocket.sample.daggerrxsample.utils.FragmentUtils;

import javax.inject.Inject;

/**
 *  Base class for all the activities, contains methods related to fragment transactions
 */
public class BaseActivity extends AppCompatActivity implements FragmentInteractionListener {

    @Inject
    protected RxBus mRxBus;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ((DaggerRxApplication) getApplicationContext()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void setCurrentFragment(Bundle bundle, int fragmentType, int transType, int frameId) {
        addFragment(bundle, fragmentType, transType, frameId);
    }

    /**
     *  Used for performing fragment transactions
     * @param bundle to be passed to the new fragment
     * @param fragmentType identifier for the fragment to be instantiated
     * @param transType
     * @param frameId container id
     */
    private void addFragment(Bundle bundle, int fragmentType, int transType, int frameId) {

        if (!isFinishing()) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment fragment = null;

            if (fragment == null) {
                fragment = Fragment.instantiate(this,
                        FragmentUtils.getFragmentTag(fragmentType), bundle);
                if (transType == FRAG_ADD) {
                    ft.add(frameId, fragment, FragmentUtils.getFragmentTag(fragmentType));
                } else if (transType == FRAG_REPLACE) {
                    ft.replace(frameId, fragment, FragmentUtils.getFragmentTag(fragmentType));
                } else if (transType == FRAG_REPLACE_WITH_STACK) {
                    ft.replace(frameId, fragment, FragmentUtils.getFragmentTag(fragmentType));
                    ft.addToBackStack(FragmentUtils.getFragmentTag(fragmentType));
                } else if (transType == FRAG_ADD_WITH_STACK) {
                    ft.add(frameId, fragment, FragmentUtils.getFragmentTag(fragmentType));
                    ft.addToBackStack(FragmentUtils.getFragmentTag(fragmentType));
                }

            } else {
                ft.attach(fragment);
            }
            ft.commitAllowingStateLoss();
            fm.executePendingTransactions();
        }
    }


    @Override
    public void popTopFragment() {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStackImmediate();
    }

    @Override
    public void popAllFromStack() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        int count = fm.getBackStackEntryCount();

        for (int i = 0; i < count; i++) {

            Fragment fragment = null;
            if (getActiveFragmentTag() != null) {
                fragment = fm.findFragmentByTag(getActiveFragmentTag());
                fm.popBackStackImmediate(getActiveFragmentTag(),
                        FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            if (fragment != null)
                ft.remove(fragment);

        }


        ft.commitAllowingStateLoss();
    }

    @Override
    public String getActiveFragmentTag() {
        return null;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
