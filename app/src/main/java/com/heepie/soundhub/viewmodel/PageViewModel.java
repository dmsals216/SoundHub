package com.heepie.soundhub.viewmodel;

import android.databinding.Bindable;

import com.heepie.soundhub.domain.logic.PostApi;
import com.heepie.soundhub.domain.model.Post;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Heepie on 2017. 12. 18..
 */

public class PageViewModel {
    public static PageViewModel instance;
    private PostApi postApi;
    private List<Post> postList;

    @Bindable
    public PostsViewModel postsViewModel;

    private PageViewModel() {
        postApi = PostApi.getInstance();
        postList = new ArrayList<>();
    }

    public static PageViewModel getInstance() {
        if (instance == null)
            instance = new PageViewModel();
        return instance;
    }

    public void displayData(int type, int page_num) {
        Observable<Response<List<Post>>> postObs = postApi.getPosts(type, page_num);

        // TODO 서버 API 완료 후 진행
        postObs.observeOn(Schedulers.io())
               .subscribeOn(Schedulers.newThread())
               .subscribe(
                       jsonData -> {
                           postList = jsonData.body();
                           setData(postList);
                       }
               );
    }

    private void setData(List<Post> postList) {
        for (Post post : postList) {
            postsViewModel.addPostViewModel(new PostViewModel(post));
        }
    }
}
