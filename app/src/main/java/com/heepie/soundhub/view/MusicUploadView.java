package com.heepie.soundhub.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.heepie.soundhub.R;
import com.heepie.soundhub.utils.SignUtil;

/**
 * Created by eunmin on 2017-12-04.
 */

public class MusicUploadView extends AppCompatActivity {

    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musicupload_view);
        initView();
    }

    private void initView() {
        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(view -> {
            SignUtil.logout(this, this);
        });
    }
}
