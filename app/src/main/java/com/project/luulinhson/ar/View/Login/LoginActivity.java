package com.project.luulinhson.ar.View.Login;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.luulinhson.ar.Model.Object.User;
import com.project.luulinhson.ar.Presenter.Login.HandleLogin;
import com.project.luulinhson.ar.R;
import com.project.luulinhson.ar.View.Reminder.ReminderActivity;

public class LoginActivity extends AppCompatActivity implements ViewHandleLogin{

    EditText edEmail,edPassword;
    Button btnLogin;
    HandleLogin handleLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = (EditText) findViewById(R.id.edEmail);
        edPassword = (EditText) findViewById(R.id.edPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        handleLogin = new HandleLogin(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(KiemTraKetNoiMang()){
                    String email = edEmail.getText().toString();
                    String password = edPassword.getText().toString();
                    User user = new User();
                    user.setEmail(email);
                    user.setPassword(password);

                    handleLogin.HandleLogin(LoginActivity.this,user);
                }else {
                    Toast.makeText(LoginActivity.this, "Please connect to internet!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean KiemTraKetNoiMang() {
        ConnectivityManager connectivityManager = (ConnectivityManager) LoginActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void LoginSuccess(User user) {

    }

    @Override
    public void LoginFail() {

    }
}
