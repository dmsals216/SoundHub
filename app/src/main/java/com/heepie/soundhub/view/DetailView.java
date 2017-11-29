package com.heepie.soundhub.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.DetailViewBinding;
import com.heepie.soundhub.databinding.NavigationHeaderBinding;

public class DetailView extends AppCompatActivity {
    DetailViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.detail_view);
        binding.setView(this);

        initToolbar();
        Log.i("heepie", getIntent().getStringExtra("title"));
    }

    private void initToolbar() {
        // 데이터 바인딩으로 변경해야 함
        // 네비게이션 뷰(액션바 햄버거) 설정
        NavigationHeaderBinding navigationViewHeaderBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.navigation_header,binding.navigation,false);
        binding.navigation.addHeaderView(navigationViewHeaderBinding.getRoot());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.toolbar, 0, 0);
//            ((DrawerLayout)view).addDrawerListener(toggle);
        toggle.syncState();
    }
}
