package com.heepie.soundhub.viewmodel;

import android.databinding.BaseObservable;

import com.heepie.soundhub.Interfaces.IModelGettable;
import com.heepie.soundhub.Interfaces.IShowable;
import com.heepie.soundhub.model.TestPost;


/**
 * Created by Heepie on 2017. 11. 24..
 */

public class PostViewModel extends BaseObservable implements IModelGettable<TestPost>, IShowable {
    private final TestPost model;

    public PostViewModel(TestPost model) {
        this.model = model;
    }

    @Override
    public TestPost getModel() {
        return model;
    }


    @Override
    public boolean isShow() {
        return model.isShow;
    }
}