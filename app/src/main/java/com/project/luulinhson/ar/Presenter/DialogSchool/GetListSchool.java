package com.project.luulinhson.ar.Presenter.DialogSchool;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.project.luulinhson.ar.CustomView.ServerCallback;
import com.project.luulinhson.ar.Model.DialogSchool.ModelDialogSchool;
import com.project.luulinhson.ar.Model.Object.School;
import com.project.luulinhson.ar.View.DialogSchool.ViewGetListSchool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 6/25/2017.
 */

public class GetListSchool implements iGetListSchool {

    ViewGetListSchool viewGetListSchool;
    ModelDialogSchool modelDialogSchool;

    public GetListSchool(ViewGetListSchool viewGetListSchool){
        this.viewGetListSchool = viewGetListSchool;
        modelDialogSchool = new ModelDialogSchool();
    }

    @Override
    public void GetListSchool(Context context) {
        final List<School> schoolList = new ArrayList<>();
        modelDialogSchool.GetListSchool(context,new ServerCallback() {
            @Override
            public void onSuccess(String result) {
                Log.d("KET QUA..........", "onSuccess: " + result);
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jOb = jsonArray.getJSONObject(i);
                        int id = Integer.parseInt(jOb.getString("id"));
                        String schoolName = jOb.getString("school");
                        School school = new School();
                        school.setId(id);
                        school.setSchoolName(schoolName);

                        schoolList.add(school);

                        viewGetListSchool.GetListSchoolSuccess(schoolList);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
