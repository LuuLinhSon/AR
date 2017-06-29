package com.project.luulinhson.ar.Presenter.Reminder;

import android.content.Context;

import com.project.luulinhson.ar.CustomView.ServerCallback;
import com.project.luulinhson.ar.Model.Reminder.ModelReminder;
import com.project.luulinhson.ar.View.Reminder.ViewHandleValidateEmail;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 6/29/2017.
 */

public class HandleValidateEmail implements iHandleValidateEmail {

    ViewHandleValidateEmail viewHandleValidateEmail;
    ModelReminder modelReminder;

    public HandleValidateEmail(ViewHandleValidateEmail viewHandleValidateEmail){
        this.viewHandleValidateEmail = viewHandleValidateEmail;
        modelReminder = new ModelReminder();
    }


    @Override
    public void HandleValidateEmail(Context context, String email) {
        modelReminder.ValidateEmail(context, email, new ServerCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if(success == 1){
                        viewHandleValidateEmail.validateSuccess();
                    }else {
                        viewHandleValidateEmail.validateFail();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
