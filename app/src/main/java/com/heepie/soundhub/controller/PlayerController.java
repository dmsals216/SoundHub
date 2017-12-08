package com.heepie.soundhub.controller;

import android.content.Context;
import android.databinding.ObservableField;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.heepie.soundhub.utils.Const;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Heepie on 2017. 12. 1..
 * 음악을 제어하는 컨트롤
 */

public class PlayerController {
    public final String TAG = getClass().getSimpleName();
    public static String playerStatus;

    private static PlayerController instance;
    private MediaPlayer mPlayer;
    private List<MediaPlayer> playerList;

    private Context context;
    private AtomicInteger countOfsession;

    private int maxDuration;
    public ObservableField<Float> curDuration;

    private PlayerController() {
        mPlayer = new MediaPlayer();
        playerStatus = Const.ACTION_MUSIC_NOT_INIT;
        playerList = new ArrayList<>();
        countOfsession = new AtomicInteger(0);

        curDuration = new ObservableField<>();

    }

    public static PlayerController getInstance() {
        if (instance == null)
            instance = new PlayerController();
        return instance;
    }

    public void setMusic(List<String> urls) {
        // audio 파형을 그리기 위한 데이터
        File file = new File(context.getExternalFilesDir(null) + File.separator + "heepie2.mp3");
        if(file.exists()){
            MediaPlayer player = MediaPlayer.create(context, Uri.fromFile(file));

            player.start();
        } else {
            Log.e("heepie", "없음");
        }

        /*public void setMusic(String url) {
            Log.d(TAG, "setMusic: " + url);
            new Thread(() -> {
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    player.setDataSource(url);
                    player.prepare();
                    play(playerList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }).start();*/

    }

    private void play(List<MediaPlayer> playerList) {
        for (MediaPlayer track : playerList) {
            new Thread(() -> {
                track.start();
            }).start();
        }
        playerStatus = Const.ACTION_MUSIC_PLAY;
    }

    public void pause() {
        for (MediaPlayer track : playerList) {
            new Thread(() -> {
                track.pause();
            }).start();
        }
        playerStatus = Const.ACTION_MUSIC_PAUSE;
    }

    public void startPlaying(String mFileName, int duration) {
        Log.d(TAG, "startPlaying: " + mFileName);
        try {
            this.maxDuration = duration/1000;
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
            startDurationTimer();
        } catch (IOException e) {
            Log.e(TAG, "prepare() failed");
        }
    }

    public void stopPlaying() {
        mPlayer.reset();
    }

    public void initPlayer(Context context) {
        this.context = context;
    }


    private Observable<Float> makeDurationSubscr() {
        Observable mDurationSubscr = Observable.create(new ObservableOnSubscribe<Float>() {
            @Override
            public void subscribe(ObservableEmitter<Float> e) throws Exception {
                try {
                    for (int i = 0; i < maxDuration; i = i + 1) {
                        e.onNext((i*(100/Float.parseFloat(maxDuration+""))));
                        Thread.sleep(1000);
                    }
                    e.onComplete();
                } catch (Exception ex) {
                    e.onError(ex.getCause());
                }
            }
        });

        return mDurationSubscr;
    }

    private void startDurationTimer() {
        makeDurationSubscr()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        curProgress -> {
                            Log.d(TAG, "startDurationTimer: " + curProgress);
                            curDuration.set(curProgress);
                        }
                );
    }
}