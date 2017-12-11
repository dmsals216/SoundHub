package com.heepie.soundhub.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.heepie.soundhub.BuildConfig;
import com.heepie.soundhub.R;
import com.heepie.soundhub.controller.PlayerController;
import com.heepie.soundhub.Interfaces.ICallback;
import com.heepie.soundhub.controller.RecordController;
import com.heepie.soundhub.domain.logic.CommentAPI;
import com.heepie.soundhub.domain.logic.FileApi;
import com.heepie.soundhub.domain.model.Comment_track;
import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.view.RecordView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Heepie on 2017. 12. 1..
 */

public class DetailViewModel {
    public final String TAG = getClass().getSimpleName();
    public static DetailViewModel instance;

    public PlayerController player;
    private RecordController recorder;
    private CommentAPI commentAPI;

    private Post post;
    public ArrayList<String> urls;
    private String url;
    private Context context;
    private String mRecordFilePath;

    private StringBuilder urlBuilder;
    public ObservableField<String> masterPath;

    private Activity activity;

    public static DetailViewModel getInstance() {
        if (instance == null)
            instance = new DetailViewModel();
        return instance;
    }

    private DetailViewModel() {
        urls = new ArrayList<>();
        masterPath = new ObservableField<>("");
        commentAPI = CommentAPI.getInstance();
    }

    public void initContext(Activity activity) {
        this.activity = activity;
        this.context = activity;
        player = PlayerController.getInstance();
        player.initPlayer(context);

        recorder = RecordController.getInstance();
        recorder.initRecorder(context);
    }

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
                checkSelectedTrack();

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
        checkSelectedTrack();

        Intent intent = new Intent(view.getContext(), RecordView.class);
        view.getContext().startActivity(intent);
    }

    // 체크박스로 선택된 track 추출
    private void checkSelectedTrack() {
        for(String instrument : post.getComment_tracks().keySet()) {
            for(Comment_track track : post.getComment_tracks().get(instrument)) {
                if (track.getIsCheck()) {
                    urlBuilder = new StringBuilder();
                    urlBuilder.append(BuildConfig.FILE_SERVER_URL)
                            .append("media/")
                            .append(track.getComment_track());

                    Log.d(TAG, "checkSelectedTrack: " + urlBuilder.toString());

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


    public void onClickedRecord(View v, View targetView) {
        Toast.makeText(v.getContext(), "onClickedRecord", Toast.LENGTH_SHORT).show();
        checkSelectedTrack();

        /*targetView.setVisibility(View.VISIBLE);

        Observable<String> createCounter = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                try {
                    String text="";
                    for (int i=3; i>=0; i=i-1) {
                        text = (i == 0) ? "START!" : i+"";
                        e.onNext(text);
                        Thread.sleep(1000);
                    }
                    e.onComplete();
                } catch (Exception ex) {

                }
            }
        });

        createCounter.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        string -> {
                            Log.d(TAG, "onClickedRecord: " + string);
                        }
                );
*/

        // 녹음 기능
        onRecording = (onRecording == true) ? false : true;

        player.setMusic(urls);
        if (onRecording) {
            // 재생을 멈추고 녹음 시작
            player.stopPlaying();
            recorder.startRecording();
            ((ImageButton)v).setImageResource(android.R.drawable.btn_minus);
        } else {
            mRecordFilePath = recorder.stopRecording();
            // 녹음을 멈추고 재생 시작
            player.startPlaying(mRecordFilePath, 0);
            player.pause();
            ((ImageButton)v).setImageResource(android.R.drawable.btn_minus);

        }
    }

    public void onUploadFrAudio (View v, Activity callFrom) {
        if (mRecordFilePath == null)
            Toast.makeText(v.getContext(), "먼저 녹음을 해주세요.", Toast.LENGTH_SHORT).show();
        else {
            commentAPI.pushComment(post.getId(), "Keyboard", mRecordFilePath,
            (code, msg, body) -> {
                Comment_track commentTrack = ((Comment_track)body);
                Log.d(TAG, "onClickedUpLoad: " + body.toString());
                post.getComment_tracks().get(commentTrack.getInstrument()).add(commentTrack);
            });
            callFrom.finish();
        }


    }

    public void onUploadFrFile (View v, Activity callFrom) {
        Toast.makeText(v.getContext(), "onClicked Upload From File Btn", Toast.LENGTH_SHORT).show();
        callFrom.finish();
    }

    public void onClickedRepeat(View v) {
        player.stopPlaying();
        player.startPlaying(mRecordFilePath, 0);
    }
}
