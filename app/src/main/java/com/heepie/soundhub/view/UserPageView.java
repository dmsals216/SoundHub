package com.heepie.soundhub.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.heepie.soundhub.R;
import com.heepie.soundhub.adapter.UserPageTabAdatper;
import com.heepie.soundhub.databinding.UserpageViewBinding;
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
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUser();
        UserpageViewBinding binding = DataBindingUtil.setContentView(this, R.layout.userpage_view);
        binding.setViewModel(viewModel);
        setToolbar();
        setTabLayout();
        setViewPager();
        setListener();
    }

    public void setListener() {
        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager)
        );
        // ViewPager의 변경사항을 탭레이아웃에 전달
        viewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(tabLayout)
        );
    }

    public void setViewPager() {
        viewPager = findViewById(R.id.viewPager);
        UserPageTabAdatper adatper = new UserPageTabAdatper();
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


    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        DrawerLayout drawer = findViewById(R.id.drawerLayout2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, 0, -1);
        toggle.syncState();
    }


    @Override
    public void onBackPressed() {
        if(viewModel.sucheck.get()) {
            viewModel.sucheck.set(false);
            return;
        }
        DrawerLayout drawer = findViewById(R.id.drawerLayout2);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
