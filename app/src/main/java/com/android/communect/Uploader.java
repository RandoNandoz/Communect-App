package com.android.communect;
import android.widget.ImageView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Uploader {
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static FirebaseStorage storage = FirebaseStorage.getInstance();
    public StorageReference storageReference = storage.getReference();

    public static boolean Upload(String FilePath) {
        StorageReference storageReference = storage.getReference();
        StorageReference pictureReference = storageReference.child(FilePath);
    }
}
