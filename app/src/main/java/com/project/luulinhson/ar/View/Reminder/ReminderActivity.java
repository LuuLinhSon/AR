package com.project.luulinhson.ar.View.Reminder;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.luulinhson.ar.R;
import com.project.luulinhson.ar.View.RegistrationInfo.RegistrationInfoActivity;
import com.project.luulinhson.ar.View.Top.TopActivity;

public class ReminderActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edEmail;
    Button btnSend,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        edEmail = (EditText) findViewById(R.id.edEmail);
        btnSend = (Button) findViewById(R.id.btnSendPassword);
        btnBack = (Button) findViewById(R.id.btnBack);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnSendPassword:
                if(KiemTraKetNoiMang()){

                }else {
                    Toast.makeText(ReminderActivity.this, "Please connect to internet!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnBack:
                Intent iTop = new Intent(ReminderActivity.this,TopActivity.class);
                startActivity(iTop);
                break;
        }
    }

    private boolean KiemTraKetNoiMang() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ReminderActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
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
            Intent iTop = new Intent(ReminderActivity.this,TopActivity.class);
            startActivity(iTop);
        }
        return false;
    }
}
