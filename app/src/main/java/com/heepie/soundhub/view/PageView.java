package com.heepie.soundhub.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.heepie.soundhub.BR;
import com.heepie.soundhub.Interfaces.IGoHome;
import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.NavigationViewBinding;
import com.heepie.soundhub.databinding.PageViewBinding;
import com.heepie.soundhub.handler.ViewHandler;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.viewmodel.PageViewModel;

public class PageView extends AppCompatActivity implements IGoHome {
    private final String TAG = getClass().getSimpleName();
    private PageViewBinding binding;
    private PageViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = PageViewModel.getInstance();
        binding   = DataBindingUtil.setContentView(this, R.layout.page_view);
        binding.setVariable(BR.view, this);
        binding.setVariable(BR.viewModel, viewModel);

        initNavigationView();
        initListener();
        initData();
    }

    public void onClickedMenu(View v) {
        binding.drawerLayout.openDrawer(binding.navigation);
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

    @Override
    public void goHome(View v) {

    }

    private void initData() {
        int type = getIntent().getIntExtra("type", -1);
        if (!(type == -1)) {
            viewModel.displayData(type, 0);
        }
    }

    private void initListener() {
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int itemTotalCount = recyclerView.getAdapter().getItemCount() - 1;
                if (lastVisibleItemPosition == itemTotalCount) {
                    // TODO 서버 API 완료 후 변경
                    // Dummy Data
                    viewModel.displayData(-1, 0);
                    Toast.makeText(recyclerView.getContext(), "Last Position", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
