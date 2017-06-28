package com.project.luulinhson.ar.Presenter.Edit;

import android.content.Context;
import android.util.Log;

import com.project.luulinhson.ar.CustomView.ServerCallback;
import com.project.luulinhson.ar.Model.Edit.ModelEdit;
import com.project.luulinhson.ar.Model.Object.User;
import com.project.luulinhson.ar.View.Edit.ViewHandleEdit;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 6/28/2017.
 */

public class HandleEditUser implements iHandleEditUser{

    ViewHandleEdit viewHandleEdit;
    ModelEdit modelEdit;
    public HandleEditUser(ViewHandleEdit viewHandleEdit){
        this.viewHandleEdit = viewHandleEdit;
        modelEdit = new ModelEdit();
    }

    @Override
    public void HandleEditUser(Context context, User user) {
        modelEdit.EditUser(context, user, new ServerCallback() {
            @Override
            public void onSuccess(String result) {
                Log.d("RESULT_______EDIT", "onSuccess: " + result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if(success == 1){
                        String token = jsonObject.getString("token");
                        viewHandleEdit.editSuccsess(token);
                    }else {
                        viewHandleEdit.editFail();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
