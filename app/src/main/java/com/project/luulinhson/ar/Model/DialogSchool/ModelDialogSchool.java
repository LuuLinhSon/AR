package com.project.luulinhson.ar.Model.DialogSchool;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.project.luulinhson.ar.CustomView.ServerCallback;

import org.json.JSONArray;

/**
 * Created by Admin on 6/25/2017.
 */

public class ModelDialogSchool {

    public void GetListSchool(Context context,final ServerCallback serverCallback){
        String URL = "http://192.168.100.174:8080/login/views/school.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                serverCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

}
