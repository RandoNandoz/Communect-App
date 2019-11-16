package com.android.communect;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class activity_view_all_reports extends AppCompatActivity {
    ImageView imageViewThumbnail;
    ImageView imageViewThumbnail1;
    ImageView imageViewThumbnail2;
    ImageView ImageViewThumbnail3;
    private static StorageReference storageReference;
    private static FirebaseStorage storage;
    private static StorageReference pathReference;
    private static StorageReference gsReference;
    private static StorageReference httpsReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_reports);
        imageViewThumbnail = findViewById(R.id.report_thumbnail);
        imageViewThumbnail1 = findViewById(R.id.report_thumbnail1);
        imageViewThumbnail2 = findViewById(R.id.report_thumbnail2);
        ImageViewThumbnail3 = findViewById(R.id.report_thumbnail3);
       /*
       storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        pathReference = storageReference.child("images/*.png");
        gsReference = storage.getReferenceFromUrl("gs://bucket/images/stars.jpg");
        httpsReference = storage.getReferenceFromUrl("gs://communect.appspot.com\n");

        */
    }

    private void GetImages() {
       //Context.Storage imageReference = storageReference.child("image");
    }
}
