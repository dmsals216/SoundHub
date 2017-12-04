package com.heepie.soundhub.domain.logic;

/**
 * Created by Heepie on 2017. 11. 30..
 */

import android.databinding.ObservableArrayList;

import com.heepie.soundhub.Interfaces.ICallback;
import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.viewmodel.PostViewModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by Heepie on 2017. 11. 29..
 */

public class PostApi extends AbsApi {
    public static int populPostIndex = 0;
    public static int newPostIndex = 0;
    private static PostApi instance;
    public static ObservableArrayList<PostViewModel> posts = new ObservableArrayList<>();

    private PostApi() {

    }

    public static PostApi getInstance() {
        if (instance == null)
            instance = new PostApi();
        return instance;
    }

    @Override
    public void getData(ICallback callback) {
        // 데이터 초기화
        posts.clear();

        createRetrofit();

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

    public void getData(String category, ICallback callback) {

    }

    public interface IPost {
        @GET("post")
        Observable<Response<List<Post>>> getPost();
    }
}
