package com.rocket.sample.daggerrxsample;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.rocket.sample.daggerrxsample.dagger.components.AppComponent;
import com.rocket.sample.daggerrxsample.dagger.components.DaggerAppComponent;
import com.rocket.sample.daggerrxsample.dagger.modules.AppModule;
import com.rocket.sample.daggerrxsample.dagger.modules.DatabaseModule;
import com.rocket.sample.daggerrxsample.dagger.modules.NetworkModule;
import com.rocket.sample.daggerrxsample.utils.ClearGlideCacheTask;

/**
 *  Application class used for initializing app level components
 */
public class DaggerRxApplication extends Application {
    private AppComponent mAppComponent;
    private static DaggerRxApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .databaseModule(new DatabaseModule(this))
        .build();

    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public DaggerRxApplication() {
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        clearMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_RUNNING_LOW || level == TRIM_MEMORY_RUNNING_CRITICAL || level == TRIM_MEMORY_UI_HIDDEN) {
            clearMemory();
        }
    }

    /**
     *  Free up some memory in case of low memory
     */
    private void clearMemory() {
        ClearGlideCacheTask glideCache = new ClearGlideCacheTask();
        glideCache.execute();
        Glide.get(DaggerRxApplication.getContext()).clearMemory();
        Glide.get(this).setMemoryCategory(MemoryCategory.LOW);
    }
}
