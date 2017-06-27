package com.project.luulinhson.ar.Model.Registration;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.project.luulinhson.ar.CustomView.ServerCallback;
import com.project.luulinhson.ar.Model.Object.User;
import com.project.luulinhson.ar.View.Registration.ViewHandleRegistrantion;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by Admin on 6/13/2017.
 */

public class ModelRegistration {

    public void RegistrationUser(Context context, final User user, final ServerCallback callback)
    {
        String URL = "http://192.168.100.174:8080/login/views/signup.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                 callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
           @Override
           public String getBodyContentType()
           {
               return "application/x-www-form-urlencoded; charset=UTF-8";
           }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", user.getEmail());
                params.put("password",user.getPassword() );
                params.put("id_school",user.getId_school() );
                params.put("first_name",user.getFirst_name() );
                params.put("last_name",user.getLast_name() );
                params.put("date_of_birth",user.getDate_of_birth());
                return params;
            }
       };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }
}
