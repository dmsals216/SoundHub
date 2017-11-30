package com.heepie.soundhub.domain.logic;

/**
 * Created by Heepie on 2017. 11. 30..
 */

import android.databinding.ObservableArrayList;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.heepie.soundhub.Interfaces.IRetrofitCallback;
import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.viewmodel.PostViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Heepie on 2017. 11. 29..
 */

public class PostApi {
    public static ObservableArrayList<PostViewModel> posts = new ObservableArrayList<>();;

    public static void getPosts(IRetrofitCallback callback) {
        Retrofit.Builder rBuilder = new Retrofit.Builder();

        // 기본 URL 설정
        rBuilder.baseUrl(IPost.SERVER_URL);
        // Gson 팩토리로 JSON 데이터 처리 설정
        rBuilder.addConverterFactory(GsonConverterFactory.create(/*new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()*/));

        // RxJava2 어뎁터 사용 설정
        rBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        // 해당 Retrofit 생성
        Retrofit retrofit = rBuilder.build();

        // Retrofit으로 서비스 생성
        IPost server = retrofit.create(IPost.class);

        // 서비스를
        Observable<Response<List<Post>>> observable = server.getPost();

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        post -> {
                            /* 서버와 통신 테스트
                            Log.e("heepie", post.code() + "\n" +
                                    post.message() + "\n" +
                                    post.body());*/

                            // 데이터 입력
                            for (Post item : post.body()) {
                                posts.add(new PostViewModel(item));
                            }

                            /* 입력 받은 데이터 확인
                            for (PostViewModel i : posts) {
                                Log.e("heepie", i.getModel().toString());
                            }*/
                            callback.initData(post.code(), post.message(), post.body());
                        });
    }

    public interface IPost {
        public static final String SERVER_URL = "https://soundhub.che1.co.kr/";

        @GET("post")
        Observable<Response<List<Post>>> getPost();
    }
}
