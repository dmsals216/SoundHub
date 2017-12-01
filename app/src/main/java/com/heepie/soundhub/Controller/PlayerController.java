package com.heepie.soundhub.Controller;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import com.heepie.soundhub.utils.Const;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Heepie on 2017. 12. 1..
 * 음악을 제어하는 컨트롤
 */

public class PlayerController {
    public final String TAG = getClass().getSimpleName();
    public static String playerStatus;

    private static PlayerController instance;
    private MediaPlayer player;
    private List<MediaPlayer> playerList;

    private Context context;

    private AtomicInteger countOfsession;

    private PlayerController() {
        player = new MediaPlayer();
        playerStatus = Const.ACTION_MUSIC_NOT_INIT;
        playerList = new ArrayList<>();
        countOfsession = new AtomicInteger(0);
    }

    public static PlayerController getInstance() {
        if (instance == null)
            instance = new PlayerController();
        return instance;
    }

    /*public void setMusic(String url) {
        Log.d(TAG, "setMusic: " + url);
        new Thread(() -> {
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                player.setDataSource(url);
                player.prepare();
                play();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }*/

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
                        play();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }

    public void play() {
        for (MediaPlayer track : playerList) {
            new Thread(() -> {
                track.start();
            }).start();
        }

//        player.start();
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

    public void initPlayer(Context context) {
        this.context = context;
    }
}
