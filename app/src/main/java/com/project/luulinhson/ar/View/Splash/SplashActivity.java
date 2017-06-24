package com.project.luulinhson.ar.View.Splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.luulinhson.ar.R;
import com.project.luulinhson.ar.View.MyPage.MyPageActivity;
import com.project.luulinhson.ar.View.Top.TopActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();

        SharedPreferences sharedPreferences = getSharedPreferences("usertoken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token","");
        if(!token.equals("")){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this,MyPageActivity.class);
                    startActivity(intent);
                    finish();
                }
            },1000);
        }else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this,TopActivity.class);
                    startActivity(intent);
                    finish();
                }
            },1000);
        }



    }

}
