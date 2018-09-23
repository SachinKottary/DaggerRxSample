package com.rocket.sample.daggerrxsample.network;

import com.rocket.sample.daggerrxsample.utils.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Helper class used for providing details related to network
 */

public class NetworkUtils {

    public static final int REQUEST_GET_DELIVERY = 000;

    public static final String END_URL_DELIVIERY = "deliveries";

    /**
     *  Returns the URL for particular request
     * @param requestType
     * @return
     */
    public static String getUrl(int requestType) {
        switch (requestType) {
            case REQUEST_GET_DELIVERY:
                return END_URL_DELIVIERY;
        }
        return "";
    }

    /**
     * Returns query details from delivery API
     * @param offset position to fetch data from
     * @return
     */
    public static Map getQueryDetailsForDelivery(int offset) {
        HashMap<String, String> map = new HashMap<>();
        map.put(ServerConstants.DELIVERY_OFFSET, String.valueOf(offset));
        map.put(ServerConstants.DELIVERY_LIMIT, String.valueOf(Constants.DELIVERY_DEFAULT_LIMIT));
        return map;
    }
}