package com.rocket.sample.daggerrxsample.dagger.modules;

import android.content.Context;

import com.rocket.sample.daggerrxsample.DaggerRxApplication;
import com.rocket.sample.daggerrxsample.rx.RxBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *  Used for providing dependency for Context and RxBus
 */
@Module
public class AppModule {

    private DaggerRxApplication mAppContext;

    public AppModule(DaggerRxApplication appContext) {
        mAppContext = appContext;
    }

    @Singleton
    @Provides
    public Context provideAppContext() {
        return mAppContext;
    }

    @Provides
    @Singleton
    public RxBus providesRxBus() {
        return new RxBus();
    }
}
