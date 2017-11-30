package com.heepie.soundhub.viewmodel;

import android.databinding.BaseObservable;

import com.heepie.soundhub.Interfaces.IModelGettable;
import com.heepie.soundhub.Interfaces.IShowable;
import com.heepie.soundhub.model.TestUser;

/**
 * Created by Heepie on 2017. 11. 25..
 */

public class UserViewModel extends BaseObservable implements IModelGettable<TestUser> , IShowable{
    private final TestUser model;

    public UserViewModel(TestUser model) {
        this.model = model;
    }

    @Override
    public TestUser getModel() {
        return model;
    }

    @Override
    public boolean isShow() {
        return true;
    }
}
