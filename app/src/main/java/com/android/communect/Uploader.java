package com.android.communect;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class Uploader {
    // spaghetti code
    // idk what the hell im doing
    // private static final String filePath = "/image.png";
    private static FirebaseStorage storage = FirebaseStorage.getInstance();
    // public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public StorageReference storageReference = storage.getReference();
    public void UploadDrawable(Drawable drawable) {
        StorageReference ImageRef = storageReference.child("image.jpg");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // OutputStream outputStream = new FileOutputStream(filePath);
        FileOperation.DrawableToBitmap(drawable).compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = ImageRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                MainActivity mainActivity = new MainActivity();
                Toast.makeText(mainActivity.mActivityContext,e.toString(), Toast.LENGTH_LONG).show();
            }}).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                taskSnapshot.getMetadata();
            }
        });
    }}

