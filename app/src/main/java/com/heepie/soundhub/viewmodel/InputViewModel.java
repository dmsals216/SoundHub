package com.heepie.soundhub.viewmodel;

import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

/**
 * Created by Heepie on 2017. 11. 27..
 */

public class InputViewModel {
    public final String TAG = getClass().getSimpleName();
    public boolean showSignUp = false;
    public boolean showSignUpOption = false;
    public boolean showSignIn = false;

    public void onClickedSignUp(View v) {
        if (showSignUpOption)
            showSignUpOption = false;
        else
            showSignUpOption = true;
        Log.d(TAG, "showSignUp: " + showSignUp);
    }

    public void onClickedSignIn(View v) {
        if (showSignIn)
            showSignIn = false;
        else
            showSignIn = true;
        Log.d(TAG, "showSignUp: " + showSignIn);
    }

    public void onClickedSignUpOption(View v) {
        if (showSignUp)
            showSignUp = false;
        else
            showSignUp = true;
        Log.d(TAG, "showSignUp: " + showSignUp);
    }

    public void onClickedFrame(View v) {
        showSignUpOption = false;
        showSignIn = false;
        showSignUp = false;
    }
}
