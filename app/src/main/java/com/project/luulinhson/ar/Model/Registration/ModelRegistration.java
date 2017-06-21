package com.project.luulinhson.ar.Model.Registration;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.project.luulinhson.ar.Conection.DownLoadJson;
import com.project.luulinhson.ar.Model.Object.User;
import com.project.luulinhson.ar.View.Registration.ViewHandleRegistrantion;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

/**
 * Created by Admin on 6/13/2017.
 */

public class ModelRegistration {

    public boolean Registration(Context context,User user){
        String url = "http://localhost:8080/login/views/signup.php";
        Gson gson = new Gson();
        String jsonString = gson.toJson(user);
        Log.d("GSON", "Registration: " + jsonString);
        Boolean check = false;

        DownLoadJson downLoadJson = new DownLoadJson(context,url);
        downLoadJson.execute(jsonString);

        try {
            String json = downLoadJson.get();
            JSONObject jsonObject = new JSONObject(json);
            String result = jsonObject.getString("message");
            if(result.equals("Sign Up Successful")){
                check = true;
            }else {
                check = false;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return check;
    }
}
