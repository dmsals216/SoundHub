package com.heepie.soundhub.controller;

import android.content.Context;
import android.databinding.ObservableField;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.utils.MusicUtil;
import com.heepie.soundhub.utils.TimeUtil;

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
    public ObservableField<String> curTime;
    private int curIndex;

    // Dispose 가능한 객체 선언
    private Disposable durationTimer;

    // Dispose 가능한 객체를 담아 놓을 수 있는 Container 선언
    private CompositeDisposable observableDisposal;

    private String masterPath;
    private boolean isPreparePlayers = false;

    private PlayerController() {
        mPlayer = new MediaPlayer();
        playerStatus = Const.ACTION_MUSIC_NOT_INIT;
        playerList = new ArrayList<>();
        countOfsession = new AtomicInteger(0);

        curDuration = new ObservableField<>();
        observableDisposal = new CompositeDisposable();
        curTime = new ObservableField<>(" ");
        curIndex = 1;
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
                        isPreparePlayers = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }

    public void setMasterMusic(String mFileName, int duration) {
        masterPath = mFileName;
        stopPlaying();
        Log.d(TAG, "startPlaying: " + masterPath);
        this.maxDuration = duration/1000;
        try {
            mPlayer.setDataSource(masterPath);
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        playerStatus = Const.ACTION_MUSIC_PREPARE;
    }

    public void play() {
        if (isPreparePlayers) {
            for (MediaPlayer track : playerList) {
                new Thread(() -> {
                    track.start();
                }).start();
            }
            playerStatus = Const.ACTION_MUSIC_PLAY;
        } else {
            Toast.makeText(context, "음악 준비 중입니다.", Toast.LENGTH_SHORT).show();
        }
    }

    public void pause() {
        for (MediaPlayer track : playerList) {
            new Thread(() -> {
                track.pause();
            }).start();
        }
        playerStatus = Const.ACTION_MUSIC_PAUSE;
    }

    public void startPlaying() {
        if (!mPlayer.isPlaying()) {
            mPlayer.start();
            startDurationTimer();
        } else {
            mPlayer.notify();
            durationTimer.notify();
        }
        playerStatus = Const.ACTION_MUSIC_PLAY;
    }

    public void stopPlaying() {
        // Dispose 객체 Clear
        observableDisposal.clear();
        mPlayer.reset();
        playerStatus = Const.ACTION_MUSIC_PAUSE;
    }

    public void pausePlayer() {
        mPlayer.pause();
        durationTimer.dispose();
        playerStatus = Const.ACTION_MUSIC_PAUSE;
    }

    public void setCurPlayer(float curDuration) {
        Log.d(TAG, "setCurPlayer: " + curDuration);
        currIndex = (int)curDuration/1000;
        mPlayer.seekTo((int)curDuration);
    }

    public void initPlayer(Context context) {
        this.context = context;
    }

    // 1초마다 값을 변경하는 Observable
    private Observable<Integer> makeDurationSubscr() {
        Observable mDurationSubscr = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                try {
                    for (int i = curIndex; i < maxDuration+1; i = i + 1) {
                        e.onNext(1);
                        Thread.sleep(1000);
                        curIndex += 1;
                    }
                    e.onComplete();
                } catch (Exception ex) {

                }
            }
        });

        return mDurationSubscr;
    }

    int currIndex = 0;

    // 해당 Observable을 실행하는 메소드
    private void startDurationTimer() {
        durationTimer =
                makeDurationSubscr()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            curProgress -> {
                                if (currIndex < maxDuration) {
                                    currIndex += curProgress;
                                    Log.d(TAG, "startDurationTimer: " + curProgress);
                                    curDuration.set(MusicUtil.durationToPercent(currIndex, maxDuration));
                                    curTime.set(TimeUtil.secondToMMSS(currIndex) + " / " + TimeUtil.secondToMMSS(maxDuration));
                                }
                            }
                    );
        // Container에 등록
        observableDisposal.add(durationTimer);
    }

    public void initData() {
        currIndex = 0;
        curIndex = 0;
        curDuration.set(0f);
        curTime.set(" ");
    }
}