package com.android.communect;

        import android.graphics.drawable.Drawable;

        import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Uploader {
    private static FirebaseStorage storage = FirebaseStorage.getInstance();
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public StorageReference storageReference = storage.getReference();

    public static void SubmitReport(Drawable image) {
        image
    }
}
