package com.example.mtmi.captureimageexample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE=1;
    Button take_picture_btn;
    ImageView picturePreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        take_picture_btn= (Button) findViewById(R.id.take_picture_btn);

        take_picture_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                take_picture_btn.setVisibility(View.GONE);

                Intent takePictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(takePictureIntent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        picturePreview= (ImageView) findViewById(R.id.picturePreview);

        if(requestCode==REQUEST_IMAGE_CAPTURE&&resultCode==RESULT_OK){
            Bundle extras=data.getExtras();
            Bitmap imageBitmap= (Bitmap) extras.get("data");
            picturePreview.setImageBitmap(imageBitmap);
            picturePreview.setVisibility(View.GONE);
            picturePreview.setVisibility(View.VISIBLE);
        }
    }
}
