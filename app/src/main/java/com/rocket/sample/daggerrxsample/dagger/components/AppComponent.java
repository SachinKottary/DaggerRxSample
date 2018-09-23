package com.rocket.sample.daggerrxsample.dagger.components;


import com.rocket.sample.daggerrxsample.dagger.modules.AppModule;
import com.rocket.sample.daggerrxsample.dagger.modules.DatabaseModule;
import com.rocket.sample.daggerrxsample.dagger.modules.NetworkModule;
import com.rocket.sample.daggerrxsample.ui.activities.BaseActivity;
import com.rocket.sample.daggerrxsample.ui.fragments.BaseFragment;
import com.rocket.sample.daggerrxsample.ui.viewholders.BaseViewHolder;

import javax.inject.Singleton;

import dagger.Component;

/**
 *  Dagger single app component used for injecting AppModule.class, NetworkModule.class, DatabaseModule.class
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, DatabaseModule.class})
public interface AppComponent {

    void inject(BaseActivity activity);

    void inject(BaseFragment fragment);

    void inject(BaseViewHolder viewHolder);
}
