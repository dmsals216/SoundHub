package com.heepie.soundhub.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.ListViewBinding;
import com.heepie.soundhub.databinding.NavigationHeaderBinding;
import com.heepie.soundhub.domain.logic.PostApi;
import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.model.TestUser;
import com.heepie.soundhub.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListView extends AppCompatActivity {
    private ListViewBinding listBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Binding 초기화
        listBinding = DataBindingUtil.setContentView(this, R.layout.list_view);

        initToolbar();
        initTabLayout();
        initData();

        setList();
    }

    private void initTabLayout() {
        listBinding.tabLayout.addTab(listBinding.tabLayout.newTab().setText("Genre"));
        listBinding.tabLayout.addTab(listBinding.tabLayout.newTab().setText("Instrument"));

        // 리스너 설정 필요
    }

    private void initData() {
        // 더미 데이터 생성
        ListViewModel listViewModel = new ListViewModel();


        for (int i=0; i<20; i=i+1) {
            listViewModel.addPopulUser("populUser " + i, R.drawable.test, "1 " + i);
        }

        for (int i=0; i<5; i=i+1) {
            listViewModel.addPopulPost(
                    new TestUser("populPost " + i, R.drawable.test2, "1 " + i),
                    "Title " + i,
                    R.drawable.test2,
                    "05:00" + i,
                    "10 " + i,
                    "15 " + i,
                    "#Vocal #Piano" + i,
                    true
            );
        }

        for (int i=0; i<5; i=i+1) {
            listViewModel.addNewPost(
                    new TestUser("newPost " + i, R.drawable.test3, "1 " + i),
                    "Title " + i,
                    R.drawable.test3,
                    "05:00" + i,
                    "10 " + i,
                    "15 " + i,
                    "#Vocal #Piano" + i,
                    true
            );
        }

        listBinding.setViewModel(listViewModel);
        listBinding.setView(this);
    }

    private void initToolbar() {
        // 데이터 바인딩으로 변경해야 함
        // 네비게이션 뷰(액션바 햄버거) 설정
        NavigationHeaderBinding navigationViewHeaderBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.navigation_header,listBinding.navigation,false);
        listBinding.navigation.addHeaderView(navigationViewHeaderBinding.getRoot());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, listBinding.drawerLayout, listBinding.toolbar, 0, 0);
//            ((DrawerLayout)view).addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setList() {
        PostApi.getPosts((code, msg, data) -> {
            for (Post item : PostApi.posts)
                Log.e("heepie", item.toString());
        });
    }
}
