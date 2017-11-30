package com.heepie.soundhub.view;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.ListViewBinding;
import com.heepie.soundhub.databinding.NavigationHeaderBinding;
import com.heepie.soundhub.domain.logic.PostApi;
import com.heepie.soundhub.domain.logic.UserApi;
import com.heepie.soundhub.domain.model.Post;
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
    }

    private void initData() {
        // 더미 데이터 생성
        listViewModel = new ListViewModel();

        setPopulPostList();
        setPopulUserList();
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

    private void setPopulPostList() {
        PostApi.getInstance().getData((code, msg, data) -> {
            /*입력 데이터 확인
            for (PostViewModel item : PostApi.posts) {
                Log.e("heepie", item.getModel().toString());
            }*/

            for (PostViewModel i : PostApi.posts) {
                listViewModel.populPosts.addPostViewModel(i);
            }
        });
    }

    private void setPopulUserList() {
//        서버 측 API 완료 시 주석 제거
        /*UserApi.getInstance().getData((code, msg, data) -> {
            for (UserViewModel u : UserApi.users) {
                Log.e("heepie", u.getModel().toString());
                listViewModel.populUsers.addUserViewModel(u);
            }
        });*/

    }
}
