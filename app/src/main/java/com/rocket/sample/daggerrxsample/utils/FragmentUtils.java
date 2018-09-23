package com.rocket.sample.daggerrxsample.utils;


import com.rocket.sample.daggerrxsample.ui.fragments.GoogleMapFragment;
import com.rocket.sample.daggerrxsample.ui.fragments.ListingFragment;

public class FragmentUtils {

    public static final int FRAGMENT_DELIVERY_LIST = 0;
    public static final int FRAGMENT_GOOGLE_MAP = 1;

    /**
     *  Returns the fragment name to be instantiate
     * @param type
     * @return
     */
    public static String getFragmentTag(int type) {
        switch (type) {
            case FRAGMENT_DELIVERY_LIST:
                return ListingFragment.class.getName();

            case FRAGMENT_GOOGLE_MAP:
                return GoogleMapFragment.class.getName();
        }
        return "";
    }
}
