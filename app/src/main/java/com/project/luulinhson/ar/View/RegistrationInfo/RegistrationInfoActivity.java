package com.project.luulinhson.ar.View.RegistrationInfo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.project.luulinhson.ar.Model.Object.User;
import com.project.luulinhson.ar.R;
import com.project.luulinhson.ar.View.MyPage.MyPageActivity;
import com.project.luulinhson.ar.View.Registration.RegistrationActivity;

public class RegistrationInfoActivity extends AppCompatActivity {

    TextView tvName,tvBirthDay,tvSchool,tvEmail,tvPassword;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_info);

        tvName = (TextView) findViewById(R.id.tvName);
        tvBirthDay = (TextView) findViewById(R.id.tvBirthDay);
        tvSchool = (TextView) findViewById(R.id.tvSchool);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvPassword = (TextView) findViewById(R.id.tvPassword);
        btnNext = (Button) findViewById(R.id.btnNext);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
        String school = intent.getStringExtra("school");


        tvName.setText(user.getFirst_name() + " " + user.getLast_name());
        tvBirthDay.setText(user.getDate_of_birth());
        tvSchool.setText(school);
        tvEmail.setText(user.getEmail());
        tvPassword.setText(user.getPassword());

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(KiemTraKetNoiMang()){
                    Intent iMyPage = new Intent(RegistrationInfoActivity.this, MyPageActivity.class);
                    startActivity(iMyPage);
                    finish();
                }else {
                    Toast.makeText(RegistrationInfoActivity.this, "Please connect to internet!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean KiemTraKetNoiMang() {
        ConnectivityManager connectivityManager = (ConnectivityManager) RegistrationInfoActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            finish();
        }
        return false;
    }
}
