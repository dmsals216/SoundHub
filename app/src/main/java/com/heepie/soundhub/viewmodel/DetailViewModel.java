package com.heepie.soundhub.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.heepie.soundhub.BuildConfig;
import com.heepie.soundhub.controller.PlayerController;
import com.heepie.soundhub.Interfaces.ICallback;
import com.heepie.soundhub.controller.RecordController;
import com.heepie.soundhub.domain.logic.CommentAPI;
import com.heepie.soundhub.domain.logic.FileApi;
import com.heepie.soundhub.domain.model.Comment_track;
import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.utils.Const;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heepie on 2017. 12. 1..
 */

public class DetailViewModel {
    public final String TAG = getClass().getSimpleName();
    public PlayerController player;
    private RecordController recorder;
    private CommentAPI commentAPI;

    private Post post;
    private List<String> urls;
    private String url;
    private Context context;
    private String mRecordFilePath;

    private StringBuilder urlBuilder;

    public ObservableField<String> masterPath;


    public void setPost(Post post) {
        this.post = post;

        // 무조건 실행되는 Author 트랙 초기 설정
        urlBuilder = new StringBuilder();
        urlBuilder.append(BuildConfig.FILE_SERVER_URL)
                .append("media/")
                .append(post.getAuthor_track());

        url = urlBuilder.toString();

        urls.add(urlBuilder.toString());
        setMasterTrackWave();
    }

    public DetailViewModel(Context context) {
        player = PlayerController.getInstance();
        player.initPlayer(context);

        recorder = RecordController.getInstance();
        recorder.initRecorder(context);


        this.context = context;
        urls = new ArrayList<>();
        masterPath = new ObservableField<>("");

        commentAPI = CommentAPI.getInstance();

    }

    private void setMasterTrackWave() {
        FileApi.getInstance().getMusic(context, url, post.getId(), new ICallback() {
            @Override
            public void initData(int code, String msg, Object data) {
                Log.i("heepie", code+" " + msg + " " + data);
                masterPath.set((String)data);
            }
        });

    }

    public void onClickedMerge(View view) {
        Log.d(TAG, "onClickedMerge: Clicked");
        Toast.makeText(view.getContext(), "onClickedMerge", Toast.LENGTH_SHORT).show();

    }

    public void onClickedPlayPause(View view) {
        Log.d(TAG, "onClickedPlay: Clicked");
        switch (PlayerController.playerStatus) {
            case Const.ACTION_MUSIC_NOT_INIT:
//                checkSelectedTrack();

                player.setMusic(urls);
                ((Button)view).setText("일시정지");
                break;
            case Const.ACTION_MUSIC_PAUSE:
//                player.play();
                ((Button)view).setText("일시정지");
                break;

            case Const.ACTION_MUSIC_PLAY:
                player.pause();
                ((Button)view).setText("재생");
                break;
        }
    }

    private boolean onRecording = false;

    public void onClickedUpLoad(View view) {
        Log.d(TAG, "onClickedUpLoad: Clicked");
//        Toast.makeText(view.getContext(), "onClickedUpLoad", Toast.LENGTH_SHORT).show();

        // 녹음 기능
        onRecording = (onRecording == true) ? false : true;

        if (onRecording) {
            Toast.makeText(view.getContext(), "onRecording", Toast.LENGTH_SHORT).show();

            // 재생을 멈추고 녹음 시작
            player.stopPlaying();
            recorder.startRecording();

            ((Button)view).setText("녹음 중지");
        } else {
            mRecordFilePath = recorder.stopRecording();
            // 녹음을 멈추고 재생 시작
            player.startPlaying(mRecordFilePath, 0);

            commentAPI.pushComment(post.getId(), "Guitar", mRecordFilePath,
                                  (code, msg, body) -> {
                Comment_track commentTrack = ((Comment_track)body);
                Log.d(TAG, "onClickedUpLoad: " + body.toString());
                post.getComment_tracks().get(commentTrack.getInstrument()).add(commentTrack);
            });


            Toast.makeText(view.getContext(), "offRecording", Toast.LENGTH_SHORT).show();
            ((Button)view).setText("녹음 시작");
        }
    }

    // 체크박스로 선택된 track 추출
    public void checkSelectedTrack() {
        for(String instrument : post.getComment_tracks().keySet()) {
            for(Comment_track track : post.getComment_tracks().get(instrument)) {
                if (track.getIsCheck()) {
                    urlBuilder = new StringBuilder();
                    urlBuilder.append(BuildConfig.FILE_SERVER_URL)
                            .append("media/")
                            .append(track.getComment_track());

                    urls.add(urlBuilder.toString());
                }
            }
        }

        for (String url : urls)
            Log.d(TAG, "onClickedMerge: " + url);
    }

    public void onPause() {
        player.stopPlaying();
    }
}
