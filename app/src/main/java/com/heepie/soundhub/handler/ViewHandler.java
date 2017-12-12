package com.heepie.soundhub.handler;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.heepie.soundhub.R;
import com.heepie.soundhub.domain.logic.DataAPI;
import com.heepie.soundhub.domain.logic.PostApi;
import com.heepie.soundhub.domain.logic.UserApi;
import com.heepie.soundhub.domain.model.Comment_track;
import com.heepie.soundhub.domain.model.Data;
import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.domain.model.User;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.view.DetailView;
import com.heepie.soundhub.view.ListView;
import com.heepie.soundhub.view.UserPageView;
import com.heepie.soundhub.viewmodel.PostViewModel;
import com.heepie.soundhub.viewmodel.PostsViewModel;
import com.heepie.soundhub.viewmodel.UserViewModel;

import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Heepie on 2017. 11. 30..
 */

public class ViewHandler {
    public final String TAG = getClass().getSimpleName();

    public static ViewHandler intance;
    private int populPostIndex;
    private int newPostIndex;

    private ViewHandler() {
        populPostIndex = Const.DEFAULT_COUNT_OF_SHOW_ITEM;
        newPostIndex   = Const.DEFAULT_COUNT_OF_SHOW_ITEM;
    }

    public static ViewHandler getIntance() {
        if (intance == null)
            intance = new ViewHandler();
        return intance;
    }

    // '더보기' 버튼 클릭시 실행되는 메소드
    public void onClickedMoreBtn(View view , PostsViewModel viewModel) {
        Data data = DataAPI.getInstance().getModelData();
        int startIndex;

        switch (view.getId()) {
        case R.id.populMoreBtn:
            startIndex = populPostIndex;

            // MAX_COUNT_OF_SHOW_ITEM까지 보여주고 추가로 누르면 상세 페이지로 이동
            if (startIndex == Const.MAX_COUNT_OF_SHOW_ITEM) {
                Toast.makeText(view.getContext(), "상세 페이지로 이동", Toast.LENGTH_SHORT).show();
            } else {
                for (int i = startIndex; i < startIndex + Const.DEFAULT_COUNT_OF_SHOW_ITEM; i = i + 1) {
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
                for (int i = startIndex; i < startIndex + Const.DEFAULT_COUNT_OF_SHOW_ITEM; i = i + 1) {
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
        Intent intent = new Intent(v.getContext(), DetailView.class);
        intent.putExtra("model", model);

        if (intent != null)
            v.getContext().startActivity(intent);
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
}
