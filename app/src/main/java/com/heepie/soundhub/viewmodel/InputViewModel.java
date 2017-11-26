package com.heepie.soundhub.viewmodel;

import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

/**
 * Created by Heepie on 2017. 11. 27..
 */

public class InputViewModel {
    public final String TAG = getClass().getSimpleName();
    public final ObservableField<Boolean> showSignUp = new ObservableField<>(false);
    public final ObservableField<Boolean> showSignIn = new ObservableField<>(false);

    public void onClickedSignUp(View v) {
        if (showSignUp.get())
            showSignUp.set(false);
        else
            showSignUp.set(true);
        Log.d(TAG, "showSignUp: " + showSignUp.get());
    }

    public void onClickedSignIn(View v) {
        if (showSignIn.get())
            showSignIn.set(false);
        else
            showSignIn.set(true);
        Log.d(TAG, "showSignUp: " + showSignIn.get());
    }

    public void onClickedFrame(View v) {
        showSignUp.set(false);
        showSignIn.set(false);
        v.setVisibility(View.GONE);
    }
}
