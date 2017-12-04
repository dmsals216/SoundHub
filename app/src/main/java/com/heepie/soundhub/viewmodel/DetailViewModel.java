package com.heepie.soundhub.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.heepie.soundhub.BuildConfig;
import com.heepie.soundhub.Controller.PlayerController;
import com.heepie.soundhub.domain.model.Comment_tracks;
import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.utils.Const;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heepie on 2017. 12. 1..
 */

public class DetailViewModel {
    public final String TAG = getClass().getSimpleName();
    private PlayerController player;
    private Post post;
    private List<String> urls;

    public void setPost(Post post) {
        this.post = post;

        // 무조건 실행되는 Author 트랙 초기 설정
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(BuildConfig.FILE_SERVER_URL)
                .append("media/")
                .append(post.getAuthor_track());
        urls.add(urlBuilder.toString());
    }

    public DetailViewModel(Context context) {
        this.player = PlayerController.getInstance();
        player.initPlayer(context);
        urls = new ArrayList<>();
    }

    public void onClickedMerge(View view) {
        Log.d(TAG, "onClickedMerge: Clicked");
        Toast.makeText(view.getContext(), "onClickedMerge", Toast.LENGTH_SHORT).show();


    }

    public void onClickedPlayPause(View view) {
        Log.d(TAG, "onClickedPlay: Clicked");
        switch (PlayerController.playerStatus) {
            case Const.ACTION_MUSIC_NOT_INIT:
                checkSelectedTrack();

                player.setMusic(urls);
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

    // 체크박스로 선택된 track 추출
    public void checkSelectedTrack() {
        // 임시 데이터
        for (Comment_tracks track : post.getComment_tracks()) {
            Log.d(TAG, "checkSelectedTrack: Url " + track.getComment_track());
            urls.add(track.getComment_track());
        }
    }
}
