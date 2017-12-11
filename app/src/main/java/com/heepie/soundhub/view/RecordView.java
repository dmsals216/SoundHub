package com.heepie.soundhub.view;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.heepie.soundhub.BR;
import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.RecordViewBinding;

import java.util.List;

public class RecordView extends AppCompatActivity {
    private RecordViewBinding binding;
    public int[] layoutResIds;

    public List<String> urls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.record_view);
        binding.setVariable(BR.view, this);

        initTabLayout();
        connectViewpagerAndTabLayout();
        initData();
    }

    private void initTabLayout() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("녹음후 업로드"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("파일로 업로드"));
    }

    private void initData() {
        urls = getIntent().getStringArrayListExtra("SelectedURLs");

        layoutResIds = new int[2];
        layoutResIds[0] = R.layout.chlid_record_view;
        layoutResIds[1] = R.layout.chlid_file_upload_view;
    }

    // TabLayout과 Viewpager 연결
    private void connectViewpagerAndTabLayout() {
        binding.tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(binding.viewPager)
        );

        binding.viewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout)
        );
    }

}
