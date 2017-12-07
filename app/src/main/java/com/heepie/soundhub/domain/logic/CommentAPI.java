package com.heepie.soundhub.domain.logic;

import android.util.Log;

import com.heepie.soundhub.BuildConfig;
import com.heepie.soundhub.domain.model.Comment_track;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
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

    public void createRetrofit(String defaultURL) {
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

    public void pushComment(String user_id, Comment_track track) {
        IComment service = retrofit.create(IComment.class);
        Call<Response<ResponseBody>> result = service.pushComment(user_id, track);
        result.enqueue(new Callback<Response<ResponseBody>>() {
            @Override
            public void onResponse(Call<Response<ResponseBody>> call, Response<Response<ResponseBody>> response) {
                // 서버에서 응답을 했을 때 분기 (실패, 성공 모두)
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + "response OK!");
                } else {
                    // 응답이 실패일 경우
                }
            }

            @Override
            public void onFailure(Call<Response<ResponseBody>> call, Throwable t) {
                // 네트워크 실패나 서버에서 응답이 없을 때 분기
            }
        });
    }


    public interface IComment {
        @POST("post/{user_id}/comment")
        Call<Response<ResponseBody>> pushComment(@Path("user_id") String user_id, @Body Comment_track track);
    }
}
