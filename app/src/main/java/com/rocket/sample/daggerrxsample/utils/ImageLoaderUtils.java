package com.rocket.sample.daggerrxsample.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 *  Helper class for image related operations
 */
public class ImageLoaderUtils {

    /**
     * loads image from server
     *
     * @param imageView
     * @param url
     */
    public static void setThumbnailImage(final ImageView imageView, String url, final int defaultImage) {
        Glide.with(imageView.getContext())
                .load(url)
                .priority(Priority.IMMEDIATE)
                .placeholder(defaultImage)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }

}
