package com.project.luulinhson.ar.View.Registration;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.luulinhson.ar.CustomView.DatePickerFragment;
import com.project.luulinhson.ar.Model.Object.User;
import com.project.luulinhson.ar.Presenter.Registration.HandleRegistration;
import com.project.luulinhson.ar.R;
import com.project.luulinhson.ar.View.DialogSchool.ListSchoolActivity;
import com.project.luulinhson.ar.View.Error.ErrorOne;
import com.project.luulinhson.ar.View.Error.ErrorTwo;
import com.project.luulinhson.ar.View.RegistrationInfo.RegistrationInfoActivity;
import com.project.luulinhson.ar.View.Top.TopActivity;

import java.util.Calendar;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener,ViewHandleRegistrantion{

    EditText edFirstName,edLastName,edEmail,edPassword;
    TextView tvBirthDay,tvSchool;
    CheckBox checkBox;
    Button btnDone;
    HandleRegistration handleRegistration;
    User user;
    int idSchool;
    public static final int REQUEST_CODE_SCHOOL = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edFirstName = (EditText) findViewById(R.id.edFirstName);
        edLastName = (EditText) findViewById(R.id.edLastName);
        edEmail = (EditText) findViewById(R.id.edEmail);
        edPassword = (EditText) findViewById(R.id.edPassword);
        tvBirthDay = (TextView) findViewById(R.id.tvBirthDay);
        tvSchool = (TextView) findViewById(R.id.tvSchool);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        btnDone = (Button) findViewById(R.id.btnDone);

        tvBirthDay.setOnClickListener(this);
        tvSchool.setOnClickListener(this);
        btnDone.setOnClickListener(this);

        handleRegistration = new HandleRegistration(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.tvBirthDay:
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getFragmentManager(),"ngaysinh");
                break;
            case R.id.tvSchool:
                if(KiemTraKetNoiMang()){
                    Intent iListSchool = new Intent(RegistrationActivity.this, ListSchoolActivity.class);
                    startActivityForResult(iListSchool,REQUEST_CODE_SCHOOL);
                }else {
                    Toast.makeText(this, "Please connect to internet!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnDone:
                if(KiemTraKetNoiMang()){
                    String firstName = edFirstName.getText().toString();
                    String lastName = edLastName.getText().toString();
                    String birthDay = tvBirthDay.getText().toString();
                    String school = tvSchool.getText().toString();
                    String email = edEmail.getText().toString();
                    String password = edPassword.getText().toString();
                    Boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(email).matches();
                    Boolean isCheck = checkBox.isChecked();

                    if(firstName.trim().equals("")){
//                    Toast.makeText(this,"The first name can not be empty",Toast.LENGTH_LONG).show();
                        Intent iError = new Intent(RegistrationActivity.this,ErrorOne.class);
                        startActivity(iError);
                    }else if(lastName.trim().equals("")){
//                    Toast.makeText(this,"The last name can not be empty",Toast.LENGTH_LONG).show();
                        Intent iError = new Intent(RegistrationActivity.this,ErrorOne.class);
                        startActivity(iError);
                    }else if(birthDay.trim().equals("")){
//                    Toast.makeText(this,"The birth day can not be empty",Toast.LENGTH_LONG).show();
                        Intent iError = new Intent(RegistrationActivity.this,ErrorOne.class);
                        startActivity(iError);
                    }else if(school.trim().equals("")){
//                    Toast.makeText(this,"The school can not be empty",Toast.LENGTH_LONG).show();
                        Intent iError = new Intent(RegistrationActivity.this,ErrorOne.class);
                        startActivity(iError);
                    }else if(email.trim().equals("") || !kiemtraemail){
//                    Toast.makeText(this,"Incorrect email address",Toast.LENGTH_LONG).show();
                        Intent iError = new Intent(RegistrationActivity.this,ErrorOne.class);
                        startActivity(iError);
                    }else if(password.trim().equals("")){
//                    Toast.makeText(this,"The password can not be empty",Toast.LENGTH_LONG).show();
                        Intent iError = new Intent(RegistrationActivity.this,ErrorOne.class);
                        startActivity(iError);
                    }else if(!isCheck){
//                    Toast.makeText(this,"You have not confirmed the terms",Toast.LENGTH_LONG).show();
                        Intent iError = new Intent(RegistrationActivity.this,ErrorOne.class);
                        startActivity(iError);
                    }else {
                        // code gửi data lên server
                        user = new User();
                        user.setFirst_name(firstName);
                        user.setLast_name(lastName);
                        user.setDate_of_birth(birthDay);
                        user.setId_school(String.valueOf(idSchool));
                        user.setEmail(email);
                        user.setPassword(password);

                        handleRegistration.HandleRegistration(RegistrationActivity.this,user);
                    }
                }else {
                    Toast.makeText(this, "Please connect to internet!", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void RegistrantionSuccsess() {
        // code chuyển trang và put data user qua activity registration_info
        Intent iRegistrationInfo = new Intent(RegistrationActivity.this, RegistrationInfoActivity.class);
        iRegistrationInfo.putExtra("user",user);
        startActivity(iRegistrationInfo);
    }

    @Override
    public void RegistrantionFail() {
        Intent iErrorTwo = new Intent(RegistrationActivity.this, ErrorTwo.class);
        startActivity(iErrorTwo);
    }

    private boolean KiemTraKetNoiMang() {
        ConnectivityManager connectivityManager = (ConnectivityManager) RegistrationActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_SCHOOL){
            if(resultCode == RESULT_OK){
                Intent intent = data;
                tvSchool.setText(intent.getStringExtra("school"));
                idSchool = intent.getIntExtra("id",0);
            }
        }
    }
}


