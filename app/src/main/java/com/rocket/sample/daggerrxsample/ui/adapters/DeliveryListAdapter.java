package com.rocket.sample.daggerrxsample.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.rocket.sample.daggerrxsample.R;
import com.rocket.sample.daggerrxsample.model.DeliveryDetails;
import com.rocket.sample.daggerrxsample.ui.viewholders.DeliveryDetailsViewHolder;

import java.util.ArrayList;

public class DeliveryListAdapter extends RecyclerView.Adapter<DeliveryDetailsViewHolder> {

    private ArrayList<DeliveryDetails> mDeliverList = new ArrayList<>();

    /**
     *  Adds new delivery details to the recycler view
     * @param deliveryList
     */
    public void setDeliveryList(ArrayList<DeliveryDetails> deliveryList) {
        if (deliveryList != null && deliveryList.size() > 0) {
            mDeliverList.addAll(deliveryList);
            notifyDataSetChanged();
        }
    }
    @NonNull
    @Override
    public DeliveryDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DeliveryDetailsViewHolder(viewGroup, R.layout.delivery_list_item);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryDetailsViewHolder deliveryDetailsViewHolder, int position) {
        if (mDeliverList != null && mDeliverList.size() > position) {
            deliveryDetailsViewHolder.onBindData(mDeliverList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mDeliverList.size();
    }

    /**
     *  Adds a new item at the end of the recycler view
     * @param deliveryDetails
     */
    public void addFooter(DeliveryDetails deliveryDetails) {
        mDeliverList.add(deliveryDetails);
        notifyItemInserted(mDeliverList.size() -1);
    }

    /**
     *  Removes the last item present in the recycler view
     */
    public void removeFooter() {
        if (mDeliverList != null && mDeliverList.size() > 0) {
            int footerPosition = mDeliverList.size() - 1;
            mDeliverList.remove(footerPosition);
            notifyItemRemoved(footerPosition);
        }
    }
}
