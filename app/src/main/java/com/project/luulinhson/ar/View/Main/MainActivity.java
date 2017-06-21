package com.project.luulinhson.ar.View.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.project.luulinhson.ar.Model.Login.ModelLogin;
import com.project.luulinhson.ar.Model.Object.User;
import com.project.luulinhson.ar.Model.Registration.ModelRegistration;
import com.project.luulinhson.ar.R;

public class MainActivity extends AppCompatActivity {
    Button btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOK = (Button) findViewById(R.id.btnOK);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ModelRegistration modelRegistration = new ModelRegistration();
//                ModelLogin modelLogin = new ModelLogin();
//                User user = new User();
//                boolean kiemtra = modelRegistration.Registration(MainActivity.this,user);
//                user = modelLogin.Login(MainActivity.this,user);


            }
        });

    }
}
