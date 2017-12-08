package com.heepie.soundhub.binding;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.heepie.soundhub.controller.PlayerController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import kotlin.io.ByteStreamsKt;
import rm.com.audiowave.AudioWaveView;
import rm.com.audiowave.OnProgressListener;
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

    // Audio 파일을 Wave로 바꿔주는 Adapter
    @BindingAdapter("setWave")
    public static void setWave(AudioWaveView audioWaveView, String path) {
        // 필요한 변수 선언
        PlayerController player = PlayerController.getInstance();
        FileInputStream inputStream = null;
        long time=0;
        byte[] musicByte=null;

        // Music Duration 추출
        try {
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            inputStream = new FileInputStream(path);
            retriever.setDataSource(inputStream.getFD());
            time = Long.parseLong(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));

            Log.d("ViewBinding", "setWave: " + time);
        } catch (Exception e) {

        }


/*        audioWaveView.setOnProgressListener(new OnProgressListener() {
            @Override
            public void onStartTracking(float v) {
//                Toast.makeText(audioWaveView.getContext(), v+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTracking(float v) {
//                Toast.makeText(audioWaveView.getContext(), v + " ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProgressChanged(float v, boolean b) {
//                Toast.makeText(audioWaveView.getContext(), v + " " + b, Toast.LENGTH_SHORT).show();
            }
        });*/


        // 임시 데이터 값 설정
        ObjectAnimator progressAnim;
        progressAnim = ObjectAnimator.ofFloat(audioWaveView, "progress", 0F, 100F);
        progressAnim.setInterpolator(new LinearInterpolator());
        progressAnim.setDuration(time);


        try {
            musicByte = ByteStreamsKt.readBytes(new FileInputStream(new File(path)), 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (musicByte != null) {
            audioWaveView.setRawData(musicByte, () -> {
//                progressAnim.start();
                player.startPlaying(path);
            });
        }
    }

    @BindingAdapter("setCurProgr")
    public static void setCurProgr(View view, ObservableField<Integer> curProgress) {
        if (view instanceof AudioWaveView) {
            if (curProgress.get() != null)
                ((AudioWaveView)view).setProgress(Float.parseFloat(curProgress.get()+""));
        }

    }

    @BindingAdapter("setVisible")
    public static void setVisible(View view, String data) {
        if (data == null)
            view.setVisibility(View.INVISIBLE);
        else
            view.setVisibility(View.VISIBLE);
    }
}
