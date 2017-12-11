package com.heepie.soundhub.view;

import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.ListViewBinding;
import com.heepie.soundhub.databinding.NavigationHeaderBinding;
import com.heepie.soundhub.databinding.NewPostViewBinding;
import com.heepie.soundhub.databinding.PopulPostViewBinding;
import com.heepie.soundhub.domain.logic.PostApi;
import com.heepie.soundhub.domain.logic.UserApi;
import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.handler.ViewHandler;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.viewmodel.ListViewModel;
import com.heepie.soundhub.viewmodel.PostViewModel;
import com.heepie.soundhub.viewmodel.PostsViewModel;
import com.heepie.soundhub.viewmodel.UserViewModel;

import java.io.File;

public class ListView extends AppCompatActivity {
    public final String TAG = getClass().getSimpleName();
    private ListViewBinding listBinding;
    private ListViewModel listViewModel;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category="";
        // Binding 초기화
        listBinding = DataBindingUtil.setContentView(this, R.layout.list_view);

        // 더미 데이터 생성
        listViewModel = new ListViewModel(this);

        initToolbar();
        initTabLayout();
        // 서버 완료 후 구현
//        setCategoryData();
        initData(Const.CATEGORY_DEFAULT);

        listBinding.setViewModel(listViewModel);
        listBinding.setView(this);

    }

    private void initTabLayout() {
        listBinding.tabLayout.addTab(listBinding.tabLayout.newTab().setText("Genre"));
        listBinding.tabLayout.addTab(listBinding.tabLayout.newTab().setText("Instrument"));

        // 리스너 설정 필요
        listBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                Toast.makeText(ListView.this, tab.getText(), Toast.LENGTH_SHORT).show();
                category = tab.getText().toString().toLowerCase();
                // 해당 카테고리 보여주기
                switch (tab.getText().toString()) {

                    case "Genre":
                        listBinding.instrumentCategory.setVisibility(View.GONE);
                        listBinding.genreCategory.setVisibility(View.VISIBLE);
                        break;
                    case "Instrument":
                        listBinding.genreCategory.setVisibility(View.GONE);
                        listBinding.instrumentCategory.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void initData(String category) {
        listViewModel.resetData();
        listViewModel.setDisplayData(category);
        listViewModel.setDisplayCategory();
    }

/*    private void initData(String category) {
        listViewModel.resetData();

        setPopulUserList(category);
        setPopulPostList(category);
        // 추후 구현
//        setNewPostList();

    }*/

    private void initToolbar() {
        // 데이터 바인딩으로 변경해야 함
        // 네비게이션 뷰(액션바 햄버거) 설정
        NavigationHeaderBinding navigationViewHeaderBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.navigation_header,listBinding.navigation,false);
        listBinding.navigation.addHeaderView(navigationViewHeaderBinding.getRoot());
        navigationViewHeaderBinding.setModel(Const.user);
        navigationViewHeaderBinding.setViewhandler(ViewHandler.getIntance());



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, listBinding.drawerLayout, listBinding.toolbar, 0, 0);
//            ((DrawerLayout)view).addDrawerListener(toggle);
        toggle.syncState();

        initNavigationView();
    }

    private void initNavigationView() {
        listBinding.navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(ListView.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                switch (item.getTitle().toString()) {
                    case "Home":
                        listBinding.genreCategory.setVisibility(View.GONE);
                        listBinding.instrumentCategory.setVisibility(View.GONE);
                        initData(Const.CATEGORY_DEFAULT);
                        listBinding.drawerLayout.closeDrawers();
                        break;
                }

                return true;
            }
        });
    }

/*    private void setPopulPostList(String category) {
        PostApi.getInstance().getData(category, (code, msg, data) -> {
            *//*입력 데이터 확인
            for (PostViewModel item : PostApi.posts) {
                Log.e("heepie", item.getModel().toString());
            }*//*

            for (int i=0; i< Const.DEFAULT_COUNT_OF_SHOW_ITEM; i=i+1) {
                if (PostApi.posts.get(i) != null) {
                    listViewModel.populPosts.addPostViewModel(PostApi.posts.get(i));
                    PostApi.populPostIndex++;
                }
            }
        });
    }*/

    /*private void setPopulUserList(String category) {
        UserApi.getInstance().getData(category, (code, msg, data) -> {
            for (UserViewModel u : UserApi.users) {
                Log.e("heepie", u.getModel().toString());
                listViewModel.populUsers.addUserViewModel(u);
            }
        });
    }*/

    private void setNewPostList(String category) {
//        서버 측 API 완료 시 주석 제거
        /*PostApi.getInstance().getData(category, (code, msg, data) -> {
            *//*입력 데이터 확인
            for (PostViewModel item : PostApi.posts) {
                Log.e("heepie", item.getModel().toString());
            }*//*

            for (PostViewModel i : PostApi.posts) {
                listViewModel.newPosts.addPostViewModel(i);
            }
        });*/
    }

    // 서버로 부터 카테고리 정보를 입력 받는 메소드
    private void setCategoryData() {
        // 1. 서버와 통신 후 카테고리 정보 가져오기

        // 2. 해당 카테고리 설정
        // 2-1. 해당 이름에 따라 Button 카테고리 설정 및 빈 카테고리 버튼은 View.GONE 처리
    }

    // 카테고리 클릭 리스너
    public void onClickedCategory(View v) {
        listBinding.genreCategory.setVisibility(View.GONE);
        listBinding.instrumentCategory.setVisibility(View.GONE);

        Log.d(TAG, "onClickedCategory: " + category + File.separator + ((Button)v).getText().toString());
        initData(category + File.separator +((Button)v).getText().toString());
    }

    public void onClickedUserImage(View v) {
        Toast.makeText(ListView.this, "Clicked UserImage", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}