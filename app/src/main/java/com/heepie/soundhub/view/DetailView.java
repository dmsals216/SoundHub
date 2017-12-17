package com.heepie.soundhub.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.heepie.soundhub.BR;
import com.heepie.soundhub.Interfaces.IGoHome;
import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.DetailViewBinding;
import com.heepie.soundhub.databinding.NavigationHeaderBinding;
import com.heepie.soundhub.databinding.NavigationViewBinding;
import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.handler.ViewHandler;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.viewmodel.DetailViewModel;

public class DetailView extends AppCompatActivity implements IGoHome {
    final String TAG = getClass().getSimpleName();
    private DetailViewModel   viewModel;
    private DetailViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding   = DataBindingUtil.setContentView(this, R.layout.detail_view);
        viewModel = DetailViewModel.getInstance();
        viewModel.initContext(this);

        initData();
        initNavigationView();

        binding.setVariable(BR.view, this);
        binding.setVariable(BR.viewModel, viewModel);
    }

    private void initData() {
        Post model = getIntent().getParcelableExtra("model");
        binding.setModel(model);
        viewModel.setPost(model);
    }

    private void initNavigationView() {
        NavigationViewBinding naviViewBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.navigation_view, binding.navigation,false);
        binding.navigation.addView(naviViewBinding.getRoot());
        naviViewBinding.setVariable(BR.activity, this);
        naviViewBinding.setVariable(BR.view, this);
        naviViewBinding.setVariable(BR.model, Const.user);
        naviViewBinding.setVariable(BR.viewhandler, ViewHandler.getIntance());
        naviViewBinding.setVariable(BR.drawerLayout, binding.drawerLayout);
    }

    public void onClickedMenu(View v) {
        binding.drawerLayout.openDrawer(binding.navigation);
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.onPause();
    }

    @Override
    public void goHome(View v) {
        Toast.makeText(this, "Clicked Home", Toast.LENGTH_SHORT).show();
    }
}
