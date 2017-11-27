package com.heepie.soundhub.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import com.heepie.soundhub.model.User;
import com.heepie.soundhub.utils.IModelGettable;

/**
 * Created by Heepie on 2017. 11. 25..
 */

public class UsersViewModel extends BaseObservable implements IModelGettable<ObservableArrayList<UserViewModel>> {
    @Bindable
    public ObservableArrayList<UserViewModel> users;

    public UsersViewModel()
    {
        this.users = new ObservableArrayList<>();
    }

    public void addUser(String user_name, int user_image_path, String user_like_count) {
        this.users.add(new UserViewModel(new User(user_name, user_image_path, user_like_count)));
    }

    @Override
    public ObservableArrayList<UserViewModel> getModel() {
        return users;
    }

    public void setUsers(ObservableArrayList<UserViewModel> users) {
        this.users = users;
    }
}
