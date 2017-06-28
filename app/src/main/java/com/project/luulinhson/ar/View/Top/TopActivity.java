package com.project.luulinhson.ar.View.Top;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.project.luulinhson.ar.R;
import com.project.luulinhson.ar.View.Login.LoginActivity;
import com.project.luulinhson.ar.View.MyPage.MyPageActivity;
import com.project.luulinhson.ar.View.Registration.RegistrationActivity;
import com.project.luulinhson.ar.View.Reminder.ReminderActivity;

public class TopActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imRegister,imLogin,imPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        imRegister = (ImageView) findViewById(R.id.activity_top_registration);
        imLogin = (ImageView) findViewById(R.id.activity_top_login);
        imPass = (ImageView) findViewById(R.id.activity_top_pass);

        imRegister.setOnClickListener(this);
        imLogin.setOnClickListener(this);
        imPass.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.activity_top_registration:
                if(KiemTraKetNoiMang()){
                    Intent iRegistration = new Intent(TopActivity.this, RegistrationActivity.class);
                    startActivity(iRegistration);
                    finish();
                }else {
                    Toast.makeText(this, "Please connect to internet!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.activity_top_login:
                if(KiemTraKetNoiMang()){
                    Intent iLogin = new Intent(TopActivity.this, LoginActivity.class);
                    startActivity(iLogin);
                    finish();
                }else {
                    Toast.makeText(this, "Please connect to internet!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.activity_top_pass:
                if(KiemTraKetNoiMang()){
                    Intent iReminder = new Intent(TopActivity.this, ReminderActivity.class);
                    startActivity(iReminder);
                    finish();
                }else {
                    Toast.makeText(this, "Please connect to internet!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private boolean KiemTraKetNoiMang() {
        ConnectivityManager connectivityManager = (ConnectivityManager) TopActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }

    }
}
