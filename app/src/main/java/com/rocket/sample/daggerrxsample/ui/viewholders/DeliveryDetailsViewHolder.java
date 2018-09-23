package com.rocket.sample.daggerrxsample.ui.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rocket.sample.daggerrxsample.R;
import com.rocket.sample.daggerrxsample.model.DeliveryDetails;
import com.rocket.sample.daggerrxsample.model.RxEvents;
import com.rocket.sample.daggerrxsample.utils.ImageLoaderUtils;

/**
 *  Used for displaying the delivery list
 */

public class DeliveryDetailsViewHolder extends BaseViewHolder {

    private ImageView mImageView;
    private TextView mDescription;
    private RelativeLayout mProgressContainer;
    private LinearLayout mDescriptionContainer;

    public DeliveryDetailsViewHolder(@NonNull ViewGroup parent, int layout) {
        super(parent, layout);

        mImageView = getBaseView().findViewById(R.id.imageView);
        mDescription = getBaseView().findViewById(R.id.description);
        mProgressContainer = getBaseView().findViewById(R.id.progressBarContainer);
        mDescriptionContainer = getBaseView().findViewById(R.id.descriptionContainer);
    }

    @Override
    public <T> void onBindData(T data) {
        if (data instanceof DeliveryDetails) {
            final DeliveryDetails deliveryDetails = (DeliveryDetails) data;
            switch (deliveryDetails.getTrayType()) {
                case 1://Show progress bar
                    mProgressContainer.setVisibility(View.VISIBLE);
                    mDescriptionContainer.setVisibility(View.GONE);
                    break;

                default:
                    mProgressContainer.setVisibility(View.GONE);
                    mDescriptionContainer.setVisibility(View.VISIBLE);
                    mDescription.setText(deliveryDetails.getDescription());
                    ImageLoaderUtils.setThumbnailImage(mImageView, deliveryDetails.getImageUrl(), R.mipmap.ic_launcher);
                    mDescriptionContainer.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            RxEvents events = new RxEvents();
                            events.setFlag(RxEvents.EVENT_DELIVERY_ITEM_SELECTED);
                            events.setData(deliveryDetails);
                            mRxBus.send(events);// Notify when item list is clicked
                        }
                    });
                    break;
            }

        }
    }


}
