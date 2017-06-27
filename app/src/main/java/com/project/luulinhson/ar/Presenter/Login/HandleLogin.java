package com.project.luulinhson.ar.Presenter.Login;

import android.content.Context;
import android.util.Log;

import com.project.luulinhson.ar.CustomView.ServerCallback;
import com.project.luulinhson.ar.Model.Login.ModelLogin;
import com.project.luulinhson.ar.Model.Object.User;
import com.project.luulinhson.ar.View.Login.ViewHandleLogin;

import org.json.JSONException;
import org.json.JSONObject;

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
    public void HandleLogin(Context context, String email, String password) {
        modelLogin.Login(context, email, password, new ServerCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int message = jsonObject.getInt("success");
                    Log.d("MESSAGE", "onSuccess: " + message);
                    if(message == 1){
                        String token = jsonObject.getString("token");
//                        Log.d("TOKEN..........", "onSuccess: " + token);
                        viewHandleLogin.LoginSuccess(token);
                    }else {
                        viewHandleLogin.LoginFail();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
