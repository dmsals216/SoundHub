package com.heepie.soundhub.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.adapters.TextViewBindingAdapter;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.heepie.soundhub.domain.logic.UserApi;
import com.heepie.soundhub.domain.model.User;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.utils.RetrofitUtil;
import com.heepie.soundhub.view.ListView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.Result;


/**
 * Created by Heepie on 2017. 11. 27..
 */

public class InputViewModel implements TextViewBindingAdapter.OnTextChanged{
    public final String TAG = getClass().getSimpleName();
    public final ObservableBoolean showSignUp = new ObservableBoolean(false);
    public final ObservableBoolean showSignUpOption = new ObservableBoolean(false);
    public final ObservableBoolean showSignIn = new ObservableBoolean(false);

    public final ObservableField<String> email = new ObservableField<>("");
    public final ObservableField<String> password = new ObservableField<>("");
    public final ObservableField<String> passCheck = new ObservableField<>("");
    public final ObservableField<String> instrument = new ObservableField<>("vocal");
    public final ObservableField<String> name = new ObservableField<>("");

    public final ObservableBoolean checkEmail = new ObservableBoolean(false);
    public final ObservableBoolean checkPassword = new ObservableBoolean(false);
    public final ObservableBoolean checkPassCheck = new ObservableBoolean(false);
    public final ObservableBoolean checkName = new ObservableBoolean(false);

    public class LoginResult {
        public String token;
        public User user;
    }

    private Context context;
    private LoginModelListener listener;

    public InputViewModel(Context context, LoginModelListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public interface LoginModelListener {
        public void activityClose();
    }

    public void onClickedSignUp(View v) {
        showSignUp.set(true);
    }

    public void onClickedSignIn(View v) {
        showSignIn.set(true);
    }

    public void onClickedSignUpOption(View v) {
        showSignUp.set(false);
        showSignUpOption.set(true);
    }

    public void onClickedFrame(View v) {
        showSignUpOption.set(false);
        showSignIn.set(false);
        showSignUp.set(false);
        email.set("");
        password.set("");
        passCheck.set("");
        instrument.set("Vocal");
        name.set("");

    }
    public void onClickedFrame() {
        showSignUpOption.set(false);
        showSignIn.set(false);
        showSignUp.set(false);
        email.set("");
        password.set("");
        passCheck.set("");
        instrument.set("Vocal");
        name.set("");
    }

    public void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public void onClickedNewSignUp(View v) {
        UserApi.IUser service = RetrofitUtil.getInstance().create(UserApi.IUser.class);
        RequestBody email1 = RequestBody.create(MediaType.parse("text/plain"), email.get());
        RequestBody password1 = RequestBody.create(MediaType.parse("text/plain"), password.get());
        RequestBody password2 = RequestBody.create(MediaType.parse("text/plain"), password.get());
        RequestBody instrument1 = RequestBody.create(MediaType.parse("text/plain"), instrument.get());
        RequestBody name1 = RequestBody.create(MediaType.parse("text/plain"), name.get());
        Call<Result> response = service.getData(email1, password1, password2, instrument1, name1);
        response.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "이메일을 확인해주세요", Toast.LENGTH_SHORT).show();
                    onClickedFrame();
                }else {
                    Toast.makeText(context, "회원가입에 실패 하셨습니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(context, "회원가입에 실패 하셨습니다2", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickedLogin(View v) {
        UserApi.IUser service = RetrofitUtil.getInstance().create(UserApi.IUser.class);
        RequestBody email1 = RequestBody.create(MediaType.parse("text/plain"), email.get());
        RequestBody password1 = RequestBody.create(MediaType.parse("text/plain"), password.get());
        Call<LoginResult> response = service.getLogin(email1, password1);
        response.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if(response.isSuccessful()) {
                    LoginResult result = response.body();
                    Const.TOKEN = result.token;
                    Const.user = result.user;
                    SharedPreferences sp = context.getSharedPreferences("User", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("userEmail", email.get());
                    editor.putString("userPass", password.get());
                    editor.commit();
                    Intent intent = new Intent(context, ListView.class);
                    context.startActivity(intent);
                    listener.activityClose();
                }else {
                    Toast.makeText(context, "로그인에 실패 하셨습니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(context, "로그인에 실패 하셨습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean activityCheck() {
        return showSignUp.get() || showSignIn.get() || showSignUpOption.get();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        checkEmail.set(isValidEmail());
        checkPassword.set(isValidPassword());
        checkPassCheck.set(password.get().equals(passCheck.get()));
        checkName.set(isValidName());
    }

    public boolean isValidPassword() {
        String regex = "^[A-Za-z0-9]{8,16}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password.get());
        return m.matches();
    }

    public boolean isValidEmail() {
        String regex = "^[a-z0-9A-Z_-]+(.[a-z0-9A-Z_-])*@([a-z0-9A-Z.])+.([a-zA-Z]){2,}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email.get());
        return m.matches();
    }

    public boolean isValidName(){
        String regex = "^[가-힣A-Za-z0-9]{2,12}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name.get());
        return m.matches();
    }
}
