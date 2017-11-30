package com.heepie.soundhub.handler;

import android.util.Log;
import android.view.View;

import com.heepie.soundhub.R;
import com.heepie.soundhub.domain.logic.PostApi;
import com.heepie.soundhub.domain.logic.UserApi;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.viewmodel.PostsViewModel;
import com.heepie.soundhub.viewmodel.UserViewModel;

/**
 * Created by Heepie on 2017. 11. 30..
 */

public class ViewHandler {
    public static ViewHandler intance;

    private ViewHandler() {

    }

    public static ViewHandler getIntance() {
        if (intance == null)
            intance = new ViewHandler();
        return intance;
    }

    // '더보기' 버튼 클릭시 실행되는 메소드
    public void onClickedMoreBtn(View view , PostsViewModel viewModel) {
        switch (view.getId()) {
            case R.id.populMoreBtn:
                int startIndex = PostApi.populPostIndex;

                for (int i=startIndex; i<startIndex + Const.DEFAULT_COUNT_OF_SHOW_ITEM; i=i+1) {
                    if (PostApi.posts.size() > i) {
                        viewModel.addPostViewModel(PostApi.posts.get(i));
                        PostApi.populPostIndex++;
                    }
                }
                break;

            case R.id.newMoreBtn:
                // 서버 Api 추가 시 진행
                /*int startIndex = PostApi.newPostIndex;

                for (int i=startIndex; i<startIndex + Const.DEFAULT_COUNT_OF_SHOW_ITEM; i=i+1) {
                    if (PostApi.posts.get(i) != null) {
                        viewModel.addPostViewModel(PostApi.posts.get(i));
                        PostApi.newPostIndex++;
                    }
                }*/
                break;
        }


    }
}
