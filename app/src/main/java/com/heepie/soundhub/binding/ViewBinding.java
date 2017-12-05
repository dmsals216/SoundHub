package com.heepie.soundhub.binding;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.databinding.BindingAdapter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import kotlin.io.ByteStreamsKt;
import rm.com.audiowave.AudioWaveView;
import rm.com.audiowave.OnSamplingListener;

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

    @BindingAdapter("setWave")
    public static void setWave(AudioWaveView audioWaveView, String path) {
        byte[] musicByte=null;

        // 임시 데이터 값 설정
        ObjectAnimator progressAnim;
        progressAnim = ObjectAnimator.ofFloat(audioWaveView, "progress", 0F, 100F);
        progressAnim.setInterpolator(new LinearInterpolator());
        progressAnim.setDuration(1000);

        try {
            musicByte = ByteStreamsKt.readBytes(new FileInputStream(new File(path)), 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (musicByte != null) {
            audioWaveView.setRawData(musicByte, () -> {
                progressAnim.start();
            });
        }
    }
}
