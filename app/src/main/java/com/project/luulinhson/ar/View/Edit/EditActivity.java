package com.project.luulinhson.ar.View.Edit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.luulinhson.ar.CustomView.DatePickerFragment;
import com.project.luulinhson.ar.Model.Object.User;
import com.project.luulinhson.ar.Presenter.Edit.HandleEditUser;
import com.project.luulinhson.ar.R;
import com.project.luulinhson.ar.View.DialogSchool.ListSchoolActivity;
import com.project.luulinhson.ar.View.Error.ErrorOne;
import com.project.luulinhson.ar.View.Error.ErrorTwo;
import com.project.luulinhson.ar.View.MyPage.MyPageActivity;
import com.project.luulinhson.ar.View.Registration.RegistrationActivity;
import com.project.luulinhson.ar.View.Reminder.ReminderActivity;


public class EditActivity extends AppCompatActivity implements View.OnClickListener,ViewHandleEdit{

    EditText edFirstName,edLastName,edPassword;
    TextView tvBirthDay,tvSchool,tvNewSchool;
    Button btnEdit;
    User user;
    int idSchool,idNewSchool;
    public static final int REQUEST_CODE_SCHOOL_EDIT_ONE = 3333;
    public static final int REQUEST_CODE_SCHOOL_EDIT_TWO = 5555;
    HandleEditUser handleEditUser;

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

        handleEditUser = new HandleEditUser(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

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
                    Intent iListSchool = new Intent(EditActivity.this, ListSchoolActivity.class);
                    startActivityForResult(iListSchool,REQUEST_CODE_SCHOOL_EDIT_ONE);
                }else {
                    Toast.makeText(this, "Please connect to internet!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tvNewSchool:
                if(KiemTraKetNoiMang()){
                    Intent iListSchool = new Intent(EditActivity.this, ListSchoolActivity.class);
                    startActivityForResult(iListSchool,REQUEST_CODE_SCHOOL_EDIT_TWO);
                }else {
                    Toast.makeText(this, "Please connect to internet!", Toast.LENGTH_SHORT).show();
                }
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
                        iError.putExtra("idactivity",3);
                        startActivity(iError);
                    }else if(lastName.trim().equals("")){
//                    Toast.makeText(this,"The last name can not be empty",Toast.LENGTH_LONG).show();
                        Intent iError = new Intent(EditActivity.this,ErrorOne.class);
                        iError.putExtra("idactivity",3);
                        startActivity(iError);
                    }else if(birthDay.trim().equals("")){
//                    Toast.makeText(this,"The birth day can not be empty",Toast.LENGTH_LONG).show();
                        Intent iError = new Intent(EditActivity.this,ErrorOne.class);
                        iError.putExtra("idactivity",3);
                        startActivity(iError);
                    }else if(school.trim().equals("") && newschool.trim().equals("")){
//                    Toast.makeText(this,"The school can not be empty",Toast.LENGTH_LONG).show();
                        Intent iError = new Intent(EditActivity.this,ErrorOne.class);
                        iError.putExtra("idactivity",3);
                        startActivity(iError);
                    }else if(password.trim().equals("")){
//                    Toast.makeText(this,"The password can not be empty",Toast.LENGTH_LONG).show();
                        Intent iError = new Intent(EditActivity.this,ErrorOne.class);
                        iError.putExtra("idactivity",3);
                        startActivity(iError);
                    }else {
                        // code gửi data lên server.Cập nhật lại thông tin User
                        SharedPreferences sharedPreferences = getSharedPreferences("usertoken",Context.MODE_PRIVATE);
                        String token = sharedPreferences.getString("token","");
                        Log.d("TOKEN>>>>>>>>>>>>", "onClick: " + token);
                        user = new User();
                        user.setFirst_name(firstName);
                        user.setLast_name(lastName);
                        user.setDate_of_birth(birthDay);
                        user.setId_school(String.valueOf(idSchool));
                        user.setPassword(password);
                        user.setToken(token);

                        handleEditUser.HandleEditUser(EditActivity.this,user);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_SCHOOL_EDIT_ONE){
            if(resultCode == RESULT_OK){
                Intent intent = data;
                tvSchool.setText(intent.getStringExtra("school"));
                idSchool = intent.getIntExtra("id",0);
            }
        }else if(requestCode == REQUEST_CODE_SCHOOL_EDIT_TWO){
            if(resultCode == RESULT_OK){
                Intent intent = data;
                tvNewSchool.setText(intent.getStringExtra("school"));
                idNewSchool = intent.getIntExtra("id",0);
            }
        }
    }

    @Override
    public void editSuccsess(String token) {
        SharedPreferences sharedPreferences = getSharedPreferences("usertoken",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token",token);
        editor.commit();

        Intent iMyPage = new Intent(EditActivity.this, MyPageActivity.class);
        startActivity(iMyPage);
        finish();
    }

    @Override
    public void editFail() {
        Toast.makeText(this,"Edit fail!!!!",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            Intent iMyPage = new Intent(EditActivity.this, MyPageActivity.class);
            startActivity(iMyPage);
            finish();
        }
        return false;
    }
}
