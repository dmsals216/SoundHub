package com.heepie.soundhub.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.heepie.soundhub.domain.logic.PostApi;
import com.heepie.soundhub.domain.model.Post;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by Heepie on 2017. 12. 18..
 */

public class PageViewModel extends BaseObservable {
    public static PageViewModel instance;
    private PostApi postApi;
    private List<Post> postList;

    // For Dummy
    private int index;
    @Bindable
    public PostsViewModel postsViewModel;

    private PageViewModel() {
        index=0;
        postApi = PostApi.getInstance();
        postList = new ArrayList<>();
        postsViewModel = new PostsViewModel();
    }

    public static PageViewModel getInstance() {
        if (instance == null)
            instance = new PageViewModel();
        return instance;
    }

    public void displayData(int type, int page_num) {
        // TODO 서버 API 완료 후 진행
        /*Observable<Response<List<Post>>> postObs = postApi.getPosts(type, page_num);
        postObs.observeOn(Schedulers.io())
               .subscribeOn(Schedulers.newThread())
               .subscribe(
                       jsonData -> {
                           postList = jsonData.body();
                           setData(postList);
                       }
               );*/

        // Dummy Data
        int startIndex = index;
        for (int i=startIndex; i<index+15; i=i+1) {
            Post dummyPost = new Post();
            dummyPost.setId(i+"");
            dummyPost.setAuthor("Tester_"+i);
            dummyPost.setNum_liked(i+"");
            dummyPost.setNum_comments(i+"");
            postsViewModel.addPostViewModel(new PostViewModel(dummyPost));
        }
        index += 15;
    }

    private void setData(List<Post> postList) {
        for (Post post : postList) {
            postsViewModel.addPostViewModel(new PostViewModel(post));
        }
    }
}
