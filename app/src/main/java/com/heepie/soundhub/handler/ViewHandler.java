package com.heepie.soundhub.handler;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.heepie.soundhub.R;
import com.heepie.soundhub.domain.logic.DataAPI;
import com.heepie.soundhub.domain.logic.PostApi;
import com.heepie.soundhub.domain.model.Data;
import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.domain.model.User;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.utils.SignUtil;
import com.heepie.soundhub.view.DetailView;
import com.heepie.soundhub.view.UserPageView;
import com.heepie.soundhub.viewmodel.PostViewModel;
import com.heepie.soundhub.viewmodel.PostsViewModel;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Heepie on 2017. 11. 30..
 */

public class ViewHandler {
    public final String TAG = getClass().getSimpleName();
    private PostApi postAPI;
    private int newPostIndex;
    private int populPostIndex;
    public static ViewHandler intance;

    private ViewHandler() {
        newPostIndex   = Const.DEFAULT_COUNT_OF_SHOW_ITEM;
        populPostIndex = Const.DEFAULT_COUNT_OF_SHOW_ITEM;
    }

    public static ViewHandler getIntance() {
        if (intance == null)
            intance = new ViewHandler();
        return intance;
    }

    public void initIndex() {
        newPostIndex   = Const.DEFAULT_COUNT_OF_SHOW_ITEM;
        populPostIndex = Const.DEFAULT_COUNT_OF_SHOW_ITEM;
    }

    // '더보기' 버튼 클릭시 실행되는 메소드
    public void onClickedMoreBtn(View view , PostsViewModel viewModel) {
        int startIndex;
        Data data = DataAPI.getInstance().getModelData();

        switch (view.getId()) {
        case R.id.populMoreBtn:
            startIndex = populPostIndex;

            // MAX_COUNT_OF_SHOW_ITEM까지 보여주고 추가로 누르면 상세 페이지로 이동
            if (startIndex == Const.MAX_COUNT_OF_SHOW_ITEM) {
                Toast.makeText(view.getContext(), "상세 페이지로 이동", Toast.LENGTH_SHORT).show();
            } else {
                for (int i=startIndex; i<startIndex+Const.DEFAULT_COUNT_OF_SHOW_ITEM; i=i+1) {
                    if (data.getPop_posts().size() > i) {
                        viewModel.addPostViewModel(new PostViewModel(data.getPop_posts().get(i)));
                        populPostIndex++;
                    }
                }
            }
            break;

        case R.id.newMoreBtn:
            // 서버 Api 추가 시 진행
            startIndex = newPostIndex;

            // MAX_COUNT_OF_SHOW_ITEM까지 보여주고 추가로 누르면 상세 페이지로 이동
            if (startIndex == Const.MAX_COUNT_OF_SHOW_ITEM) {
                Toast.makeText(view.getContext(), "상세 페이지로 이동", Toast.LENGTH_SHORT).show();
            } else {
                for (int i=startIndex; i<startIndex+Const.DEFAULT_COUNT_OF_SHOW_ITEM; i=i+1) {
                    if (data.getRecent_posts().size() > i) {
                        viewModel.addPostViewModel(new PostViewModel(data.getRecent_posts().get(i)));
                        newPostIndex++;
                    }
                }
            }
            break;
        }
    }

    public void onClickPostItem(View v, Post model) {
        final Post[] post = new Post[1];
        postAPI = PostApi.getInstance();
        Intent intent = new Intent(v.getContext(), DetailView.class);

        Observable<Response<Post>> postObs = postAPI.getPost(model.getId());
        postObs.subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                   jsonData -> {
                       if (jsonData.isSuccessful())
                           post[0] = jsonData.body();
                   },
                   // Error 처리
                   throwable -> {},
                   // Complete 처리
                   () -> {
                       if (post[0] != null) {
                           intent.putExtra("model", post[0]);
                           v.getContext().startActivity(intent);
                       }
                   }
               );
    }


    public void onClickUserItem(View v, User model) {
        Intent intent = new Intent(v.getContext(), UserPageView.class);
        intent.putExtra("user", model);

        if (intent != null)
            v.getContext().startActivity(intent);
    }

    public void onClickedSetting(View v) {
        Intent intent = new Intent(v.getContext(), UserPageView.class);
        intent.putExtra("user", Const.user);
        v.getContext().startActivity(intent);
    }

    public void onClickedLike(View view, View root, Post model) {
        if (view instanceof ToggleButton) {
            ToggleButton toggleBtn = (ToggleButton)view;

            PostApi.getInstance().pushLike(model.getId(), (code, msg, data) -> {
                model.setNum_liked(((Post) data).getNum_liked());
                Log.d(TAG, "onClickedLike: " + model.getNum_liked());
            });

            if (toggleBtn.isChecked()) {
                toggleBtn.setBackgroundDrawable(
                        root.getResources().
                                getDrawable(R.drawable.icon_like)
                );
            } else {
                toggleBtn.setBackgroundDrawable(
                        root.getResources().
                                getDrawable(R.drawable.icon_unlike)
                );
            }
        }
    }

    public void onClickedToast(View v) {
        Toast.makeText(v.getContext(), "Test", Toast.LENGTH_SHORT).show();
    }

    public void onClickedLogOut(View v, Activity activity) {
        SignUtil.logout(activity, activity);
    }

    public void onClickedClose(View v, DrawerLayout drawerLayout) {
        drawerLayout.closeDrawers();
    }
}
