package com.heepie.soundhub.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.heepie.soundhub.R;
import com.heepie.soundhub.controller.PlayerController;
import com.heepie.soundhub.databinding.DetailViewBinding;
import com.heepie.soundhub.databinding.NavigationHeaderBinding;
import com.heepie.soundhub.domain.logic.DataAPI;
import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.viewmodel.DetailViewModel;

public class DetailView extends AppCompatActivity {
    final String TAG = getClass().getSimpleName();
    private DetailViewBinding binding;
    private DetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.detail_view);
        viewModel = new DetailViewModel(this);

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
//        Post model = DataAPI.getInstance().getModelData().getRecent_posts().get(0);
        Log.d(TAG, "initData: " + model.getComment_tracks().keySet());

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
