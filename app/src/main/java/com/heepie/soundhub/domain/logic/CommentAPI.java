package com.heepie.soundhub.domain.logic;

import android.util.Log;

import com.heepie.soundhub.BuildConfig;
import com.heepie.soundhub.Interfaces.ICallback;
import com.heepie.soundhub.domain.model.Comment_track;
import com.heepie.soundhub.utils.Const;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Heepie on 2017. 12. 7..
 */

public class CommentAPI {
    public final String TAG = getClass().getSimpleName();
    public static CommentAPI instance;

    private Retrofit retrofit;

    private CommentAPI() {
        createRetrofit(BuildConfig.SERVER_URL);
    }

    public static CommentAPI getInstance() {
        if (instance == null)
            instance = new CommentAPI();
        return instance;
    }

    private void createRetrofit(String defaultURL) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …

// add logging as last interceptor
        httpClient.addInterceptor(logging);

        Retrofit.Builder rBuilder = new Retrofit.Builder();

        // 기본 URL 설정
        rBuilder.baseUrl(defaultURL);

        // Gson 팩토리로 JSON 데이터 처리 설정
        rBuilder.addConverterFactory(GsonConverterFactory.create());

        // RxJava2 어뎁터 사용 설정
        rBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        // 해당 Retrofit 생성
        retrofit = rBuilder.build();
    }

    public <U> void pushComment(String post_id, String instrument, U fileInfo, ICallback callback) {
        Log.d(TAG, "pushComment: " + post_id + " " + instrument + " " + fileInfo + " " + "Token " + Const.TOKEN);

        IComment service = retrofit.create(IComment.class);
        File track = null;

        // 업로드 파일 등록
        if (fileInfo instanceof File) {
            track = (File)fileInfo;
        } else if (fileInfo instanceof String) {
            track = new File((String)fileInfo);
        }

        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"),
                        track
                );
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("comment_track", track.getName(), requestFile);


        // 악기 정보 등록
        RequestBody mInstrument = RequestBody.create(MediaType.parse("text/plain"), instrument);


        Log.d(TAG, "pushComment: " + "In PushComment");

        Call<Comment_track> result = service.pushComment(post_id,
                                                        "Token a3d95f545426ac432f466d3164a735b6fa92fc31",
                                                        mInstrument,
                                                        body);

        result.enqueue(new Callback<Comment_track>() {
            @Override
            public void onResponse(Call<Comment_track> call, Response<Comment_track> response) {
                // 서버에서 응답을 했을 때 분기 (실패, 성공 모두)
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + "response OK!");

                    // Callback으로 돌아온 결과값 callback, 이후 callback 데이터 comment에 등록
                    callback.initData(response.code(), response.message(), response.body());

                } else {
                    // 응답이 실패일 경우
                    Log.d(TAG, "onResponse: " + "response Fail! " + response.code());
                }

//                결과를 callback으로 돌려준다.
//                callback.initData();
            }

            @Override
            public void onFailure(Call<Comment_track> call, Throwable t) {
                // 네트워크 실패나 서버에서 응답이 없을 때 분기
                Log.d(TAG, "onFailure: " + "Connection Fail");
            }
        });
    }


    public interface IComment {
        @Multipart
        @POST("post/{post_id}/comments/")
        Call<Comment_track> pushComment(@Path("post_id") String post_id,
                                        @Header("Authorization")String token,
                                        @Part("instrument") RequestBody instrument,
                                        @Part MultipartBody.Part track);
    }
}
