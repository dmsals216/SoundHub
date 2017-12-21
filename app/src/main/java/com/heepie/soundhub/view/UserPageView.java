package com.heepie.soundhub.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.heepie.soundhub.R;
import com.heepie.soundhub.adapter.UserPageTabAdatper;
import com.heepie.soundhub.databinding.UserpageViewBinding;
import com.heepie.soundhub.domain.logic.UserApi;
import com.heepie.soundhub.domain.model.User;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.utils.RetrofitUtil;
import com.heepie.soundhub.viewmodel.UserPageViewModel;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.Result;

/**
 * Created by eunmin on 2017-12-04.
 */

public class UserPageView extends AppCompatActivity {
    User user;
    UserPageViewModel viewModel;
    TabLayout tabLayout;
    UserpageViewBinding binding;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUser();
    }

    public void setListener() {
        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager)
        );
        viewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(tabLayout)
        );
    }

    public void setViewPager() {
        viewPager = findViewById(R.id.viewPager);
        UserPageTabAdatper adatper = new UserPageTabAdatper(user, this);
        viewPager.setAdapter(adatper);
    }

    private void setTabLayout() {
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Upload"));
        tabLayout.addTab(tabLayout.newTab().setText("Like"));
        tabLayout.addTab(tabLayout.newTab().setText("Following"));
        tabLayout.addTab(tabLayout.newTab().setText("Follower"));
    }

    private void getUser() {
        Intent intent = getIntent();
        Retrofit retrofit = RetrofitUtil.getInstance();
        UserApi.IUser service = retrofit.create(UserApi.IUser.class);
        Call<User> result = service.get1User(intent.getStringExtra("userid"));
        result.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                viewModel = new UserPageViewModel(UserPageView.this);
                viewModel.setUserName(user.getNickname());
                viewModel.setUserInstrument(user.getInstrument());
                viewModel.setUserGenre(user.getGenre());
                if (user.getId().equals(Const.user.getId())) {
                    viewModel.setCheck(true);
                }
                binding = DataBindingUtil.setContentView(UserPageView.this, R.layout.userpage_view);
                binding.setViewModel(viewModel);
                setTabLayout();
                setViewPager();
                setListener();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }




    @Override
    public void onBackPressed() {
        if(viewModel.sucheck.get()) {
            viewModel.onClickedCancel();
            return;
        }
        super.onBackPressed();
    }
}
