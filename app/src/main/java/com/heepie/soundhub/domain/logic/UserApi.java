package com.heepie.soundhub.domain.logic;

import android.databinding.ObservableArrayList;
import android.util.Log;

import com.heepie.soundhub.Interfaces.IPostCallback;
import com.heepie.soundhub.Interfaces.IUserCallback;
import com.heepie.soundhub.domain.model.User;
import com.heepie.soundhub.viewmodel.UserViewModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Heepie on 2017. 11. 30..
 */

public class UserApi extends AbsApi {
    private static UserApi instance;
    public static ObservableArrayList<UserViewModel> users = new ObservableArrayList<>();

    private UserApi() {

    }

    public static UserApi getInstance() {
        if (instance == null)
            instance = new UserApi();
        return instance;
    }

    @Override
    public void getData(IPostCallback callback) {
        /*서버 측 완료시 제거
        createRetrofit();

        // Retrofit으로 서비스 생성
        IUser server = retrofit.create(IUser.class);

        // 서비스를 생성
        Observable<Response<List<User>>> observable = server.getUser();

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        user -> {
//                            서버와 통신 테스트
                            Log.e("heepie", user.code() + "\n" +
                                    user.message() + "\n" +
                                    user.body());

                            *//* 입력 받은 데이터 확인
                            for (User item : user.body()) {
                                users.add(new UserViewModel(item));
                            }*//*


//                            callback.initData(user.code(), user.message(), user.body());
                        });
                        */
    }

    public interface IUser {
        @GET("post/")
        Observable<Response<List<User>>> getUser();
    }
}
