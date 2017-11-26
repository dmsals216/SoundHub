package com.heepie.soundhub.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import com.heepie.soundhub.model.Post;
import com.heepie.soundhub.model.User;

/**
 * Created by Heepie on 2017. 11. 24..
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
