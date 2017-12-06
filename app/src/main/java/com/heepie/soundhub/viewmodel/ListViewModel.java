package com.heepie.soundhub.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.heepie.soundhub.domain.logic.DataAPI;
import com.heepie.soundhub.domain.model.Data;
import com.heepie.soundhub.domain.model.Post;
import com.heepie.soundhub.domain.model.User;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.view.MusicUploadView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Heepie on 2017. 11. 27..
 */

public class ListViewModel extends BaseObservable {
    public final String TAG = getClass().getSimpleName();
    private DataAPI dataAPI;
    private Data data;



    @Bindable
    public UsersViewModel populUsers;

    @Bindable
    public PostsViewModel populPosts;

    @Bindable
    public PostsViewModel newPosts;
    private Context context;

    public ListViewModel(Context context) {
        populUsers = new UsersViewModel();
        populPosts = new PostsViewModel();
        newPosts = new PostsViewModel();
        this.context = context;
    }

    public void resetData() {
        populUsers.users.clear();
        populPosts.posts.clear();
        newPosts.posts.clear();
    }

    public void setDisplayData(String category) {
        dataAPI = DataAPI.getInstance();
        Observable<Response<Data>> dataObs = dataAPI.getData(category);

        dataObs.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        jsonData -> {
                            // 네트워크로 입력 받은 데이터 DataModel에 셋팅
                            dataAPI.setModelData(jsonData.body());
                            data = jsonData.body();
                            Log.d(TAG, "setDisplayData: " + data.getPop_posts().get(0).getComment_tracks().get("Bass").get(1).getId());
                            setData(data);
                        });
    }

    // DataModel로부터 가져온 데이터 ViewModel 내부에 셋팅
    private void setData(Data data) {
        for (User user : data.getPop_users()) {
            populUsers.addUserViewModel(new UserViewModel(user));
        }

        // 최초 5개 설정
        for (int i=0; i<Const.DEFAULT_COUNT_OF_SHOW_ITEM; i=i+1) {
            Post post = data.getPop_posts().get(i);
            populPosts.addPostViewModel(new PostViewModel(post));
        }

        // 최초 5개 설정
        for (int i=0; i<Const.DEFAULT_COUNT_OF_SHOW_ITEM; i=i+1) {
            Post post = data.getRecent_posts().get(i);
            newPosts.addPostViewModel(new PostViewModel(post));
        }
    }



    public void onClickedUpLoad(View view) {
        Toast.makeText(view.getContext(), "Clicked UpLoad", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, MusicUploadView.class);
        context.startActivity(intent);
    }
}
