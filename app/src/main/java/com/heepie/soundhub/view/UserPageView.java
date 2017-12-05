package com.heepie.soundhub.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.UserpageViewBinding;
import com.heepie.soundhub.domain.model.User;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.viewmodel.UserPageViewModel;

/**
 * Created by eunmin on 2017-12-04.
 */

public class UserPageView extends AppCompatActivity {
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        user = intent.getParcelableExtra("user");
        UserPageViewModel viewModel = new UserPageViewModel(this);
        viewModel.setUserName(user.getNickname());
        viewModel.setUserInstrument(user.getInstrument());
        if(user.getId().equals(Const.user.getId())) {

            viewModel.setCheck(true);
        }
        UserpageViewBinding binding = DataBindingUtil.setContentView(this, R.layout.userpage_view);
        binding.setViewModel(viewModel);
    }
}
