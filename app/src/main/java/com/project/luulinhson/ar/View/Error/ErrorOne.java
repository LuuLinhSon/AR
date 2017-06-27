package com.project.luulinhson.ar.View.Error;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.project.luulinhson.ar.R;
import com.project.luulinhson.ar.View.Login.LoginActivity;
import com.project.luulinhson.ar.View.Registration.RegistrationActivity;
import com.project.luulinhson.ar.View.Reminder.ReminderActivity;
import com.project.luulinhson.ar.View.Top.TopActivity;

public class ErrorOne extends AppCompatActivity {

    Button btnBack;
    int idactivity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_one);

        btnBack = (Button) findViewById(R.id.btnBack);

        Intent intent = getIntent();
        idactivity = intent.getIntExtra("idactivity",0);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idactivity == 1){
                    Intent iRegistration = new Intent(ErrorOne.this,RegistrationActivity.class);
                    startActivity(iRegistration);
                }else if(idactivity == 2) {
                    Intent iLogin = new Intent(ErrorOne.this,LoginActivity.class);
                    startActivity(iLogin);
                }

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            finish();
            if(idactivity == 1){
                Intent iRegistration = new Intent(ErrorOne.this,RegistrationActivity.class);
                startActivity(iRegistration);
            }else if(idactivity == 2) {
                Intent iLogin = new Intent(ErrorOne.this,LoginActivity.class);
                startActivity(iLogin);
            }
        }
        return false;
    }
}
