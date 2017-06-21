package com.project.luulinhson.ar.View.Edit;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.luulinhson.ar.CustomView.DatePickerFragment;
import com.project.luulinhson.ar.Model.Object.User;
import com.project.luulinhson.ar.R;
import com.project.luulinhson.ar.View.Error.ErrorOne;
import com.project.luulinhson.ar.View.Error.ErrorTwo;
import com.project.luulinhson.ar.View.Registration.RegistrationActivity;
import com.project.luulinhson.ar.View.Reminder.ReminderActivity;

public class EditActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edFirstName,edLastName,edPassword;
    TextView tvBirthDay,tvSchool,tvNewSchool;
    Button btnEdit;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edFirstName = (EditText) findViewById(R.id.edFirstName);
        edLastName = (EditText) findViewById(R.id.edLastName);
        edPassword = (EditText) findViewById(R.id.edPassword);
        tvBirthDay = (TextView) findViewById(R.id.tvBirthDay);
        tvSchool = (TextView) findViewById(R.id.tvSchool);
        tvNewSchool = (TextView) findViewById(R.id.tvNewSchool);
        btnEdit = (Button) findViewById(R.id.btnEdit);

        tvBirthDay.setOnClickListener(this);
        tvSchool.setOnClickListener(this);
        tvNewSchool.setOnClickListener(this);
        btnEdit.setOnClickListener(this);

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
                break;
            case R.id.tvNewSchool:
                break;
            case R.id.btnEdit:
                if(KiemTraKetNoiMang()){
                    String firstName = edFirstName.getText().toString();
                    String lastName = edLastName.getText().toString();
                    String birthDay = tvBirthDay.getText().toString();
                    String password = edPassword.getText().toString();
                    String school = tvSchool.getText().toString();
                    String newschool = tvNewSchool.getText().toString();


                    if(firstName.trim().equals("")){
//                    Toast.makeText(this,"The first name can not be empty",Toast.LENGTH_LONG).show();
                        Intent iError = new Intent(EditActivity.this,ErrorOne.class);
                        startActivity(iError);
                    }else if(lastName.trim().equals("")){
//                    Toast.makeText(this,"The last name can not be empty",Toast.LENGTH_LONG).show();
                        Intent iError = new Intent(EditActivity.this,ErrorOne.class);
                        startActivity(iError);
                    }else if(birthDay.trim().equals("")){
//                    Toast.makeText(this,"The birth day can not be empty",Toast.LENGTH_LONG).show();
                        Intent iError = new Intent(EditActivity.this,ErrorOne.class);
                        startActivity(iError);
                    }else if(school.trim().equals("") && newschool.trim().equals("")){
//                    Toast.makeText(this,"The school can not be empty",Toast.LENGTH_LONG).show();
                        Intent iError = new Intent(EditActivity.this,ErrorOne.class);
                        startActivity(iError);
                    }else if(password.trim().equals("")){
//                    Toast.makeText(this,"The password can not be empty",Toast.LENGTH_LONG).show();
                        Intent iError = new Intent(EditActivity.this,ErrorOne.class);
                        startActivity(iError);
                    }else {
                        // code gửi data lên server.Cập nhật lại thông tin User
//                    user = new User();
//                    user.setFirstName(firstName);
//                    user.setLastName(lastName);
//                    user.setBirthDay(birthDay);
//                    user.setSchool(school);
//                    user.setPassword(password);

                    }
                }else {
                    Toast.makeText(EditActivity.this, "Please connect to internet!", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private boolean KiemTraKetNoiMang() {
        ConnectivityManager connectivityManager = (ConnectivityManager) EditActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }

    }
}
