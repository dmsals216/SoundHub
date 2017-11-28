package com.heepie.soundhub.view;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.ListViewBinding;
import com.heepie.soundhub.databinding.NavigationHeaderBinding;
import com.heepie.soundhub.model.User;
import com.heepie.soundhub.viewmodel.ListViewModel;
import com.heepie.soundhub.viewmodel.PostsViewModel;

public class ListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListViewBinding listBinding = DataBindingUtil.setContentView(this, R.layout.list_view);
        ListViewModel listViewModel = new ListViewModel();

        // 데이터 바인딩으로 변경해야 함
        // 네비게이션 뷰(액션바 햄버거) 설정
        NavigationHeaderBinding navigationViewHeaderBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.navigation_header,listBinding.navigation,false);
        listBinding.navigation.addHeaderView(navigationViewHeaderBinding.getRoot());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, listBinding.drawerLayout, listBinding.toolbar, 0, 0);
//            ((DrawerLayout)view).addDrawerListener(toggle);
        toggle.syncState();


        // 더미 데이터 생성
        for (int i=0; i<20; i=i+1) {
            listViewModel.addPopulUser("populUser " + i, R.drawable.test, "1 " + i);
        }

        for (int i=0; i<5; i=i+1) {
            listViewModel.addPopulPost(
                    new User("populPost " + i, R.drawable.test2, "1 " + i),
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
                    new User("newPost " + i, R.drawable.test3, "1 " + i),
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
}
