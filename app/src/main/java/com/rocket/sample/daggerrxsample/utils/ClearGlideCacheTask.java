package com.rocket.sample.daggerrxsample.utils;

import android.os.AsyncTask;

import com.bumptech.glide.Glide;
import com.rocket.sample.daggerrxsample.DaggerRxApplication;

/**
 *  Used to clear glide cache in case of low memory
 */
public class ClearGlideCacheTask extends AsyncTask<Void, Void, Boolean> {

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            Glide.get(DaggerRxApplication.getContext()).clearDiskCache();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
