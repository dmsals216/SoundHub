package com.heepie.soundhub.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.net.Uri;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.heepie.soundhub.R;
import com.heepie.soundhub.controller.PlayerController;
import com.heepie.soundhub.databinding.DetailViewBinding;
import com.heepie.soundhub.databinding.NavigationHeaderBinding;
import com.heepie.soundhub.domain.logic.DataAPI;
import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.utils.PathUtil;
import com.heepie.soundhub.viewmodel.DetailViewModel;

import java.io.File;

public class DetailView extends AppCompatActivity {
    final String TAG = getClass().getSimpleName();
    private DetailViewBinding binding;
    private DetailViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.detail_view);
        viewModel = DetailViewModel.getInstance();
        viewModel.initContext(this);

        initToolbar();
        initData();

        binding.setViewModel(viewModel);
//        binding.setPlayer(PlayerController.getInstance());

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

    private void initData() {
        Post model = getIntent().getParcelableExtra("model");

        Log.d(TAG, "initData: " + model.toString());

        binding.setModel(model);
        // ViewModel에 설정
        viewModel.setPost(model);
    }

    @Override
    protected void onPause() {
        viewModel.onPause();
        super.onPause();
    }
}
