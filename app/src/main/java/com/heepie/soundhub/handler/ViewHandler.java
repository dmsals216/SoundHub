package com.heepie.soundhub.handler;

import android.content.Intent;
import android.view.View;

import com.heepie.soundhub.R;
import com.heepie.soundhub.view.DetailView;

/**
 * Created by Heepie on 2017. 11. 29..
 */

public class ViewHandler {
    public void onClickItem(View v) {
        Intent intent=null;

        switch (v.getId()) {
            case R.id.item_user:
                // 넘겨줄 데이터 설정
//                intent.putExtra("title", "Detail User");
                break;

            case R.id.item_post:
                intent = new Intent(v.getContext(), DetailView.class);
                // 넘겨줄 데이터 설정
                intent.putExtra("title", "Detail Post");

                break;

        }
        if (intent != null)
            v.getContext().startActivity(intent);
    }
}
