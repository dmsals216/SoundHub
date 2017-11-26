package com.heepie.soundhub.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import com.heepie.soundhub.model.Post;
import com.heepie.soundhub.model.User;

/**
 * Created by Heepie on 2017. 11. 27..
 */

public class ListViewModel extends BaseObservable {
    @Bindable
    public ObservableArrayList<UserViewModel> populUsers;

    @Bindable
    public ObservableArrayList<PostViewModel> populPosts;

    @Bindable
    public ObservableArrayList<PostViewModel> newPosts;

    public ListViewModel() {
        populUsers  = new ObservableArrayList<>();
        populPosts  = new ObservableArrayList<>();
        newPosts    = new ObservableArrayList<>();
    }

    public void addPopulUser(String user_name, int user_image_path, String user_like_count) {
        this.populUsers.add(new UserViewModel(new User(user_name, user_image_path, user_like_count)));
    }

    public void addPopulPost(User user, String title, int post_image_path, String music_length, String like_count, String comment_count, String tag) {
        this.populPosts.add(new PostViewModel(new Post(user, title, post_image_path, music_length, like_count, comment_count, tag)));
    }

    public void addNewPost(User user, String title, int post_image_path, String music_length, String like_count, String comment_count, String tag) {
        this.newPosts.add(new PostViewModel(new Post(user, title, post_image_path, music_length, like_count, comment_count, tag)));
    }
}
