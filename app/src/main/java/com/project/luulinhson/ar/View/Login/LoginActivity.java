package com.project.luulinhson.ar.View.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.luulinhson.ar.Model.Object.User;
import com.project.luulinhson.ar.Presenter.Login.HandleLogin;
import com.project.luulinhson.ar.R;
import com.project.luulinhson.ar.View.Error.ErrorOne;
import com.project.luulinhson.ar.View.MyPage.MyPageActivity;
import com.project.luulinhson.ar.View.Reminder.ReminderActivity;
import com.project.luulinhson.ar.View.Top.TopActivity;

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

                    handleLogin.HandleLogin(LoginActivity.this,email,password);
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
    public void LoginSuccess(String token) {
        SharedPreferences sharedPreferences = getSharedPreferences("usertoken",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token",token);
        editor.commit();

        Intent iMyPage = new Intent(LoginActivity.this, MyPageActivity.class);
        startActivity(iMyPage);
    }

    @Override
    public void LoginFail() {
        Intent iErrorOne = new Intent(LoginActivity.this, ErrorOne.class);
        iErrorOne.putExtra("idactivity",2);
        startActivity(iErrorOne);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            finish();
            Intent iTop = new Intent(LoginActivity.this,TopActivity.class);
            startActivity(iTop);
        }
        return false;
    }
}
