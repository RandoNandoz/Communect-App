package com.android.communect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    public String FirstName;
    public String LastName;
    public Context mActivityContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirstNameEditText = findViewById(R.id.first_name_edittext_launcher);
        mLastNameEditText = findViewById(R.id.last_name_edittext_launcher);
        mActivityContext = getApplicationContext();
    }

    public void makeReport(View view) {
        Log.d(null, "makeReport: should be transitioning to MakeReport(activity)");
        String firstName = mFirstNameEditText.getText().toString();
        String lastName = mLastNameEditText.getText().toString();
        FirstName = firstName;
        LastName = lastName;
        Intent intent = new Intent(this, MakeReport.class);
        // intent.putExtra(firstName, lastName);
        startActivity(intent);
    }

    public void seeReports(View view) {
    }
    
}
