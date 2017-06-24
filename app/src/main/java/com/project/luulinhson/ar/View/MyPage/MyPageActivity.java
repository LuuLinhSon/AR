package com.project.luulinhson.ar.View.MyPage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.luulinhson.ar.R;
import com.project.luulinhson.ar.View.Edit.EditActivity;
import com.project.luulinhson.ar.View.Popup.PopupCameraActivity;

import static java.security.AccessController.getContext;

public class MyPageActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imAvatar,imCamera,imEdit;
    TextView tvName,tvBirthDay,tvSchool;
    public static final int REQUES_CODE_AVATAR = 8888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        imAvatar = (ImageView) findViewById(R.id.imAvatar);
        imCamera = (ImageView) findViewById(R.id.imCamera);
        imEdit = (ImageView) findViewById(R.id.imEdit);
        tvName = (TextView) findViewById(R.id.tvName);
        tvBirthDay = (TextView) findViewById(R.id.tvBirthDay);
        tvSchool = (TextView) findViewById(R.id.tvSchool);

        imAvatar.setOnClickListener(this);
        imCamera.setOnClickListener(this);
        imEdit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.imAvatar:
                Intent iPopupCamera = new Intent(MyPageActivity.this, PopupCameraActivity.class);
                startActivityForResult(iPopupCamera, REQUES_CODE_AVATAR);
                break;
            case R.id.imCamera:
                if(KiemTraKetNoiMang()){
                    
                }else {
                    Toast.makeText(this, "Please connect to internet!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imEdit:
                Intent iEdit = new Intent(MyPageActivity.this, EditActivity.class);
                startActivity(iEdit);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUES_CODE_AVATAR){
            if (resultCode == RESULT_OK){
                Intent result = data;
                Bitmap bm = (Bitmap) result.getExtras().get("hinhanh");
                imAvatar.setImageBitmap(bm);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            finish();
        }
        return false;
    }

    private boolean KiemTraKetNoiMang() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MyPageActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }

    }
}