package com.heepie.soundhub.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.heepie.soundhub.R;
import com.heepie.soundhub.adapter.UserPageTabAdatper;
import com.heepie.soundhub.databinding.UserpageMainviewBinding;
import com.heepie.soundhub.domain.model.User;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.viewmodel.UserPageViewModel;

/**
 * Created by eunmin on 2017-12-04.
 */

public class UserPageView extends AppCompatActivity {
    User user;
    UserPageViewModel viewModel;
    TabLayout tabLayout;
    UserpageMainviewBinding binding;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUser();
        binding = DataBindingUtil.setContentView(this, R.layout.userpage_mainview);
        binding.setViewModel(viewModel);
        setTabLayout();
        setViewPager();
        setListener();
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
        UserPageTabAdatper adatper = new UserPageTabAdatper(user);
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
        user = intent.getParcelableExtra("user");
        viewModel = new UserPageViewModel(this);
        viewModel.setUserName(user.getNickname());
        viewModel.setUserInstrument(user.getInstrument());
        if(user.getId().equals(Const.user.getId())) {
            viewModel.setCheck(true);
        }
    }




    @Override
    public void onBackPressed() {
        if(viewModel.sucheck.get()) {
            viewModel.sucheck.set(false);
            return;
        }
        super.onBackPressed();
    }
}
