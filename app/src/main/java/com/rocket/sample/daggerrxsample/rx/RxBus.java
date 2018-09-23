package com.rocket.sample.daggerrxsample.rx;

import io.reactivex.subjects.PublishSubject;

/**
 *  This class is used for sending events or to subscribe to it.
 *
 */
public class RxBus {

    private final PublishSubject<Object> bus = PublishSubject.create();

    /**
     *  Sends an event to all the observers connected to it
     * @param event
     */
    public void send(final Object event) {
        bus.onNext(event);
    }

    public PublishSubject<Object> getBus() {
        return bus;
    }

    public boolean hasObservers() {
        return bus.hasObservers();
    }
} 