package com.android.communect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

class Uploader {
    // spaghetti code
    // idk what the hell im doing
    // private static final String filePath = "/image.png";
    private static FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageReference = storage.getReference();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //  private CollectionReference collectionReference = db.collection("");


    public void UploadDrawable(Drawable drawable, final Context context) {
        long timestamp = System.currentTimeMillis() / 1000L;
        StorageReference ImageRef = storageReference.child(timestamp + ".jpg");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // OutputStream outputStream = new FileOutputStream(filePath);
        FileOperation.DrawableToBitmap(drawable).compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = ImageRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            }}).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                taskSnapshot.getMetadata();
            }
        });
    }

    void ListenForReport()
    {
        DocumentReference documentReference = db.collection("Reports").document();
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e(null, "onEvent: Listen failed", e);
                }
                if (documentSnapshot != null && documentSnapshot.exists()) {
                    Log.e(null, "onEvent: exits" + documentSnapshot.getData());
                }
                else {
                    Log.e(null, "onEvent: null");
                }
            }
        });
    }

    void QueryForImages() {
        StorageReference listRef = storage.getReference().child("image/png");
        listRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                for (StorageReference item : listResult.getItems()) {
                    Log.d(null, "onSuccess: " + item);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(null, "onFailure: an error has occurred");
            }
        });
    }
}
