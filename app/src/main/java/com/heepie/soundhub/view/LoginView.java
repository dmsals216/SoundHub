package com.heepie.soundhub.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.heepie.soundhub.R;
import com.heepie.soundhub.databinding.LoginViewBinding;
import com.heepie.soundhub.viewmodel.InputViewModel;

public class LoginView extends AppCompatActivity {
    public final String TAG = getClass().getSimpleName();

    InputViewModel inputViewModel = new InputViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoginViewBinding loginViewBinding = DataBindingUtil.setContentView(this, R.layout.login_view);
        loginViewBinding.setViewModel(inputViewModel);
    }
}
