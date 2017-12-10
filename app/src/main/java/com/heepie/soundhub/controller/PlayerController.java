package com.heepie.soundhub.controller;

import android.content.Context;
import android.databinding.ObservableField;
import android.media.AudioManager;
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
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
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

    // Dispose 가능한 객체 선언
    private Disposable durationTimer;

    // Dispose 가능한 객체를 담아 놓을 수 있는 Container 선언
    private CompositeDisposable observableDisposal;

    private PlayerController() {
        mPlayer = new MediaPlayer();
        playerStatus = Const.ACTION_MUSIC_NOT_INIT;
        playerList = new ArrayList<>();
        countOfsession = new AtomicInteger(0);

        curDuration = new ObservableField<>();
        observableDisposal = new CompositeDisposable();

    }

    public static PlayerController getInstance() {
        if (instance == null)
            instance = new PlayerController();
        return instance;
    }

    public void setMusic(List<String> urls) {
        for (String url : urls) {
            Log.d(TAG, "setMusic: " + url);

            new Thread(() -> {
                MediaPlayer track = new MediaPlayer();
                track.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    track.setDataSource(url);
                    track.prepare();
                    playerList.add(track);
                    countOfsession.set(countOfsession.get()+1);

                    // 모든 session이 준비가 완료되었다면 play 실행
                    if (countOfsession.get() == urls.size())
                        play(playerList);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();
        }
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
        // Dispose 객체 Clear
        observableDisposal.clear();
        mPlayer.reset();
    }

    public void initPlayer(Context context) {
        this.context = context;
    }

    // 1초마다 값을 변경하는 Observable
    private Observable<Float> makeDurationSubscr() {
        Observable mDurationSubscr = Observable.create(new ObservableOnSubscribe<Float>() {
            @Override
            public void subscribe(ObservableEmitter<Float> e) throws Exception {
                try {
                    for (int i = 1; i < maxDuration+1; i = i + 1) {
                        e.onNext((i*(100/Float.parseFloat(maxDuration+""))));
                        Thread.sleep(1000);
                    }
                    e.onComplete();
                } catch (Exception ex) {

                }
            }
        });

        return mDurationSubscr;
    }

    // 해당 Observable을 실행하는 메소드
    private void startDurationTimer() {
        durationTimer =
                makeDurationSubscr()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            curProgress -> {
                                Log.d(TAG, "startDurationTimer: " + curProgress);
                                curDuration.set(curProgress);
                            }
                    );
        // Container에 등록
        observableDisposal.add(durationTimer);
    }


}