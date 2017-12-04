package com.heepie.soundhub.view;

import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
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
import com.heepie.soundhub.domain.logic.PostApi;
import com.heepie.soundhub.domain.logic.UserApi;
import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.viewmodel.ListViewModel;
import com.heepie.soundhub.viewmodel.PostViewModel;
import com.heepie.soundhub.viewmodel.PostsViewModel;
import com.heepie.soundhub.viewmodel.UserViewModel;

public class ListView extends AppCompatActivity {
    private ListViewBinding listBinding;
    private ListViewModel listViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Binding 초기화
        listBinding = DataBindingUtil.setContentView(this, R.layout.list_view);

        // 더미 데이터 생성
        listViewModel = new ListViewModel(this);

        initToolbar();
        initTabLayout();
        initData();

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
                // 해당 카테고리 보여주기
                switch (tab.getText().toString()) {
                    case "Genre":
                        listBinding.category.setVisibility(View.VISIBLE);
                        break;
                    case "Instrument":
                        listBinding.category.setVisibility(View.VISIBLE);
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

    private void initData() {
        listViewModel.resetData();
        // 추후 구현
//        setCategoryData();

        setPopulUserList();
        setPopulPostList();
        // 추후 구현
//        setNewPostList();


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

        initNavigationView();
    }

    private void initNavigationView() {
        listBinding.navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(ListView.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                switch (item.getTitle().toString()) {
                    case "Home":
                        listBinding.category.setVisibility(View.GONE);
                        initData();
                        listBinding.drawerLayout.closeDrawers();
                        break;
                    case "User Home":
                        Intent intent = new Intent(ListView.this, UserPageView.class);
                        intent.putExtra("user", Const.user);
                        startActivity(intent);
                        break;
                }



                return true;
            }
        });
    }

    private void setPopulPostList() {
        PostApi.getInstance().getData((code, msg, data) -> {
            /*입력 데이터 확인
            for (PostViewModel item : PostApi.posts) {
                Log.e("heepie", item.getModel().toString());
            }*/

            for (int i=0; i< Const.DEFAULT_COUNT_OF_SHOW_ITEM; i=i+1) {
                if (PostApi.posts.get(i) != null) {
                    listViewModel.populPosts.addPostViewModel(PostApi.posts.get(i));
                    PostApi.populPostIndex++;
                }
            }
        });
    }

    private void setPopulUserList() {
        UserApi.getInstance().getData((code, msg, data) -> {
            for (UserViewModel u : UserApi.users) {
                Log.e("heepie", u.getModel().toString());
                listViewModel.populUsers.addUserViewModel(u);
            }
        });
    }

    private void setNewPostList() {
//        서버 측 API 완료 시 주석 제거
        /*PostApi.getInstance().getData((code, msg, data) -> {
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
    }

    public void onClickedCategory(View v) {
        listBinding.category.setVisibility(View.GONE);

        initData();
        // 해당 카테고리 정보 입력 받기
//        setPopulUserList(((Button)v).getText());
//        setPopulPostList(((Button)v).getText());
//        setNewPostList(((Button)v).getText());
    }

    public void onClickedUserImage(View v) {
        Toast.makeText(ListView.this, "Clicked UserImage", Toast.LENGTH_SHORT).show();
    }
}
