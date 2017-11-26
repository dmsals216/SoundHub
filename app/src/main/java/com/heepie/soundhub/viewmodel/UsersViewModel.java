package com.heepie.soundhub.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import com.heepie.soundhub.model.User;

/**
 * Created by Heepie on 2017. 11. 25..
 */

public class UsersViewModel extends BaseObservable {
    @Bindable
    public ObservableArrayList<UserViewModel> users;

    public UsersViewModel()
    {
        this.users = new ObservableArrayList<>();
    }

    public void addUser(String user_name, int user_image_path, String user_like_count) {
        this.users.add(new UserViewModel(new User(user_name, user_image_path, user_like_count)));
    }

    public ObservableArrayList<UserViewModel> getPosts() {
        return users;
    }

    public void setUsers(ObservableArrayList<UserViewModel> users) {
        this.users = users;
    }
}