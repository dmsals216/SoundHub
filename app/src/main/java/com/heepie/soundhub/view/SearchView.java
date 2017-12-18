package com.heepie.soundhub.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.heepie.soundhub.R;
import com.heepie.soundhub.adapter.SearchViewTitleAdapter;
import com.heepie.soundhub.databinding.SearchViewBinding;
import com.heepie.soundhub.viewmodel.SearchViewModel;

/**
 * Created by eunmin on 2017-12-18.
 */

public class SearchView extends AppCompatActivity{

    SearchViewBinding binding;
    SearchViewModel viewModel;
    SearchViewTitleAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new SearchViewTitleAdapter();
        viewModel = new SearchViewModel(adapter);
        binding = DataBindingUtil.setContentView(this, R.layout.search_view);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
        binding.setViewModel(viewModel);
    }
}
