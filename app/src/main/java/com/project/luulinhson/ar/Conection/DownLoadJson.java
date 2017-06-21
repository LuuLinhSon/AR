package com.project.luulinhson.ar.Conection;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Admin on 6/16/2017.
 */

public class DownLoadJson extends AsyncTask<String,Void,String> {

    Context context;
    String linkURL;
    OutputStream os;
    InputStream is;
    String result;

    public DownLoadJson(Context context,String linkURL){
        this.context = context;
        this.linkURL = linkURL ;
    }

    @Override
    protected String doInBackground(String... params) {

        String jsondata = params[0];

        try {
            URL url = new URL(linkURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type","application/json");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();

            os = httpURLConnection.getOutputStream();
            os.write(jsondata.getBytes());

            is = httpURLConnection.getInputStream();
            result = "";
            int byteCharacrer;
            while ((byteCharacrer = is.read()) != -1){
                result += (char)byteCharacrer;
            }

            is.close();
            os.close();
            httpURLConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("RESULT...............", "doInBackground: " + result);
        return result;

    }
}
