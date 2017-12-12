package com.heepie.soundhub.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
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
import com.heepie.soundhub.view.RecordView;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
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
    public ObservableField<String> selectedInstrument;
    public ObservableField<String> countDown;

    public static DetailViewModel getInstance() {
        if (instance == null)
            instance = new DetailViewModel();
        return instance;
    }

    private DetailViewModel() {
        urls = new ArrayList<>();
        masterPath = new ObservableField<>(" ");
        selectedInstrument = new ObservableField<>(" ");
        countDown = new ObservableField<>(" ");
        commentAPI = CommentAPI.getInstance();
    }

    public void initContext(Activity activity) {
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
        // 녹음 기능
        onRecording = (onRecording == true) ? false : true;

        if (onRecording) {
            // 녹음 진행 시작
            checkSelectedTrack();
            targetView.setVisibility(View.VISIBLE);

            Observable<String> createCounter = Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(ObservableEmitter<String> e) throws Exception {
                    try {
                        String text="";
                        for (int i=3; i>=-1; i=i-1) {
                            if (i >= 0) {
                                text = (i == 0) ? "START!" : i + "";
                                e.onNext(text);
                                Thread.sleep(1000);
                            } else {
                                e.onNext(i+"");
                            }
                        }
                        e.onComplete();
                    } catch (Exception ex) {

                    }
                }
            });

            createCounter.observeOn(Schedulers.io())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(
                            string -> {
                                countDown.set(string);
                                if ("START!".equals(string)) {
                                    player.setMusic(urls);
                                    recorder.startRecording();
                                }
                            },
                            // Error 처리
                            throwable -> {},
                            // Complete 처리
                            () ->{
                                //
                                /*player.setMusic(urls);
                                recorder.startRecording();*/
                            }
                    );
            ((ImageButton)v).setImageResource(android.R.drawable.ic_media_pause);
        } else {
            // 녹음이 진행 중지
            mRecordFilePath = recorder.stopRecording();
            // 녹음을 멈추고 재생 시작
            player.startPlaying(mRecordFilePath, 0);
            player.pause();
            ((ImageButton)v).setImageResource(android.R.drawable.ic_media_play);
        }
    }

    public void onUploadFrAudio (View v, Activity callFrom) {
        Log.d(TAG, "onUploadFrAudio: " + selectedInstrument.get());
        if (mRecordFilePath == null) {
            Toast.makeText(v.getContext(), "먼저 녹음을 해주세요.", Toast.LENGTH_SHORT).show();
        } else if(" ".equals(selectedInstrument.get()) || "Select your instrument".equals(selectedInstrument.get())) {
            Toast.makeText(v.getContext(), "Select your instrument", Toast.LENGTH_SHORT).show();
        } else {
            commentAPI.pushComment(post.getId(), selectedInstrument.get(), mRecordFilePath,
            (code, msg, body) -> {
                Comment_track commentTrack = ((Comment_track)body);
                Log.d(TAG, "onClickedUpLoad: " + body.toString());
                post.getComment_tracks().get(commentTrack.getInstrument()).add(commentTrack);
            });
            callFrom.finish();
        }
    }

    public void onUploadFrFile (View v, View filePath, Activity callFrom) {
        Toast.makeText(v.getContext(), "onClicked Upload From File Btn " + ((TextView)filePath).getText() + " Instrument: " + selectedInstrument.get(), Toast.LENGTH_SHORT).show();
        /*if (filePath == null)
            Toast.makeText(v.getContext(), "먼저 파일을 선택해 해주세요.", Toast.LENGTH_SHORT).show();
        else {
            commentAPI.pushComment(post.getId(), selectedInstrument.get(), ((TextView)filePath).getText().toString(),
                    (code, msg, body) -> {
                        Comment_track commentTrack = ((Comment_track)body);
                        Log.d(TAG, "onClickedUpLoad: " + body.toString());
//                        post.getComment_tracks().get(commentTrack.getInstrument()).add(commentTrack);
                    });
            callFrom.finish();
        }*/
    }

    public void onClickedRepeat(View v) {
        player.stopPlaying();
        player.startPlaying(mRecordFilePath, 0);
    }
}
