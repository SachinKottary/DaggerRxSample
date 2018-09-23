package com.rocket.sample.daggerrxsample.interfaces;

/**
 * Used for holding base methods for any presenter
 */
public interface BasePresenter {

    void start();
    void resume();
    void pause();
    void stop();
    void destroy();

}
