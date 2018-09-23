package com.rocket.sample.daggerrxsample.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rocket.sample.daggerrxsample.R;
import com.rocket.sample.daggerrxsample.contracts.ListFragmentContract;
import com.rocket.sample.daggerrxsample.model.DeliveryDetails;
import com.rocket.sample.daggerrxsample.presenters.ListFragmentPresenter;
import com.rocket.sample.daggerrxsample.ui.adapters.DeliveryListAdapter;
import com.rocket.sample.daggerrxsample.utils.DialogUtils;
import com.rocket.sample.daggerrxsample.widgets.EndlessRecyclerViewOnScrollListener;

import java.util.ArrayList;

/**
 *  Used to display a list of deliveries
 */
public class ListingFragment extends BaseFragment implements ListFragmentContract.View {

    private static final String TAG = ListingFragment.class.getSimpleName();
    private ListFragmentPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private DeliveryListAdapter mAdapter;
    private LinearLayout mErrorMessageContainer;
    private RelativeLayout mProgressBarContainer;
    private TextView mErrorMessageView;
    private boolean mIsDataLoading;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_delivery_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        mPresenter = new ListFragmentPresenter(this, mNetworkManager, mRealmController);
        showProgress();
        mPresenter.loadDeliveryDetails(0);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new DeliveryListAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewOnScrollListener(manager) {
            @Override
            public void onLoadMore(int totalItemsCount, RecyclerView view) {
                if (!mIsDataLoading && mAdapter != null && mAdapter.getItemCount() > 0) {
                    mIsDataLoading = true;
                    int nextOffset = mAdapter.getItemCount() + 1;//The next offset to request
                    addProgressBarToRecycler();
                    if (mPresenter != null) {
                        mPresenter.loadDeliveryDetails(nextOffset);
                    }
                }
            }
        });
    }

    private void initViews() {
        mRecyclerView = getView().findViewById(R.id.recycler_view);
        mErrorMessageContainer = getView().findViewById(R.id.errorMessageContainer);
        mErrorMessageView = getView().findViewById(R.id.errorMessage);
        mProgressBarContainer = getView().findViewById(R.id.progressBarContainer);
    }

    /**
     *  Displays a loading progress at the bottom of recycler when pagination in progress
     */
    private void addProgressBarToRecycler() {
        if (mAdapter != null) {
            DeliveryDetails progressLoader = new DeliveryDetails(1);
            mAdapter.addFooter(progressLoader);
        }
    }


    @Override
    public boolean handleNetworkState() {
        return true;
    }

    @Override
    public void onNetworkDisConnected() {
        DialogUtils.showNetworkErrorDialog(getContext(), getResources());
    }

    @Override
    public void addDeliveryDetails(ArrayList<DeliveryDetails> deliveryDetailList) {
        hideProgress();
        if (mAdapter != null && deliveryDetailList != null) {
            showRecyclerView();
            removeProgressFooter();
            mAdapter.setDeliveryList(deliveryDetailList);
        }
        mIsDataLoading = false;
    }

    /**
     *  Removes the progressbar from the bottom of recycler view
     */
    private void removeProgressFooter() {
        if (mIsDataLoading && mAdapter.getItemCount() > 0) {
            mAdapter.removeFooter();
        }
    }

    @Override
    public void showProgress() {
        if (mProgressBarContainer != null) {
            mProgressBarContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (mProgressBarContainer != null) {
            mProgressBarContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(int stringResource) {
        hideProgress();
        if (isDeliveryListEmpty()) {//Update error screen when no data is present
            mRecyclerView.setVisibility(View.GONE);
            mErrorMessageContainer.setVisibility(View.VISIBLE);
            mErrorMessageView.setText(getString(stringResource));
        } else {
            removeProgressFooter();
        }
    }

    @Override
    public void hideNoNetworkDialog() {
        DialogUtils.hideNoNetworkDialog();
    }

    @Override
    public void onNetworkConnected() {
        if (isDeliveryListEmpty() && mPresenter != null) {//Load delivery item only if its empty, otherwise pagination will take it
            mRecyclerView.setVisibility(View.VISIBLE);
            mErrorMessageContainer.setVisibility(View.GONE);
            showProgress();
            mPresenter.loadDeliveryDetails(0);
        }
    }

    private void showRecyclerView() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mErrorMessageContainer.setVisibility(View.GONE);
        hideProgress();
    }

    private boolean isDeliveryListEmpty() {
        return mAdapter != null && mAdapter.getItemCount() == 0;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mPresenter != null) {
            mPresenter.destroy();
        }
    }
}
