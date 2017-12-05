package com.heepie.soundhub.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.Toast;

import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.domain.model.User;
import com.heepie.soundhub.view.MusicUploadView;

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
    private Context context;

    public ListViewModel(Context context) {
        populUsers = new UsersViewModel();
        populPosts = new PostsViewModel();
        newPosts = new PostsViewModel();
        this.context = context;
    }

    public void resetData() {
        populUsers.users.clear();
        populPosts.posts.clear();
        newPosts.posts.clear();
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

    public void addPopulPost(PostViewModel postViewModel) {
        this.populPosts.getModel().add(postViewModel);
    }

    public void addPopulUser(UserViewModel userViewModel) {
        this.populUsers.getModel().add(userViewModel);
    }

    public void onClickedUpLoad(View view) {
        Toast.makeText(view.getContext(), "Clicked UpLoad", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, MusicUploadView.class);
        context.startActivity(intent);
    }
}
