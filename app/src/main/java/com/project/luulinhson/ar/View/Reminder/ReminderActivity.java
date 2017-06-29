package com.project.luulinhson.ar.View.Reminder;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.luulinhson.ar.Presenter.Reminder.HandleValidateEmail;
import com.project.luulinhson.ar.R;
import com.project.luulinhson.ar.View.Login.LoginActivity;
import com.project.luulinhson.ar.View.Registration.RegistrationActivity;
import com.project.luulinhson.ar.View.RegistrationInfo.RegistrationInfoActivity;
import com.project.luulinhson.ar.View.Top.TopActivity;

public class ReminderActivity extends AppCompatActivity implements View.OnClickListener,ViewHandleValidateEmail{

    EditText edEmail;
    Button btnSend,btnBack;
    HandleValidateEmail handleValidateEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        edEmail = (EditText) findViewById(R.id.edEmail);
        btnSend = (Button) findViewById(R.id.btnSendPassword);
        btnBack = (Button) findViewById(R.id.btnBack);

        btnSend.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        handleValidateEmail = new HandleValidateEmail(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnSendPassword:
                if(KiemTraKetNoiMang()){
                    handleValidateEmail.HandleValidateEmail(ReminderActivity.this,edEmail.getText().toString());

                }else {
                    Toast.makeText(ReminderActivity.this, "Please connect to internet!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnBack:
                Intent iTop = new Intent(ReminderActivity.this,TopActivity.class);
                startActivity(iTop);
                finish();
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
            Intent iTop = new Intent(ReminderActivity.this,TopActivity.class);
            startActivity(iTop);
            finish();
        }
        return false;
    }

    @Override
    public void validateSuccess() {
        // gửi password qua email ở đây
        Toast.makeText(this,"Take password in my email.Thank you!!!",Toast.LENGTH_LONG).show();
        Intent iLogin = new Intent(ReminderActivity.this, LoginActivity.class);
        startActivity(iLogin);
        finish();
    }

    @Override
    public void validateFail() {
        Toast.makeText(this,"Email not existed!!!",Toast.LENGTH_LONG).show();
        Intent iRegister = new Intent(ReminderActivity.this, RegistrationActivity.class);
        startActivity(iRegister);
        finish();
    }
}
