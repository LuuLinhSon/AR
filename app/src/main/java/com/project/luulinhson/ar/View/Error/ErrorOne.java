package com.project.luulinhson.ar.View.Error;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.project.luulinhson.ar.R;
import com.project.luulinhson.ar.View.Registration.RegistrationActivity;

public class ErrorOne extends AppCompatActivity {

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_one);

        btnBack = (Button) findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iRegistration = new Intent(ErrorOne.this,RegistrationActivity.class);
                startActivity(iRegistration);
            }
        });
    }
}
