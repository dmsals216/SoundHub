package com.heepie.soundhub.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import com.heepie.soundhub.domain.logic.PostApi;
import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.utils.RetrofitUtil;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by eunmin on 2017-11-29.
 */

public class MusicUploadModel {

    public interface MusicUpload {
        String setModel();
        void finishActivityss();
        void logout();
    }

    Context context;
    MusicUpload listener;
    public ObservableField<String> title = new ObservableField<>("");
    public ObservableField<String> file_name = new ObservableField<>("");
    public ObservableField<String> instrument = new ObservableField<>("");
    public ObservableField<String> genre = new ObservableField<>("");

    public MusicUploadModel(Context context, MusicUpload listener) {
        this.context = context;
        this.listener = listener;
    }

    public void onClickedLogout(View view) {
        listener.logout();
    }

    public void onClickedDone(View v) {
        String mediaPath = listener.setModel();
        Retrofit retrofit = RetrofitUtil.getInstance();
        PostApi.IPost upload = retrofit.create(PostApi.IPost.class);
        File file = new File(mediaPath);
        RequestBody title1 = RequestBody.create(MediaType.parse("text/plain"), title.get());
        RequestBody instrument1 = RequestBody.create(MediaType.parse("text/plain"), instrument.get());
        RequestBody genre1 = RequestBody.create(MediaType.parse("text/plain"), genre.get());
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part author_track = MultipartBody.Part.createFormData("author_track", file.getName(), requestBody);

        Call<Post> response = upload.getLogin("Token " + Const.TOKEN + "", title1, instrument1, genre1, author_track);
        response.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.code() == 201) {
                    Toast.makeText(context, "업로드 완료 되었습니다.", Toast.LENGTH_SHORT).show();
                    listener.finishActivityss();
                }else {
                    Toast.makeText(context, "업로드 실패하였습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(context, "업로드 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onClickedCancel(View view) {
        listener.finishActivityss();
    }
}