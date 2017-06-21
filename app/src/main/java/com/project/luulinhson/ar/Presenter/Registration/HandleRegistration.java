package com.project.luulinhson.ar.Presenter.Registration;

import android.content.Context;

import com.project.luulinhson.ar.Model.Object.User;
import com.project.luulinhson.ar.Model.Registration.ModelRegistration;
import com.project.luulinhson.ar.View.Registration.ViewHandleRegistrantion;

/**
 * Created by Admin on 6/13/2017.
 */

public class HandleRegistration implements iHandleRegistration {

    ViewHandleRegistrantion viewHandleRegistrantion;
    ModelRegistration modelRegistration;

    public HandleRegistration(ViewHandleRegistrantion viewHandleRegistrantion){
        this.viewHandleRegistrantion = viewHandleRegistrantion;
        modelRegistration = new ModelRegistration();
    }

    @Override
    public void HandleRegistration(Context context,User user) {
        boolean check = modelRegistration.Registration(context,user);
        if(check){
            viewHandleRegistrantion.RegistrantionSuccsess();
        }else {
            viewHandleRegistrantion.RegistrantionFail();
        }

    }
}
