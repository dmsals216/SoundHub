package com.heepie.soundhub.view;

import android.databinding.DataBindingUtil;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.heepie.soundhub.BR;
import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.ListViewBinding;
import com.heepie.soundhub.handler.ViewHandler;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.viewmodel.ListViewModel;

import java.io.File;

public class ListView extends AppCompatActivity {
    public final String TAG = getClass().getSimpleName();
    private ListViewBinding listBinding;
    private ListViewModel listViewModel;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category = "";
        // Binding 초기화
        listBinding = DataBindingUtil.setContentView(this, R.layout.list_view);

        listViewModel = new ListViewModel(this);

        initData(Const.CATEGORY_DEFAULT);

        listBinding.setVariable(BR.viewModel, listViewModel);
        listBinding.setVariable(BR.view, this);
        listBinding.setVariable(BR.viewhandler, ViewHandler.getIntance());
        listBinding.setVariable(BR.user, Const.user);

    }

    private void initData(String category) {
        listViewModel.resetData();
        listViewModel.setDisplayData(category);
        listViewModel.setDisplayCategory();
    }

    // 서버로 부터 카테고리 정보를 입력 받는 메소드
    private void setCategoryData() {
        // 1. 서버와 통신 후 카테고리 정보 가져오기

        // 2. 해당 카테고리 설정
        // 2-1. 해당 이름에 따라 Button 카테고리 설정 및 빈 카테고리 버튼은 View.GONE 처리
    }

    public void onClickedTopCategory(View v) {
        category = ((Button) v).getText().toString().toLowerCase();
        switch (v.getId()) {
            case R.id.category_genre:
                if (listBinding.genreCategory.getVisibility() == View.VISIBLE) {
                    listBinding.instrumentCategory.setVisibility(View.GONE);
                    listBinding.genreCategory.setVisibility(View.GONE);
                } else {
                    listBinding.instrumentCategory.setVisibility(View.GONE);
                    listBinding.genreCategory.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.category_instrument:
                if (listBinding.instrumentCategory.getVisibility() == View.VISIBLE) {
                    listBinding.genreCategory.setVisibility(View.GONE);
                    listBinding.instrumentCategory.setVisibility(View.GONE);
                } else {
                    listBinding.genreCategory.setVisibility(View.GONE);
                    listBinding.instrumentCategory.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    public void onClickedCategory(View v) {
        listBinding.genreCategory.setVisibility(View.GONE);
        listBinding.instrumentCategory.setVisibility(View.GONE);
        initData(category + File.separator + ((Button) v).getText().toString());
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