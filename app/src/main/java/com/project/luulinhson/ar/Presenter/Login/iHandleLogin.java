package com.project.luulinhson.ar.Presenter.Login;

import android.content.Context;

import com.project.luulinhson.ar.CustomView.ServerCallback;
import com.project.luulinhson.ar.Model.Object.User;

/**
 * Created by Admin on 6/16/2017.
 */

public interface iHandleLogin {
    void HandleLogin(Context context, String email, String password);
}
