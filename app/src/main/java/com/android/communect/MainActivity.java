package com.android.communect;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    public String FirstName;
    public String LastName;
    public Context mActivityContext;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirstNameEditText = findViewById(R.id.first_name_edittext_launcher);
        mLastNameEditText = findViewById(R.id.last_name_edittext_launcher);
        mActivityContext = getApplicationContext();
        b1 = (Button)findViewById(R.id.view_your_reports_button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotification();
            }
        });
        addNotification();
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
        Intent intent = new Intent(this, activity_view_all_reports.class);
        startActivity(intent);
    }
    public void OpenNotification(View view) { /*
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);


// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

// notificationID allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build()); */
        }
    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("New report in your area")
                        .setContentText("Litter report at 6339 Arlington St.")
                        .setPriority(Notification.PRIORITY_HIGH);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
