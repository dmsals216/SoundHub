package com.heepie.soundhub.domain.logic;

import android.databinding.ObservableArrayList;
import android.util.Log;

import com.heepie.soundhub.BuildConfig;
import com.heepie.soundhub.Interfaces.ICallback;
import com.heepie.soundhub.domain.model.User;
import com.heepie.soundhub.viewmodel.UserViewModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Heepie on 2017. 11. 30..
 */

public class UserApi extends AbsApi {
    public static int populUserIndex = 0;
    private static UserApi instance;
    public static ObservableArrayList<UserViewModel> users = new ObservableArrayList<>();

    private UserApi() {

    }

    public static UserApi getInstance() {
        if (instance == null)
            instance = new UserApi();
        return instance;
    }

    public void getData(ICallback callback) {
        // 데이터 초기화
        users.clear();

//        서버 측 완료시 제거
        createRetrofit(BuildConfig.SERVER_URL);

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

//                            입력 받은 데이터 확인
                            for (User item : user.body()) {
                                users.add(new UserViewModel(item));
                            }


                            callback.initData(user.code(), user.message(), user.body());
                        });

    }

    public interface IUser {
        @GET("user/")
        Observable<Response<List<User>>> getUser();

        @Multipart
        @POST("user/signup/")
        Call<Result> getData(@Part("email") String email, @Part("password1") String password1, @Part("password2") String password2, @Part("instrument") String instrument, @Part("nickname") String nickname);

        @Multipart
        @POST("user/login/")
        Call<Result> getLogin(@Part("email") RequestBody email, @Part("password") RequestBody password);
    }
}
