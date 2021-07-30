package com.example.sleymantkr.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button show,stop;
    NotificationManager nm;
    boolean isActive = false;
    int notifID = 33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show = (Button)findViewById(R.id.showButton);
        stop = (Button)findViewById(R.id.stopButton);
    }
    public void showNotification(View view)
    {
        NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(this);
        noBuilder.setContentTitle("Message");
        noBuilder.setContentText("New Message");
        noBuilder.setTicker("Alert New Message");
        noBuilder.setSmallIcon(R.mipmap.ic_launcher);

        Intent moreInfoIntent = new Intent(this,MoreInfoNotification.class);
        TaskStackBuilder taskStackBuilder =  TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(MoreInfoNotification.class);
        taskStackBuilder.addNextIntent(moreInfoIntent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        noBuilder.setContentIntent(pendingIntent);

        nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(notifID,noBuilder.build());
        isActive = true;
    }

    public void stopNotification(View view)
    {
        if(isActive)
            nm.cancel(notifID);
    }
}