package com.project.luulinhson.ar.View.Error;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.luulinhson.ar.R;
import com.project.luulinhson.ar.View.Login.LoginActivity;
import com.project.luulinhson.ar.View.Reminder.ReminderActivity;

public class ErrorTwo extends AppCompatActivity implements View.OnClickListener{

    ImageView imDuplication;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_two);

        imDuplication = (ImageView) findViewById(R.id.imDuplication);
        btnBack = (Button) findViewById(R.id.btnBack);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.imDuplication:
                if(KiemTraKetNoiMang()){

                }else {
                    Toast.makeText(ErrorTwo.this, "Please connect to internet!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnBack:
                Intent iLogin = new Intent(ErrorTwo.this, LoginActivity.class);
                startActivity(iLogin);
                break;
        }
    }

    private boolean KiemTraKetNoiMang() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ErrorTwo.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }

    }
}
