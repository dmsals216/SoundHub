package com.heepie.soundhub.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.heepie.soundhub.model.TestPost;
import com.heepie.soundhub.model.TestUser;

/**
 * Created by Heepie on 2017. 11. 27..
 */

public class ListViewModel extends BaseObservable {
    @Bindable
    public UsersViewModel populUsers;

    @Bindable
    public PostsViewModel populPosts;

    @Bindable
    public PostsViewModel newPosts;

    public ListViewModel() {
        populUsers = new UsersViewModel();
        populPosts = new PostsViewModel();
        newPosts = new PostsViewModel();
    }

    public void addPopulUser(String user_name, int user_image_path, String user_like_count) {
        this.populUsers.getModel().add(new UserViewModel(new TestUser(user_name, user_image_path, user_like_count)));
    }

    public void addPopulPost(TestUser testUser, String title, int post_image_path, String music_length, String like_count, String comment_count, String tag, boolean isShow) {
        this.populPosts.getModel().add(new PostViewModel(new TestPost(testUser, title, post_image_path, music_length, like_count, comment_count, tag, isShow)));
    }

    public void addNewPost(TestUser testUser, String title, int post_image_path, String music_length, String like_count, String comment_count, String tag, boolean isShow) {
        this.newPosts.getModel().add(new PostViewModel(new TestPost(testUser, title, post_image_path, music_length, like_count, comment_count, tag, isShow)));
    }
}
