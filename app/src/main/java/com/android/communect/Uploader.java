package com.android.communect;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Uploader {
    private static FirebaseStorage storage = FirebaseStorage.getInstance();
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public StorageReference storageReference = storage.getReference();

    public static boolean Upload(String FilePath) {
        // TODO Make this work lol
        StorageReference storageReference = storage.getReference();
        StorageReference pictureReference = storageReference.child(FilePath);
        return false;

    }
}
