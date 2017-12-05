package com.heepie.soundhub.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.heepie.soundhub.domain.logic.UserApi;
import com.heepie.soundhub.domain.model.User;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.utils.RetrofitUtil;
import com.heepie.soundhub.view.ListView;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by eunmin on 2017-12-04.
 */

public class UserPageViewModel {
    private Context context;
    public final ObservableBoolean check = new ObservableBoolean(false);
    public final ObservableField<String> userName = new ObservableField<>("");
    public final ObservableField<String> userInstrument = new ObservableField<>("");

    public final ObservableBoolean sucheck = new ObservableBoolean(false);

    public UserPageViewModel(Context context) {
        this.context = context;
    }

    public void setCheck(boolean check) {
        this.check.set(check);
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public void setUserInstrument(String userInstrument) {
        this.userInstrument.set(userInstrument);
    }

    public void sucheckChanged(View view) {
        if(sucheck.get()) {
            sucheck.set(false);
        }else {
            sucheck.set(true);
        }
    }

    public void onClickedModify(View view) {
        UserApi.IUser service = RetrofitUtil.getInstance().create(UserApi.IUser.class);
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), userName.get());
        RequestBody instrument = RequestBody.create(MediaType.parse("text/plain"), userInstrument.get());

        Call<User> response = service.getModify(Const.user.getId(), "Token " + Const.TOKEN, name, instrument);
        response.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    sucheck.set(false);
                    Const.user = response.body();
                    userName.set(Const.user.getNickname());
                    userInstrument.set(Const.user.getInstrument());
                    Toast.makeText(context, "회원 정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "회원수정에 실패 하셨습니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, "회원수정에 실패 하셨습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
