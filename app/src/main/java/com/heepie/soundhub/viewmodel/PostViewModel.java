package com.heepie.soundhub.viewmodel;

import android.databinding.BaseObservable;

import com.heepie.soundhub.model.Post;
import com.heepie.soundhub.utils.IModelGettable;


/**
 * Created by Heepie on 2017. 11. 24..
 */

public class PostViewModel extends BaseObservable implements IModelGettable<Post> {
    private final Post model;

    public PostViewModel(Post model) {
        this.model = model;
    }

    @Override
    public Post getModel() {
        return model;
    }


}