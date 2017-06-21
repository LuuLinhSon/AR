package com.project.luulinhson.ar.View.Popup;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.project.luulinhson.ar.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PopupCameraActivity extends AppCompatActivity {

    ListView lvCamera;
    List<String> lisCamera;
    int position;
    private static final int RESQUEST_TAKE_PHOTO = 123;
    private static final int RESQUEST_CHOOSE_PHOTO = 321;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_camera);

        lvCamera = (ListView) findViewById(R.id.lvPopup);
        setTitle("Select");

        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);

        lisCamera = new ArrayList<>();
        lisCamera.add("Camera");
        lisCamera.add("Gallery");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lisCamera);
        lvCamera.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        lvCamera.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    takePicture();
                }else {
                    choosePicture();
                }

            }
        });
    }

    private void takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, RESQUEST_TAKE_PHOTO);
    }
    private void choosePicture(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, RESQUEST_CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == RESQUEST_CHOOSE_PHOTO){
                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    Bitmap bm = Bitmap.createScaledBitmap(bitmap,500,600,false); // Resize lại ảnh cho không bị lỗi OutOfBound
                    Intent iCamera = new Intent();
                    iCamera.putExtra("hinhanh",bm);
                    iCamera.putExtra("position",position);
                    setResult(Activity.RESULT_OK,iCamera);
                    finish();

                }catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }else if (requestCode == RESQUEST_TAKE_PHOTO){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//                Bitmap bm1 = Bitmap.createScaledBitmap(bitmap,450,800,false);// Resize lại ảnh cho không bị lỗi OutOfBound
                Intent iCamera = new Intent();
                iCamera.putExtra("hinhanh",bitmap);
                iCamera.putExtra("position",position);
                setResult(Activity.RESULT_OK,iCamera);
                finish();
            }
        }
    }
}
