package com.android.communect;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_view_all_reports extends AppCompatActivity {
    private static ImageView imageViewThumbnail;
    TextView mTextViewReportDescription;
    TextView mTextViewReportUnderDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_reports);
        mTextViewReportDescription = findViewById(R.id.report_description1);
        mTextViewReportUnderDescription = findViewById(R.id.report_description_under1);
        imageViewThumbnail = findViewById(R.id.report_thumbnail);
    }



    private void GetImages(){
        Uploader uploader = new Uploader();
        uploader.QueryForImages();
    }

    @SuppressLint("SetTextI18n")
    public void refreshForReports(View view) {
        GetImages();
        MainActivity mainActivity = new MainActivity();
        MakeReport makeReport = new MakeReport();
        mTextViewReportDescription.setText("A fallen tree on Tyne street has fell down due to an earthquake. If anyone wants to help please come to 49th of Tyne. We will be taking the tree to Burnaby eco centre.");
        mTextViewReportUnderDescription.setText("Matthew Lin" + ","  + " " + "Location: " + " " + "49.226568, -123.044303"); // mainActivity.FirstName + mainActivity.LastName + makeReport.Latitude + makeReport.Longitude);
        imageViewThumbnail.setImageResource(R.drawable.image);
    }
}
