package com.heepie.soundhub.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.heepie.soundhub.BuildConfig;
import com.heepie.soundhub.Controller.PlayerController;
import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.utils.Const;

/**
 * Created by Heepie on 2017. 12. 1..
 */

public class DetailViewModel {
    public final String TAG = getClass().getSimpleName();
    private PlayerController player;
    private Post post;

    public void setPost(Post post) {
        this.post = post;
    }

    public DetailViewModel(Context context) {
        this.player = PlayerController.getInstance();
        player.initPlayer(context);
    }

    public void onClickedMerge(View view) {
        Log.d(TAG, "onClickedMerge: Clicked");
        Toast.makeText(view.getContext(), "onClickedMerge", Toast.LENGTH_SHORT).show();
    }

    public void onClickedPlayPause(View view) {
        Log.d(TAG, "onClickedPlay: Clicked");
        switch (PlayerController.playerStatus) {
            case Const.ACTION_MUSIC_NOT_INIT:
                StringBuilder urlBuilder = new StringBuilder();
                urlBuilder.append(BuildConfig.FILE_SERVER_URL)
                          .append("media/")
                          .append(post.getAuthor_track());

                player.setMusic(urlBuilder.toString());
                ((Button)view).setText("일시정지");
                break;
            case Const.ACTION_MUSIC_PAUSE:
                player.play();
                ((Button)view).setText("일시정지");
                break;

            case Const.ACTION_MUSIC_PLAY:
                player.pause();
                ((Button)view).setText("재생");
                break;
        }
    }

    public void onClickedUpLoad(View view) {
        Log.d(TAG, "onClickedUpLoad: Clicked");
        Toast.makeText(view.getContext(), "onClickedUpLoad", Toast.LENGTH_SHORT).show();
    }
}
