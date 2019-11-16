package com.android.communect;

import android.app.Activity;
import android.content.Intent;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MakeReport extends AppCompatActivity {
    private static final int GET_FROM_GALLERY = 3;
    public static Drawable image;
    private ImageView mThumbnailImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_report);
        mThumbnailImageView = findViewById(R.id.uploaded_report_image);
    }

    public void openGalleryToUpload(View view) {
        startActivityForResult(
                new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI),
                GET_FROM_GALLERY
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if(requestCode == GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            image = null;
            try {
                ImageDecoder.Source src = ImageDecoder.createSource(this.getContentResolver(), selectedImage);
                image = ImageDecoder.decodeDrawable(src);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            mThumbnailImageView.setImageDrawable(image);
        }
    }

    public void getGPSLocation(View view) {

    }
}
