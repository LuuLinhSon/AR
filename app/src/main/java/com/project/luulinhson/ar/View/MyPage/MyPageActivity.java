package com.project.luulinhson.ar.View.MyPage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.project.luulinhson.ar.Model.Object.User;
import com.project.luulinhson.ar.Presenter.MyPage.HandleGetDataMyPage;
import com.project.luulinhson.ar.R;
import com.project.luulinhson.ar.View.Edit.EditActivity;
import com.project.luulinhson.ar.View.Popup.PopupCameraActivity;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.MessageDigest;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static java.security.AccessController.getContext;

public class MyPageActivity extends AppCompatActivity implements View.OnClickListener,ViewHandleGetDataMyPage{

    ImageView imAvatar,imCamera,imEdit;
    TextView tvName,tvBirthDay,tvSchool;
    public static final int REQUES_CODE_AVATAR = 8888;
    HandleGetDataMyPage handleGetDataMyPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

//        imAvatar = (ImageView) findViewById(R.id.imAvatar);
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

    @Override
    public void GetDataSuccess(User user) {

        tvName.setText(user.getFirst_name() + " " + user.getLast_name());
        tvBirthDay.setText(user.getDate_of_birth());
        tvSchool.setText(user.getId_school());
        Uri uri = Uri.parse(user.getProfile_picture());
        Glide.with(MyPageActivity.this).load(uri).into(imAvatar);

    }

    @Override
    public void GetDataFail() {
        Toast.makeText(this,"ERROR!",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("usertoken",Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token","");
        Log.d("TOKEN...........", "onResume: " + token);
        if(!token.equals("")){
            handleGetDataMyPage = new HandleGetDataMyPage(this);
            handleGetDataMyPage.HandleGetDataMyPage(this,token);
        }
    }
}
