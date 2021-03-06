package com.project.luulinhson.ar.Presenter.Registration;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.project.luulinhson.ar.CustomView.ServerCallback;
import com.project.luulinhson.ar.Model.Object.User;
import com.project.luulinhson.ar.Model.Registration.ModelRegistration;
import com.project.luulinhson.ar.View.Registration.ViewHandleRegistrantion;

import org.json.JSONException;
import org.json.JSONObject;

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
    public void HandleRegistration(final Context context, User user) {
        modelRegistration.RegistrationUser(context, user, new ServerCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonOb = new JSONObject(result);
                    String message = jsonOb.getString("message");
                    Log.d("MESSAGE......", "onSuccess: " + message);
                    if(message.equals("Sign Up Successful")){
                        viewHandleRegistrantion.RegistrantionSuccsess();
                        // Lấy token server trả về và lưu vào SharedPreferences
                        String token = jsonOb.getString("token");
                        SharedPreferences sharedPreferences = context.getSharedPreferences("usertoken",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token",token);
                        editor.commit();
                    }else {
                        viewHandleRegistrantion.RegistrantionFail();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
