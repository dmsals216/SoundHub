package com.heepie.soundhub.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.heepie.soundhub.BR;
import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.PageViewBinding;
import com.heepie.soundhub.viewmodel.PageViewModel;

public class PageView extends AppCompatActivity {
    private PageViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.page_view);

        binding.setVariable(BR.view, this);
        binding.setVariable(BR.viewModel, PageViewModel.getInstance());
    }
}
