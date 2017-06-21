package com.project.luulinhson.ar.View.Login;

import com.project.luulinhson.ar.Model.Object.User;

/**
 * Created by Admin on 6/16/2017.
 */

public interface ViewHandleLogin {
    void LoginSuccess(User user);
    void LoginFail();
}
