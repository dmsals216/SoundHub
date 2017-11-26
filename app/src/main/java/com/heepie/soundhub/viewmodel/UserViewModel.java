package com.heepie.soundhub.viewmodel;

import android.databinding.BaseObservable;

import com.heepie.soundhub.model.User;
import com.heepie.soundhub.utils.IModelGettable;

/**
 * Created by Heepie on 2017. 11. 25..
 */

public class UserViewModel extends BaseObservable implements IModelGettable<User> {
    private final User model;

    public UserViewModel(User model) {
        this.model = model;
    }

    @Override
    public User getModel() {
        return model;
    }
}
