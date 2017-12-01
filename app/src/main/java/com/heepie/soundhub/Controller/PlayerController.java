package com.heepie.soundhub.Controller;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import com.heepie.soundhub.utils.Const;

import java.io.IOException;

/**
 * Created by Heepie on 2017. 12. 1..
 * 음악을 제어하는 컨트롤
 */

public class PlayerController {
    public final String TAG = getClass().getSimpleName();
    public static String playerStatus;

    private static PlayerController instance;
    private MediaPlayer player;
    private Context context;

    private PlayerController() {
        player = new MediaPlayer();
        playerStatus = Const.ACTION_MUSIC_NOT_INIT;
    }

    public static PlayerController getInstance() {
        if (instance == null)
            instance = new PlayerController();
        return instance;
    }

    public void setMusic(String url) {
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
    }

    public void play() {
        player.start();
        playerStatus = Const.ACTION_MUSIC_PLAY;
    }

    public void pause() {
        playerStatus = Const.ACTION_MUSIC_PAUSE;
        player.pause();
    }

    public void initPlayer(Context context) {
        this.context = context;
    }
}
