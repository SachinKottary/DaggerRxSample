package com.rocket.sample.daggerrxsample.ui.viewholders;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.rocket.sample.daggerrxsample.DaggerRxApplication;
import com.rocket.sample.daggerrxsample.network.NetworkManager;
import com.rocket.sample.daggerrxsample.rx.RxBus;

import javax.inject.Inject;

/**
 *  Provides base implementation for all the view holders
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    @Inject
    protected RxBus mRxBus;

    @Inject
    protected NetworkManager mRSNetworkManager;

    /**
     * Create a new tile. Use this constructor when the tile should not create a new view, but
     * instead just update properties of existing views.
     *
     * @param view The view for this tile.
     */
    public BaseViewHolder(View view) {
        super(view);
    }

    /**
     * Create a new tile. Use this constructor when the tile should not create a new view, but
     * instead just update properties of existing views.
     *
     * @param view The view for this tile.
     */
    public BaseViewHolder(Context context, View view) {
        super(view);
    }

    /**
     * Create a new tile.
     *
     * @param parent   The parent {@link ViewGroup} to which this tile's view will be attached later.
     *                 Used to properly generate {@link ViewGroup.LayoutParams}.
     * @param resource The layout resource ID to inflate for this tile's view.
     */
    public BaseViewHolder(ViewGroup parent, int resource) {
        super(LayoutInflater.from(parent.getContext()).inflate(resource, parent, false));
        ((DaggerRxApplication) parent.getContext().getApplicationContext()).getAppComponent().inject(this);
    }

    /**
     * Returns the view for this tile.
     */
    public View getBaseView() {
        return itemView;
    }


    public abstract <T> void onBindData(T data);


}
