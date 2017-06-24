package com.project.luulinhson.ar.Presenter.MyPage;

import android.content.Context;

import com.project.luulinhson.ar.CustomView.ServerCallback;
import com.project.luulinhson.ar.Model.MyPage.ModelMyPage;
import com.project.luulinhson.ar.Model.Object.User;
import com.project.luulinhson.ar.View.MyPage.ViewHandleGetDataMyPage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 6/24/2017.
 */

public class HandleGetDataMyPage implements iHandleGetDataMyPge {

    ViewHandleGetDataMyPage viewHandleGetDataMyPage;
    ModelMyPage modelMyPage;

    public HandleGetDataMyPage(ViewHandleGetDataMyPage viewHandleGetDataMyPage){
        this.viewHandleGetDataMyPage = viewHandleGetDataMyPage;
        modelMyPage = new ModelMyPage();
    }

    @Override
    public void HandleGetDataMyPage(Context context, String token) {
        modelMyPage.GetDataMyPage(context, token, new ServerCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if(success == 1){
                        User user = new User();
                        user.setFirst_name(jsonObject.getString("firstName"));
                        user.setLast_name(jsonObject.getString("lastName"));
                        user.setId_school(jsonObject.getString("idSchool"));
                        user.setDate_of_birth(jsonObject.getString("dateOfBirth"));
                        user.setProfile_picture(jsonObject.getString("profilePicture"));

                        viewHandleGetDataMyPage.GetDataSuccess(user);
                    }else {
                        viewHandleGetDataMyPage.GetDataFail();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
