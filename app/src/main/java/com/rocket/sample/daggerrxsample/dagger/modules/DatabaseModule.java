package com.rocket.sample.daggerrxsample.dagger.modules;

import android.content.Context;

import com.rocket.sample.daggerrxsample.database.RealmController;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 *  Used for providing database related dependency
 */

@Module
public class DatabaseModule {

    private Context mContext;

    public DatabaseModule(Context context) {
        mContext = context;
    }

    @Provides
    public RealmConfiguration getRealMConfiguration() {
      return new RealmConfiguration.Builder(mContext)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
    }

    @Provides
    @Singleton
    public Realm getRealm(RealmConfiguration realmConfiguration) {
        Realm.setDefaultConfiguration(realmConfiguration);
        return Realm.getDefaultInstance();
    }

    @Provides
    @Singleton
    public RealmController getRealmController(Realm realm) {
        return new RealmController(realm);
    }
}
