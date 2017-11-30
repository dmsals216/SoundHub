package com.heepie.soundhub.binding;

import android.app.Activity;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by Heepie on 2017. 11. 25..
 */

public class ViewBinding {
    @BindingAdapter("loadImage")
    public static void setLoadImage(ImageView view, String path) {
        Glide.with(view.getContext())
                .load(path)
                .into(view);
    }

    @BindingAdapter("loadImage")
    public static void setLoadImage(ImageView view, Uri uri) {
        Glide.with(view.getContext())
                .load(uri)
                .into(view);
    }

    @BindingAdapter("loadImage")
    public static void setLoadImage(ImageView view, int resId) {
        Glide.with(view.getContext())
                .load(resId)
                .into(view);
    }

    @BindingAdapter("loadCircleImage")
    public static void setLoadCircleImage(ImageView view, int resId) {
        Glide.with(view.getContext())
                .load(resId)
                .apply(bitmapTransform(new CircleCrop()))
                .into(view);
    }
}
