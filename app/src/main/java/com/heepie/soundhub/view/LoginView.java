package com.heepie.soundhub.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.heepie.soundhub.BuildConfig;
import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.LoginViewBinding;
import com.heepie.soundhub.domain.logic.UserApi;
import com.heepie.soundhub.utils.Const;
import com.heepie.soundhub.utils.RetrofitUtil;
import com.heepie.soundhub.viewmodel.InputViewModel;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginView extends AppCompatActivity implements InputViewModel.LoginModelListener{
    public final String TAG = getClass().getSimpleName();
    private int GOOGLELOGINREQ = 100;

    InputViewModel inputViewModel = new InputViewModel(this, this);
    GoogleSignInClient mGoogleSignInClient;
    LoginViewBinding loginViewBinding;
    OAuthLogin mOAuthLoginModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginWithStart();
        loginViewBinding = DataBindingUtil.setContentView(this, R.layout.login_view);
        loginViewBinding.setViewModel(inputViewModel);
        setListener();
    }

    private void loginWithNaverStart() {
        mOAuthLoginModule = OAuthLogin.getInstance();
        mOAuthLoginModule.init(this
                ,BuildConfig.NAVER_CLIENT_ID
                ,BuildConfig.NAVER_CLIENT_PW
                ,BuildConfig.NAVER_CLIENT_NAME
        );
        if(null != mOAuthLoginModule.getAccessToken(this)) {
            mOAuthLoginModule.startOauthLoginActivity(this, mOAuthLoginHandler);
        }
    }

    private void loginWithGoogleStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(null != account) {
            Intent intent = new Intent(this, ListView.class);
            startActivity(intent);
            finish();
        }
    }

    private void loginWithServerStart() {
        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        if(!sp.getString("userEmail", "").equals("")) {
            UserApi.IUser service = RetrofitUtil.getInstance().create(UserApi.IUser.class);
            RequestBody email1 = RequestBody.create(MediaType.parse("text/plain"), sp.getString("userEmail", ""));
            RequestBody password1 = RequestBody.create(MediaType.parse("text/plain"), sp.getString("userPass", ""));
            Call<InputViewModel.LoginResult> response = service.getLogin(email1, password1);
            response.enqueue(new Callback<InputViewModel.LoginResult>() {
                @Override
                public void onResponse(Call<InputViewModel.LoginResult> call, Response<InputViewModel.LoginResult> response) {
                    if (response.isSuccessful()) {
                        InputViewModel.LoginResult result = response.body();
                        Const.TOKEN = result.token;
                        Const.user = result.user;
                        Intent intent = new Intent(LoginView.this, ListView.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(LoginView.this, "로그인을 다시 시도해 주십시오", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<InputViewModel.LoginResult> call, Throwable t) {

                }
            });
        }
    }

    private void loginWithStart() {
        loginWithServerStart();
        loginWithGoogleStart();
        loginWithNaverStart();
    }

    private void setListener() {
        loginViewBinding.signInButton.setOnClickListener(view -> {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, GOOGLELOGINREQ);
        });

        loginViewBinding.signInButton2.setOnClickListener(view -> {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, GOOGLELOGINREQ);
        });

        loginViewBinding.buttonOAuthLoginImg.setOAuthLoginHandler(mOAuthLoginHandler);
        loginViewBinding.buttonOAuthLoginImg2.setOAuthLoginHandler(mOAuthLoginHandler);
    }

    @Override
    protected void onStart() {
        super.onStart();



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == GOOGLELOGINREQ) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Intent intent = new Intent(this, ListView.class);
            startActivity(intent);
            finish();
        } catch (ApiException e) {
            Toast.makeText(this, "구글 로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("HandlerLeak")
    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
                Intent intent = new Intent(LoginView.this, ListView.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginView.this, "네이버 로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
            }
        };
    };

    @Override
    public void onBackPressed() {
        if(inputViewModel.activityCheck()) {
            inputViewModel.onClickedFrame();
            return;
        }

        super.onBackPressed();
    }

    @Override
    public void activityClose() {
        finish();
    }
}
