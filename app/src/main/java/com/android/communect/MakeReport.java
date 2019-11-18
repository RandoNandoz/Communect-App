package com.android.communect;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MakeReport extends AppCompatActivity {
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final int GET_FROM_GALLERY = 3;
    public static Drawable image;
    private ImageView mThumbnailImageView;
    private TextView mReportBody;
    private TextView mReportLocation;
    public String Latitude;
    public String Longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_report);
        mThumbnailImageView = findViewById(R.id.uploaded_report_image);
        mReportBody = findViewById(R.id.make_report_description_toUpload);
        mReportLocation = findViewById(R.id.location_field);
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
        if (requestCode == GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
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

    @SuppressLint("SetTextI18n")
    public void getGPSLocation(View view) {
        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        String bestProvider = "";
        try {
            assert locationManager != null;
            bestProvider = locationManager.getBestProvider(criteria, true);
            Log.d(null, "getGPSLocation: " + bestProvider);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        try {
            Location location = locationManager.getLastKnownLocation(bestProvider);
            if (location != null) {
                // String locationString = location.toString();
                // Log.d(null, "getGPSLocation:" + locationString);
                double doubleLat = location.getLatitude();
                double doubleLong = location.getLongitude();
                Latitude = String.valueOf(doubleLat);
                Longitude = String.valueOf(doubleLong);
                // Its fine to concatenate in a setText, as in this case these values will always be
                // numbers, so translation is impossible.
                mReportLocation.setText(Latitude + "," + " " + Longitude);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    public void sendAllToServer(View view) {
        Map<String, Object> Report = new HashMap<>();
        MainActivity mainActivity = new MainActivity();
        Report.put("Report body", mReportBody.getText().toString());
        Report.put("Report Location", mReportLocation.getText().toString());
        Report.put("Report User", mainActivity.FirstName + " " + mainActivity.LastName);
        db.collection("Reports").add(Report).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(null, "onSuccess: Document add success" + documentReference);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(null, "onFailure: error", e);
            }
        });
        Uploader uploader = new Uploader();
        uploader.UploadDrawable(image, getApplicationContext());
        finish();
        uploader.ListenForReport();
    }
}
