package com.heepie.soundhub.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.view.View;

import com.heepie.soundhub.Interfaces.IModelGettable;
import com.heepie.soundhub.R;
import com.heepie.soundhub.model.TestPost;
import com.heepie.soundhub.model.TestUser;

/**
 * Created by Heepie on 2017. 11. 24..
 * View Model은 View를 참조하지 않는다. 오직 비즈니스 로직(데이터를 표현하는 방법만 집중)
 * ViewModel은 생산자, View는 소비자
 * 소비자는 생산자가 누구인지 알아야하지만, 생산자는 소비자가 누구인지 몰라도 된다.
 */

public class PostsViewModel extends BaseObservable implements IModelGettable<ObservableArrayList<PostViewModel>>
{
    @Bindable
    public ObservableArrayList<PostViewModel> posts;

    public PostsViewModel()
    {
        posts = new ObservableArrayList<>();
        // 초기 데이터 설정 필요

        // 더미 아이템 테스트
        /*this.posts = new ObservableArrayList<>();
        for (int i=index; i<index + Const.DEFAULT_COUNT_OF_SHOW_ITEM; i=i+1) {
            posts.add(new PostViewModel(new TestPost(new TestUser("OrigiPpopulPost ", R.drawable.test2, "1 "), "Title ",
                    R.drawable.test2,
                    "05:00",
                    "10 ",
                    "15 ",
                    "#Vocal #Piano",
                    true)));
        }*/
    }

    public void addPost(TestUser testUser, String title, int post_image_path, String music_length, String like_count, String comment_count, String tag, boolean isShow) {
        this.posts.add(new PostViewModel(new TestPost(testUser, title, post_image_path, music_length, like_count, comment_count, tag, isShow)));
    }

    public ObservableArrayList<PostViewModel> getModel() {
        return posts;
    }

    public void setPosts(ObservableArrayList<PostViewModel> posts) {
        this.posts = posts;
    }

    // 네트워크로 입력 받은 데이터 추가
    public void onClickedPopulPostMoreBtn(View v) {
        // 더미 데이터
        for (int i=0; i<4; i=i+1) {
            posts.add(new PostViewModel(new TestPost(new TestUser("AddPopulPost ", R.drawable.test2, "1 "), "Title ",
                    R.drawable.test2,
                    "05:00",
                    "10 ",
                    "15 ",
                    "#Vocal #Piano",
                    true)));
        }
    }

    // 네트워크로 입력 받은 데이터 추가
    public void onClickedNewPostMoreBtn(View v) {
        // 더미 데이터
        for (int i=0; i<4; i=i+1) {
            posts.add(new PostViewModel(new TestPost(new TestUser("AddPopulPost ", R.drawable.test3, "1 "), "Title ",
                    R.drawable.test3,
                    "05:00",
                    "10 ",
                    "15 ",
                    "#Vocal #Piano",
                    true)));
        }
    }
}
