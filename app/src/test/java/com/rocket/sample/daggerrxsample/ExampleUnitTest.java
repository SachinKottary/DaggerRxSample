package com.rocket.sample.daggerrxsample;

import com.rocket.sample.daggerrxsample.network.NetworkUtils;
import com.rocket.sample.daggerrxsample.ui.fragments.ListingFragment;
import com.rocket.sample.daggerrxsample.utils.FragmentUtils;

import org.junit.Test;

import static com.rocket.sample.daggerrxsample.network.NetworkUtils.END_URL_DELIVIERY;
import static com.rocket.sample.daggerrxsample.network.NetworkUtils.REQUEST_GET_DELIVERY;
import static com.rocket.sample.daggerrxsample.network.ServerConstants.DELIVERY_OFFSET;
import static com.rocket.sample.daggerrxsample.utils.FragmentUtils.FRAGMENT_DELIVERY_LIST;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void checkUrl() {
        assertEquals(NetworkUtils.getUrl(REQUEST_GET_DELIVERY), END_URL_DELIVIERY);
        assertEquals(NetworkUtils.getUrl(1), "");
    }
    @Test
    public void checkQuerryMapDetails() {
        assertEquals("0", NetworkUtils.getQueryDetailsForDelivery(0).get(DELIVERY_OFFSET));
        assertEquals("1", NetworkUtils.getQueryDetailsForDelivery(1).get(DELIVERY_OFFSET));
    }

    @Test
    public void checkFragmentName() {
        assertEquals(FragmentUtils.getFragmentTag(FRAGMENT_DELIVERY_LIST), ListingFragment.class.getName());
        assertEquals(FragmentUtils.getFragmentTag(5), "");
    }
}