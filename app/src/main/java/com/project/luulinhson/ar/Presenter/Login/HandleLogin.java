package com.project.luulinhson.ar.Presenter.Login;

import android.content.Context;

import com.project.luulinhson.ar.Model.Login.ModelLogin;
import com.project.luulinhson.ar.Model.Object.User;
import com.project.luulinhson.ar.View.Login.ViewHandleLogin;

/**
 * Created by Admin on 6/16/2017.
 */

public class HandleLogin implements iHandleLogin {

    ViewHandleLogin viewHandleLogin;
    ModelLogin modelLogin;

    public HandleLogin(ViewHandleLogin viewHandleLogin){
        this.viewHandleLogin = viewHandleLogin;
        modelLogin = new ModelLogin();
    }
    @Override
    public void HandleLogin(Context context, User user) {
        User userResult = modelLogin.Login(context,user);
        String name = userResult.getFirst_name();
        if(!name.equals("")){
            viewHandleLogin.LoginSuccess(userResult);
        }else {
            viewHandleLogin.LoginFail();
        }
    }
}
