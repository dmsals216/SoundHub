package com.heepie.soundhub.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.ListViewBinding;
import com.heepie.soundhub.model.User;
import com.heepie.soundhub.viewmodel.PostsViewModel;

public class ListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListViewBinding listBinding = DataBindingUtil.setContentView(this, R.layout.list_view);

        PostsViewModel populPostsVM = new PostsViewModel();
        for (int i=0; i<10; i=i+1) {
            populPostsVM.addPost(
                    new User("Heepie" + i, R.drawable.test, "1 " + i),
                    "Title" + i,
                    R.drawable.test,
                    "05:00" + i,
                    "10 " + i,
                    "15 " + i,
                    "#Vocal #Piano" + i
            );
        }

        listBinding.setPopulPosts(populPostsVM);
        listBinding.setView(this);
    }
}
