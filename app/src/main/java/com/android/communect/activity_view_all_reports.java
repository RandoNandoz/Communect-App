package com.android.communect;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_view_all_reports extends AppCompatActivity {
    ImageView imageViewThumbnail;
    ImageView imageViewThumbnail1;
    ImageView imageViewThumbnail2;
    ImageView ImageViewThumbnail3;
    TextView mTextViewReportDescription;
    TextView mTextViewReportUnderDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_reports);
        mTextViewReportDescription = findViewById(R.id.report_description1);
        mTextViewReportUnderDescription = findViewById(R.id.report_description_under1);
    }



    private void GetImages(){
        Uploader uploader = new Uploader();
        uploader.QueryForImages();
    }

    public void refreshForReports(View view) {
        GetImages();
        MainActivity mainActivity = new MainActivity();
        mTextViewReportUnderDescription.setText( mainActivity.FirstName + mainActivity.LastName + mainActivity.locatio);
    }
}
