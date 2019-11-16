package com.android.communect;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class Uploader {
    private static FirebaseStorage storage = FirebaseStorage.getInstance();
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public StorageReference storageReference = storage.getReference();
    public void Upload(File file) {

    }
}
