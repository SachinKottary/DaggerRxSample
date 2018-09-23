package com.rocket.sample.daggerrxsample.interfaces;

import android.support.annotation.StringRes;

/**
 *  Used for providing base methods to access the views
 */
public interface BaseView {

    void showProgress();

    void hideProgress();

    void showError(@StringRes int stringResource);

}
