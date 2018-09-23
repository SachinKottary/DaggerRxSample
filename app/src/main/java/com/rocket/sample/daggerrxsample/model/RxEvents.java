package com.rocket.sample.daggerrxsample.model;

/**
 * This class is used as event notifier which can be sent or received using RxBus class
 */

public class RxEvents<T> {

    public static final String EVENT_DELIVERY_ITEM_SELECTED = "event_delivery_item_selected";

    private String flag;// Event identifier
    private T data;//Event data

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
