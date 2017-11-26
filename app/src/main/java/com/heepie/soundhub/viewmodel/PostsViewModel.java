package com.heepie.soundhub.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import com.heepie.soundhub.model.Post;
import com.heepie.soundhub.model.User;

/**
 * Created by Heepie on 2017. 11. 24..
 * View Model은 View를 참조하지 않는다. 오직 비즈니스 로직(데이터를 표현하는 방법만 집중)
 * ViewModel은 생산자, View는 소비자
 * 소비자는 생산자가 누구인지 알아야하지만, 생산자는 소비자가 누구인지 몰라도 된다.
 */

public class PostsViewModel extends BaseObservable
{
    @Bindable
    public ObservableArrayList<PostViewModel> posts;

    public PostsViewModel()
    {
        this.posts = new ObservableArrayList<>();
    }

    public void addPost(User user, String title, int post_image_path, String music_length, String like_count, String comment_count, String tag) {
        this.posts.add(new PostViewModel(new Post(user, title, post_image_path, music_length, like_count, comment_count, tag)));
    }

    public ObservableArrayList<PostViewModel> getPosts() {
        return posts;
    }

    public void setPosts(ObservableArrayList<PostViewModel> posts) {
        this.posts = posts;
    }
}
