package com.heepie.soundhub.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.domain.model.User;

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

    public void addPopulUser(String id, String nickname, String email, String instrument) {
        this.populUsers.getModel().add(new UserViewModel((new User(id, nickname, email, instrument))));
    }

    public void addPopulPost(User author, String author_track, String title, String master_track) {
        this.populPosts.getModel().add(new PostViewModel(new Post(author_track, author, title, master_track)));
    }

    public void addNewPost(User author, String author_track, String title, String master_track) {
        this.newPosts.getModel().add(new PostViewModel(new Post(author_track, author, title, master_track)));
    }
}
