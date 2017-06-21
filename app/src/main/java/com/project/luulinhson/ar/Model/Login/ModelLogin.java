package com.project.luulinhson.ar.Model.Login;

import android.content.Context;
import android.util.Log;

import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.project.luulinhson.ar.Conection.DownLoadJson;
import com.project.luulinhson.ar.Model.Object.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Admin on 6/16/2017.
 */

public class ModelLogin {

    public User Login(Context context, User user){
        String url = "";
        Gson gson = new Gson();
        User user1 = new User();
        user1.setEmail("sonlinh249@gmail.com");
        user1.setPassword("123456");
        String jsonString = gson.toJson(user1);
        Log.d("JSON", "Login: " + jsonString);
        User userResult = new User();

//        DownLoadJson downLoadJson = new DownLoadJson(context,url);
//        downLoadJson.execute(jsonString);

//        try {
//            String json = downLoadJson.get();
//            JSONObject jsonObject = new JSONObject(json);
//            Gson gsonResult = new Gson();
//            userResult = gsonResult.fromJson(json,User.class);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        return userResult;
    }
}
