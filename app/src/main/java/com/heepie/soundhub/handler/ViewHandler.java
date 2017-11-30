package com.heepie.soundhub.handler;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.heepie.soundhub.R;
import com.heepie.soundhub.model.Post;
import com.heepie.soundhub.view.DetailView;

/**
 * Created by Heepie on 2017. 11. 29..
 */

public class ViewHandler {
    final String TAG = getClass().getSimpleName() + " ";

    public void onClickPostItem(View v, Post model) {
        Intent intent=null;

        intent = new Intent(v.getContext(), DetailView.class);
        // 넘겨줄 데이터 설정
        intent.putExtra("title", "Detail Post");
        intent.putExtra("model", model);

//        Toast.makeText(v.getContext(), model.toString(), Toast.LENGTH_SHORT).show();

        Log.i("heepie", TAG + model.toString());

        if (intent != null)
            v.getContext().startActivity(intent);
    }
}
